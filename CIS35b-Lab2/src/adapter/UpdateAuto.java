package adapter;

import exception.AutoException;

public interface UpdateAuto{
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws AutoException;
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) throws AutoException;
}