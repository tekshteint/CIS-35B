package servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.CarModelOptionsIO;
import client.SelectCarOption;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/ViewCars")
public class ViewCars extends HttpServlet implements ServletAuto {
	private static final long serialVersionUID = 1L;
    private Socket sock;  
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String strHost;
	private int iPort;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCars() {
    	this.strHost="127.0.0.1";
    	this.iPort=4443;
    	establishConnection();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: \n").append(request.getContextPath());
		try {
			
			sendOutput(3);
			in.readObject().toString();
			sendOutput("");
			response.setContentType("text/html");
			response.getWriter().println("<body>"
					+ "<h1> All Automobiles uploaded to the server</h1> </body>");
			System.out.println(in.readObject().toString());
			response.setContentType("text/plain");
			response.getWriter().println(in.readObject().toString());
			response.setContentType("text/html");
			response.getWriter().println("<form action =\"/lab6\">"
					+"<input type=\"submit\" value = \"Return Home\" >"
					+ "</form>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		sendOutput(0);
		out.reset();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("doPost");
		
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
