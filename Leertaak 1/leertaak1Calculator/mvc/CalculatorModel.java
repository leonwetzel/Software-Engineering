package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import exceptions.NumberBaseException;
import multiformat.*;

public class CalculatorModel {
	private ArrayList<ActionListener> actionListenerList = new ArrayList<>();
	private Rational operand_0 = new Rational();
	private Rational operand_1 = new Rational();
    private InputView inputView;
	
	/**
	 * Constructor for a CalculatorModel object.
	 */
	public CalculatorModel(InputView newView) {
		this.inputView = newView;
	}

    public InputView getInputView()
    {
        return inputView;
    }

	// The current format of the calculator
	private Format format = new FixedPointFormat();
	// The current numberbase of the calculator
	private Base base = new DecimalBase();

    public void addOperand(String newOperand) throws FormatException {
        try
        {
            if(checkNewOperandForPossibleErrors(newOperand))
            {
                operand_1 = operand_0;
                operand_0 = format.parse(newOperand, base);
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
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

    public boolean checkNewOperandForPossibleErrors(String newOperand) throws NumberBaseException
    {
        newOperand.trim();
        if(base instanceof HexBase)
        {
            if( hexBasedCheck(newOperand))
            {
                return true;
            }
            throw new  NumberBaseException("Verkeerde input, hex is 0-9 en ABCDEF");
        }
        else if (base instanceof OctalBase)
        {
            if( octalBaseCheck(newOperand))
            {
                return true;
            }
            throw new   NumberBaseException("Verkeerde input, Octaal is 0-7");
        }
        else if(base instanceof DecimalBase)
        {
            if(decimalBaseCheck(newOperand))
            {
                return true;
            }
            throw new   NumberBaseException("Verkeerde input, decimaal is 0-9");
        }
        return false;
    }

    private boolean hexBasedCheck(String operand)
    {
        for(int i=0; i< operand.length();i++)
        {
            char c = operand.charAt(i);
            switch(c)
            {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean octalBaseCheck(String operand)
    {
        for(int i=0;i< operand.length();i++)
        {
            char c = operand.charAt(i);
            switch(c)
            {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean decimalBaseCheck(String operand)
    {
        for(int i=0; i<operand.length();i++)
        {
            char c = operand.charAt(i);
            switch(c)
            {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                default:
                    return false;
            }
        }
        return true;
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
