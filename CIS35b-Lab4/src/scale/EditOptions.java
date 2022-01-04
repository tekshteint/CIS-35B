package scale;

import adapter.proxyAutomobile;
import model.*;

public class EditOptions extends proxyAutomobile implements Runnable {
	public static int threadCount=1;
	public static Automobile a1;
	public String make;
	public String model;
	public String opsetName;
	public String optName;
	public String newOptName;
	public float newPrice;
	
	public EditOptions() {
	}
	/**
	 * @param a1 - auto obj
	 * @param make - make of automobile
	 * @param model - automobile model name
	 * @param opsetName - option set name 
	 * @param newOptName - new option name, if same name write old name
	 * @param optName - current option name
	 * @param newPrice  - new option price
	 */
	public EditOptions(Automobile a1,String opsetName,String newOptName,String optName,float newPrice) {
		this.a1=a1;	
		this.make=a1.getMake();
		this.model=a1.getModel();
		this.opsetName=opsetName;
		this.newOptName=newOptName;
		this.newPrice=newPrice;
		this.optName=optName;
		
	}
	
	

	@Override
	public synchronized void run() {
		for(int i=0; i<a1.getOpset().size();i++) {
			if (a1.getOpsetName(i).equals(this.opsetName)) {
				a1.updateOption(a1.findOpset(this.opsetName), this.optName, this.newOptName, this.newPrice);
			}
		}
		
		
	}

}
