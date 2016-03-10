//@ file csvReader.java
//@ author Mark Pikulik 
//@ date 4/20/15
//@ Version 2.0

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class csvReader {
	// auto creates a path to the app's location
	private static String path = new File(".").getAbsolutePath();
	// the CSV file name that you will be accessing
	private static String file;
	// the amount of rows are in the CSV file
	private static int RowOfCSV;
	// the path to the CSV file
	private static String csvFileLocation;

	// @post the path to the CSV file will be saved
	// @param the name of the CSV file as a string with csv at the end
	public void setFile(String File) {
		file = File;
		csvFileLocation = path + "/Data/" + file;
	}

	// @returns int how may rows in the csv-1 because of the heading
	public int getRowCount() {
		return RowOfCSV;
	}

	// @pre setFile() needs to be run before to set the path
	// @post RowOfCSV will get set with the amount of rows in the CSV
	public void countRowInCSV() throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(path
				+ "/Data/" + file));
		String input;
		int count = 0;
		while ((input = bufferedReader.readLine()) != null) {
			count++;
		}
		RowOfCSV = count - 1;
		System.out.println("Count of Rows: " + RowOfCSV);
	}

	// @pre setFile() needs to be run before to set the path
	// @post in console the the CSV will be printed
	public void printCSV() throws IOException {
		String total[][];
		total = getCsvFile();
		System.out.println("\nPrinting CSV");
		System.out.println("First, Last, Cell # , Carrier");
		for (int i = 0; i < RowOfCSV; i++) {

			for (int j = 0; j < 4; j++) {
				System.out.print(total[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	// @pre setFile() needs to be run before to set the path
	// @returns a double array of the strings that contain all the info of the
	// CSV
	public static String[][] getCsvFile() {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[][] CSVarray = new String[RowOfCSV][4];

		try {
			br = new BufferedReader(new FileReader(csvFileLocation));
			int i = 0;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] stock = line.split(cvsSplitBy);
				CSVarray[i][0] = stock[0];
				CSVarray[i][1] = stock[1];
				CSVarray[i][2] = stock[2];
				CSVarray[i][3] = stock[3];
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Check file location given.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return CSVarray;
	}
}
