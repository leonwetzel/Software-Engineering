package mvc;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class CalculatorMVC extends JApplet {
	private CalculatorModel model;
	private CalculatorController controller;
	private KeyboardView keyboardView;
	private InputView inputView;
	private JTextArea historyField;
	
	/**
	 * Setup the application
	 */
	@Override
    public void init() {
        inputView = new InputView();
        inputView.setBackground(Color.lightGray);
        getContentPane().add(inputView, BorderLayout.NORTH);

		historyField = new JTextArea();
		historyField.setColumns(10);
		historyField.setEnabled(true);
		getContentPane().add(historyField, BorderLayout.SOUTH);

		model = new CalculatorModel(inputView, historyField);
		controller = new CalculatorController(model);
		
        getContentPane().add(controller, BorderLayout.CENTER);
        // Registreer de views bij het model
        model.addActionListener(keyboardView);
        model.addActionListener(inputView);
	}
}
