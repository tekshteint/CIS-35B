package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import client.*;
import model.Automobile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigureAuto
 */
@WebServlet("/Configure")
public class ConfigureAuto extends HttpServlet implements ServletAuto {
	private static final long serialVersionUID = 1L;
    private Socket sock;  
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String strHost;
	private int iPort;
	private Object fromServer;
	private SelectCarOption clientProtocol;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigureAuto() {
    	this.strHost="127.0.0.1";
    	this.iPort=4443;
    	establishConnection();
        // TODO Auto-generated constructor stub
    }
    public void establishConnection() {
		try {
				System.out.println("Connecting to host ... ");
			this.sock = new Socket(this.strHost, this.iPort);

			
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			fromServer=in.readObject();
			clientProtocol = new SelectCarOption();

		
		}
		catch (IOException e) {
			System.out.println(e);
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			response.setContentType("text/plain");
			sendOutput(4);
			in.readObject();			
			sendOutput("");

			ArrayList<Automobile> autos = (ArrayList<Automobile>) (in.readObject());
			sendOutput("");
			in.readObject();
			//sendOutput(0);
			for (int i=0;i<autos.size();i++) {
				String index=String.valueOf(i+1);
				if (index.equals(request.getParameter("CarSelection"))) {
					request.setAttribute("data", autos.get(i));
					request.setAttribute("carChoice", index);
					RequestDispatcher rd = request.getRequestDispatcher("config.jsp");
					rd.forward(request, response);
					}
				}
			//sendOutput(0);
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
		//System.out.println("doPost! ConfigureAuto!");
		doGet(request,response);
		
		
	}

}
