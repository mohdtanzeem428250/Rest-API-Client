# Rest-API-Client

COMPANY : CODTECH IT SOLUTIONS PVT. LTD.

NAME : MOHD TANZEEM

INTERN ID : CT08DA696

DOMAIN : JAVA PROGRAMMING

DURATION : 8 WEEKS

MENTOR : NEELA SANTHOSH KUMAR

# Task Description: Rest API Client

# Weather Application Using Java Swing and REST API 

This project is a desktop-based Weather Application developed using Java Swing for the graphical user interface (GUI) and REST API to fetch real-time weather data. This application was built as part of an internship task to demonstrate skills in Java GUI development and external API integration.

# Introduction

The Weather App is a Java-based application that provides users with real-time weather information for a specified location. It fetches weather data from an external API and displays it in a graphical user interface (GUI). Users can enter a location, and the app retrieves and presents weather details, including temperature, weather condition, humidity, and wind speed. This documentation outlines the project's architecture, technologies used, and the functionality of each class within the application.

# Technologies Used
The Weather App utilizes the following technologies and libraries:

- Java 18
- JSON Simple - Used to parse and read through JSON data
- HTTPURLConnection: Java's built-in library for making HTTP requests to fetch data from external APIs.

## Output India Weather

![Image](https://github.com/user-attachments/assets/d014347c-227d-4969-a417-17222d85e335)

## Output France Weather

![Image](https://github.com/user-attachments/assets/865512d8-ae30-456d-85b5-e38324c5f9ec)

## Output London Weather

# Class Summaries

# 3.1. AppLauncher 
**Description:** The AppLauncher class serves as the entry point for the Weather App. It initializes the GUI and displays the main application window.

# 3.2. WeatherAppGui
**Description:** The WeatherAppGui class represents the graphical user interface (GUI) of the Weather App. It is responsible for displaying weather information for a specified location.

Summary: This class handles the layout and display of GUI components, including text fields, labels, buttons, and images. It also implements the user interface for entering a location and updating the weather information based on user input.

# 3.3. WeatherApp
**Description:** The WeatherApp class contains the backend logic for fetching weather data from an external API. It retrieves geographic coordinates for a location, fetches weather data for that location, and provides methods to convert weather codes.

# Summary: 

This class encapsulates the core functionality of the Weather App. It includes methods to fetch weather data and location coordinates, convert weather codes into readable weather conditions, and manage API requests. This class acts as the bridge between the GUI and the external weather data source, ensuring that weather information is retrieved and displayed accurately.
