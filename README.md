# Green Supermarket

Green Supermarket is a Java-based web application for managing an online supermarket. The application provides functionalities for product management, checkout process, email notifications, and employee management.

## Project Structure

The project follows a typical Maven project structure:

- `src/main/java`: Contains the Java source files.
- `src/main/resources`: Contains resources like configuration files and SQL scripts.
- `src/main/webapp`: Contains JSP files for the web interface.

## Setup

1. Update the `database.properties`, `email.properties`, and `paypal.properties` files in `src/main/resources/config/` with your configuration.
2. Run the SQL script in `src/main/resources/sql/SQL.sql` to set up the database.
3. Build the project using Maven: `mvn clean install`.