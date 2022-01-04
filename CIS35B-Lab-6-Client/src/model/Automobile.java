

package model;
import java.io.Serializable;
import java.util.ArrayList;
import model.OptionSet.Option;
import exception.*;

public class Automobile implements Serializable {
	
	private float baseprice;
	private ArrayList<OptionSet> opset;
	private boolean DEBUG = false;
	private String make;
	private String model;
	private ArrayList<OptionSet> choice; //LAB 3
	private static final long serialVersionUID = 1L;
	


	/** Constructors */
	
	public Automobile() {
		baseprice = 0;
		opset = new ArrayList<OptionSet>();
		make = "";
		model = "";
		choice = new ArrayList<OptionSet>(); //LAB 3
	}
	
	// does not necessarily need default constructor... 
	public Automobile(String make,String model, float baseprice) {
		
		// use if statement to check if array index at 'size' is not equal to null 
		this.baseprice = baseprice;
		opset = new ArrayList<OptionSet>(); 
		this.make = make;
		this.model = model; 
		choice = new ArrayList<OptionSet>(); //LAB 3
	}

	
    /** Accessors */
	
	public String getMake() { //LAB 3
		return make;
	}
	
	public String getModel() { //LAB 3
		return model;
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
	
	public ArrayList<OptionSet> getOptionChoice(){
		return choice;
	}

	public OptionSet getOpset(int index) {
		return opset.get(index);
	}
	public String getOpsetName(int index) {
		return opset.get(index).getName();
	}
	
	public  OptionSet getOpset(String opsetName) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(opsetName))
				return opset.get(i);
		}
		return null;
	}
	public String getOptionName(int opsetIndex,int optionIndex) { //LAB 3
		return opset.get(opsetIndex).getOpt().get(optionIndex).getName();
	}
	
	public float getOptionPrice(int opsetIndex,int optionIndex) { //LAB 3
		return opset.get(opsetIndex).getOpt().get(optionIndex).getPrice();
	}
	public ArrayList<Option> getOptions(int index) { //LAB 3
		return opset.get(index).getOpt();
	}

	
	public String getOptionChoice(String name) { //LAB 3
		for (int i = 0; i < choice.size(); i++) {
			if (choice.get(i).getName().equals(name)) {
				return choice.get(i).getOpt().get(0).getName();
			}
		}return null;
	}
	
	public Integer getOptionChoicePrice(String name) { //LAB 3
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(name)) {
				int j = (int) opset.get(i).getOpt().get(1).getPrice();
				return j;
			}
		}
		return null; // not found
	}
	public int getTotalPrice() { //LAB 3
		int total=(int) getBaseprice();
		for (int i=0;i<choice.size();i++) {
			for (int j=0;j<choice.get(i).getOpt().size();j++) {
				if (DEBUG) {
					System.out.println(choice.get(i).getOpt().get(j).getPrice());
				}
				total+=choice.get(i).getOpt().get(j).getPrice();
			}
		}
		
		return total;
	}
	public String getOptionSetName(int index) { //LAB 3
		return opset.get(index).getName();
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
	
	public void setMake(String make) { //LAB 3
		this.make = make;
	}
	
	public void setModel(String model) { //LAB 3
		this.model = model;
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
	
	public void setOptionChoice (String setName, String optionName) { //LAB 3
		for (int i=0;i<opset.size();i++) {
			if (opset.get(i).getName().equals(setName)) {
				for (int j=0; j<opset.get(i).getOpt().size();j++) {
					if (opset.get(i).getOpt().get(j).getName().equals(optionName)) {
						choice.add(new OptionSet(setName));
						for (int k=0;k<choice.size();k++) {
							if (choice.get(k).getName().equals(setName)) {
								choice.get(k).addOption(optionName, opset.get(i).getOpt().get(j).getPrice(), DEBUG);
							}
						}
						
					}	
				}
				
			}
		}
	}
	public void clearAll() {
		this.choice.clear();
		this.opset.clear();
		setBaseprice(0);
		setMake(null);
		setModel(null);
		
	}
	public void clearChoice() {
		this.choice.clear();
	}
	/**
	 * Adds a new OptionSet to an existing Automobile
	 * @param name - name of new OptionSet
	 */
	public void addOptionSet(String name) {
		if (DEBUG) {
			System.out.printf("Automobile addOptionSet(): add(new OptionSet(%s)) to opset\n", name);

		}
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
				if (DEBUG) {
					System.out.printf("Automotive addOption(): addOption(%s, %f) to %s\n", name, price, opset.get(i).getName());
				}
				opset.get(i).addOption(name, price,DEBUG);	
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
	 * @throws AutoException 
	 */
	public void updateOption(OptionSet o, String currName, String newName, float newPrice) throws AutoException {
		Option updateMe = findOption(o, currName);
		try {
			updateMe.setName(newName);
		} catch (NullPointerException e) {
			throw new AutoException ("Error with Thread, either the Optionset name does not exist or current option name does not exist");
		}
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
	 * Prints all Automobile properties in formatted output
	 */
	public void print() {
		System.out.printf("%s %s starting at a base price of $%.2f.\nSee additional options below:\n",make,model,baseprice);
		
		for (int i = 0; i < opset.size(); i++) {
			System.out.printf("********************** %-25s **********************\n", opset.get(i).getName());
			opset.get(i).print();
			System.out.println();
		} System.out.println("Total after options: $"+getTotalPrice());
	}

}
