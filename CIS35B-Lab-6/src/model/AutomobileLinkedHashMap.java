package model;

import java.util.LinkedHashMap;
import adapter.BuildAuto;

public class AutomobileLinkedHashMap {
	private Automobile Data;
	private int Key;
	public static LinkedHashMap<Integer, Automobile> garageObjects = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> garageNames = new LinkedHashMap<>();
	private boolean DEBUG = false;
	private static int CarCount=1;
	

	public AutomobileLinkedHashMap() {
	}
	
	public AutomobileLinkedHashMap(Automobile data) {
		this.Data = data;
	}
	
	public AutomobileLinkedHashMap(Automobile data, int key) {
		this.Data = data;
		this.Key = key;
	}

	public static LinkedHashMap<Integer, Automobile> getgarageObjects() {
		return garageObjects;
	}
	
	public static LinkedHashMap<Integer, String> getgarageNames() {
		return garageNames;
	}

	public static Automobile getObject(int key) {
		if (garageObjects.containsKey(key)) {
			return garageObjects.get(key);
		} else {
			return null;
		}
	}
	public String getName(int key) {
		if (garageNames.containsKey(key)) {
			return garageNames.get(key);
		} else {
			return null;
		}
	}

	public static void addNewCar(Automobile data) {
		String prettyAutoValue = data.getMake()+" "+data.getModel();
		garageObjects.put(CarCount, data);
		garageNames.put(CarCount, prettyAutoValue);
		CarCount++;
	}

	public void deleteCar(int key) {
		if (garageObjects.containsKey(key)) {
			garageObjects.remove(key);
		}
		if (garageNames.containsKey(key))
			garageNames.remove(key);
	}

	public void updateCarData(int key, Automobile newData) {
		if (garageObjects.containsKey(key)) {
			String prettyAutoValue = newData.getMake()+" "+newData.getModel();
			garageObjects.replace(key, Data, newData);
			garageNames.replace(key, prettyAutoValue);
		}
	}

	public void printObjects() {
		System.out.println(garageObjects);
	}
	
	public String printNames() {
		String response="";
		for (int i=1;i<=garageNames.size();i++) {
			if (i==1)
				response+=garageNames.get(i);
			if (i==garageNames.size()&& i!=1)
				response+=", "+garageNames.get(i);
			if (i>1 && i<garageNames.size())
				response+=", "+garageNames.get(i);
		} return response;
	}
	
}
