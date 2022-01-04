package util;

import model.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import exception.*;

/** FileIO Class parses a text file and builds an Automobile */
public class FileIO implements Serializable {
	private boolean DEBUG;
	private Automobile data;
	private String Filename;
	private static final long serialVersionUID = 1L;

	public FileIO(String filename, Automobile data) {
		Filename = filename;
		data = null;
		DEBUG = false;
	}

	public FileIO(Automobile data) {
	}

	public FileIO() {
	}

	public boolean isDEBUG() {
		return DEBUG;
	}

	public void setDEBUG(boolean debug) {
		DEBUG = debug;
	}

	public Automobile getData() {
		return data;
	}

	public void setData(Automobile data) {
		this.data = data;
	}

	public Automobile buildAutoObject(String filename) throws AutoException {
		try {
			Automobile data = new Automobile();
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			ArrayList<String> tester = new ArrayList<>();
			int counter = 0;
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (counter == 0) {
					data.setMake(line);
				}
				if (counter == 1) {
					data.setModel(line);
				}
				if (counter == 2) {
					try {
						float basePrice = Float.parseFloat(line);
						data.setBaseprice(basePrice);
					} catch (NumberFormatException e) {
						throw new AutoException(2, "Base Price in text is not a float");
					}
				}
				if (counter > 3 && line != null) {
					if (!line.contains(",")) {
						data.addOptionSet(line);
						tester.add(line);
					} else {
						String[] textLine = line.split(",");
						List<String> textList = Arrays.asList(textLine);
						ArrayList<String> optionList = new ArrayList<>(textList);
						for (int i = 0; i < data.getOpset().size(); i++) {
							if (tester.get(i).equals(optionList.get(0))) {
								data.addOption(optionList.get(0), optionList.get(1),
										Float.parseFloat(optionList.get(2)));
							}
						}
					}
				}
				counter += 1;
				if (line == null) {
					eof = true;
				} else {

				}
			}
			buff.close();
		} catch (IOException e) {
			throw new AutoException(1, "Error with reading a file");

		}
		return data;
	}
	public Automobile loadTxtData(String txt) throws AutoException {
		try {
			Automobile data = new Automobile();
			Reader inputString = new StringReader(txt);
			BufferedReader buff = new BufferedReader(inputString);
			ArrayList<String> tester = new ArrayList<>();
			int counter = 0;
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (counter == 0) {
					data.setMake(line);
				}
				if (counter == 1) {
					data.setModel(line);
				}
				if (counter == 2) {
					try {
						float basePrice = Float.parseFloat(line);
						data.setBaseprice(basePrice);
					} catch (NumberFormatException e) {
						throw new AutoException(2, "Base Price in text is not a float");
					}
				}
				if (counter > 3 && line != null) {
					if (!line.contains(",")) {
						data.addOptionSet(line);
						tester.add(line);
					} else {
						String[] textLine = line.split(",");
						List<String> textList = Arrays.asList(textLine);
						ArrayList<String> optionList = new ArrayList<>(textList);
						for (int i = 0; i < data.getOpset().size(); i++) {
							if (tester.get(i).equals(optionList.get(0))) {
								data.addOption(optionList.get(0), optionList.get(1),
										Float.parseFloat(optionList.get(2)));
							}
						}
					}
				}
				counter += 1;
				if (line == null) {
					eof = true;
				} else {

				}
			}
			serializeFile(data);
			buff.close();
			return data;
		} catch (IOException e) {
			throw new AutoException(1, "Error with reading a file");

		}
	}
	public Automobile readPropData(String filename) throws IOException {
		Automobile data = new Automobile();
		Properties props = new Properties();
		FileInputStream in = new FileInputStream(filename);
		props.load(in);
		int opCount = 1;
		data.setMake(props.getProperty("Make"));
		data.setModel(props.getProperty("Model"));
		data.setBaseprice(Float.parseFloat(props.getProperty("BasePrice")));

		// add options
		String OptionLine = "Option" + String.valueOf(opCount); // Option1, Option2, etc

		while (props.getProperty(OptionLine) != null) { // if the optionSet name exists
			String opsetName = props.getProperty(OptionLine); // Color
			data.addOptionSet(opsetName);

			// printing out which option set we are working on
			if (DEBUG)
				System.out.println(OptionLine);

			// populate the optionSet
			int letter = 65; // int value of letter A
			String opName = OptionLine + Character.toString((char) letter) + "_name"; // Option1A_name
			String opPrice = OptionLine + Character.toString((char) letter) + "_price"; // Option1A_price

			while (props.getProperty(opName) != null) { // add option if it exists
				data.addOption(opsetName, props.getProperty(opName), Float.parseFloat(props.getProperty(opPrice)));
				letter++;
				opName = OptionLine + Character.toString((char) letter) + "_name";
				opPrice = OptionLine + Character.toString((char) letter) + "_price";
			}

			opCount++;
			OptionLine = "Option" + String.valueOf(opCount); // set to next option, ie. Option2

		}
		return data;

	}
	public Automobile loadPropData(Properties props) {
		Automobile data = new Automobile();
		int opCount = 1;
		data.setMake(props.getProperty("Make"));
		data.setModel(props.getProperty("Model"));
		data.setBaseprice(Float.parseFloat(props.getProperty("BasePrice")));

		// add options
		String OptionLine = "Option" + String.valueOf(opCount); // Option1, Option2, etc

		while (props.getProperty(OptionLine) != null) { // if the optionSet name exists
			String opsetName = props.getProperty(OptionLine); // Color
			data.addOptionSet(opsetName);

			// printing out which option set we are working on
			if (DEBUG)
				System.out.println(OptionLine);

			// populate the optionSet
			int letter = 65; // int value of letter A
			String opName = OptionLine + Character.toString((char) letter) + "_name"; // Option1A_name
			String opPrice = OptionLine + Character.toString((char) letter) + "_price"; // Option1A_price

			while (props.getProperty(opName) != null) { // add option if it exists
				data.addOption(opsetName, props.getProperty(opName), Float.parseFloat(props.getProperty(opPrice)));
				letter++;
				opName = OptionLine + Character.toString((char) letter) + "_name";
				opPrice = OptionLine + Character.toString((char) letter) + "_price";
			}

			opCount++;
			OptionLine = "Option" + String.valueOf(opCount); // set to next option, ie. Option2

		}
		return data;
	}
	
	public Automobile buildAutoObject(String filename, String fileType) throws AutoException, IOException {
		Automobile a1=null;
		if (filename.contains(".txt") || fileType.equals("text"))
			a1 = buildAutoObject(filename);
		if (filename.contains(".prop") || fileType.equals("properties"))
			a1 = readPropData(filename);
		if (DEBUG) {
			System.out.print("Make: " + a1.getMake());
			System.out.print("Model: " + a1.getModel());
			System.out.print("Base Price: " + a1.getBaseprice());
			System.out.println("Automobile object sucessfully built. \n");
		}
		serializeFile(a1);
		return a1;
	}

	/**
	 * Serializes an Automobile object to a file
	 * 
	 * @param car - Automobile object to serialize
	 * @return filename of serialized object
	 */
	public String serializeFile(Automobile car) {

		String filename = "";

		try {
			filename = "automobile.ser";
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(car);
			out.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return filename;

	}

	/**
	 * Deserializes an Automobile object from a file
	 * 
	 * @param filename - filename to read in
	 * @return new Automobile object loaded from file contents
	 */
	public Automobile deserializeFile(String filename) {

		Automobile newCar = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			newCar = (Automobile) in.readObject();

		} catch (FileNotFoundException e) {
			System.out.println("readSerFile(): No file found with that name." + e.toString());
		} finally {
			return newCar;
		}

	}

}
