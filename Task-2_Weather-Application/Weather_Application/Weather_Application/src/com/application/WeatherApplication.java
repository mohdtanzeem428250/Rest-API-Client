package com.application; // Declares the package for this class

// Imports for working with JSON data
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import java.io.IOException; // Handles I/O exceptions
import java.net.HttpURLConnection; // Used to establish HTTP connections
import java.net.URL; // Represents a URL
import java.time.LocalDateTime; // For getting current date and time
import java.time.format.DateTimeFormatter; // For formatting date and time
import java.util.Scanner; // Used to read input streams

public class WeatherApplication // Class to fetch and process weather data
{
    // Method to get weather data based on location name
    public static JSONObject getWeatherData(String locationName)
    {
        // Get geographical coordinates (latitude, longitude) for the location
        JSONArray locationData = getLocationData(locationName);
        JSONObject location = (JSONObject) locationData.get(0); // Get the first result from the list

        // Extract latitude and longitude from location JSON object
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        // Construct the weather API URL using latitude and longitude
        String url = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + latitude + "&longitude=" + longitude +
                "&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=America%2FLos_Angeles";

        try
        {
            // Connect to the weather API
            HttpURLConnection connection = fetchApiResponse(url);

            // Check if connection was successful
            if (connection.getResponseCode() != 200)
            {
                System.out.println("Error: Could Not Connect To API");
                return null;
            }

            // Read API response
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext())
            {
                resultJson.append(scanner.nextLine()); // Append each line to the result
            }
            scanner.close();
            connection.disconnect(); // Disconnect the connection

            // Parse the response JSON
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObject = (JSONObject) parser.parse(String.valueOf(resultJson));

            // Extract hourly weather data
            JSONObject hourly = (JSONObject) resultJsonObject.get("hourly");
            JSONArray time = (JSONArray) hourly.get("time");

            // Find the index of the current hour in the time array
            int index = findIndexOfCurrentTime(time);

            // Extract specific weather details using the index
            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureData.get(index);

            JSONArray weathercode = (JSONArray) hourly.get("weathercode");
            String weathercondition = convertWeatherCode((long) weathercode.get(index));

            JSONArray relativeHumidity = (JSONArray) hourly.get("relativehumidity_2m");
            long humidity = (long) relativeHumidity.get(index);

            JSONArray windspeedData = (JSONArray) hourly.get("windspeed_10m");
            double windspeed = (double) windspeedData.get(index);

            // Create a new JSON object to store the final weather data
            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature", temperature);
            weatherData.put("weather_condition", weathercondition);
            weatherData.put("humidity", humidity);
            weatherData.put("windspeed", windspeed);

            // Return the collected weather data
            return weatherData;
        }
        catch (Exception exception)
        {
            // Print any errors that occur
            exception.printStackTrace();
        }
        return null;
    }

    // Method to get location data (latitude & longitude) from location name
    public static JSONArray getLocationData(String locationName)
    {
        // Replace spaces in the location name with '+' for the URL format
        locationName = locationName.replaceAll(" ", "+");

        // URL for the Open Meteo Geocoding API
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";

        try
        {
            // Connect to the API
            HttpURLConnection connection = fetchApiResponse(url);

            // If connection failed
            if (connection.getResponseCode() != 200)
            {
                System.out.println("Error: Could Not Connect To API ");
                return null;
            }
            else
            {
                // Read API response
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNext())
                {
                    resultJson.append(scanner.nextLine());
                }
                scanner.close();
                connection.disconnect();

                // Parse the JSON response
                JSONParser parser = new JSONParser();
                JSONObject resultJsonObject = (JSONObject) parser.parse(String.valueOf(resultJson));

                // Get the "results" array containing location data
                JSONArray locationData = (JSONArray) resultJsonObject.get("results");
                return locationData;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    // Helper method to send an HTTP GET request to the given URL
    private static HttpURLConnection fetchApiResponse(String urlString)
    {
        try
        {
            URL url = new URL(urlString); // Create URL object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Open HTTP connection
            connection.setRequestMethod("GET"); // Set request method to GET
            connection.connect(); // Establish the connection
            return connection;
        }
        catch (IOException exception)
        {
            exception.printStackTrace(); // Handle any errors
        }
        return null;
    }

    // Finds the index of the current hour in the "time" array from the API
    private static int findIndexOfCurrentTime(JSONArray timeList)
    {
        String currentTime = getCurrentTime(); // Get current time in required format
        for (int i = 0; i < timeList.size(); i++)
        {
            String time = (String) timeList.get(i);
            if (time.equalsIgnoreCase(currentTime)) // Compare with each time in the array
            {
                return i; // Return index if match found
            }
        }
        return 0; // Default to index 0 if current time not found
    }

    // Returns the current time formatted as required by the API
    public static String getCurrentTime()
    {
        LocalDateTime currentDateTime = LocalDateTime.now(); // Get current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'"); // Format to match API time
        String formattedDateTime = currentDateTime.format(formatter); // Apply formatting
        return formattedDateTime;
    }

    // Converts numeric weather code into readable weather condition
    private static String convertWeatherCode(long weathercode)
    {
        String weatherCondition = ""; // Variable to store the weather condition as text
        if (weathercode == 0L)
        {
            weatherCondition = "Clear";
        }
        else if (weathercode >= 0L && weathercode <= 3L)
        {
            weatherCondition = "Cloudy";
        }
        else if ((weathercode >= 51L && weathercode <= 67L) || weathercode >= 80L && weathercode <= 99L)
        {
            weatherCondition = "Rain";
        }
        else if (weathercode >= 71L && weathercode <= 77L)
        {
            weatherCondition = "Snow";
        }
        return weatherCondition;	
    }
}
