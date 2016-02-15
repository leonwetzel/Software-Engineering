package mvc;

import multiformat.FormatException;

import java.awt.Dimension;
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
		// TODO Auto-generated constructor stub
		this.model = model;
		
		this.add(add);
		add.addActionListener(this);
        this.add(subtract);
        subtract.addActionListener(this);
        this.add(divide);
        divide.addActionListener(this);
        this.add(multiply);
        multiply.addActionListener(this);
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

		if(e.getSource() == enter)
		{
			try {
				model.addOperand(model.getInputView().getInputContext().toString());
			} catch (FormatException e1) {
				e1.getMessage();
			}
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
