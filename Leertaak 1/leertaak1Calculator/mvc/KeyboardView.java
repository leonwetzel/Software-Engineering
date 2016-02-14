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
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
	    model = (CalculatorModel) event.getSource();
	}

}
