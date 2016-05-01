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
import java.io.BufferedReader; //To read files when loading them
import java.io.File; //To hold a File object
import java.io.FileReader; //To read files when loading them
import java.io.IOException; //Exceptions can occur when reading files
import java.io.PrintWriter; //To write to files when saving them
import java.util.Arrays; //Array functions
import java.util.Collections; //Collections.sort
import java.util.HashMap; //HashMaps
import java.util.List; //Lists
import java.util.regex.Matcher; //Matching regular expressions
import java.util.regex.Pattern; //Regular expressions


//JavaFX
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
/*
 * syntaxHighlightingGUI
 * 
 * The purpose of this class is to run the JavaFX window for the syntax highlighter.
 * It is a subclass of the JavaFX Application class.
 * It takes no arguments when invoked.
 */
public class syntaxHighlightingGUI extends Application {
 
	//The DFA, which will decide whether a string is a valid program or not.
	protected static syntaxHighlightingDFA syntaxHighlightingDFA = new syntaxHighlightingDFA();
	
	//The HTMLEditor, an interactive text editor
	final static HTMLEditor htmlEditor = new HTMLEditor();
	
	//The JavaFX scene, with a Group for MenuBar + HTMLEditor
	static Scene scene = new Scene(new Group()); 
	
	//Flag for whether statements will be split over lines
	static boolean lineMode = true;
	
    //References used: https://docs.oracle.com/javafx/2/ui_controls/editor.htm - For HTMLEditor
    //http://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm - For MenuBar
	//http://stackoverflow.com/questions/22128153/javafx-htmleditor-text-change-listener - For event listener
	//http://stackoverflow.com/questions/23735815/how-get-only-text-from-javafx2-htmleditor - For regex help
	//http://javajdk.net/tutorial/multiple-javafx-scenes-sharing-one-menubar/ - For getting MenuBar and HTMLEditor both in the window
	//http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm - For the File Chooser
	//http://stackoverflow.com/questions/10685395/webview-with-contenteditable-cannot-be-focused-programmatically - For focusing on the editor
	//http://stackoverflow.com/questions/28106194/positioning-caret-in-javafx-htmleditor - For a caret position fix
	
	/*
	 * main
	 * 
	 * This function sets up the JavaFX window.
	 *
	 * Parameters: 
	 *  args: the array of String arguments given by the user
	 *
	 * Return value: none
	 */
    public static void main(String[] args) {
        launch(args);
    }
 
    /*
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     * 
     * This overrides a JavaFX method, which is necessary to start the application by setting up a "scene".
     * 
     * Parameters: stage : A Stage object
     * 
     * Return value: None
     */
    @Override
    public void start(Stage stage) {
    	
    	//Set a title for the window
        stage.setTitle("Formal Languages Syntax Highlighter");
        
        //Set up a VBox in the window to hold everything and set it as the root
        VBox rootbox = new VBox();    
        scene.setRoot(new VBox());
        
        //Set height, width of the window
        htmlEditor.setPrefHeight(400);
        htmlEditor.setPrefWidth(500);
        
        //A MenuBar for the window
        MenuBar menuBar = new MenuBar();
 
        //A File Menu on the MenuBar
        Menu menuFile = new Menu("File");

        //Loading files - Opens a file in the editor.
        //Shortcut: Ctrl+O
        MenuItem loadFile = new MenuItem("Load File");
        loadFile.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        //When clicked, this event handler is run
        loadFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	//A File Chooser is standard for the OS being used and allows the user to select a file.
            	FileChooser fileChooser = new FileChooser();
            	//Set a title for the new window, and show it as an "open dialog"
            	fileChooser.setTitle("Load File");
            	File file = fileChooser.showOpenDialog(stage);
            	//If the user selects a file...
            	if (file != null) {
            		try {
            	            //Get the file
            			    FileReader fileReader =  new FileReader(file.getPath());
                            //Get a Buffered Reader to read it
            	            BufferedReader bufferedReader = new BufferedReader(fileReader);

            	            //Holds a line of the file
            	            String line = "";
            	            
            	            //Hold the whole file
            	            String completeFile = "";
            	            
            	            //While there are lines to read, add them to completeFile
            	            while((line = bufferedReader.readLine()) != null) {
            	                completeFile += line;
            	            }   
            	            
            	            //Put the whole file into the editor
            	            String htmlString = "<body style='background-color:black;color:white;font-family:Segoe UI' contenteditable=true" +
            	            		  " onload='document.body.focus(); " +  focusJS + "'>";
            	            htmlString += completeFile;
            	            htmlString += "</body>";
            	            htmlEditor.setHtmlText(htmlString);
            	            
            	            //Syntax highlight it
            	            syntaxHighlight();

            	            //Close the reader
            	            bufferedReader.close();   
                    } catch (IOException e) {
                        //Handle an exception
                    	e.printStackTrace();
                    }
                } //if   	
            } //handler
        }); //listener
        
        //Saving files - Allows a user to save their work
        //Shortcut: Ctrl+S
        MenuItem saveFile = new MenuItem("Save File");
        saveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        //When clicked, this event handler is run
        saveFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	//Standard file chooser for the OS
            	FileChooser fileChooser = new FileChooser();
            	//Set a title for the new window, and show it as a "save dialog" 
                fileChooser.setTitle("Save File");
                File file = fileChooser.showSaveDialog(stage);
                //If the user saves their file as some name, it will ask if the user wants to overwrite if a file already exists
                if (file != null) {
                   try {
                	   //Open up a writer for the file, in UTF-8 encoding mode
                	   PrintWriter writer = new PrintWriter(file.getPath(), "UTF-8");
                	   //Get the contents of the HTMLEditor and write it into the saved file
                	   writer.print(htmlEditor.getHtmlText());
                	   //Close the writer
                	   writer.close();
                    } catch (IOException e) {
                    	//Handle an exception
                        e.printStackTrace();
                    }
                } //if
            } //handler
        }); //listener
        
        //Loading color files - Allows a user to change the syntax highlighting color scheme
        //Shortcut: Ctrl+H
        MenuItem loadColorScheme = new MenuItem("Load Colors");
        loadColorScheme.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        //When clicked, this event handler is run
        loadColorScheme.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	//Standard file chooser for the OS
            	FileChooser fileChooser = new FileChooser();
            	//Set a title for the new window, and show it as an "open dialog"
            	fileChooser.setTitle("Load Color Scheme File");
            	File file = fileChooser.showOpenDialog(stage);
            	//If the user selects a color scheme file...
            	if (file != null) {
            		try {
            		        //Same as loading a file
            			    FileReader fileReader = new FileReader(file.getPath());
                            BufferedReader bufferedReader = new BufferedReader(fileReader);

                            //Holds a line of a file
            	            String line = "";
            	            //Holds a color pair
            	            String[] pair = new String[2];
            	            
            	            //Default colors
            	            String commentColor = "40E0D0";
            	            String vardeclColor = "FF4500";
            	            String printColor = "00FA20";
            	            String intassignColor = "FF00FF";
            	            String stringassignColor = "FFFF00";
            	            String varassignColor = "FFD700";
            	              	         
            	            //While there are lines to read in the colors file...
            	            while((line = bufferedReader.readLine()) != null) {
                                //Create a pair containing type of color and color value, separated by "=". 
            	            	//This format is expected. Order doesn't matter, as long as each line is of the form:
            	            	//<statement-for-color>=<hex-color-value>
            	            	//Example line to set comment color to red:
            	            	//comment=FF0000
            	            	//No hash mark is necessary for the color values.
            	            	//Any or no colors can be specified in the file in any order.
            	                pair = line.split("=");
            	                //Set colors
            	                switch(pair[0]){
            	                case "comment": commentColor = pair[1]; break;
            	                case "vardecl": vardeclColor = pair[1]; break;
            	                case "print": printColor = pair[1]; break;
            	                case "intassign": intassignColor = pair[1]; break;
            	                case "stringassign": stringassignColor = pair[1]; break;
            	                case "varassign": varassignColor = pair[1]; break;
            	                default: //nothing, not recognized
            	                }
            	            }
            	            
             	            //Sets the colors for the DFA so it can set them correctly
            	            syntaxHighlightingDFA.setColors(commentColor, vardeclColor, printColor, intassignColor, stringassignColor, varassignColor);

            	            //Update the editor with new colors.
            	            syntaxHighlight();
            	            
            	            //Close the reader
            	            bufferedReader.close();   
                    } catch (IOException e) {
                        //Handles an exception
                    	e.printStackTrace();
                    }
                } //if
            } //handler
        }); //listener
        
      //Line mode - decides whether statements should break over lines or not.
        //Shortcut: Ctrl+B
        MenuItem lineModeMenu = new MenuItem("Change Line Mode");
        lineModeMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
        //When clicked, this event handler is run
        lineModeMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	lineMode = lineMode ? false : true;
            } //handler
        }); //listener
        
        //Adds three menu options to the File menu.
        menuFile.getItems().addAll(loadFile, saveFile, new SeparatorMenuItem(), loadColorScheme, lineModeMenu);
        
        //Adds the File menu to the MenuBar.
        menuBar.getMenus().addAll(menuFile);
        
        //Adds the MenuBar to the VBox.
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
       
        //Adds the MenuBar and HTMLEditor to the window.
        rootbox.getChildren().addAll(menuBar, htmlEditor);
        
        //Sets the root as the VBox with both the MenuBar and the HTMLEditor in it.
        scene.setRoot(rootbox);
        
        //Set the default to a black background with white text.
        //Some issues caused the editor to be out of focus so the user would need to click on it while typing. JavaScript fixes this.
        //The onload event handler always sets the editor to the focus, and extra JS sets the cursor to the correct position.
        htmlEditor.setHtmlText("<body style='background-color:black;color:white;' onload='document.body.focus(); " +  focusJS + "'>");
        
        //Set the scene
        stage.setScene(scene);
        
        //Event handler source:
        //http://stackoverflow.com/questions/22128153/javafx-htmleditor-text-change-listener 
        //Listens on changes to the text typed in and reacts accordingly.
        //This runs when a key is released.
        htmlEditor.setOnKeyReleased(new EventHandler<KeyEvent>()
      	      {
      	        @Override
      	        public void handle(KeyEvent event)
      	        {
      	          if (isValidEvent(event))
      	          {
      	        	  //Highlight the syntax of the text.
      	        	  syntaxHighlight();
      	        	  //Get focus back
      	        	  htmlEditor.requestFocus();
      	          }
      	        }

      	        //Checks if it is a valid text editor event - not a Select All, but either a Paste or another key being released
      	        //Exceptions: keys ALT, COMMAND, CONTROL, and SHIFT are not counted
      	        private boolean isValidEvent(KeyEvent event)
      	        {
      	          return !isSelectAllEvent(event)
      	              && ((isPasteEvent(event)) || isCharacterKeyReleased(event));
      	        }

      	        private boolean isSelectAllEvent(KeyEvent event)
      	        {
      	          return event.isShortcutDown() && event.getCode() == KeyCode.A;
      	        }

      	        private boolean isPasteEvent(KeyEvent event)
      	        {
      	          return event.isShortcutDown() && event.getCode() == KeyCode.V;
      	        }

      	        private boolean isCharacterKeyReleased(KeyEvent event)
      	        {
      	          switch (event.getCode())
      	          {
      	            case ALT:
      	            case COMMAND:
      	            case CONTROL:
      	            case SHIFT:
      	              return false;
      	            default:
      	              return true;
      	          }
      	        }
      	      });
        
        //Show the window
        stage.show();
 
        //Try to get HTMLEditor focus
        htmlEditor.requestFocus();
    }
  
  /**
   * syntaxHighlight
   * 
   * This function uses our DFA to evaluate the text currently in the HTMLEditor.
   * If valid statements are found, they will be colored according to the current color scheme.
   * 
   * Parameters: None
   * 
   * Return Value: None
   * 
   */
  protected static void syntaxHighlight(){
	  //Matches all HTML tags and removes them
	  //Text from the HTMLEditor contains HTML tags by default.
	  //These are not counted in the DFA, so they are removed to get the raw user input
      Pattern pattern = Pattern.compile("<[^>]*>");
      Matcher matcher = pattern.matcher(htmlEditor.getHtmlText());
      final StringBuffer sb = new StringBuffer(htmlEditor.getHtmlText().length());
      while(matcher.find()) {
          matcher.appendReplacement(sb, " ");
      }
      matcher.appendTail(sb);
     
      String cleanText = sb.toString().trim();
      //Preserve spaces
      if(cleanText.endsWith("&nbsp;")){
    	  cleanText = cleanText.substring(0,cleanText.length()-6);
    	  cleanText = cleanText.replaceAll("&nbsp;", " ");
    	  cleanText += "&nbsp;";
      }
      //Replace spaces so that DFA can read in the string
      else{
    	  cleanText = cleanText.replaceAll("&nbsp;", " ");
      }
      //Reduce whitespace
      cleanText = cleanText.replaceAll(" +", " ");
     
      //Use DFA to evaluate the user input and return a mapping of the colors to be used and where they should be used.
	  HashMap<Integer,String> syntaxHighlighting = syntaxHighlightingDFA.accepts(cleanText);

	  //Default text color is white.
	  String textColor = "#FFFFFF";
	  
	  //These are all indices in the user input string where a valid Statement ends and should be colored up to that last character.
	  Object[] stringIndices = syntaxHighlighting.keySet().toArray();
	  
	  //Represents the start of a new Statement.
	  int start = 0;
	  
	  //Converts the keys to Integers
	  Integer[] stringIndicesInt = new Integer[stringIndices.length];
	  for(int j = 0; j < stringIndices.length; j++){
		  stringIndicesInt[j] = (Integer)(stringIndices[j]); 
	  }
	  
	  //Makes a list of integers and sorts it, ascending
	  //The list of string indices must be sorted so that Statements are colored in order.
	  List<Integer> stringList = Arrays.asList(stringIndicesInt);
	  Collections.sort(stringList);
	  //Now we have a sorted array of where the colors should be
	  stringIndices = stringList.toArray();
	  
	  //Add default HTML
	  String htmlString = "<body style='background-color:black;color:white;font-family:Segoe UI' contenteditable=true" +
	  " onload='document.body.focus(); " +  focusJS + "'>";
	  
	  //If valid Statements were found...
	  if(stringIndices.length > 0){
		  //For each Statement found...
		  for(int i = 0; i < stringIndices.length; i++){
			  //Get the color for the Statement
			  textColor = "#" + syntaxHighlighting.get(stringIndices[i]);
			  //Make a new span element for that color
			  htmlString += "<span style=color:" + textColor + ">";
			  
			  //Select only that Statement from the whole input
			  if(cleanText.length() > 6 && cleanText.length() > start+6 && cleanText.substring(start,start+6) == "&nbsp;"){
				  htmlString += cleanText.substring(start+7,(Integer)(stringIndices[i])+7); //1
				  //The start of the next Statement will be at the next position
				  start = (Integer)(stringIndices[i])+7; 
			  }
			  else{
				  String forHTML = cleanText.substring(start,(Integer)(stringIndices[i])+1).replaceAll(" ", "&nbsp;").replaceAll(" &nbsp;", "&nbsp;");
				  htmlString += forHTML;
				  //The start of the next Statement will be at the next position
				  start = (Integer)(stringIndices[i])+1; 
			  }

			  //End the span at the end of the Statement
			  htmlString += "</span>"; //adds line break
			  //Add line break if in line mode
			  if(lineMode){
				  htmlString += "<br>";
			  }
		  } 
		  //Add the rest of the input, say if there is a valid Statement and more input after it that is not valid after
		  //start will be set to 0 if there were no Statements, and this would be the entire input
		  if(start < cleanText.length()){
			  htmlString += cleanText.substring(start,cleanText.length()); 
		  }
	  }
	  //If no valid Statements yet, just add back all the input
	  else{
		  htmlString += cleanText;
	  }
	  //Finish the HTML body
	  htmlString += "</body>";
	  //Place this new, syntax highlighted HTML back into the editor
	  htmlEditor.setHtmlText(htmlString);
	  //Request focus on the editor
	  htmlEditor.requestFocus();
  }
  
  //Caret position fix:
  //http://stackoverflow.com/questions/28106194/positioning-caret-in-javafx-htmleditor
  //This JavaScript fixes a problem in that whenever the editor gains focus, the cursor/caret goes to the front of the input.
  //To make the caret stay in place while the user is typing, this script is used when the editor gains focus by creating a range or text range.
  final static String focusJS = "var el = document.body; " +
          "if (typeof window.getSelection != \"undefined\" " +
          "            && typeof document.createRange != \"undefined\") { " +
          "        var range = document.createRange(); " +
          "        range.selectNodeContents(el); " +
          "        range.collapse(false); " +
          "        var sel = window.getSelection(); " +
          "        sel.removeAllRanges(); " +
          "        sel.addRange(range); " +
          "    } else if (typeof document.body.createTextRange != \"undefined\") { " +
          "        var textRange = document.body.createTextRange(); " +
          "        textRange.moveToElementText(el); " +
          "        textRange.collapse(false); " +
          "        textRange.select(); " +
          "    }";
  
  
}
