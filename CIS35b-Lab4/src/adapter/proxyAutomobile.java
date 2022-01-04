package adapter;
import java.util.*;
import model.*;
import scale.*;
import util.FileIO;
import exception.*;


public abstract class proxyAutomobile {
	private static LinkedHashMap<Integer,Automobile> automobiles = new LinkedHashMap<Integer,Automobile>();
	private Automobile a1;
	private String Filename;
	private static int CarCount=0;
	
	public proxyAutomobile(String filename){
		a1 = new Automobile();
		this.Filename=filename;
		
	}
	public proxyAutomobile(){
		a1=new Automobile();
		this.Filename=null;
	}
	public Automobile getAuto() {
		return a1;
	}
	public static LinkedHashMap<Integer,Automobile> getAutomobiles(){
		return automobiles;
	}
	public static void setAutomobiles(LinkedHashMap<Integer,Automobile> automobiles) {
		proxyAutomobile.automobiles=automobiles;
	}
	public static Automobile getLHMauto(int carNum) {
		if (automobiles.containsKey(carNum)) {
			return automobiles.get(carNum);
		}else {
			return null;
		}
	}
	public synchronized void EditOption(Automobile a1,String opsetName,String newOptName,String optName,float newPrice) throws InterruptedException {
		EditOptions edit = new EditOptions(a1,opsetName,newOptName,optName,newPrice);
		Thread thread = new Thread(edit);
		//thread.setDaemon(true);
		thread.start();
		thread.join();
	}
	public void updateOption(int CarCount, String make, String model, String opsetName,String optName, String newOptName, float newPrice) {
		for (int i=0;i<automobiles.get(CarCount).getOpset().size();i++) {
			if (automobiles.get(CarCount).getOpsetName(i).equals(opsetName)) {
				automobiles.get(CarCount).updateOption(automobiles.get(CarCount).getOpset(i), optName, newOptName, newPrice);
			}
		}
	}
	public void BuildAuto(String filename) throws AutoException {
		a1.clearAll();
		FileIO buildObject=new FileIO(filename,a1);
		buildObject.buildAutoObject(a1);
		automobiles.put(CarCount, a1);
		CarCount++;
	}
	public void printAuto(Automobile auto) throws AutoException {
		if (a1.getModel()=="" || a1.getModel()==null) {
			throw new AutoException(5,"Model name is empty");
		} else {
			auto.print();}
	}
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws AutoException {
		if (Modelname==a1.getModel()) {
			a1.updateOptionSet(OptionSetname, newName);
		}
		
	}
	public void updateOption(String Modelname, String opsetName, String optionName,String newoptName, float newprice) {
		for (int i=0;i<a1.getOpset().size();i++) {
			if (opsetName.equals(a1.getOpsetName(i))) {
				a1.updateOption(a1.getOpset(i), optionName, newoptName, newprice);
			}
		}
	}
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) throws AutoException {
		if (a1.getModel()==Modelname) {
			for (int i=0;i<a1.getOpset().size();i++) {
				if (a1.getOptionChoice(Option)==Option) {
					a1.updateOption(a1.getOpset(i), Option, Option, newprice);
				}
			}
		}
	}
	public String fix(AutoException e) {
		FixError fe = new FixError();
		switch (e.getErrno()) {
			case 1: return fe.fix1();
			case 2: return fe.fix2();
			case 3: return fe.fix3();
			case 4: return fe.fix4();
			case 5: return fe.fix5(e.getIndex());
			default:
				System.out.println("Your error has been logged.");
				return null;
		}
	}

	public void chooseOptions() {
		if (a1.getOptionChoice().size()>0) {
			a1.clearChoice();	
		}
		Scanner in = new Scanner(System.in);
		for (int i=0; i< a1.getOpset().size();i++) {
			boolean done = false;
			boolean error = false;
			while (!done) {

				String opsetName=a1.getOptionSetName(i);
				System.out.println("Enter choice for option set: "+opsetName);
				int num;
				for (int j=0;j<a1.getOptions(i).size();j++) {
					num=j+1;
					System.out.println(num+ ". "+a1.getOptionName(i, j)+" $"+a1.getOptionPrice(i, j));
					System.out.println();
				}
				int answer;
				System.out.println("Enter choice here: ");
				answer=in.nextInt();
				if (answer>=1 && answer<=a1.getOptions(i).size()) {
					a1.setOptionChoice(opsetName, a1.getOptionName(i, answer-1));
				} else {
					error=true;
					System.out.println("Invalid number choice, please try again");
					while (error==true) {
						answer=in.nextInt();
						if (answer>=1 && answer<=a1.getOptions(i).size()) {
							a1.setOptionChoice(opsetName, a1.getOptionName(i, answer-1));
							error=false;
																			}
				System.out.println(a1.getOptionChoice(opsetName));
		}
		System.out.println();
			} done=true;
		}
	
		}
	}
}




