package adapter;
import exception.*;
import model.Automobile;
public interface ChooseAuto {
	public abstract void chooseOptions(Automobile data) throws AutoException;
	

}