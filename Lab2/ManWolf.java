//Melissa Iori

//HashMap is used to link characters to matrix position
import java.util.HashMap;

//ManWolf class - Creates and tests strings for the ManWolf problem.
public class ManWolf {
 
  //States
  private static final int q0 = 0;
  private static final int q1 = 1;
  private static final int q2 = 2;
  private static final int q3 = 3;
  private static final int q4 = 4;
  private static final int q5 = 5;
  private static final int q6 = 6;
  private static final int q7 = 7;
  private static final int q8 = 8;
  private static final int q9 = 9;
  private static final int e = 10;

  //Maps characters in the alphabet onto matrix positions
  private HashMap<Character,Integer> charLookup = new HashMap<Character,Integer>();
 
  //Transition state matrix
                          //c  g  n  w
  private int[][] delta = {{e, q1, e, e},   //q0
                           {e, q0, q2, e},  //q1
                           {q5, e, q1, q3}, //q2
                           {q2, q4, e, e},  //q3
                           {q7, q2, e, e},  //q4
                           {q2, q6, e, e},  //q5
                           {e, q5, e, q7},  //q6
                           {q4, e, q8, q6}, //q7
                           {e, q9, q7, e},  //q8
                           {e, q8, e, e},   //q9
                           {e, e, e, e}};    //e
	
    //Initializes the DFA
	public ManWolf(){
		 charLookup.put((Character)('c'), (Integer)(0));
		 charLookup.put((Character)('g'), (Integer)(1));
		 charLookup.put((Character)('n'), (Integer)(2));
		 charLookup.put((Character)('w'), (Integer)(3));
	}
	
	//Method to test whether a string is accepted by the DFA
	public boolean accepts(String s){
		int currentState = q0;
		for(int i = 0; i < s.length(); i++){
			try{
				currentState = this.delta[currentState][charLookup.get(s.charAt(i))];
			}
			catch(NullPointerException e){
				return false;
			}
		}
		return currentState == q9;
	}
}