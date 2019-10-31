

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIOHelper {

	public static void readFromFile() {

		String fileName = "write_stuff.txt";
		Path path = Paths.get("resources", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read the file.");

		}

	}

	public static void writeToFile() {

		String fileName = "write_stuff.txt";
		Path path = Paths.get("resources", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		// Person Adam = new Person("Adam Graff", "110 Prospect", "pizza");

		try {
			// true (below) adds to file and false overrides it
			output = new PrintWriter(new FileOutputStream(file, true));
			output.println("Hello World!");
			output.println();
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Hey, contact customer service!");
		} finally {
			output.close();
		}
	}

	public static void createOurFile() {

		String fileName = "write_stuff.txt";
		// we can reference a folder name as the first param if we want to organize our
		// text files
		Path path = Paths.get("resources", fileName);

		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
				System.out.println("File was created successfully!");
			} catch (IOException e) {
				System.out.println("Something went terribly wrong!");
			}
		} else {
			System.out.println("The file already exists.");
		}
	}

	public static void createDir() {
		// create a string representing the name of the folder
		// we want to create or verify that it already exists
		String dirPath = "resources";

		// this next line is turning our string reference above
		// into a Path to use with the other file methods
		Path folder = Paths.get(dirPath);

		// this Files.notExists() method is checking to make sure
		// that the folder is not already there, otherwise it will
		// create a new folder
		if (Files.notExists(folder)) {
			try {
				// this static method will create a folder for us with the associated name
				// we pass in a parameter
				Files.createDirectories(folder);
				System.out.println("The file was created successfully!");
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println("Something went wrong with the folder creation.");
			}
		} else {
			System.out.println("The folder already exists.");
		}

	}

}
