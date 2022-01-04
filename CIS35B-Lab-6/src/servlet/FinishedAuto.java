package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.SelectCarOption;
import model.Automobile;

/**
 * Servlet implementation class FinishedAuto
 */
@WebServlet("/FinishedAuto")
public class FinishedAuto extends HttpServlet implements ServletAuto {
	private static final long serialVersionUID = 1L;
    private Socket sock;  
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String strHost;
	private int iPort;
	private Object fromServer;
	private SelectCarOption clientProtocol;
	private Automobile car;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishedAuto() {
    	this.strHost="127.0.0.1";
    	this.iPort=4443;
    	establishConnection();
    	this.car=null;
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
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	try {
				response.setContentType("text/plain");
				sendOutput(4);
				in.readObject();			
				sendOutput("");

				ArrayList<Automobile> autos = (ArrayList<Automobile>) (in.readObject());
				sendOutput("");
				in.readObject();
				for (int i=0;i<autos.size();i++) {
					String index=String.valueOf(i+1);
					if (index.equals(request.getParameter("carChoice"))) {
					
						for (int j=0;j<autos.get(i).getOpset().size();j++) {
						
							for (int k=0;k<request.getParameterMap().keySet().size()-1;k++) {
								String opsetNameCheck=autos.get(i).getOpsetName(j).replaceAll(" ", "");
								if (request.getParameterMap().keySet().contains(opsetNameCheck)) {
									String optionName = Arrays.toString(request.getParameterMap().get(opsetNameCheck)).trim().replaceAll("\\[", "").replaceAll("\\]", "");
									Automobile a1=autos.get(i);
									a1.setOptionChoice(a1.getOpsetName(j), optionName.strip());
									this.car=a1;
									//System.out.println(this.car.getOptionChoicePrice(this.car.getOpsetName(j)));
									break;
								}
							}
						} response.setContentType("text/plain");
						response.getWriter().println(autos.get(i).printSTR());
						request.setAttribute("data", this.car);
						RequestDispatcher rd = request.getRequestDispatcher("finished.jsp");
						rd.forward(request, response);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
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
		doGet(request, response);
	}

}
