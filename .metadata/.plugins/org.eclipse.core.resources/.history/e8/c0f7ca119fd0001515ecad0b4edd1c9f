import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class StatistiekenView extends JPanel implements ActionListener
{
    private JTextArea steenRoodVeld = new JTextArea();
    DobbelsteenModel d;
    
    public StatistiekenView(DobbelsteenModel dobbelsteen)
    {
        this.setLayout(new FlowLayout());
        this.add(steenRoodVeld);
        this.d = dobbelsteen;
        setField();
    }
    
    private void setField()
    {
    	String stats = new String();
    	HashMap<Integer, Integer> theNumbers = d.getStats();
    	for(int i = 1; i < 7; i++)
    	{
    		if(theNumbers.containsKey(i))
    		{
    			stats= stats + Integer.toString(i) + "-" + theNumbers.get(i);
    		}
    		else
    		{
    			stats= stats + Integer.toString(i) + "-" + "0";
        		
    		}
    		stats = stats + "\n";
    	}
    	steenRoodVeld.setText(stats);
    }

    
    public void actionPerformed( ActionEvent e )
	{
    	setField();
	}

    public Dimension getPreferredSize()
    {
        return new Dimension(50,50);
    }
    
   
}