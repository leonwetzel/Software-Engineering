package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import multiformat.Base;
import multiformat.DecimalBase;
import multiformat.FixedPointFormat;
import multiformat.Format;
import multiformat.FormatException;
import multiformat.Rational;

public class CalculatorModel {
	private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	private Rational operand_0 = new Rational();
	private Rational operand_1 = new Rational();
	
	/**
	 * Constructor for a CalculatorModel object.
	 */
	public CalculatorModel() {
		
	}

	// The current format of the calculator
	private Format format = new FixedPointFormat();
	// The current numberbase of the calculator
	private Base base = new DecimalBase();

	public void addOperand(String newOperand) throws FormatException {
		operand_1 = operand_0;
		operand_0 = format.parse(newOperand, base);
	}

	public void add(){
		operand_0 = operand_1.plus(operand_0);
		operand_1 = new Rational();
	}

	public void subtract() {
		operand_0 = operand_1.minus(operand_0);
		operand_1 = new Rational();
	}

	public void multiply() {
		operand_0 = operand_1.mul(operand_0);
		operand_1 = new Rational();
	}

	public void divide() throws Exception {
		if(operand_0.getNumerator() == 0)
		{
			throw new Exception("Cannot divide by 0!");
		} else {
			operand_0 = operand_1.div(operand_0);
			operand_1 = new Rational();
		}
	}

	public void delete() {
		operand_0 = operand_1;
		operand_1 = new Rational();
	}

	public String firstOperand(){
		return format.toString(operand_1,base);
	}

	public String secondOperand(){
		return format.toString(operand_0,base);
	}

	public void setBase(Base newBase){
		base = newBase;
	}

	public Base getBase(){
		return base;
	}

	public void setFormat(Format newFormat){
		format = newFormat;
	}

	public Format getFormat(){
		return format;
	}
	
	public void addActionListener(ActionListener listener) {
		actionListenerList.add(listener);
	}

	public void removeActionListener(ActionListener listener) {
		if (actionListenerList.contains(listener)) {
			actionListenerList.remove(listener);	
		}
	}
	
	private void processEvent(ActionEvent event) {
		for(ActionListener listeners : actionListenerList) {
			listeners.actionPerformed( event );	
		}
	}
}
