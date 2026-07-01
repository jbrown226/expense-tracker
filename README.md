# Expense Tracker

This is the start of Expense Tracker. Track reccuring expenses to gauge income requirements and manage personal finances.

Currently, the application accepts user data, creating a list of expenses calculated as a monthly total.
Users can now click an entry in the list for details about an entry. 

## Prerequisites:
 - **jdk 17**
 - **apache maven**

 ```bash
java -version
mvn -version
```

## To run:
 1. Open a new terminal
 2. navigate to the project's root directory 
 3. run: 
    
    `mvn javafx:run`

## Project features:
 - Add and display recurring expenses by name and monthly cost
 - Automatically convert weekly, biweekly, and monthly expenses into estimated monthly amounts
 - Calculate and display the total monthly cost of all listed expenses
 - Store a contact phone number for each expense
 - View detailed information for any selected expense
 - Save expense data in JSON format
 - Store and retrieve expense data using an Amazon S3 bucket

## Planned Features:
 - Time and date displayed at the top of the application
 - Work on CSS styling
 
## License:

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author: 

Justin Brown

https://github.com/jbrown226

