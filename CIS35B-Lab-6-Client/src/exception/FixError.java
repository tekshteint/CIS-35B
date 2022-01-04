package exception;

public class FixError {
	private int errorno;
	
	public FixError() {	
	}
	
	public FixError(int errorno) {
		this.errorno=errorno;
	}
	
	public String fix1() {
		System.out.println("Model name corrected.");
		return "Focus Wagon ZTW";
	}
	
	public String fix2() {
		System.out.println("Make corrected.");
		return "Ford";
	}
	
	public String fix3() {
		System.out.println("Base Price corrected.");
		return String.valueOf(18455);
	}
	
	public String fix4() {
		System.out.println("Number of sets of options corrected.");
		return String.valueOf(5);
	}
	
	public String fix5(int i) {
		System.out.println("Option set name corrected.");
		String [] names = {"Color","Transmission","Brakes/Traction Control","Side Impact Air Bags","Power Moonroof"};
		return names[i];
	}
	
}
