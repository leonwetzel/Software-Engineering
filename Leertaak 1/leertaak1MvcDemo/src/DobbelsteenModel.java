import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DobbelsteenModel
{
	private int waarde;
	private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	private HashMap<Integer, Integer> stats;
	
	public DobbelsteenModel()
	{
		waarde= (int)(Math.random()*6+1);
		stats = new HashMap<Integer, Integer>();
	}
	
    public int getWaarde()
    {
        return waarde;
    }

	public void verhoog()
	{
		waarde++;
	    if (waarde>6) waarde=1;
	    processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
	    addRecordsToStats();
	}

	public void verlaag()
	{
	    waarde--;
	    if (waarde<1) waarde=6;
	    processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
	    addRecordsToStats();
	}
	
	public HashMap<Integer, Integer> getStats()
	{
		return stats;
	}
	
	private void addRecordsToStats()
	{
		int temp;
		if(stats.containsKey(waarde)) {
			temp = stats.get(waarde);
			temp++;
		} else {
			temp = 1;
		}
		stats.put(waarde, temp);
	}

	public void gooi(){
	    waarde= (int)(Math.random()*6+1); 
	    addRecordsToStats();
	    processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
	}
	
	public void addActionListener( ActionListener l){
		actionListenerList.add( l );
	}

	public void removeActionListener( ActionListener l){
		if ( actionListenerList.contains( l ) )
			actionListenerList.remove( l );
	}
	
	private void processEvent(ActionEvent e){
		for( ActionListener l : actionListenerList)
			l.actionPerformed( e );
	}
}
