package com.expensetracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class S3Repository implements AutoCloseable {
    
    private static final String BUCKET_NAME = "justin-expense-tracker-files";

    private static final String OBJECT_KEY = "expense-tracker/expenses.json";

    private final S3Client s3Client;
    private final ObjectMapper objectMapper;

    /** 
     * builds the S3 client
     */
    public S3Repository() {
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_2)
                .build();

        this.objectMapper = new ObjectMapper();
    }

    public List<Expense> findAll() {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(OBJECT_KEY)
                .build();

        try {
            ResponseBytes<GetObjectResponse> response =
                    s3Client.getObjectAsBytes(request);

            String json = response.asUtf8String();

            if (json.isBlank()) {
                return new ArrayList<>();
            }

            return objectMapper.readValue(
                    json,
                    new TypeReference<List<Expense>>() {
                    }
            );

        } catch (NoSuchKeyException e) {
            // The file has not been created in S3 yet.
            return new ArrayList<>();

        } catch (S3Exception e) {
            /*
             * Some S3 configurations return a general 404
             * instead of NoSuchKeyException.
             */
            if (e.statusCode() == 404) {
                return new ArrayList<>();
            }

            throw new IllegalStateException(
                    "Could not download expenses from S3: "
                            + e.awsErrorDetails().errorMessage(),
                    e
            );

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not convert the stored JSON into expenses",
                    e
            );
        }
    }

    public void saveAll(List<Expense> expenses) {
        try {
            String json = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(expenses);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(OBJECT_KEY)
                    .contentType("application/json")
                    .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromString(json)
            );

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not convert expenses to JSON",
                    e
            );

        } catch (S3Exception e) {
            throw new IllegalStateException(
                    "Could not upload expenses to S3: "
                            + e.awsErrorDetails().errorMessage(),
                    e
            );
        }
    }

    @Override
    public void close() {
        s3Client.close();
    }
}
