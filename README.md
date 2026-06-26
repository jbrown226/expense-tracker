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
 - Launches the JavaFX application
 - Displays the projects name
 - Store expenses objects with name and cost. 
 - Calculate expense totals based on frequency of occurrence
 - Displays Monthly Total at the bottom of the expense list
 - Detailed view of an expense
 - Delete an expense from the list
 - Expenses are now saved via JSON storage uploaded to AWS

## Planned Features:
 - Time and date displayed at the top of the application
 - Add email and/or phone contact to created expenses (optional fields)
 - Work on CSS styling
 
## License:

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author: 

Justin Brown

https://github.com/jbrown226

