# Voting App
This is a web application built with Spring Boot that allows users to vote for political parties and view the vote count for each party. The app uses Spring Boot Data JPA to interact with a MySQL database, Thymeleaf for templating, Spring Boot Security for authentication and authorization, and Spring Boot Starter Web for HTTP requests and responses.

# Installation
To run this app, you'll need to have the following installed:

* Java 8 or later
* MySQL
* Maven

1. Clone the repository to your local machine.
2. Import the project into your IDE.
3. Create a MySQL database named "voting_app".
4. Open the file application.properties in the src/main/resources folder and update the database username and password if necessary.
5. Run the application using your IDE or with the command mvn spring-boot:run.
6. Access the app in your browser at http://localhost:8080/.

# Usage

## User Roles
This app has two user roles:

**Admin**: Can view the vote count for each party and the list of registered users. Can access the admin-panel page at http://localhost:8080/admin-panel.  

**User**: Can vote once, edit their profile, and logout.

## Admin Panel
To access the admin-panel page, you'll need to log in as a user with the "admin" role. If you try to access the admin-panel page without logging in, or if you log in as a user without the "admin" role, you'll be redirected to a 403 Access Denied page.

## Voting
To vote, you'll need to log in as a user with the "user" role. Each user can only vote once. Once a user has voted, they can no longer vote.

## Editing Profile
To edit your profile, click on the dropdown button on the top right corner of the screen. You'll be redirected to a page where you can update your name and email address.

## Logging Out
To log out, click on the "Logout" button on the top right corner of the screen. You'll be redirected to the login page.

## Screenshots
Here are some screenshots of the app:


## Register Page
   ![alt text](https://i.imgur.com/FI9wvYh.png)-
## Register Success
   ![alt text](https://i.imgur.com/Ui6jFSK.png)-
## Register Fail
   ![alt text](https://i.imgur.com/E3qg2oj.png)-
## Login Page 
   ![alt text](https://i.imgur.com/XXvJWY0.png)-
## Login Fail
   ![alt text](https://i.imgur.com/zYO5KkQ.png)-
## Home Page
   ![alt text](https://i.imgur.com/wF5Eg0q.png)-
## Admin Panel
   ![alt text](https://i.imgur.com/E5U0ex2.png)-
## Edit Profile Modal
   ![alt text](https://i.imgur.com/5fwLbvd.png)-
## Vote Success
   ![alt text](https://i.imgur.com/pJCzp6m.png)   
## Vote Fail
   ![alt text](https://i.imgur.com/CJ9oZZl.png)   
## Profile Update Success
   ![alt text](https://i.imgur.com/azNLFxf.png) 
## Profile Update Fail
   ![alt text](https://i.imgur.com/ykDGXRA.png) 
    
    
# Contributing
If you'd like to contribute to this project, please fork the repository and create a pull request with your changes.
