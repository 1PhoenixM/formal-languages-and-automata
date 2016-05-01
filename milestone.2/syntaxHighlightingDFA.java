/*
 * file: syntaxHighlightingDFA.java
 * author: Melissa Iori
 * course: CMPT 440
 * assignment: Final Project
 * due date: May 3, 2016
 * version 1.0
 * 
 * This file contains the syntaxHighlightingDFA definition class.
 *
 */

/* 
 * syntaxHighlighting
 * 
 * This class implements a deterministic finite automaton for a simple programming language.
 */
//HashMap is used to link characters to matrix position
import java.util.HashMap;

//syntaxHighlightingDFA class - Creates and tests strings for a simple programming language.
public class syntaxHighlightingDFA {
 
  //Colors - Each color is used to highlight a Statement in our language.
  //They are in hex color format, RRGGBB (red-green-blue values from 00 to FF)
  private String _commentColor = "40E0D0";
  private String _vardeclColor = "FF4500";
  private String _printColor = "00FA20";
  private String _intassignColor = "FF00FF"; 
  private String _stringassignColor = "FFFF00";
  private String _varassignColor = "FFD700"; 
  
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
  private static final int q10 = 10;
  
  private static final int q11 = 11;
  private static final int q12 = 12;
  private static final int q13 = 13;
  private static final int q14 = 14;
  private static final int q15 = 15;
  private static final int q16 = 16;
  private static final int q17 = 17;
  private static final int q18 = 18;
  private static final int q19 = 19;
  private static final int q20 = 20;
  private static final int q21 = 21;
  
  private static final int q22 = 22;
  private static final int q23 = 23;
  private static final int q24 = 24;
  private static final int q25 = 25;
  private static final int q26 = 26;
  private static final int q27 = 27;
  private static final int q28 = 28;
  private static final int q29 = 29;
  private static final int q30 = 30;
  private static final int q31 = 31;
  private static final int q32 = 32;
  
  private static final int q33 = 33;
  private static final int q34 = 34;
  private static final int q35 = 35;
  private static final int q36 = 36;
  private static final int q37 = 37;
  private static final int q38 = 38;
  private static final int q39 = 39;
  private static final int q40 = 40;
  private static final int q41 = 41;
  private static final int q42 = 42;
  private static final int e = 43; //error state

  //Maps characters in the alphabet onto matrix positions
  private HashMap<Character,Integer> charLookup = new HashMap<Character,Integer>();
 
  //Transition state matrix (DFA)
  //a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9,<space>,+,*,-,"""",(,),=
  private int[][] delta = {{q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q8,q26,q26,q26,q26,q26,q38,q26,q26,q26,q26,e,e,e,e,e,e,e,e,e,e,e,e,q1,e,e,e,e,e}, //q0
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q2,e,e,e,e}, //q1
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q3,e,e,e,e,e,e,e}, //q2
		  {q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,e,e,e,e,e,e,e,e,e,e,q4,e,e,e,e,e,e,e}, //q3
		  {q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,e,e,e,e,e,e,e,e,e,e,q5,e,e,e,e,e,e,e}, //q4
		  {q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,q4,e,e,e,e,e,e,e,e,e,e,q4,e,e,q6,e,e,e,e}, //q5
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q7,e,e,e,e,e}, //q6
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q0,e,e,e,e,e,e,e}, //q7
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q9,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q27,e,e,e,e,e,e,e}, //q8
		  {e,e,e,e,e,e,e,e,q10,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e}, //q9
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,q11,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e}, //q10
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q12,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e}, //q11
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q13,e,e,e,e,e,e,e}, //q12
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q14,e,e}, //q13
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q15,e,e,e,e,e,e,e}, //q14
		  {q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q24,q16,q16,q16,q16,q16,q16,q16,q16,q16,q16,e,e,e,e,q19,e,e,e}, //q15
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q17,e,e,e,e,e,e,e}, //q16
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q18,e,e,e,e,q25,e}, //q17
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q15,e,e,e,e,e,e,e}, //q18
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q20,e,e,e,e,e,e,e}, //q19
		  {q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,e,e,e,e,e,e,e,e,e,e,q21,e,e,e,e,e,e,e}, //q20
		  {q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,q20,e,e,e,e,e,e,e,e,e,e,q20,e,e,e,q22,e,e,e}, //q21
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q23,e,e,e,e,e,e,e}, //q22
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q25,e}, //q23
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q23,e,e,e,e,e,e,e}, //q24
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q0,e,e,e,e,e,e,e}, //q25
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q27,e,e,e,e,e,e,e}, //q26
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q28}, //q27
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q29,e,e,e,e,e,e,e}, //q28
		  {q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q36,q30,q30,q30,q30,q30,q30,q30,q30,q30,q30,e,e,e,e,q33,e,e,e}, //q29
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q31,e,e,e,e,e,e,e}, //q30
		  {q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q26,q8,q26,q26,q26,q26,q26,q38,q26,q26,q26,q26,e,e,e,e,e,e,e,e,e,e,e,q32,q1,e,e,e,e,e}, //q31 - continues + int exp, or another statement
 		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q29,e,e,e,e,e,e,e}, //q32
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q34,e,e,e,e,e,e,e}, //q33
		  {q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,e,e,e,e,e,e,e,e,e,e,q35,e,e,e,e,e,e,e}, //q34
		  {q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,q34,e,e,e,e,e,e,e,e,e,e,q34,e,e,e,q37,e,e,e}, //q35
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q0,e,e,e,e,e,e,e}, //q36
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q0,e,e,e,e,e,e,e}, //q37
		  {q39,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q27,e,e,e,e,e,e,e}, //q38
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q40,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e}, //q39
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q41,e,e,e,e,e,e,e}, //q40
		  {q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,q42,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e}, //q41
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,q0,e,e,e,e,e,e,e}, //q42
		  {e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e,e}}; //e
  
   /*
    * syntaxHighlightingDFA
    *
    * Initializes an instance of the syntaxHighlighting DFA. Populates mapping of characters in the alphabet to matrix positions.
    *
    * Parameters: none
    * 
    * Return value: a DFA object, with type syntaxHighlightingDFA.
    *
    */
   public syntaxHighlightingDFA(){
     charLookup.put((Character)('a'), (Integer)(0));
     charLookup.put((Character)('b'), (Integer)(1));
     charLookup.put((Character)('c'), (Integer)(2));
     charLookup.put((Character)('d'), (Integer)(3));
     charLookup.put((Character)('e'), (Integer)(4));
     charLookup.put((Character)('f'), (Integer)(5));
     charLookup.put((Character)('g'), (Integer)(6));
     charLookup.put((Character)('h'), (Integer)(7));
     charLookup.put((Character)('i'), (Integer)(8));
     charLookup.put((Character)('j'), (Integer)(9));
     charLookup.put((Character)('k'), (Integer)(10));
     charLookup.put((Character)('l'), (Integer)(11));
     charLookup.put((Character)('m'), (Integer)(12));
     charLookup.put((Character)('n'), (Integer)(13));
     charLookup.put((Character)('o'), (Integer)(14));
     charLookup.put((Character)('p'), (Integer)(15));
     charLookup.put((Character)('q'), (Integer)(16));
     charLookup.put((Character)('r'), (Integer)(17));
     charLookup.put((Character)('s'), (Integer)(18));
     charLookup.put((Character)('t'), (Integer)(19));
     charLookup.put((Character)('u'), (Integer)(20));
     charLookup.put((Character)('v'), (Integer)(21));
     charLookup.put((Character)('w'), (Integer)(22));
     charLookup.put((Character)('x'), (Integer)(23));
     charLookup.put((Character)('y'), (Integer)(24));
     charLookup.put((Character)('z'), (Integer)(25));
     charLookup.put((Character)('0'), (Integer)(26));
     charLookup.put((Character)('1'), (Integer)(27));
     charLookup.put((Character)('2'), (Integer)(28));
     charLookup.put((Character)('3'), (Integer)(29));
     charLookup.put((Character)('4'), (Integer)(30));
     charLookup.put((Character)('5'), (Integer)(31));
     charLookup.put((Character)('6'), (Integer)(32));
     charLookup.put((Character)('7'), (Integer)(33));
     charLookup.put((Character)('8'), (Integer)(34));
     charLookup.put((Character)('9'), (Integer)(35));
     charLookup.put((Character)(' '), (Integer)(36));
     charLookup.put((Character)('+'), (Integer)(37));
     charLookup.put((Character)('*'), (Integer)(38));
     charLookup.put((Character)('-'), (Integer)(39));
     charLookup.put((Character)('"'), (Integer)(40));
     charLookup.put((Character)('('), (Integer)(41));
     charLookup.put((Character)(')'), (Integer)(42));
     charLookup.put((Character)('='), (Integer)(43));
   }
   
   /*
    * setColors
    * 
    * Called by the GUI when the user loads a color file.
    * Sets the syntax highlighting colors.
    * 
    * Parameters: commentColor : The color for comments.
    * vardeclColor : The color for variable declarations.
    * printColor : The color for print statements.
    * intassignColor : The color for integer assignments.
    * stringassignColor : The color for string assignments.
    * varassignColor : The color for variable assignments.
    * 
    * Return value: None
    */
   public void setColors(String commentColor, String vardeclColor, String printColor, String intassignColor, String stringassignColor, String varassignColor){
	   	   _commentColor = commentColor;
	   	   _vardeclColor = vardeclColor;
	   	   _printColor = printColor;
	   	   _intassignColor = intassignColor;
	   	   _stringassignColor = stringassignColor;
	   	   _varassignColor = varassignColor;
   }
  
   /*
    * accepts
    * 
    * Tests an input String to see if it is accepted by the syntaxHighlighting DFA transition table.
    * q0 is the start state. For each character in the string, the state moves ahead via the delta transition
    * for that state and character. When an accepting state is found, the hex color for that valid Statement is added
    * to the HashMap, with a key as the position in the input string where that Statement ends. For the whole program,
    * the DFA analyzes it and returns the HashMap containing string indices and the colors mapped to them.
    * 
    * Parameters: 
    *   s: a String of input characters, assumed to be in the alphabet. if a character is not in the alphabet, the String will be rejected.
    * 
    * Return value: A HashMap<Integer,String> which uses positions in the input string as keys, mapped to hex color values for the Statement accepted.
    */
   public HashMap<Integer,String> accepts(String s){
	 
	 s = s.replaceAll("&nbsp;", " ");
	 //s = s.replaceAll(" +", " ");
	 //s = s.replaceAll("\n", " ");
	 //System.out.println("DFA sees: " + s);
	 
	 //Defines the HashMap
	 HashMap<Integer,String> syntaxHighlighting = new HashMap<Integer,String>();
	 //The start state is q0
     int currentState = q0;
     
     //For each character in the input...
     for(int i = 0; i < s.length(); i++){
     try{
       //Move to a new state based on the current state and character.
       currentState = this.delta[currentState][charLookup.get(s.charAt(i))];
       if(currentState == q7){
    	   //Comment found!
    	   syntaxHighlighting.put(i, _commentColor);
       }
       else if(currentState == q25){
    	   //Print statement found!
    	   syntaxHighlighting.put(i, _printColor);
       }
       else if(currentState == q30){
    	   //Integer assignment found!
    	   syntaxHighlighting.put(i, _intassignColor);
       }
       else if(currentState == q36){
    	   //Variable assignment found!
    	   syntaxHighlighting.put(i, _varassignColor);
       }
       else if(currentState == q37){
    	   //String assignment found!
    	   syntaxHighlighting.put(i, _stringassignColor);
	   }
       else if(currentState == q42){
    	   //Variable assignment found!
    	   syntaxHighlighting.put(i, _vardeclColor);
	   }
     }
     catch(NullPointerException e){
    	 //An unrecognized character was found
     }
    }
     //Return the HashMap
     return syntaxHighlighting;
    }
}
