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
        inputView = new InputView();
        inputView.setBackground(Color.lightGray);
        getContentPane().add(inputView, BorderLayout.NORTH);

		model = new CalculatorModel(inputView);
		controller = new CalculatorController(model);
		
        getContentPane().add(controller, BorderLayout.CENTER);
        
        // Registreer de views bij het model
        model.addActionListener(keyboardView);
        model.addActionListener(inputView);
	}
}
