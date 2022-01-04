

package util;
import model.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import exception.*;


/** FileIO Class parses a text file and builds an Automobile */
public class FileIO implements Serializable {
	private boolean DEBUG;
	private Automobile data;
	private String Filename;
	
	public FileIO(String filename,Automobile data) {
		Filename=filename;
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

	public Automobile buildAutoObject(Automobile data) throws AutoException{ 
		try{
			FileReader file = new FileReader (Filename);
			BufferedReader buff = new BufferedReader(file);
			ArrayList<String> tester = new ArrayList<>();
			int counter=0;
			boolean eof = false;
				while (!eof){
					String line = buff.readLine();
					if (counter==0) {
						data.setMake(line);
					}
					if (counter==1) {
						data.setModel(line);
					}
					if (counter==2) {
						try {
						float basePrice=Float.parseFloat(line);
						data.setBaseprice(basePrice);
						} catch (NumberFormatException e) {
							throw new AutoException(2,"Base Price in text is not a float");
						}
					}
					if (counter>3 && line!=null) {
						if (!line.contains(",")) {
							data.addOptionSet(line);
							tester.add(line);
						} else {
							String[] textLine= line.split(",");
							List<String> textList = Arrays.asList(textLine);
							ArrayList<String> optionList=new ArrayList<>(textList);
							for (int i=0;i<data.getOpset().size();i++) {
								if (tester.get(i).equals(optionList.get(0))) {
									data.addOption(optionList.get(0), optionList.get(1), Float.parseFloat(optionList.get(2)));
								}
							}
						}
					}
					counter+=1;
					if (line == null){
						eof = true;
					}else{
						
					}
				}
				buff.close();
				}catch (IOException e) {
					throw new AutoException(1,"Error with reading a file");
					
				} return data;
}

	/**
	 * Serializes an Automobile object to a file
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
		}
		finally {
			return newCar;
		}

	}

}
