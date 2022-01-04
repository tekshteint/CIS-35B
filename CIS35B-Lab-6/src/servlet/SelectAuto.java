package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adapter.BuildAuto;
import client.DefaultSocketClient; 


/**
 * Servlet implementation class ConfigureModel
 */
@WebServlet("/SelectAuto")
public class SelectAuto extends HttpServlet implements ServletAuto {
	private static final long serialVersionUID = 1L;
    private Socket sock;  
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String strHost;
	private int iPort;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAuto() {
    	this.strHost="127.0.0.1";
    	this.iPort=4443;
    	establishConnection();
        // TODO Auto-generated constructor stub
    }
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			response.setContentType("text/plain");
			sendOutput(4);
			in.readObject();
			sendOutput("");

			System.out.println(in.readObject().toString());

			ArrayList<Automobile> autos = (ArrayList<Automobile>) (in.readObject());
			request.setAttribute("data", autos);
			RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
			rd.forward(request, response);
			sendOutput(0);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}out.reset();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
	public void establishConnection() {
		try {
				System.out.println("Connecting to host ... ");
			this.sock = new Socket(this.strHost, this.iPort);

			
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());

		
		}
		catch (IOException e) {
			System.out.println(e);
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
	}
	
	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			System.exit(1);
		}
	}
}
