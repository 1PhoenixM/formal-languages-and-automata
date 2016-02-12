//Melissa Iori
public class driverDFA {
	public static void main(String[] args){
		
		//Creates one string as input by creating a string with intact spaces
		String in = String.join(" ", args);
		
		//Creates the DFA
		ManWolf ManWolfDFA = new ManWolf();
		
		//Print the adequate message
		String out = ManWolfDFA.accepts(in) ? "This is a solution." : "This is not a solution.";
		System.out.println(out);
		
	}
}