package adapter;

import exception.AutoException;
import model.Automobile;

public interface CreateAuto {
	public void BuildAuto(String filename) throws AutoException;
	public void printAuto(Automobile auto)throws AutoException;
}
