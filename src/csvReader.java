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


public class csvReader{
	
	private static String path = new File(".").getAbsolutePath();
	private static String file;
	private static int RowOfCSV;
	private static String csvFileLocation;

	
	public void setFile (String File){
		file = File;
		csvFileLocation = path + "/Data/" + file;
	}
	
	public int getRowCount(){
		return RowOfCSV;
	}
	
	public void countRowInCSV() throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "/Data/" + file));
	     String input;
	     int count = 0;
	     while((input = bufferedReader.readLine()) != null)
	     {
	         count++;
	     }
	     RowOfCSV = count-1;
	     System.out.println("Count of Rows: "+RowOfCSV);
	     }
	
	public void printCSV() throws IOException {	
		String total[][];
		total = getCsvFile();		
		System.out.println("\nPrinting CSV");
		System.out.println("First, Last, Cell # , Carrier");
		for(int i = 0; i< RowOfCSV; i++){
			

			for(int j = 0; j < 4; j++){
				System.out.print(total[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	public static String[][] getCsvFile() {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[][] CSVarray = new String[RowOfCSV][4];
		
		try {
			br = new BufferedReader(new FileReader(csvFileLocation));
			int i = 0;
			line = br.readLine();
			while((line = br.readLine()) != null) {
				String[] stock = line.split(cvsSplitBy);
				CSVarray[i][0] = stock[0];
				CSVarray[i][1] = stock[1];
				CSVarray[i][2] = stock[2];
				CSVarray[i][3] = stock[3];
				i++;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Check file location given.");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(br != null) {
				try {
					br.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return CSVarray;
	}
}
