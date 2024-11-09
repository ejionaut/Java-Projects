package GuessTheNumber;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
    public static void main(String[] args) {
        Gui gui = new Gui();
		Bridge bridge = new Bridge();
        bridge.startGame(gui);
    }
    
}

class Bridge {
	private int lives = 5;
	private int random; 

    public void startGame(Gui gui){
		gui.guiPrompt();
		reset();
		setup(gui);
    }

	private void setup(Gui gui){
		gui.noTriesTextField.setText(String.valueOf(lives));
		random = (int) (Math.random() * 100);
		gui.outputTextField.setText("Press Start to Begin!"); 
		gui.topLabel.setText("Guess The Number!");
		gui.inputTextField.setText("");
		

		gui.startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {  
					gui.startButton.setText("Guess");
                    runGame(gui);
                }
			
		});
	}

	private void reset(){
		lives = 5;
	}

	private void runGame(Gui gui){ 
		gui.outputTextField.setText("The Number is: ");
	
		if(lives != 0){
			if(Integer.parseInt(gui.inputTextField.getText()) != random){
				lives-= 1;
				gui.noTriesTextField.setText(String.valueOf(lives));

				if(Integer.parseInt(gui.inputTextField.getText()) > random){
					gui.outputTextField.setText("The Number is: ?? (Go Lower!!)");
				} else if (Integer.parseInt(gui.inputTextField.getText()) < random){
					gui.outputTextField.setText("The Number is: ?? (Go Higher!!)");
				}


			} else {
				gui.outputTextField.setText("The Number is: " + random);
				gui.topLabel.setText("You WON!!!!");
				gui.startButton.setText("Play Again?");
				gui.startButton.addActionListener((ActionEvent e) -> {
                            reset();
                            setup(gui);
                        });	
			}
		}

		if(lives == 0){
			gui.outputTextField.setText("The Number is: " + random);
			gui.topLabel.setText("You Lost :((");
			gui.startButton.setText("Try Again??");
			gui.startButton.addActionListener((ActionEvent e) -> {
                            reset();
                            setup(gui);
                        });
		}
 	}
}


class Gui {
    public JTextField outputTextField;
	public JTextField inputTextField;
	public JTextField noTriesTextField;
	public JButton startButton;
	public JLabel topLabel;
    
    public void guiPrompt() {
        JFrame guessGui = new JFrame("Guess the number ( 1 - 100 )");
        guessGui.setSize(520,393);
		guessGui.setVisible(true);
		guessGui.setResizable(false);
		guessGui.getContentPane().setLayout(null);
		
		outputTextField = new JTextField();
		outputTextField.setText("Welcome to the game. Press start to begin.");
		outputTextField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		outputTextField.setHorizontalAlignment(SwingConstants.CENTER);
		outputTextField.setEditable(false);
		outputTextField.setBounds(10, 134, 482, 48);
		guessGui.getContentPane().add(outputTextField);
		outputTextField.setColumns(10);
		
		inputTextField = new JTextField();
		inputTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		inputTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		inputTextField.setText("");
		inputTextField.setColumns(10);
		inputTextField.setBounds(10, 193, 482, 48);
		guessGui.getContentPane().add(inputTextField);
		
		noTriesTextField = new JTextField();
		noTriesTextField.setText("3");
		noTriesTextField.setHorizontalAlignment(SwingConstants.CENTER);
		noTriesTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		noTriesTextField.setEditable(false);
		noTriesTextField.setColumns(10);
		noTriesTextField.setBounds(285, 75, 64, 48);
		guessGui.getContentPane().add(noTriesTextField);
		
		JLabel triesLabel = new JLabel("Number of Tries:");
		triesLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		triesLabel.setBounds(125, 75, 150, 48);
		guessGui.getContentPane().add(triesLabel);
		
		topLabel = new JLabel("Guess The Number!");
		topLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		topLabel.setBounds(126, 11, 249, 48);
		guessGui.getContentPane().add(topLabel);

		startButton = new JButton("Start Guessing");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 27));
		startButton.setBounds(69, 266, 369, 58);
		guessGui.getContentPane().add(startButton);
    }
}
