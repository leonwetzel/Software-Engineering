package mvc;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.jmx.snmp.tasks.ThreadService;

import multiformat.Base;
import multiformat.BinaryBase;

/**
 * View for control buttons and such.
 * @author leonw_000
 *
 */
public class KeyboardView extends JPanel implements ActionListener {
	private CalculatorModel model;
	
	public KeyboardView() {
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
	    model = (CalculatorModel) event.getSource();
	}

}
