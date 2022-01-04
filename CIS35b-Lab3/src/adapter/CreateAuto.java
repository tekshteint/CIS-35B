package adapter;

import exception.AutoException;

public interface CreateAuto {
	public void BuildAuto(String filename) throws AutoException;
	public void printAuto(String Modelname)throws AutoException;
}
