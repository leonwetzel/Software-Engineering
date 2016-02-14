package mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * View for input field
 * @author leonw_000
 *
 */
public class InputView extends JPanel implements ActionListener {
	private JTextField inputField = new JTextField(50);
	private CalculatorModel model;
	
	public InputView() {
	    this.setLayout(new BorderLayout());
	    this.add(inputField);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
	    model = (CalculatorModel) event.getSource();
	}

}
