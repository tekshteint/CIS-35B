

package server;

import java.net.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Properties;
import adapter.*;
import exception.AutoException;
import model.*;

public class DefaultSocketClient extends Thread  {

	////////// PROPERTIES //////////

	private boolean DEBUG = false;
	private static final int WAITING = 0;
    private static final int SENTFIRSTMENU = 1;
	private static final int REQUESTSET = 2;

    private int state = WAITING;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket clientSocket;
	private BuildCarModelOptions protocol;

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	////////// INSTANCE METHODS //////////

	public void handleConnection(Socket sock) {
		if (DEBUG)
			System.out.println("Creating server object streams ... ");
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		}
		catch (IOException e) {
			System.err.println("Error creating server object streams ... ");
			System.exit(1);
		}


		protocol = new BuildCarModelOptions();
		String menu = "\nEnter 1 to upload a new Automobile\n"
				+ "Enter 2 to configure an Automobile\n"
				+ "Enter 3 to list all Automobiles\n"
				+ "Enter 0 to terminate connection\n";

		try {
			do {
				
				if (state == WAITING) {
					if (DEBUG)
						System.out.println("Sending client interaction choices ... ");
					state = SENTFIRSTMENU;
					sendOutput(menu);
					
				}

				if (state == SENTFIRSTMENU) {
					int request;
					if (DEBUG)
						System.out.println("Reading client request ... ");
					try {
					request = Integer.parseInt(in.readObject().toString());
					}
					catch (Exception e){
						if (in.readObject().equals("")) {
							request=99;
							state=WAITING;
						}
						request = -1;
						state=WAITING;
						String response="Invalid menu choice. Press any key to continue.";		
						sendOutput(response);
					}
					if (DEBUG)
						System.out.println(request);
					if (request == 99)
						state=WAITING;
					if (request == 0)
						break;
					//tests for invalid menu choice from client
					if (request <0 || request > 4){
						state=WAITING;
						String response="Invalid menu choice. Press any key to continue";		
						sendOutput(response);
					}
					else { //Where the program actually does its work
						if (DEBUG) {
							System.out.println("Sending client request follow-up ... ");
							System.out.println(protocol.setRequest(request));
							}
						state=REQUESTSET;
						sendOutput(protocol.setRequest(request));
					}
				}
				if (state == REQUESTSET) {
					if (DEBUG)
						System.out.println("Sent request");
					int request = protocol.getRequest(); 
					if (request >= 1 && request <=4)
						handleInput(request);
					else
						state=WAITING;
				}
				
				
			} while (in.readObject() != null);

			if (DEBUG)
				System.out.println("Closing server input stream for client " + sock.getInetAddress() + " ... ");
			in.close();
		}
		catch (IOException e) {
			System.err.println("Error handling client connection ... " + e.getMessage()+" handleConnection "+e);
			System.exit(1);
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, object may be corrupted ... ");
			System.exit(1);
		}
	}

	public void sendOutput(Object obj) {
		try {
			if (DEBUG)
				System.out.println("Server side defaultsocketclient sendOutput method");
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error returning output to client ...  "+ e.getMessage()+" sendOutput");
			System.exit(1);
		}
	}

	public void handleInput(int request) {
		Object toClient = null;
		try {
			switch (request) {
				case 1: //Client request to build Automobile
					if (DEBUG)
						System.out.println("Waiting for client to upload file ... ");
					try {
						Object fromClient = in.readObject(); //MazdaMiata.prop
						toClient = (String)protocol.processRequest(fromClient);
						
						if (DEBUG) {
							System.out.println("Adding new Automobile to database ... ");
						}
					}
					catch (ClassCastException e) {
						toClient ="There was a problem with your upload. Please try again.  Press any key to continue ... "+ e;
			
						} catch (AutoException e) {
						e.printStackTrace();
					}
					finally {
						state=WAITING;
						sendOutput(toClient);
					}
					break;

				case 2: //Client request to configure Automobile
					if (DEBUG)
						System.out.println("Waiting for client to select Automobile ... ");
					String fromClientInt = in.readObject().toString();
					if (DEBUG)
						System.out.println("Sending Automobile object to client ... ");
					toClient = (Automobile)protocol.processRequest(fromClientInt); //buildcarmodeloptions
					state=WAITING;
					if (toClient ==null) {
						toClient="Not able to find your vehicle. Please press any key to continue.";
					}
					sendOutput(toClient);
					if (DEBUG)
						System.out.println("DefaultSocketClient HandleInput case 2 sent to client");
					break;
				
				case 3: //Client request to list all Automobile objects
					Object fromClientPrint = in.readObject().toString();
					if (DEBUG)
						System.out.println("Case 3 fromClientPrint: "+fromClientPrint);
					toClient="";
					if (fromClientPrint.equals("")) {
						for (int i=1;i<=protocol.listAutos().size();i++) {
							if (i==1)
								toClient+=protocol.listAutos().get(i);
							if (i==protocol.listAutos().size()&& i!=1)
								toClient+=", "+protocol.listAutos().get(i);
							if (i>1 && i<protocol.listAutos().size())
								toClient+=", "+protocol.listAutos().get(i);
						}
					if (DEBUG)
						System.out.println(protocol.listAutos()+" case 3 to Client");
					state=WAITING;
					sendOutput(toClient);
					}else{
						toClient="Please only press Enter.";
						sendOutput(toClient);
					}break;
				case 4: //web request to print all autos
					Object fromWebPrintout = in.readObject().toString();
					if (DEBUG)
						System.out.println("Case 101 fromWebPrintout: "+fromWebPrintout);
					toClient="";
					if (fromWebPrintout.equals("")) {
						toClient=protocol.getAllAutos();
					if (DEBUG)
						System.out.println(protocol.printAllAutos());
					state=WAITING;
					sendOutput(toClient);
					}else{
						toClient="Please only press Enter.";
						sendOutput(toClient);
					}break;
				default: //Invalid requests
					state=WAITING;
					toClient="Invalid . Please press any key to continue.";
					sendOutput(toClient);
					break;
					
			}
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, file may be corrupted ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error in retrieving object from client ... ");
			System.exit(1);
		}
	}
	

	@Override
	public void run() {
		handleConnection(clientSocket);

		//Close client output stream
		if (DEBUG)
			System.out.println("Closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		try {
			out.close();
		}
		catch (IOException e) {
			System.err.println("Error closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		}

		//Close client socket
		if (DEBUG)
			System.out.println("Closing client socket " + clientSocket.getInetAddress() + " ... ");
		try {
			clientSocket.close();
		}
		catch (IOException e) {
			System.err.println("Error closing client socket " + clientSocket.getInetAddress() + " ... ");
		}
	}

}
