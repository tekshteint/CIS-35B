package adapter;

import java.io.IOException;
import java.util.*;
import model.*;
import scale.*;
import util.FileIO;
import exception.*;

public abstract class proxyAutomobile {
	private Automobile a1;
	private String Filename;

	public proxyAutomobile(String filename) {
		a1 = new Automobile();
		this.Filename = filename;
	}

	public proxyAutomobile() {
		a1 = new Automobile();
		this.Filename = null;
	}

	public Automobile getAuto() {
		return a1;
	}

	public static LinkedHashMap<Integer, String> getAutomobiles() {
		return AutomobileLinkedHashMap.garageNames;
	}
	public static LinkedHashMap<Integer, Automobile> getAutomobileObjects() {
		return AutomobileLinkedHashMap.garageObjects;
	}

	public static Automobile getLHMauto(int key) {
		if (AutomobileLinkedHashMap.garageObjects.containsKey(key)) {
			return AutomobileLinkedHashMap.garageObjects.get(key);
		}
		else {
			return null;
		}
	}
	public void addAuto(Automobile auto) throws AutoException{
		try {
			AutomobileLinkedHashMap.addNewCar(auto);
		}
		 catch (Exception e) {
				String msg = "ProxyAuto: adding auto to LHM:" + e;
				throw new AutoException(500, msg);
		 }
	}
	public LinkedHashMap<Integer,String> listAutos() {
		return AutomobileLinkedHashMap.getgarageNames();
	}
	
	public Automobile getAuto(int key) {
		Automobile a = AutomobileLinkedHashMap.getObject(key);
		return a;
	}
	
	public synchronized void EditOption(Automobile a1, String opsetName, String newOptName, String optName,
			float newPrice) throws InterruptedException {
		EditOptions edit = new EditOptions(a1, opsetName, newOptName, optName, newPrice);
		Thread thread = new Thread(edit);
		thread.start();
		thread.join();
	}

	public void updateOption(int CarCount, String make, String model, String opsetName, String optName,
			String newOptName, float newPrice) throws AutoException {
		for (int i = 0; i < AutomobileLinkedHashMap.garageObjects.get(CarCount).getOpset().size(); i++) {
			if (AutomobileLinkedHashMap.garageObjects.get(CarCount).getOpsetName(i).equals(opsetName)) {
				AutomobileLinkedHashMap.garageObjects.get(CarCount).updateOption(AutomobileLinkedHashMap.garageObjects.get(CarCount).getOpset(i), optName, newOptName,
						newPrice);
			}
		}
	}

	public void loadString(String txt) throws AutoException {
		a1.clearAll();
		FileIO buildObject = new FileIO();
		a1=buildObject.loadTxtData(txt);
		addAuto(a1);
	}
	public void BuildAuto(String filename) throws AutoException {
		a1.clearAll();
		FileIO buildObject = new FileIO();
		if (filename.contains(".txt")) {
			a1=buildObject.buildAutoObject(filename);
		}
		if (filename.contains(".prop")) {
			try {
				a1=buildObject.readPropData(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		addAuto(a1);
	}
	public void BuildAuto(Properties props) throws AutoException, IOException {
		FileIO IO = new FileIO();
		a1.clearAll();
		a1= IO.loadPropData(props);;
		addAuto(a1);
	}

	public void printAuto(Automobile auto) throws AutoException {
		if (a1.getModel() == "" || a1.getModel() == null) {
			throw new AutoException(5, "Model name is empty");
		} else {
			auto.print();
		}
	}

	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws AutoException {
		if (Modelname == a1.getModel()) {
			a1.updateOptionSet(OptionSetname, newName);
		}

	}

	public void updateOption(String Modelname, String opsetName, String optionName, String newoptName, float newprice)
			throws AutoException {
		for (int i = 0; i < a1.getOpset().size(); i++) {
			if (opsetName.equals(a1.getOpsetName(i))) {
				a1.updateOption(a1.getOpset(i), optionName, newoptName, newprice);
			}
		}
	}

	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice)
			throws AutoException {
		if (a1.getModel() == Modelname) {
			for (int i = 0; i < a1.getOpset().size(); i++) {
				if (a1.getOptionChoice(Option) == Option) {
					a1.updateOption(a1.getOpset(i), Option, Option, newprice);
				}
			}
		}
	}

	public String fix(AutoException e) {
		FixError fe = new FixError();
		switch (e.getErrno()) {
		case 1:
			return fe.fix1();
		case 2:
			return fe.fix2();
		case 3:
			return fe.fix3();
		case 4:
			return fe.fix4();
		case 5:
			return fe.fix5(e.getIndex());
		default:
			System.out.println("Your error has been logged.");
			return null;
		}
	}

	public void chooseOptions(Automobile data) {
		a1=data;
		if (a1.getOptionChoice().size() > 0) {
			a1.clearChoice();
		}
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < a1.getOpset().size(); i++) {
			boolean done = false;
			boolean error = false;
			while (!done) {

				String opsetName = a1.getOptionSetName(i);
				System.out.println("Enter choice for option set: " + opsetName);
				int num;
				for (int j = 0; j < a1.getOptions(i).size(); j++) {
					num = j + 1;
					System.out.println(num + ". " + a1.getOptionName(i, j) + " $" + a1.getOptionPrice(i, j));
					System.out.println();
				}
				int answer;
				System.out.println("Enter choice here: ");
				answer = in.nextInt();
				if (answer >= 1 && answer <= a1.getOptions(i).size()) {
					a1.setOptionChoice(opsetName, a1.getOptionName(i, answer - 1));
				} else {
					error = true;
					System.out.println("Invalid number choice, please try again");
					while (error == true) {
						answer = in.nextInt();
						if (answer >= 1 && answer <= a1.getOptions(i).size()) {
							a1.setOptionChoice(opsetName, a1.getOptionName(i, answer - 1));
							error = false;
						}
						System.out.println(a1.getOptionChoice(opsetName));
					}
					System.out.println();
				}
				done = true;
			}

		}
	}
	
	public void server(int port) {
		
	}
}
