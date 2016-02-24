/**
 * Created by Robert on 24-2-2016.
 */
public class GuardTower {
    private int sightRange = 2;
    private int currentTowers;
    private int highestPoint;
    private int GuadtowerLocation;
    private int[] places = {2,4,3,2,2,2};

    public GuardTower()
    {
        highestPoint = 0;
        for(int i : places)
        {
            if(i > highestPoint)
            {
                highestPoint = i;
            }
        }
        GuadtowerLocation = highestPoint;
        currentTowers = 1;
        determineNeededTowers();
    }

    private void determineNeededTowers()
    {
        searchLeft();
        searchRight();
        System.out.println(currentTowers);
    }

    private void searchLeft()
    {
        for(int i = GuadtowerLocation-1; i > 0 ;i--)
        {
            if(GuadtowerLocation < places[i])
            {
                currentTowers++;
            }
        }
    }

    private void searchRight()
    {
        for(int i = GuadtowerLocation; i < places.length ;i++)
        {
            if(GuadtowerLocation < places[i])
            {
                currentTowers++;
            }
        }
    }


}
