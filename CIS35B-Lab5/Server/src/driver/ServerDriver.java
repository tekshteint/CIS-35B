package driver;

import util.FileIO;
import model.*;
import server.DefaultServerSocket;

import java.io.*;
import adapter.*;
import exception.*;

public class ServerDriver {

	public static void main(String[] args) throws AutoException, InterruptedException {
		DefaultServerSocket dss1= new DefaultServerSocket(4443);
		dss1.run();
	}

}
