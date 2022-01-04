

package model;
import java.io.Serializable;
import java.util.ArrayList;

//methods in option and optionset are all protected 
// can still access in Automotive b/c in same package 'model'
// can only call methods from Automotive from FileIO class (diff package) 

public class OptionSet implements Serializable {
	
	private ArrayList<Option> opt;
	private String name;
	private static final long serialVersionUID = 1L;

	
	/** Constructors */
	
	protected OptionSet(){
		opt = null;
		name = null;	
	}
	
	protected OptionSet(String name) {
		opt = new ArrayList<Option>();
		this.name = name;
	}
	
	
	/** Accessors */
	
	protected ArrayList<Option> getOpt() {
		return opt;
	}

	protected String getName() {
		return name;
	}
	

	
	/** Mutators */
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setOpt(ArrayList<Option> opt) {
		this.opt = opt;
	}
	
	/**
	 * Adds new Option to ArrayList 'opt' of Options.
	 * @param name - name of new Option
	 * @param price - price of new Option
	 */
	protected void addOption(String name, float price,boolean DEBUG) {
		opt.add(new Option(name, price));
		if (DEBUG==true) {
			System.out.println(getOpt());
		}
	}
	
	protected void deleteOption(int index) {
		opt.remove(index);
	}
	
	/** Instance Methods */
	
	/**
	 * Print formatted option name and price for each option in opt
	 */
	protected void print() {
		for (int i = 0; i < opt.size(); i++) {
			opt.get(i).print();
		}
	}
	protected String printSTR() {
		String response = "";
		for (int i = 0; i < opt.size(); i++) {
			response+=opt.get(i).printSTR();
		}
		return response;
	}

	protected class Option implements Serializable {
		// inner class of optionset
		
		private String name; 
		private float price;
		private static final long serialVersionUID = 1L;

		
		/** Constructors */
		
		protected Option() {}
		protected Option(String name,float price) {
			this.name = name;
			this.price = price;
		}
		
		/** Accessors */
		
		protected String getName() {
			return name;
		}
		protected float getPrice() {
			return price;
		}
		protected Option getOptionChoice(String name) {
			for (int i=0; i<opt.size();i++) {
				if (opt.get(i).toString()==name) {
					return opt.get(i);
				}
			}
			return null;	
		}
		/** Mutators */
		
		protected void setPrice(float price) {
			this.price = price;
		}
		
		protected void setName(String name) {
			this.name = name;
		}

		

		/** Instance Methods */
		
		/**
		 * Print formatted option name and price
		 */
		protected void print() {

				System.out.printf("%-40s $%.2f\n", getName(), getPrice());
		}
		protected String printSTR() {
			return String.format("%-40s $%.2f\n", getName(), getPrice());
		}

	}

}
