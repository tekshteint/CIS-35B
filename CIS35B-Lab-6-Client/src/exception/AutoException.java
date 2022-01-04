package exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import model.Automobile;

public class AutoException extends Exception {
	private int errno;
	private String errormsg;
	private int index;
	private Automobile a;
	
	public void WriteLog(String errormsg, int errno){
		try {
			File errLog = new File("ErrorLog.txt");			
			FileWriter fw = new FileWriter(errLog,true);
			String timestamp = new Timestamp(new java.util.Date().getTime()).toString();
			String writer = timestamp +" " +errormsg + " errno: "+errno;
			fw.write("\n");
			fw.write(writer);
			fw.close();
		} catch (IOException e) {
			System.out.println("An unknown error Occured");
		}
	}
	
	
	public AutoException() {
		super();
		printmyproblem();
	}
	
	public AutoException(String errormsg) {
		super(errormsg);
		this.errormsg = errormsg;
		printmyproblem();
	}
	
	public AutoException(int errno) {
		super();
		this.errno = errno;
		printmyproblem();
	}
	
	public AutoException(int errno, String errormsg) {
		super();
		this.errno = errno;
		this.errormsg = errormsg;
		printmyproblem();
	}
	public AutoException(int errno, String errormsg, int index) {
		super();
		this.errno = errno;
		this.errormsg = errormsg;
		this.index = index;
		printmyproblem();
	}
	public AutoException(int errno, String errormsg, int index,Automobile a) {
		super();
		this.errno = errno;
		this.errormsg = errormsg;
		this.index = index;
		this.a=a;
		printmyproblem();
	}
	public int getErrno() {
		return errno;
	}

	public void setErrno(int errorno) {
		this.errno = errorno;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public Automobile getAuto() {
		return a;
	}

	public void setAuto(Automobile a) {
		this.a = a;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public void printmyproblem() {
		System.out.println("AutoException: [errno=" + errno + ", errormsg=" + errormsg); 
		WriteLog(errormsg,errno);
	}
	public String fix(int errno) {
		FixError fe = new FixError();
		switch (errno) {
			case 1: return fe.fix1();
			case 2: return fe.fix2();
			case 3: return fe.fix3();
			case 4: return fe.fix4();
			case 5: return fe.fix5(index);
			default:
				System.out.println("Your error has been logged.");
				return null;
		}
	}
	
	
}
