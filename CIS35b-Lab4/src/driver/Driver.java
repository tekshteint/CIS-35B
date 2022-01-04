package driver;
import util.FileIO;
import model.*;
import java.io.*;
import adapter.*;
import exception.*;


public class Driver {

	public static void main(String[] args) throws AutoException, InterruptedException {
		AutomobileLinkedHashMap LHM = new AutomobileLinkedHashMap();
		BuildAuto test1=new BuildAuto();
		BuildAuto test2=new BuildAuto();
		test1.BuildAuto("FocusWagonZTW.txt");
		LHM.addNewCar("first", proxyAutomobile.getLHMauto(0));

		test2.BuildAuto("FocusWagonZTWmod1.txt");
		LHM.addNewCar("second", proxyAutomobile.getLHMauto(1));
		
		/*
		System.out.println("from driver, "+proxyAutomobile.getLHMauto(0).getModel()+" "+proxyAutomobile.getLHMauto(1).getModel());
		System.out.println(proxyAutomobile.getAutomobiles());
		System.out.println(proxyAutomobile.getLHMauto(1).getModel());
		*/
		System.out.print("-------------------------------------------------\n");
		test1.printAuto(LHM.getData("first"));
		//test2.BuildAuto("FocusWagonZTWmod1.txt");
		//LHM.addNewCar("second", proxyAutomobile.getLHMauto(1));
		System.out.print("-------------------------------------------------\n");
		test1.EditOption(LHM.getData("first"), "Color", "Winning Blue Metallic", "Fort Knox Gold Clearcoat Metallic", 100);
		test1.EditOption(LHM.getData("first"), "Transmission", "DCT", "manual", 3500);
		test1.EditOption(LHM.getData("first"), "Side Impact Air Bags", "Extra Curtain Airbags", "not present", 1000);
		test1.EditOption(LHM.getData("first"), "Power Moonroof", "manual", "not present", -100);
		test1.EditOption(LHM.getData("first"), "Brakes/Traction Control", "E46 M3 ABS Unit", "ABS", 2000);
		test1.EditOption(LHM.getData("first"), "Brakes/Traction Control", "Bosch Motorsport ABS Unit", "ABS with Advance Trac", 5000);
		System.out.print("-------------------------------------------------\n");
		test1.printAuto(LHM.getData("first"));
		System.out.print("-------------------------------------------------\n");
		test2.printAuto(LHM.getData("second"));
		test2.EditOption(LHM.getData("second"), "Color", "Alpine White III", "Fort Knox Gold Clearcoat Metallic", 100);
		test2.EditOption(LHM.getData("second"), "Transmission", "TH400", "manual", 3500);
		test2.EditOption(LHM.getData("second"), "Side Impact Air Bags", "No airbags", "not present", -500);
		test2.EditOption(LHM.getData("second"), "Power Moonroof", "Convertible", "not present", 1000);
		test2.EditOption(LHM.getData("second"), "Brakes/Traction Control", "Bosch Motorsport ABS Unit", "ABS with Advance Trac", 5000);
		System.out.print("-------------------------------------------------\n");
		test2.chooseOptions();
		test2.printAuto(LHM.getData("second"));
		
		
	}

}

