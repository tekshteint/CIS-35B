package server;

import model.Automobile;
import java.util.LinkedHashMap;
import exception.*;

public interface AutoServer {
	public void server(int port);
	public void addAuto(Automobile auto) throws AutoException;
	public abstract LinkedHashMap <Integer,String> listAutos();
	public abstract Automobile getAuto(int key);
}
