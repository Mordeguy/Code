/*FILE HEADER -------------------------------------------------------------------------------------------------------
          File Name:  CalculatorViewController.java
             Author:  Nathan M. Abbey, 040-557-192
             Course:  CST8221 - Java Application Programming
        Lab Section:  301
  Assignment Number:  01 pt.2
               Date:  November 28th, 2017
          Professor:  Svillen Ranev
            Purpose:  This program build a calculator GUI for a user to interact with. It responds to events
                      generated by the GUI and calculates mathematical formulas based on valid user inputs. 
         Class List:  1. SplashScreen 
         			  2. Calculator
         			  3. CalculatorViewController <--
         			  4. Controller (inner class) <--
         			  5. CalculatorModel
 -------------------------------------------------------------------------------------------------------------------*/
/**
 * This class extends JPanel and creates the GUI visuals and formatting for a calculator application. It implements
 * the use of JComponents to create the applications calculator components. All of the components are created in
 * the default constructor and added to the JFrame. This class also implements ActionListener in order to handle the
 * events created by interacting with the calculator GUI.
 * 
 * @author Nathan M. Abbey
 * @version 2.0
 * @see Calculator.java, SplashScreen.java
 * @since 1.8_066
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CalculatorViewController extends JPanel {

	/**Required by default for Swing applications*/
	private static final long serialVersionUID = 1L;
	/**The left-hand side of the calculator display*/
	private JTextField display1;
	/**The right-hand side of the calculator display*/
	private JTextField display2;
	/**The MODE/ERROR display label*/
	private JLabel error;
	/**The dot (.) button used for decimal places*/
	private JButton dotButton;
	/** CalculatorModel class object which handles all calculations */
	private CalculatorModel cM = new CalculatorModel();
	/** Controller class which handles all button events */
	private Controller control = new Controller();	
	/** These are global to deal with the Bonus Mark no 2*/
	JCheckBox modeBox = new JCheckBox();
	JRadioButton radio2 = new JRadioButton();



	/**This default constructor builds the GUI of the calculator and formats and adds it to a JPanel. The
	 * numerical calculator buttons are created using the method createButton() in a loop. 
	 * */
	public CalculatorViewController(){
		/**This is the hex symbol for the backspace button*/
		String b = "\u00B1";
		/**The numerical buttons will use this array to run through a loop to be created*/
		String buttonSymbols []= {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", b, "+" };

		/**These components make up the top layer of the calculator*/
		JPanel topOfCalc = new JPanel();
		JPanel textPanel = new JPanel();
		JPanel radioMode = new JPanel();
		JPanel radioPanel = new JPanel();
		JPanel fPanel = new JPanel();
		JButton backSpace = new JButton();

		/*2 radio buttons that do not need to be global variables*/
		JRadioButton radio1 = new JRadioButton();
		JRadioButton radio3 = new JRadioButton();

		/**Used to group the checkbox and radio buttons so only one can be selected at a time*/
		ButtonGroup bg = new ButtonGroup();

		/**This panel holds the 2 previous areas and is used to enable both to be put on BorderLayout.NORTH*/
		JPanel topHolder = new JPanel();

		/**These components make up the bottom of the calculator; the numeric key pad.*/
		JPanel calcBottom = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel lastButtons = new JPanel();
		setLayout(new BorderLayout());

		//JPanel that is top panel to hold the 2 yellow buttons and calculator display
		topOfCalc.setVisible(true);
		topOfCalc.setBackground(Color.YELLOW);
		topOfCalc.setLayout(new BorderLayout());

		//Initializes the JLabel and puts it's content on the F button and adds it to JPanel 'topOfCalc'
		error = new JLabel("F", JLabel.CENTER);
		error.setFont(new Font(null, Font.PLAIN, 20));

		fPanel.setLayout(new BorderLayout());
		fPanel.add(error);

		if(cM.getErrorState() == true)
			fPanel.setBackground(Color.green);

		fPanel.setBackground(Color.YELLOW);
		fPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		fPanel.setPreferredSize(new Dimension(35, 55));
		topOfCalc.add(fPanel, BorderLayout.WEST);

		//JPanel that will hold the 2 JTextFields for the calculator display
		textPanel.setLayout(new GridLayout(2,0));
		textPanel.setBackground(Color.WHITE);
		textPanel.setVisible(true);

		//Initializes 2 JTextFields, adds them to panel 'textPanel' and adds panel to the calculator
		display1 = new JTextField(16);
		display1.setEditable(false);
		display1.setBorder(BorderFactory.createEmptyBorder());
		display1.setBackground(Color.WHITE);
		display1.setHorizontalAlignment(JTextField.RIGHT);
		display2 = new JTextField(16);
		display2.setEditable(false);
		display2.setText("0.00");
		display2.setBorder(BorderFactory.createEmptyBorder());
		display2.setBackground(Color.WHITE);
		display2.setHorizontalAlignment(JTextField.RIGHT);
		textPanel.add(display1);
		textPanel.add(display2);
		topOfCalc.add(textPanel, BorderLayout.CENTER);

		//Initializes right-hand button at the top of the calculator and adds it
		backSpace = createButton("\u21B2", "backspace", Color.BLACK, Color.BLACK, control);
		backSpace.setToolTipText("ALT-B(Backspace)");
		backSpace.setFocusPainted(false);
		backSpace.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		backSpace.setPreferredSize(new Dimension(35, 55));
		backSpace.setContentAreaFilled(false);
		backSpace.setMnemonic('B');
		topOfCalc.add(backSpace, BorderLayout.EAST);

		//JPanel to hold the radio buttons and check box
		radioMode.setBackground(Color.BLACK);
		radioMode.setVisible(true);

		//Setup the check-box on the calculator and adds it
		modeBox.setVisible(true);
		modeBox.setText("Int");
		modeBox.setBackground(Color.GREEN);
		modeBox.setActionCommand("Int");
		modeBox.addActionListener(control);
		radioMode.add(modeBox);

		//Adds a blank area to keep a space between the check-box and radio buttons
		radioMode.add(Box.createRigidArea(new Dimension(30, 0)));
		radioMode.setPreferredSize(new Dimension(0, 40));

		//JPanel to hold the 3 radio buttons 
		radioPanel.setLayout(new GridLayout());
		radioPanel.setBackground(Color.YELLOW);
		radioPanel.setVisible(true);

		//First radio button
		radio1.setBackground(Color.YELLOW);
		radio1.setText(".0");
		radio1.setActionCommand(".0");
		radio1.addActionListener(control);

		//Second radio button; this one is selected by default at startup
		radio2.setBackground(Color.YELLOW);
		radio2.setSelected(true);
		radio2.setText(".00");
		radio2.setActionCommand(".00");
		radio2.addActionListener(control);

		//Third radio button
		radio3.setBackground(Color.YELLOW);
		radio3.setText("Sci");
		radio3.setActionCommand("Sci");
		radio3.addActionListener(control);

		//Adds the check-box and radio buttons to a ButtonGroup so only one can be selected at a time
		bg.add(radio1);
		bg.add(radio2);
		bg.add(radio3);
		bg.add(modeBox);

		//Adds the 3 radio buttons to the JPanel then adds that to the black area of calculator
		radioPanel.add(radio1);
		radioPanel.add(radio2);
		radioPanel.add(radio3);
		radioMode.add(radioPanel);

		//Puts the first 2 top areas in to a JPanel that is added to the top layer JPanel
		topHolder.setLayout(new BorderLayout());
		topHolder.add(topOfCalc, BorderLayout.NORTH);
		topHolder.add(radioMode, BorderLayout.SOUTH);
		add(topHolder, BorderLayout.NORTH);

		//Sets up the JPanel for the numeric keypad of the calculator
		calcBottom.setVisible(true);
		calcBottom.setBackground(Color.GREEN);
		calcBottom.setLayout(new BorderLayout());

		//JPanel to hold the keypad with all the same size buttons
		jp4.setLayout(new GridLayout(4,4, 2,2));
		jp4.setVisible(true);
		jp4.setBackground(Color.WHITE);
		jp4.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		//Loops through createButton() to populate the JPanel 'jp4' with buttons
		for (int i = 0; i < buttonSymbols.length; i++){

			// Colors the appropriate buttons CYAN
			if (buttonSymbols[i].equals("/") || buttonSymbols[i].equals("*") || buttonSymbols[i].equals("-") || buttonSymbols[i].equals("+")){
				jp4.add(createButton(buttonSymbols[i], buttonSymbols[i], Color.black, Color.CYAN, control));

				// Colors the specific button PINK
			} else if (buttonSymbols[i].equals(b)) {
				jp4.add(createButton(buttonSymbols[i], buttonSymbols[i], Color.black, Color.PINK, control));

			} else if (buttonSymbols[i].equals(".")) {
				// Assigns the '.' button to the global variable dotButton, then adds it to the keypad
				dotButton = createButton(buttonSymbols[i], buttonSymbols[i], Color.black, Color.BLUE, control);
				jp4.add(dotButton);

			} else {
				// This creates all default buttons without special traits
				jp4.add(createButton(buttonSymbols[i], buttonSymbols[i], Color.black, Color.BLUE, control));
			}
		}

		//Sets the keypad to BorderLayout.CENTER, so that the keypad expands dynamically with window
		calcBottom.add(jp4, BorderLayout.CENTER);

		//Sets up a JPanel and creates the final 2 buttons (C, =) on the right-hand side of the keypad
		lastButtons.setLayout(new GridLayout(2, 0, 2, 2));
		lastButtons.setPreferredSize(new Dimension(50, 10));
		lastButtons.setBackground(Color.WHITE);
		lastButtons.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 2));
		JButton clear = createButton("C", "C", Color.black, Color.RED, control); 	
		JButton equals = createButton("=", "=", Color.black, Color.MAGENTA, control); 
		lastButtons.add(clear);
		lastButtons.add(equals);
		calcBottom.add(lastButtons, BorderLayout.EAST);

		//Adds the entire JPanel to the top JPanel in the CENTER so it dynamically sizes itself
		add(calcBottom, BorderLayout.CENTER);
	}


	/***
	 * This method create a JButton. It is used in this program to add the numeric keypad buttons
	 * to the calculator in a loop. This makes this program easier to code as it automates this
	 * process. 
	 * 
	 * @param text The String text that will appear on the button
	 * @param ac The action command associated with the Event generated by the button
	 * @param fg The text color of the button
	 * @param bg The background color of the button
	 * @param handler The object which handles events generated by the buttons
	 * @return Initialized JButton
	 */
	private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler){

		//Create a JButton object ands sets its text to the 'text' parameter
		JButton newButton = new JButton(text);

		//Sets foreground and background colors using the input parameters
		newButton.setForeground(fg);
		newButton.setBackground(bg);

		//Set the JButtons action command, skip if the parameter is NULL
		if(ac != null){
			newButton.setActionCommand(ac);
		}

		//Sets the font of the button
		Font  font = new Font(null, Font.PLAIN, 20);	
		newButton.setFont(font);

		//Adds the action listener
		newButton.addActionListener(handler);
		//Returns JButton
		
		newButton.setFocusPainted(false);
		return newButton;	 	
	}


	//START OF INNER CLASS ------------------------------------------------------------------------------------------------------|
	
	/** This is an inner class which extends ActionListener. It takes the events created by a user interacting
	 *  with the calculator and decides what to do based on input. This inner class uses an object of the 
	 *  CalculatorModel class; which handles all calculations to produce a result.  
	 * 
	 * @author Nay-thwan
	 * @version 2.0
	 * @since 1.8_066
	 *
	 */
	private class Controller implements ActionListener{

		/**String used to feed data in to the CalculatorModel class*/
		String temp;

		/**
		 * The Default Constructor for the inner Controller Class. This enables the CalculatorViewController
		 * class to create an object of this class for use with calculations
		 * */
		public Controller(){
			temp = new String();
		}

		
		/**
		 * This method sets the Mode Indicator at the top left to the appropriate color and letter.
		 * 				F: YELLOW ~ Float mode
		 * 				I: GREEN  ~ Integer
		 * 				E: RED    ~ Error Mode
		 * Also sets the . Button to the appropriate color and function when needed.
		 */
		public void setErrMod(){
			if (cM.getErrorState() == true){
				error.setText("E");
				error.getParent().setBackground(Color.RED);
			} else {
				if (cM.getOperationalMode() == 0){
					error.setText("I");
					error.getParent().setBackground(Color.GREEN);
					dotButton.setBackground(new Color(178, 156, 250));
					dotButton.setEnabled(false);
				}

				if (cM.getOperationalMode() == 1 || cM.getOperationalMode() == 2 || cM.getOperationalMode() == 3 ){
					error.setText("F");
					error.getParent().setBackground(Color.YELLOW);
					dotButton.setBackground(Color.BLUE);
					dotButton.setEnabled(true);	
				}
			}
		}


		/**
		 * This  inner class method handles the events produced by the calculator GUI. The method
		 * takes in the event and decides what to do based on the event's 'Action Command'. In this
		 * instance it will display the action event on to the calculator screen.
		 * 
		 * @param ActionEvent generated by clicking a button on the calculator GUI
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			// These String arrays are used to simplify the code below
			String [] num = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",};
			String [] op = {"+", "-", "*", "/"};

			// If there is not error state, allow use of the numeric keypad
			if (cM.getErrorState() == false){

				// Deals with all NUMERICAL button events ************|
				for (int i = 0; i < num.length; i++){
					if (e.getActionCommand() == num[i]){
						temp += i;	
						display2.setText(temp);
						break;
					}
				}	

				// Deals with all ARITHMETIC operation events ********|
				for (int i = 0; i < op.length; i++){	
					if (e.getActionCommand() == op[i]){
						
						if (!temp.isEmpty()){
						cM.setOperand1(temp);
						}
						
						cM.setArithmeticOp(op[i]);
						display1.setText(cM.getOperand1() + " " + cM.getArithmeticOp());
						temp = "";
						break;
					}
				}

				// Deals with the BACKSPACE button *******************|
				if (e.getActionCommand().equals("backspace")){
					
					if (!temp.isEmpty()){
					temp = temp.substring(0, temp.length() - 1);
					display2.setText(temp);
					}
				}

				// Deals with the EQUAL button ***********************|
				if (e.getActionCommand() == "="){
					cM.setOperand2(temp);

					// If user tries to divide by 0 by 0; throws an error and resets variables
					if (cM.getOperand1().equals("0") && cM.getOperand2().equals("0")){
						display2.setText("Result is undefined");
						cM.setErrorState(true);
						setErrMod();
						temp = "";
						return;	
					}

					// If user tries to divide by 0; throws an error and resets variables
					if (cM.getOperand2().equals("0") && cM.getArithmeticOp().equals("/")){
						display2.setText("Cannot divide by zero");
						cM.setErrorState(true);
						setErrMod();
						temp = "";
						return;
					}

					if (temp.isEmpty())
						cM.setOperand2(cM.getOperand1());

					// Set the display to the result, and clears display1
					display1.setText("");
					display2.setText(cM.getResult());
					temp = cM.getResult();
				}

				// Deals with the +- button *****************************|
				if (e.getActionCommand().equals("\u00B1")){
					if (temp.charAt(0) == '-'){
						temp = temp.substring(1, temp.length());
					} else {
						temp = "-" + temp;
					}
					display2.setText(temp);
				}

				// Deals with the . button ******************************|
				// ONLY allows 1 period in a number
				if (e.getActionCommand().equals(".")){
					if (temp.contains("."))
						return;
					else 
						temp += ".";
					display2.setText(temp);
				}
				
			} // Skips all of this if there is an error; user has no access until calculator reset with CLEAR button.

			
			// Handles the CLEAR button *********************************|
			if (e.getActionCommand() == "C"){		

				if (cM.getOperationalMode() == 0){
					display1.setText("");
					display2.setText("0");	
				}
				if (cM.getOperationalMode() == 1){
					display1.setText("");
					display2.setText("0.0");	
				}
				if (cM.getOperationalMode() == 2){
					display1.setText("");
					display2.setText("0.00");	
				}
				if (cM.getOperationalMode() == 3){
					display1.setText("");
					display2.setText("0");	
				}
				cM.setErrorState(false);
				temp = "";
				setErrMod();
			}
			
			
			// Handles the INT check box *******************************|
			if (e.getActionCommand().equals("Int")) {
				if (cM.getOperationalMode() == 0){
					cM.setOpMode(2);
					modeBox.setSelected(false);
					radio2.setSelected(true);
					setErrMod();
					return;
				}
				
				// If there is a period and this is checked, temp set to 0
				if (temp.contains(".")){
					temp = "";
					display2.setText("0");
				}
				cM.setOpMode(0);
				setErrMod();
			}

			
			// Handles the .0 check box ********************************|
			if (e.getActionCommand().equals(".0")){
				cM.setOpMode(1);
				setErrMod();
			}

			// Handles the .00 check box *******************************|
			if (e.getActionCommand().equals(".00")){
				cM.setOpMode(2);
				setErrMod();
			}

			// Handles the SCI check box *******************************|
			if (e.getActionCommand().equals("Sci")) {
				cM.setOpMode(3);
				setErrMod();
			}
		}
		
	}  	//END OF INNER CLASS ----------------------------------------------------------------------------------------------------|

} // End of CalculatorViewController Class