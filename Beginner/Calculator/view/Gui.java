package view;

import controller.Bridge;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Gui {
    public JTextField outputTextField = new JTextField();
    public JTextField inputTextField = new JTextField();
	private Bridge bridge = new Bridge();

    public void calculatorGui() {

        JFrame test = new JFrame("Guess the number");
        test.setSize(520,510);
		test.setVisible(true);
		test.setResizable(false);
		test.getContentPane().setLayout(null);
		
		outputTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		outputTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		outputTextField.setEditable(false);
		outputTextField.setBounds(10, 62, 482, 48);
		test.getContentPane().add(outputTextField);
		outputTextField.setColumns(10);
		
		inputTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		inputTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		inputTextField.setText("");
		inputTextField.setColumns(10);
		inputTextField.setBounds(10, 11, 482, 48);
		test.getContentPane().add(inputTextField);
		
		JButton numberBtn7 = new JButton("7");
		numberBtn7.setFont(new Font("Tahoma", Font.PLAIN, 28));
		numberBtn7.setBounds(10, 142, 89, 70);
		test.getContentPane().add(numberBtn7);
        numberBtn7.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"7");
        });
		
		JButton numberBtn8 = new JButton("8");
		numberBtn8.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn8.setBounds(109, 142, 89, 70);
		test.getContentPane().add(numberBtn8);
        numberBtn8.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"8");
        });
		
		JButton numberBtn9 = new JButton("9");
		numberBtn9.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn9.setBounds(208, 142, 89, 70);
		test.getContentPane().add(numberBtn9);
        numberBtn9.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"9");
        });
		
		JButton plusBtn = new JButton("+");
		plusBtn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		plusBtn.setBounds(304, 142, 89, 70);
		test.getContentPane().add(plusBtn);
        plusBtn.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"+");
        });
		
		JButton numberBtn4 = new JButton("4");
		numberBtn4.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn4.setBounds(10, 223, 89, 70);
		test.getContentPane().add(numberBtn4);
        numberBtn4.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"4");
        });
		
		JButton numberBtn1 = new JButton("1");
		numberBtn1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn1.setBounds(10, 304, 89, 70);
		test.getContentPane().add(numberBtn1);
        numberBtn1.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"1");
        });
		
		JButton numberBtn5 = new JButton("5");
		numberBtn5.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn5.setBounds(109, 223, 89, 70);
		test.getContentPane().add(numberBtn5);
        numberBtn5.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"5");
        });
		
		JButton numberBtn6 = new JButton("6");
		numberBtn6.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn6.setBounds(208, 223, 89, 70);
		test.getContentPane().add(numberBtn6);
        numberBtn6.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"6");
        });
		
		JButton numberBtn3 = new JButton("3");
		numberBtn3.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn3.setBounds(208, 304, 89, 70);
		test.getContentPane().add(numberBtn3);
        numberBtn3.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"3");
        });
		
		JButton numberBtn2 = new JButton("2");
		numberBtn2.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn2.setBounds(109, 304, 89, 70);
		test.getContentPane().add(numberBtn2);
        numberBtn2.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"2");
        });
		
		JButton numberBtn0 = new JButton("0");
		numberBtn0.setFont(new Font("Tahoma", Font.PLAIN, 27));
		numberBtn0.setBounds(109, 385, 89, 70);
		test.getContentPane().add(numberBtn0);
        numberBtn0.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"0");
        });
		
		JButton subBtn = new JButton("-");
		subBtn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		subBtn.setBounds(304, 223, 89, 70);
		test.getContentPane().add(subBtn);
        subBtn.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"-");
        });
		
		JButton equalBtn = new JButton("=");
		equalBtn.setFont(new Font("Tahoma", Font.PLAIN, 34));
		equalBtn.setBounds(403, 142, 89, 313);
		test.getContentPane().add(equalBtn);
        equalBtn.addActionListener((ActionEvent event) -> {
			double value = bridge.stackers(inputTextField.getText());
			outputTextField.setText(String.valueOf(value));
        });
		
		JButton dotBtn = new JButton(".");
		dotBtn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		dotBtn.setBounds(208, 385, 89, 70);
		test.getContentPane().add(dotBtn);
        dotBtn.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+".");
        });
		
		JButton clearBtn = new JButton("C");
		clearBtn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		clearBtn.setBounds(10, 385, 89, 70);
		test.getContentPane().add(clearBtn);
        clearBtn.addActionListener((ActionEvent event) -> {
            inputTextField.setText(null);
        });
		
		JButton multiBtn = new JButton("*");
		multiBtn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		multiBtn.setBounds(304, 385, 89, 70);
		test.getContentPane().add(multiBtn);
        multiBtn.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"*");
        });
		
		JButton divBtn = new JButton("/");
		divBtn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		divBtn.setBounds(304, 304, 89, 70);
		test.getContentPane().add(divBtn);
        divBtn.addActionListener((ActionEvent event) -> {
            inputTextField.setText(inputTextField.getText()+"/");
        });
    }

	public JTextField getOutputTextField() {
		return outputTextField;
	}

	public JTextField getInputTextField() {
		return inputTextField;
	}
    
}
