package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import exceptions.NumberBaseException;
import multiformat.*;

import javax.swing.*;

public class CalculatorModel {
	private ArrayList<ActionListener> actionListenerList = new ArrayList<>();
	private Rational operand_0 = new Rational();
	private Rational operand_1 = new Rational();
    private InputView inputView;
    private JTextArea historyField;
	
	/**
	 * Constructor for a CalculatorModel object.
	 */
	public CalculatorModel(InputView newView, JTextArea newHistoryField) {
		this.inputView = newView;
        this.historyField = newHistoryField;
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
                inputView.getInputField().setText(newOperand);
            } else {
                throw new FormatException("Hoi Rovbert");
            }
        } catch(NumberBaseException e)
        {
            System.out.println(e.getMessage());
        }
    }

	public void add(){
        String history = makeHistory();
        history = history + "+";
		operand_0 = operand_1.plus(operand_0);
		operand_1 = new Rational();
        history = history + "=" + String.valueOf(operand_0.getNumerator());
        addHistory(history);
	}

	public void subtract() {
        String history = makeHistory();
        history = history + "-";
		operand_0 = operand_1.minus(operand_0);
		operand_1 = new Rational();
        history = history + "=" + String.valueOf(operand_0.getNumerator());
        addHistory(history);
	}

	public void multiply() {
        String history = makeHistory();
        history = history + "*";
		operand_0 = operand_1.mul(operand_0);
		operand_1 = new Rational();
        history = history + "=" + String.valueOf(operand_0.getNumerator());
        addHistory(history);
	}

	public void divide() throws Exception {
		if(operand_0.getNumerator() == 0)
		{
			throw new Exception("Cannot divide by 0!");
		} else {
            String history = makeHistory();
            history = history + "/";
			operand_0 = operand_1.div(operand_0);
			operand_1 = new Rational();
            history = history + "=" + String.valueOf(operand_0.getNumerator());
            addHistory(history);
		}
	}

	public void delete() {
		operand_0 = operand_1;
		operand_1 = new Rational();
	}

    public void clear()
    {
        operand_0 = new Rational();
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
        addHistory("Changing base to" + base.getName());
	}

	public Base getBase(){
		return base;
	}

	public void setFormat(Format newFormat){
		format = newFormat;
        addHistory("Changing format to " + format.getName());
	}

	public Format getFormat(){
		return format;
	}

    private boolean checkNewOperandForPossibleErrors(String newOperand) throws NumberBaseException
    {
        newOperand.trim();
        if(base instanceof HexBase)
        {
            if(hexBasedCheck(newOperand))
            {
                return true;
            }
            throw new NumberBaseException("Verkeerde input, hex is 0-9 en ABCDEF");
        }
        else if (base instanceof OctalBase)
        {
            if( octalBaseCheck(newOperand))
            {
                return true;
            }
            throw new NumberBaseException("Verkeerde input, Octaal is 0-7");
        }
        else if(base instanceof DecimalBase)
        {
            if(decimalBaseCheck(newOperand))
            {
                return true;
            }
            throw new NumberBaseException("Verkeerde input, decimaal is 0-9");
        }
        else if(base instanceof BinaryBase)
        {
            if(binaryBaseCheck(newOperand))
            {
                return true;
            }
            throw new NumberBaseException("Verkeerde input, binair is 0-1");
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
                case '.':
                case '-':
                case '/':
                case '^':
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
                case '.':
                case '-':
                case '/':
                case '*':
                case '^':
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
                case '.':
                case '-':
                case '/':
                case '*':
                case '^':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private boolean binaryBaseCheck(String operand)
    {
        for(int i=0; i<operand.length();i++)
        {
            char c = operand.charAt(i);
            switch(c)
            {
                case '0':
                case '1':
                case '.':
                case '-':
                case '/':
                case '*':
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

    private String makeHistory()
    {
        String temp = String.valueOf(operand_0.getNumerator());
        temp = temp + ",";
        temp = temp + String.valueOf(operand_1.getNumerator());
        temp = temp + ",";
        return temp;
    }

    private void addHistory(String newHistory)
    {
        String old = historyField.getText();
        historyField.setText(newHistory + " " + old);
    }
}
