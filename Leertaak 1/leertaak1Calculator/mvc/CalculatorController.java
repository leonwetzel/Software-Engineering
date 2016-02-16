package mvc;

import multiformat.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CalculatorController extends JPanel implements ActionListener {
    private CalculatorModel model;
    private JButton add = new JButton("+");
    private JButton subtract = new JButton("-");
    private JButton divide = new JButton("/");
    private JButton multiply = new JButton("*");
    private JButton enter = new JButton("enter");

    private JButton decimal = new JButton("dec");
    private JButton binary = new JButton("bin");
    private JButton octal = new JButton("oct");
    private JButton hex = new JButton("hex");

    private JButton floating = new JButton("floating");
    private JButton fixed = new JButton("fixed");
    private JButton rational = new JButton("rational");

    private JButton help = new JButton("help");
    private JButton clear = new JButton("clear");
    private JButton delete = new JButton("delete");

    private static final String HEXADECIMAL_CHARS = "ABCDEF";
    private static ArrayList<JButton> numberButtons = new ArrayList<>();

    public CalculatorController(CalculatorModel model) {
        this.setLayout(new GridLayout(0, 4));
        this.model = model;

        setOperators();
        setBases();
        setFormats();

        for (int i = 0; i < 10; i++) {
            JButton number = new JButton("" + i + "");
            this.add(number);
            number.addActionListener(this);
            numberButtons.add(number);
        }

        for (char character : HEXADECIMAL_CHARS.toCharArray()) {
            JButton letter = new JButton("" + character + "");
            this.add(letter);
            letter.addActionListener(this);
            numberButtons.add(letter);
        }
    }

    /**
     * Catches events and calls corresponding methods.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        triggerOperator(e);
        triggerBase(e);
        triggerOperand(e);
        triggerFormat(e);
    }

    /**
     * Setter for size of window.
     * @return Dimension object
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,50);
    }

    /**
     * Setter for all the formats.
     */
    private void setFormats() {
        this.add(floating);
        floating.addActionListener(this);
        this.add(fixed);
        fixed.addActionListener(this);
        this.add(rational);
        rational.addActionListener(this);
        this.add(help);
        help.addActionListener(this);
    }

    /**
     * Setter for all the operators.
     */
    private void setOperators() {
        this.add(add);
        add.addActionListener(this);
        this.add(subtract);
        subtract.addActionListener(this);
        this.add(divide);
        divide.addActionListener(this);
        this.add(multiply);
        multiply.addActionListener(this);
        this.add(enter);
        enter.addActionListener(this);
        this.add(clear);
        clear.addActionListener(this);
        this.add(delete);
        delete.addActionListener(this);
    }

    /**
     * Setter for all the base types.
     */
    private void setBases() {
        this.add(decimal);
        decimal.addActionListener(this);
        this.add(octal);
        octal.addActionListener(this);
        this.add(hex);
        hex.addActionListener(this);
        this.add(binary);
        binary.addActionListener(this);
    }

    /**
     * Triggers a method if the event is corrsponding.
     * @param e Event which occured.
     */
    private void triggerOperator(ActionEvent e) {
        if (e.getSource() == add) {
            System.out.println("Optellen!");
            model.add();
            setInputField();
        }
        else if (e.getSource() == subtract)
        {
            System.out.println("Aftrekken!");
            model.subtract();
            setInputField();
        }
        else if (e.getSource() == divide)
        {
            try {
                System.out.println("Delen!");
                model.divide();
                setInputField();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        else if(e.getSource() == multiply)
        {
            System.out.println("Vermenigvuldigen!");
            model.multiply();
            setInputField();
        }
        else if(e.getSource() == enter)
        {
            try {
                model.addOperand(model.getInputView().getInputField().getText());
                model.getInputView().getInputField().setText("");
            } catch (FormatException fe) {
                System.out.println(fe.getMessage());
            }
        }else if(e.getSource() == clear)
        {
            model.getInputView().getInputField().setText("");
            model.clear();
        }else if(e.getSource() == delete)
        {
            model.delete();
        }

    }

    /**
     * Triggers a method if the event is corrsponding.
     * @param e Event which occured.
     */
    private void triggerBase(ActionEvent e) {
        if(e.getSource() == binary) {
            model.setBase(new BinaryBase());
        } else if(e.getSource() == hex) {
            model.setBase(new HexBase());
        } else if(e.getSource() == octal) {
            model.setBase(new OctalBase());
        } else if(e.getSource() == decimal) {
            model.setBase(new DecimalBase());
        }
    }

    /**
     * Triggers a method if the event is corrsponding.
     * @param e Event which occured.
     */
    private void triggerOperand(ActionEvent e) {
        int limit;
        if(model.getBase() instanceof BinaryBase) {
            limit = 2;
        } else if(model.getBase() instanceof OctalBase) {
            limit = 8;
        } else if(model.getBase() instanceof HexBase) {
            limit = 16;
        } else {
            limit = 10;
        }

        for (int i = 0; i < limit; i++) {
            String old = model.getInputView().getInputField().getText();
            if (e.getSource() == numberButtons.get(i)) {
                if (i < 10) {
                    System.out.println(old);
                    model.getInputView().getInputField().setText(old + "" + i);
                } else {
                    char hexNumber = HEXADECIMAL_CHARS.toCharArray()[i - 10];
                    System.out.println(hexNumber);
                    model.getInputView().getInputField().setText(old + "" + hexNumber);
                }
            }
        }
    }

    /**
     * Triggers a method if the event is corrsponding.
     * @param e Event which occured.
     */
    private void triggerFormat(ActionEvent e) {
        if(e.getSource() == rational) {
            model.setFormat(new RationalFormat());
        } else if(e.getSource() == fixed) {
            model.setFormat(new FixedPointFormat());
        } else if(e.getSource() == floating) {
            model.setFormat(new FloatingPointFormat());
        }
    }

    /**
     * Sets textfield for input.
     */
    private void setInputField() {
        model.getInputView().getInputField().setText(model.secondOperand());
    }


}
