package server;
import java.io.ObjectInputStream;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import util.*;
import adapter.*;
import exception.*;
import model.*;
import adapter.*;

public class BuildCarModelOptions implements AutoServer {
	private int request;
	private boolean DEBUG=false;
	
	public String setRequest(int request) {
		this.request=request;
		switch (request) {
		case 1:
			return ("Please enter the filename for the automobile you would like to upload.");
		case 2:
			return("Which car would you like to configure from the following list:\n"+ listAutos());
		case 3:
			return("Here are all of the Automobiles you have created, please press Enter to view:");
		default:{
			this.request=-1;
			return("Not a valid Menu Choice. Press any key to continue.");
			}
		}
	}
	
	public int getRequest() {
		return this.request;
	}
	
	public void addAuto(Automobile auto) throws AutoException{
		try {
			BuildAuto b = new BuildAuto();
			b.addAuto(auto);
		}
		 catch (Exception e) {
				if (DEBUG)
					System.out.print("Error: " + e);
				// System.exit(1);
				String msg = "Server>BuildCarModel: adding auto to LHM:" + e;
				throw new AutoException(500, msg);
		 }
	}
	public LinkedHashMap<Integer,String> listAutos() {
		return proxyAutomobile.getAutomobiles();
	}
	

	
	public Automobile getAuto(int key) {
		BuildAuto b = new BuildAuto();
		return b.getAuto(key);
	}
	
	public String processRequest(Object fromClient) throws AutoException {
		BuildAuto b = new BuildAuto();
		if (DEBUG)
			System.out.println(fromClient.getClass());
		if (fromClient instanceof Properties) {
			try {
				b.BuildAuto((Properties) fromClient);
				} catch (Exception e) {
					 if(DEBUG) {
						return
						e+"\nFrom Server Side BuildCarModelOptions processRequest(Properties fromClient)"
						+fromClient.toString()
						+"There was an error uploading your automobile. Please try again. Press any key to continue.";}
					return "There was an error uploading your automobile. Please try again. Press any key to continue.";
				 }
				String response = ((Properties) fromClient).getProperty("Year").toString()
						+ " " + ((Properties) fromClient).getProperty("Make").toString() + " " 
						+ ((Properties) fromClient).getProperty("Model").toString();
				response += " has been added to available automobiles. Press Enter to continue.\n";
				return response;
		} if (fromClient instanceof String){
			b.loadString((String)fromClient);
			FileIO IO = new FileIO();
			Automobile data = IO.deserializeFile("automobile.ser");
			String response =data.getMake()+ " " +data.getModel();
			response += " has been added to available automobiles. Press Enter to continue.\n";
			return response;
		} else return "There was an error processing your request ... ";
	}	
	 public Automobile processRequest(String key) {
	 
		Automobile a = null;
		try {
			a= proxyAutomobile.getLHMauto(Integer.parseInt(key));
			if (DEBUG)
				System.out.println("Found " + a.getMake());
		}
		catch (Exception e) {
			if (DEBUG)
				System.out.println("NOT Found: " + key);
			
		}

		return a;
		
	}

	@Override
	public void server(int port) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
