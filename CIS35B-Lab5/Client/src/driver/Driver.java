package driver;
import util.FileIO;
import model.*;
import java.io.*;
import adapter.*;
import client.DefaultSocketClient;
import exception.*;


public class Driver {

	public static void main(String[] args) throws AutoException, InterruptedException {
		DefaultSocketClient dsc1 = new DefaultSocketClient("127.0.0.1" ,4443);
		dsc1.run();

		
	}

}

