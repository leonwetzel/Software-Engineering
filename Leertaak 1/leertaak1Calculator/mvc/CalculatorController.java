package mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CalculatorController extends JPanel implements ActionListener {
	private CalculatorModel model;
	private JButton add = new JButton("add");
    private JButton subtract = new JButton("extract");
    private JButton divide = new JButton("divide");
    private JButton multiply = new JButton("multiply");
    private JButton enter = new JButton("enter");
	
	public CalculatorController(CalculatorModel model) {
		this.model = model;
		
		this.add(add);
		add.addActionListener(this);
        this.add(subtract);
        subtract.addActionListener(this);
        this.add(divide);
        divide.addActionListener(this);
        this.add(multiply);
        multiply.addActionListener(this);
        this.add(enter);
        enter.addActionListener(this);

        this.setLayout(new GridLayout(0, 4));
        this.add(new JButton("+"));
        this.add(new JButton("-"));
        this.add(new JButton("*"));
        this.add(new JButton("/"));

        this.add(new JButton("bin"));
        this.add(new JButton("dec"));
        this.add(new JButton("hex"));
        this.add(new JButton("oct"));

        this.add(new JButton("fixed"));
        this.add(new JButton("floating"));
        this.add(new JButton("rational"));
        this.add(new JButton("help"));

        for(int i = 0; i < 10; i++) {
            this.add(new JButton("" + i + ""));
        }

        String hexLetters = "ABCDEF";
        for(char letter : hexLetters.toCharArray()) {
            this.add(new JButton("" + letter + ""));
        }
	}
	
	/**
	 * Catches events and calls corresponding methods.
	 */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
        	model.add();
        }  
        
        if (e.getSource() == subtract) {
        	model.subtract();
        } 
        
        if (e.getSource() == divide) {
        	try {
        	model.divide();
        	} catch (Exception exception) {
        		System.out.println(exception.getMessage());
        	}
        }
       
        if(e.getSource() == multiply) {
        	model.multiply();
        }
        
        for(int i = 0; i < 10; i++) {
        	if(e.getSource() == new JButton("" + i + "")) {
        		
        	}
        }
	}

	public Dimension getPreferredSize() {
	    return new Dimension(50,50);
	} 

}
