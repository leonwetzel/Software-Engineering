/**
 * Created by Robert on 24-2-2016.
 */
public class GuardTower {
    private int sightRange = 2;
    private int currentTowers;
    private int highestPoint;
    private int guardTowerLocation;
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
        guardTowerLocation = highestPoint;
        currentTowers = 1;
        determineNeededTowers();
    }

    private void determineNeededTowers()
    {
        searchLeft();
        searchRight();
        System.out.println(currentTowers);
    }

    private void checkRight() {
        int top = 0;
        for(int place : places) {
            if(place > top) {
                top = place;
            }
        }
    }

    private void searchLeft()
    {
        for(int i = guardTowerLocation -1; i > 0 ;i--)
        {
            if(guardTowerLocation < places[i])
            {
                currentTowers++;
            }
        }
    }

    private void searchRight()
    {
        for(int i = guardTowerLocation; i < places.length ;i++)
        {
            if(guardTowerLocation < places[i])
            {
                currentTowers++;
            }
        }
    }


}
