package client;

import model.*;

import adapter.BuildAuto;

public class SelectCarOption {

	////////// PROPERTIES //////////
	private boolean DEBUG = false;

	////////// CONSTRUCTORS //////////

	public SelectCarOption() {

	}

	////////// INSTANCE METHODS //////////

	public void configureAuto(Object obj) {
		Automobile a = (Automobile)obj;
		BuildAuto b = new BuildAuto();
		b.chooseOptions(a);
		System.out.print("You have configured the following:\n");
		a.print();
		System.out.println("");
		System.out.println("Press any key to continue.");
	}

	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}

}
