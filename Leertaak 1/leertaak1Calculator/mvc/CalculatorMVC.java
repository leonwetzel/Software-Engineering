package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JApplet;

public class CalculatorMVC extends JApplet {
	private CalculatorModel model;
	private CalculatorController controller;
	private KeyboardView keyboardView;
	private InputView inputView;
	
	/**
	 * Setup the application
	 */
	@Override
    public void init() {
		model = new CalculatorModel();
		controller = new CalculatorController(model);
		
        // Maak de views
		keyboardView = new KeyboardView();
		keyboardView.setBackground(Color.lightGray);
        getContentPane().add(keyboardView,BorderLayout.CENTER);
        
        inputView = new InputView();
        inputView.setBackground(Color.lightGray);
        getContentPane().add(inputView,BorderLayout.NORTH);
        
        // Registreer de views bij het model
        model.addActionListener(keyboardView);
        model.addActionListener(inputView);
	}
}
