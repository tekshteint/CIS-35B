

package util;
import model.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;


/** FileIO Class parses a text file and builds an Automotive */
public class FileIO implements Serializable {
	private boolean DEBUG;
	private Automotive data;

	public FileIO() {
		data = null;
		DEBUG = false;
	}

	public boolean isDEBUG() {
		return DEBUG;
	}

	public void setDEBUG(boolean debug) {
		DEBUG = debug;
	}

	public Automotive getData() {
		return data;
	}

	public void setData(Automotive data) {
		this.data = data;
	}

	public Automotive buildAutoObject(String filename) { 
		Automotive auto=new Automotive("empty",000);
		try{
				FileReader file = new FileReader (filename);
				BufferedReader buff = new BufferedReader(file);
				int counter=0;
				boolean eof = false;
					while (!eof){
						String line = buff.readLine();
						if (counter==0) {
							auto.setName(line);
						}
						if (counter==1) {
							float basePrice=Float.parseFloat(line);
							auto.setBaseprice(basePrice);
						}
						if (counter>7 && line!=null) {
							auto.addOptionSet(line);
						}
						counter+=1;
						if (line == null){
							eof = true;
							
						}else{
							
						}
					}buff.close();
					}catch (IOException e) {
					System.out.println("Error -- " + e.toString());}
		return auto;
		
	
	}

	/**
	 * Serializes an Automotive object to a file
	 * @param car - Automotive object to serialize
	 * @return filename of serialized object
	 */
	public String serializeFile(Automotive car) {

		String filename = "";

		try {
			filename = "automotive.ser"; 
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(car);
			out.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return filename;

	}

	/**
	 * Deserializes an Automotive object from a file
	 * @param filename - filename to read in
	 * @return new Automotive object loaded from file contents
	 */
	public Automotive deserializeFile(String filename) {

		Automotive newCar = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			newCar = (Automotive) in.readObject();


		} catch (FileNotFoundException e) {
			System.out.println("readSerFile(): No file found with that name." + e.toString());
		}
		finally {
			return newCar;
		}

	}

}
