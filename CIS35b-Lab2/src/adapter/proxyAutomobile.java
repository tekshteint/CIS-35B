package adapter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.*;
import exception.*;


public abstract class proxyAutomobile {
	private Automobile a1;
	
	public proxyAutomobile(){
		a1 = new Automobile();
		
	}
	
	public void BuildAuto(String filename) throws AutoException {
		try{
				FileReader file = new FileReader (filename);
				BufferedReader buff = new BufferedReader(file);
				int counter=0;
				boolean eof = false;
					while (!eof){
						String line = buff.readLine();
						if (counter==0) {
							a1.setName(line);
						}
						if (counter==1) {
							try {
							float basePrice=Float.parseFloat(line);
							a1.setBaseprice(basePrice);
							} catch (NumberFormatException e) {
								throw new AutoException(2,"Base Price in text is not a float");
							}
						}
						if (counter>7 && line!=null) {
							a1.addOptionSet(line);
						}
						counter+=1;
						if (line == null){
							eof = true;
						}else{
							
						}
					}buff.close();
					serializeFile(a1);
					}catch (IOException e) {
						throw new AutoException(1,"Error with reading a file");
						
					}
	}
	public void printAuto(String Modelname) throws AutoException {
		if (a1.getName()=="" || a1.getName()==null) {
			throw new AutoException(5,"Model name is empty");
		} else {
		a1.print();}
	}
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws AutoException {
		try{
			Automobile newCar=deserializeFile("Automobile.ser"); //made for trying to do number 3 in checklist but not needed in the end
			for (int i = 0; i < newCar.getOpset().size(); i++) {
				if (OptionSetname==newCar.getOpset(i).toString()) {
					System.out.println(OptionSetname);
				} 
			}
		} catch (NullPointerException e) {
			throw new AutoException(3,"Serialization error");
		}
		
	}
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) throws AutoException {
		if (a1.getName()==Modelname && a1.findOpset(Optionname)!=null && a1.inOpset(Option)==true) {
			if (Optionname!=Option) {
				a1.updateOption(a1.getOpset(0), Option, Option, newprice);
			} else {
				throw new AutoException(4,"Error with Option Name");}
			

		} else {
			throw new AutoException(0,"Unknown Error");
		}
	}
	public void fix() {
		System.out.println("Fix");
	}
	/*
	public void fix(int errno) {
		switch (errno) {
		case 1: AutoException(errno,"Error with reading a file");break;
		case 2: AutoException(errno,"Error with baseprice in text file");break;
		case 3: AutoException(errno,"Error with Serialization");break;
		case 4: AutoException(errno,"Error with Option");break;
		case 5: AutoException(errno,"Error with Serialization");break;
		default: AutoException(errno,"Unknown error"); break;
		}
	}
	*/
	/**
	 * Serializes an Automobile object to a file
	 * @param car - Automobile object to serialize
	 * @return filename of serialized object
	 */
	public String serializeFile(Automobile car) throws AutoException {

		String filename = "";

		try {
			filename = "Automobile.ser"; 
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(car);
			out.close();

		} catch (Exception e) {
			throw new AutoException(3,"Serialization error");
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
			in.close();


		} catch (FileNotFoundException e) {
			System.out.println("readSerFile(): No file found with that name." + e.toString());
		}
		finally {
			return newCar;
		}

	}

}


