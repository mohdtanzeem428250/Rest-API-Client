package com.application; // Declares that this class belongs to the 'com.application' package

import javax.swing.SwingUtilities; // Imports SwingUtilities for managing GUI updates on the Event Dispatch Thread

public class AppLauncher // Main class to launch the application
{
	public static void main(String[] args) // Main method - entry point of the application
	{
		// Ensures that GUI creation runs on the Event Dispatch Thread (EDT) for thread safety
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() // The code inside this method runs on the EDT
			{
				// Creates a new instance of WeatherApplicationGUI and makes it visible
				new WeatherApplicationGUI().setVisible(true);

				// (Optional) Calls a static method to get the current time and prints it
				System.out.println(WeatherApplication.getCurrentTime());

				// The line below is commented out, but if enabled, it would fetch location data for "Tokyo"
				// System.out.println(WeatherApplication.getLocationData("Tokyo"));
			}
		});
	}
}
