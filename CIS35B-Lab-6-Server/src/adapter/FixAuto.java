package adapter;

import exception.AutoException;

public interface FixAuto {
	public abstract String fix(AutoException e) throws AutoException;
}
