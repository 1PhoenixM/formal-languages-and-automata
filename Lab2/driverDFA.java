/*
 * file: driverDFA.java
 * author: Melissa Iori
 * course: CMPT 440
 * assignment: Lab 2
 * due date: February 15, 2016
 * version 1.0
 * 
 * This file contains the DFA driver, which interfaces with the user.
 *
 */

/*
 * driverDFA
 * 
 * The purpose of this class is to provide an interface to the user for testing the ManWolf DFA.
 * An example invocation from the command line:
 *
 * > java driverDFA gncwcng
 *
 * The invocation will tell whether the input, gncwcng, is a solution to the ManWolf problem or not.
 */
public class driverDFA {

  /*
   * main
   * 
   * This function takes input from the user via the command line, creates an instance of the ManWolf DFA, and
   * tests the input string against the DFA. Based on whether the input is accepted or not, a message will be 
   * output to the user.
   *
   * Parameters: 
   *  args: the array of String arguments given by the user
   *
   * Return value: none
   */
  public static void main(String[] args){
    
    //Creates one string as input by creating a string with intact spaces
    //String in = String.join(" ", args);  //this line leads to erros on older versions of java
    String in = args[0];
    
    //Creates the DFA
    ManWolf ManWolfDFA = new ManWolf();
    
    //Print the adequate message
    String out = ManWolfDFA.accepts(in) ? "That is a solution." : "That is not a solution.";
    System.out.println(out);
    
  }
}
