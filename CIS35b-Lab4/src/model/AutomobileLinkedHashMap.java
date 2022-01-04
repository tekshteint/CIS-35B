package model;
import java.util.LinkedHashMap;
import adapter.BuildAuto;

public class AutomobileLinkedHashMap {
	private Automobile Data;
	private String Key;
	private LinkedHashMap<String, Automobile> garage = new LinkedHashMap<>();
	private boolean DEBUG=false;
	
	public AutomobileLinkedHashMap() {
	}
	
	public AutomobileLinkedHashMap(Automobile data,String key) {
		this.Data=data;
		this.Key=key;
		
	}
	public LinkedHashMap<String,Automobile> getGarage() {
		return garage;
	}
	public Automobile getData(String key) {
		if (garage.containsKey(key)) {
			return garage.get(key);
		} else {
			return null;
		}
	}
	
	public void addNewCar(String key,Automobile data) {
		garage.put(key, data);
		if (DEBUG==true) {
			System.out.println("from LHM addNewCar key then automobile model "+key+" "+data.getModel());
		}
	}
	public void deleteCar(String key) {
		if (garage.containsKey(key)) {
			garage.remove(key);	
		}
	}
	public void updateCarData(String key, Automobile newData) {
		if (garage.containsKey(key)) {
			garage.replace(key, Data, newData);
		}
	}
	public void print() {
		System.out.println(garage);
	}
	
	//private LinkedHashMap<k,v> LMH(Automobile Data,String Key) {
		
	//}
}
