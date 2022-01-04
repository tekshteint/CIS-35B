

package model;
import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;

public class Automotive implements Serializable {
	
	private String name;
	private float baseprice;
	private ArrayList<OptionSet> opset;
	private boolean DEBUG = false;
	


	/** Constructors */
	
	public Automotive() {
		baseprice = 0;
		opset = new ArrayList<OptionSet>();
		name = "";
	}
	
	// does not necessarily need default constructor... 
	public Automotive(String name, float baseprice) {
		
		// use if statement to check if array index at 'size' is not equal to null 
		this.baseprice = baseprice;
		opset = new ArrayList<OptionSet>(); 
		this.name = name;
	}

	
    /** Accessors */
	
	public String getName() {
		return name;
	}

	public float getBaseprice() {
		return baseprice;
	}

	public boolean isDEBUG() {
		return DEBUG;
	}

	public ArrayList<OptionSet> getOpset() {
		return opset;
	}

	public OptionSet getOpset(int index) {
		return opset.get(index);
	}
	
	
	/**
	 * Checks whether param name is a name of an OptionSet in ArrayList 'opset'.
	 * @param name
	 * @return true/false
	 */
	public boolean inOpset(String name) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(name)) {
				return true; // found name match in opset
			}
		}
		return false; // not found
	}
	
	/**
	 * Finds OptionSet based on name
	 * @param name
	 * @return OptionSet object if available, otherwise null
	 */
	public OptionSet findOpset(String name) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(name)) {
				return opset.get(i);
			}
		}
		return null; // not found
	}
	
	/**
	 * Finds Option in OptionSet(o param), based on optionName param
	 * @param o - OptionSet object to search in
	 * @param optionName - Option name you are looking for
	 * @return Option object if available, otherwise null
	 */
	public Option findOption(OptionSet o, String optionName) {
		for (int i = 0; i < o.getOpt().size(); i++) {
			if (o.getOpt().get(i).getName().equals(optionName)) {
				return o.getOpt().get(i);
			}
		}
		return null; // not found
	}
	
	
	
	/** Mutators */
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	public void setDEBUG(boolean debug) {
		DEBUG = debug;
	}
	
	public void setOpset(ArrayList<OptionSet> opset) {
		this.opset = opset;
	}
	
	
	/**
	 * Adds a new OptionSet to an existing Automotive
	 * @param name - name of new OptionSet
	 */
	public void addOptionSet(String name) {
		if (DEBUG)
			System.out.printf("Automotive addOptionSet(): add(new OptionSet(%s)) to opset\n", name);
		opset.add(new OptionSet(name));
	}
	
	/**
	 * Adds an option to an existing OptionSet 
	 * @param opsetName - Existing OptionSet name
	 * @param name - new Option's name
	 * @param price - new Option's price
	 */
	public void addOption(String opsetName, String name, float price) {
		
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(opsetName)) {
				if (DEBUG)
					System.out.printf("Automotive addOption(): addOption(%s, %f) to %s\n", name, price, opset.get(i).getName());
				opset.get(i).addOption(name, price);	
			}
		}
	}
	
	public void deleteOption(int setIndex, int optIndex) {
		opset.get(setIndex).deleteOption(optIndex);
	}
	
	public void deleteOptionSet(int index) {
		opset.remove(index);
	}
	
	/**
	 * Updates values of Option instance variables
	 * @param o - OptionSet object
	 * @param currName - Name of Option to update
	 * @param newName - updated name for Option
	 * @param newPrice - updated price for Option
	 */
	public void updateOption(OptionSet o, String currName, String newName, float newPrice) {
		Option updateMe = findOption(o, currName);
		updateMe.setName(newName);
		updateMe.setPrice(newPrice);
	}
	
	/**
	 * Updates values of OptionSet instance variables 
	 * @param currName - current OptionSet name
	 * @param newName - updated OptionSet name
	 */
	public void updateOptionSet(String currName,String newName) {
		OptionSet updateMe = findOpset(currName);
		updateMe.setName(newName);
	}
	
	
	/** Instance Methods */
	
	/**
	 * Prints all Automotive properties in formatted output
	 */
	public void print() {
		System.out.printf("%s starting at a base price of $%.2f.\nSee additional options below:\n",name, baseprice);
		
		
		for (int i = 0; i < opset.size(); i++) {
			System.out.printf("********************** %-25s **********************\n", opset.get(i).getName());
			opset.get(i).print();
			System.out.println();
		}
	}

}
