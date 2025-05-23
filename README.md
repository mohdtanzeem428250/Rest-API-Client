# Rest-API-Client

COMPANY : CODTECH IT SOLUTIONS PVT. LTD.

NAME : MOHD TANZEEM

INTERN ID : CT08DA696

DOMAIN : JAVA PROGRAMMING

DURATION : 8 WEEKS

MENTOR : NEELA SANTHOSH KUMAR

# Task Description: Rest API Client

Weather Application Using Java Swing and REST API 

This project is a desktop-based Weather Application developed using Java Swing for the graphical user interface (GUI) and REST API to fetch real-time weather data. This application was built as part of an internship task to demonstrate skills in Java GUI development and external API integration.

Objective
The main objective of this application is to provide current weather information for any city entered by the user. It aims to showcase how to build interactive desktop applications using Java Swing and how to consume RESTful web services in Java using HTTP clients.

Technologies Used
Java SE

Java Swing (for GUI)

HttpURLConnection / HttpClient (for REST API calls)

JSON Parsing Library (like org.json or Gson)

OpenWeatherMap API (or any public weather API)

Application Workflow
User Interface (Swing): The application starts with a simple GUI created using Java Swing components such as JFrame, JLabel, JTextField, and JButton. The user enters a city name into a text field and clicks a button to get the weather.

API Request: Upon clicking the button, the application constructs a URL for the weather API, including the city name and API key. This request is made using Java’s HttpURLConnection or HttpClient.

Fetching Data: The response from the weather API is received in JSON format. This includes details such as temperature, humidity, weather conditions, and wind speed.

Parsing JSON: The application parses the JSON response using a JSON parsing library like org.json or Google’s Gson to extract relevant weather data.

Displaying Results: The extracted weather data is then displayed on the same Swing GUI using labels or text areas. The user can see the temperature, condition (e.g., Sunny, Rainy), humidity, and more.

Features
City-based weather search

Real-time weather data using a public REST API

Easy-to-use and clean graphical user interface

Error messages for invalid inputs or network failures
