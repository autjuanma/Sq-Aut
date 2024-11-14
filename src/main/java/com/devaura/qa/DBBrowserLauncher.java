package com.devaura.qa;

import java.io.File;
import java.io.IOException;

public class DBBrowserLauncher {

	public static void main(String[] args) {
		// Path to the AppImage
		String appImagePath = "resources/db/DB.Browser.for.SQLite-v3.13.1-x86.64-v2.AppImage";

		// Create a process to run the AppImage
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(appImagePath);
		processBuilder.directory(new File("resources"));

		try {
			// Start the process
			Process process = processBuilder.start();
			System.out.println("DB Browser for SQLite is launching...");
			process.waitFor(); // Wait for the process to finish
		} catch (IOException | InterruptedException e) {
			System.err.println("Error launching DB Browser for SQLite.");
			e.printStackTrace();
		}
	}
}