package driver;
import util.FileIO;
import model.*;
import java.io.*;
import adapter.*;
import exception.*;


public class Driver {

	public static void main(String[] args) throws AutoException {

		/**
		 * Lab 1 Test - correct car info
		 */
		
		System.out.println("******************************Lab 1 Test - Run 1 (correct car info)*********************************");
		CreateAuto a1 = new BuildAuto();
		UpdateAuto a2 = new BuildAuto();
		a1.BuildAuto("FocusWagonZTW.txt");
		a1.printAuto("Focus Wagon ZTW");	
			
		//a2.updateOptionSetName("FocusWagonZTW.txt", "Color,Fort Knox Gold Clearcoat Metallic,0", "test name by Tom");
		a2.updateOptionSetName("FocusWagonZTW.txt", "Color,Fort Knox Gold Clearcoat Metallic,0", "test name by Tom");
		System.out.println("******************************With new option set update*********************************");
		a1.printAuto("FocusWagonZTW.txt");
	}

}

