package com.application; // Defines the package name

// Import necessary libraries for GUI, image handling, and JSON
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.JSONObject; // For working with JSON objects

public class WeatherApplicationGUI extends JFrame // GUI class that extends JFrame
{
	private JSONObject weatherData; // Stores weather data for the selected location

	// Constructor for GUI
	public WeatherApplicationGUI()
	{
		super("Weather Application"); // Set window title
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Close the application when window is closed
		setSize(450, 650); // Set fixed size of the window
		setLocationRelativeTo(null); // Center the window on the screen
		setLayout(null); // Use absolute positioning for components
		setResizable(false); // Prevent resizing the window
		setUndecorated(true); // Remove default window decorations (title bar, borders)

		addGUIComponents(); // Add all GUI components to the frame
	}

	// Method to add GUI components
	private void addGUIComponents()
	{
		// Input field for location name
		JTextField searchTextField = new JTextField("Search In Weather...");
		searchTextField.setBounds(15, 15, 351, 45); // Position and size
		searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24)); // Font settings
		add(searchTextField); // Add to GUI

		// Weather condition image
		JLabel weatherConditionImage = new JLabel(loadImage("src/images/cloudy.png"));
		weatherConditionImage.setBounds(0, 125, 450, 217);
		add(weatherConditionImage);

		// Temperature label
		JLabel temperatureText = new JLabel("10 C");
		temperatureText.setBounds(0, 350, 450, 54);
		temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
		temperatureText.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		add(temperatureText);

		// Weather condition description (e.g., Cloudy)
		JLabel weatherConditionDesc = new JLabel("Cloudy");
		weatherConditionDesc.setBounds(0, 405, 450, 36);
		weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
		weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
		add(weatherConditionDesc);

		// Humidity icon
		JLabel humidityImage = new JLabel(loadImage("src/images/humidity.png"));
		humidityImage.setBounds(15, 500, 74, 66);
		add(humidityImage);

		// Humidity value
		JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
		humidityText.setBounds(90, 500, 85, 55);
		humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
		add(humidityText);

		// Windspeed icon
		JLabel windspeedImage = new JLabel(loadImage("src/images/windspeed.png"));
		windspeedImage.setBounds(220, 500, 74, 66);
		add(windspeedImage);

		// Windspeed value
		JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 5KM</html>");
		windspeedText.setBounds(310, 500, 85, 55);
		windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
		add(windspeedText);

		// Search button with image icon
		JButton searchButton = new JButton(loadImage("src/images/search.png"));
		searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set hand cursor on hover
		searchButton.setBounds(375, 13, 47, 45); // Button position and size

		// Event listener for search button
		searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Get user input from text field
				String userInput = searchTextField.getText();

				// If input is empty, do nothing
				if(userInput.replace("\\s", "").length() <= 0)
				{
					return;
				}

				// Fetch weather data from API
				weatherData = WeatherApplication.getWeatherData(userInput);

				// Get weather condition (Clear, Rain, etc.)
				String weatherCondition = (String) weatherData.get("weather_condition");

				// Change image based on weather condition
				switch(weatherCondition)
				{
					case "Clear":
						weatherConditionImage.setIcon(loadImage("src/images/clear.png"));
						break;
					case "Cloudy":
						weatherConditionImage.setIcon(loadImage("src/images/cloudy.png"));
						break;
					case "Rain":
						weatherConditionImage.setIcon(loadImage("src/images/rain.png"));
						break;
					case "Snow":
						weatherConditionImage.setIcon(loadImage("src/images/snow.png"));
						break;
				}

				// Update temperature
				double temperature = (double) weatherData.get("temperature");
				temperatureText.setText(temperature + " C");

				// Update weather condition text
				weatherConditionDesc.setText(weatherCondition);

				// Update humidity
				long humidity = (long) weatherData.get("humidity");
				humidityText.setText("<html><b>Humidity</b> " + humidity + "</html>");

				// Update windspeed
				double windspeed = (double) weatherData.get("windspeed");
				windspeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");
			}
		});
		
		add(searchButton); // Add search button to GUI
	}

	// Method to load an image and return it as an ImageIcon
	private ImageIcon loadImage(String resourcePath)
	{
		try
		{
			BufferedImage image = ImageIO.read(new File(resourcePath)); // Read image file
			return new ImageIcon(image); // Return as icon
		}
		catch(IOException exception)
		{
			exception.printStackTrace(); // Print error if file not found
		}
		System.out.println("Could Not Find Resource"); // Show fallback error message
		return null; // Return null if loading fails
	}
}
