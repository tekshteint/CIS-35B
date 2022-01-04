


package driver;

import util.FileIO;
import model.*;



public class Driver {

	public static void main (String[] args) {

		/**
		 * Lab 1 Test - correct car info
		 */
		System.out.println("******************************Lab 1 Test - Run 1 (correct car info)*********************************");
		// Build Automobile Object from a file.
		FileIO lab1 = new FileIO();
		Automotive FordZTW = lab1.buildAutoObject("FocusWagonZTW.txt");

		// Print attributes before serialization
		System.out.println("Before serialization - printing FordZTW:");
		FordZTW.print();

		// Serialize the object
		String serFile = lab1.serializeFile(FordZTW);

		// Deserialize the object and read it into memory
		Automotive newFordZTW = lab1.deserializeFile(serFile);

		// Print new attributes.
		System.out.println("After serialization - printing newFordZTW:");
		newFordZTW.print();

		
		/**
		 * Lab 1 Test - Run 2 (altered car file)
		 */
		System.out.println("******************************Lab 1 Test - Run 2 (altered car file #1)*********************************");
		// Build Automobile Object from a file.
		FileIO lab1a = new FileIO();
		Automotive FordZTW2 = lab1.buildAutoObject("FocusWagonZTWmod1.txt");

		// Print attributes before serialization
		System.out.println("Before serialization - printing FordZTW2:");
		FordZTW2.print();

		// Serialize the object
		String serFile2 = lab1.serializeFile(FordZTW2);

		// Deserialize the object and read it into memory
		Automotive newFordZTW2 = lab1.deserializeFile(serFile2);

		// Print new attributes.
		System.out.println("After serialization - printing newFordZTW2:");
		newFordZTW2.print();
		
		
		/**
		 * Lab 1 Test - Run 3 (altered car file)
		 */
		System.out.println("******************************Lab 1 Test - Run 3 (altered car file #2)*********************************");
		// Build Automobile Object from a file.
		FileIO lab1b = new FileIO();
		Automotive FordZTW3 = lab1.buildAutoObject("FocusWagonZTWmod2.txt");

		// Print attributes before serialization
		System.out.println("Before serialization - printing FordZTW3:");
		FordZTW3.print();

		// Serialize the object
		String serFile3 = lab1.serializeFile(FordZTW3);

		// Deserialize the object and read it into memory
		Automotive newFordZTW3 = lab1.deserializeFile(serFile3);

		// Print new attributes.
		System.out.println("After serialization - printing newFordZTW3:");
		newFordZTW3.print();

	}

}

