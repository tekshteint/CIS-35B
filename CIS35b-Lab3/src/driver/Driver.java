package driver;
import util.FileIO;
import model.*;
import java.io.*;
import adapter.*;
import exception.*;


public class Driver {

	public static void main(String[] args) throws AutoException {
		AutomobileLinkedHashMap LHM = new AutomobileLinkedHashMap();
		
		BuildAuto test1=new BuildAuto();
		BuildAuto test2=new BuildAuto();
		BuildAuto test3=new BuildAuto();
		
		test1.BuildAuto("FocusWagonZTW.txt");
		test2.BuildAuto("FocusWagonZTWmod1.txt");
		test3.BuildAuto("FocusWagonZTWmod2.txt");		
		
		System.out.println("******************************Lab 3 Test - Run 1 (correct car info)*********************************");
		test1.chooseOptions();
		test1.printAuto("Focus Wagon ZTW");
		
		///*
		System.out.println("******************************Lab 3 Test - Run 2 (modified car info)*********************************");
		test2.chooseOptions();
		test2.printAuto("Focus Wagon ZTW");
		
		System.out.println("******************************Lab 3 Test - Run 3 (modified car info)*********************************");
		test3.chooseOptions();
		test3.printAuto("Focus Wagon ZTW");
		
		
		System.out.println();
		
		LHM.addNewCar("first", test1);
		LHM.addNewCar("second", test2);
		LHM.addNewCar("third", test3);
		LHM.print();
		//*/
		
	}

}

