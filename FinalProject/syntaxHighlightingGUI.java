/*
 * file: syntaxHighlightingGUI.java
 * author: Melissa Iori
 * course: CMPT 440
 * assignment: Final Project
 * due date: May 3, 2016
 * version 1.0
 * 
 * This file contains the syntax highlighting GUI for our language.
 *
 */

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * syntaxHighlightingGUI
 * 
 * The purpose of this class is to run the Swing GUI window for the syntax highlighter.
 * It takes no arguments when invoked.
 */
public class syntaxHighlightingGUI {
	
  //The JEditorPane which the user will type text into.
  protected static JEditorPane jEdPane = new JEditorPane();
  
  //The DFA, which will decide whether a string is a valid program or not.
  protected static syntaxHighlightingDFA syntaxHighlightingDFA = new syntaxHighlightingDFA();
	 
  /*
   * main
   * 
   * This function sets up the Swing window and JEditorPane.
   *
   * Parameters: 
   *  args: the array of String arguments given by the user
   *
   * Return value: none
   */
  public static void main(String[] args){
	
	//Creates new GUI Window and sets its title.
	JFrame frame = new JFrame("Formal Languages Syntax Highlighter");
	
	//Sets default way to exit.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Sets size of the window.
    frame.setBounds(100, 100, 902, 554);

    //Code snippet help from: http://www.thecodingforums.com/threads/jtextfield-cant-listen-when-the-text-is-changed.136479/
    //This code sets up an Event Listener on the JEditorPane.
    //It waits for text to be changed, removed, or added to the JEditorPane by the user.
    //Each time one of these events occurs, the resulting string is re-evaluated by the DFA.
    jEdPane.getDocument().addDocumentListener(new DocumentListener() {
    	public void changedUpdate(DocumentEvent e) {
    		syntaxHighlight();
    	}
    	public void removeUpdate(DocumentEvent e) {
    		syntaxHighlight();
    	}
    	public void insertUpdate(DocumentEvent e) {
    		syntaxHighlight();
    	}
    });
    
    jEdPane.setBounds(100, 100, 902, 554);
    
    //Adds the JEditorPane to the window.
    frame.add(jEdPane);
    
    //Makes the window visible.
    frame.pack();
    frame.setVisible(true);
    
  }
  
  /**
   * syntaxHighlight
   * 
   * This function uses our DFA to evaluate the text currently in the JEditorPane. If a valid statement exists, the .accepts() function
   * returns its hex color. That color is set to the text color for the JEditorPane.
   * 
   */
  protected static void syntaxHighlight(){
	  Color c = Color.decode("0x" + syntaxHighlightingDFA.accepts(jEdPane.getText()));
	  jEdPane.setForeground(c);
  }
}
