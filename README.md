# Java Chatting Application

## Overview

This is a simple console-based chatting application implemented in Java. The program allows users to connect to a server, log in, and engage in real-time chat with other users. The application uses JDBC and MySQL for creating a login database.


## Features

- User-friendly console interface.
- Client-server architecture for multi-user communication.
- User authentication using JDBC and MySQL for secure access.
- Real-time messaging between connected clients.
- Graceful handling of client disconnections.
- Dynamic updating of the user list.


## Prerequisites

- Java Development Kit (JDK)
- MySQL Database
- Basic understanding of Java socket programming, JDBC, and MySQL


## Usage


### Setup MySQL Database

1. Create a MySQL database named `chatting_application`.
2. Create a table named `login_info` table with columns `Name varchar(50), UserID varchar(50), Password varchar(50)`.


### Compile and Run the Server

1. javac ChatServer.java
2. java ChatServer


## JDBC Connector

Add jar file of mysql connector to your project libraries to establish a connection with the database.
Download the jar file from offiicial website of MySql or directly during installation.

## Compile and Run the Client

javac ChatClient.java
java ChatClient


## Follow On-Screen Instructions:

Start the server and specify the port.
Run multiple instances of the client, providing a username and connecting to the server.
Engage in real-time chat with other connected users.


## Error Handling

The application handles common errors such as invalid input, connection issues, etc.
Provides appropriate messages for errors and guides users to enter valid information.


## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


## Limitations

The application assumes a local MySQL setup for user authentication.
The chat application focuses on simplicity and real-time messaging without advanced features such as file sharing or multimedia.
Feel free to contribute and enhance the functionality of the chatting application!


## Project Credits:

Rafan R. Nadiadwala: https://www.linkedin.com/in/rafan-nadiadwala-062b70262
