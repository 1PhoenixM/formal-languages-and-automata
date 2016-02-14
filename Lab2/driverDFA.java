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

public class driverDFA {
  public static void main(String[] args){
    
    //Creates one string as input by creating a string with intact spaces
    String in = String.join(" ", args);
    
    //Creates the DFA
    ManWolf ManWolfDFA = new ManWolf();
    
    //Print the adequate message
    String out = ManWolfDFA.accepts(in) ? "That is a solution." : "That is not a solution.";
    System.out.println(out);
    
  }
}