package scale;

import model.Automobile;

public interface EditThread {
	public void EditOption(Automobile a1, String opsetName, String newOptName, String optName, float newPrice)
			throws InterruptedException;
}
