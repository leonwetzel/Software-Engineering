package cards;
import java.util.ArrayList;
import java.util.Stack;
/** the solution is a sequence of cards placed on the board according to the card positions
 example without border
 */
public class Solution extends Stack<Candidate>
{
    // The board is an 2D array.
    // 0123
    // 0..-.
    // 1---.
    // 2.---
    // 3..-.
    private Candidate[][] board = new Candidate[4][4];

    // card positions on the board
    // the first card position on the board are
    // {0,2}, {1,0}. {1,1}
    private int[] row    = { 0, 1, 1, 1, 2, 2, 2, 3 };
    private int[] column = { 2, 0, 1, 2, 1, 2, 3, 2 };
	private Integer [][] adjacent = new Integer [8][4];
	private char[] occupiedCardPositions = new char[8];
	int [][] check = {{},{},{1},{0},{2},{3,4},{6},{5,7}};

	private Integer [][] occupied = new Integer [8][2];

    private int[][] test = new int[][] {{row[0], column[0]}};

    //
    // array with adjacent card positions lower than the card that is placed
    //                        0   1   2       3    4       5    6   7
    //int [] [] adjacent  = { {}, {}, {1}, {0,2}, {2},  {3,4}, {5},{5}  };
    //
    // array with all adjacent card positions
    //                         0    1        2        3      4          5     6   7
    //int [] [] adjacent  = { {3}, {2}, {1,3,4}, {0,2,5}, {2,5},  {3,4,6,7}, {5},{5}  };
    //
    // array with positions to check for bordering
    //
    //  indices cards that must be checked.
    //  e.g. when the 5th card is placed the cards 3&4 can be checked
    //                 0   1  2   3   4     5   6    7
	public Solution(){}

    // Checks whether a candidate with card CardChar is in
    // an adjacent position of the board position (row, column)
    // @param row, column, candidate
    // @return Boolean indicating if cardChar is found.
    // can be used in the methods fits and isCorrect
    private boolean bordersCard(int row, int column, char cardChar){
        ArrayList<Candidate> neighbours = new ArrayList<>();
        int limit = board[row].length;
        if(row-1 >= 0) {
            Candidate left = board[row-1][column];
            if(left != null) neighbours.add(left);
        }
        if(row+1 < limit) {
            Candidate right = board[row+1][column];
            if(right != null) neighbours.add(right);
        }
        if(column-1 >= 0) {
            Candidate down = board[row][column-1];
            if(down != null) neighbours.add(down);
        }
        if(column+1 < limit) {
            Candidate top = board[row][column+1];
            if(top != null) neighbours.add(top);
        }

        for(Candidate item : neighbours) {
            if(item != null && item.getCardChar() == cardChar) {
                System.out.println("Matching card char has been found!");
                return true;
            }
        }
        System.out.println("No matching card has been found");
        return false;
    }

    /**
     * Checks whether candidate card of same kind.
     * Checks whether by placing candidate the solution sofar still complies with the rules
     * @param candidate
     * @return boolean indicating whether this candidate can be put in the
     * next free position.
     */
    public boolean fits(Candidate candidate){
		Integer[] available = new Integer[2];
        char currentCard = candidate.getCardChar();
		for(int i = 0; i < occupiedCardPositions.length; i++)
		{
          if(mustBeAdjacentTo(currentCard) == occupiedCardPositions[i] || currentCard == 'J')
          {
              int k = neededPosition(currentCard, i);
              if(occupiedCardPositions[k] == 0 )
              {
                  return true;
              }
          }

		}
        return false;
	}

    private int neededPosition(char card, int j)
    {
        int localRow = row[j];
        int localColumn = column[j];
        for(int k = 0; k < row.length; k++)
        {
            if(k != j)
            {
                if(row[k] == localRow-1 && column[k] == localColumn -1)
                {
                    return k;
                }
                else if(row[k] == localRow+1 && column[k] == localColumn -1)
                {
                    return k;
                }
                else if(row[k] == localRow-1 && column[k] == localColumn +1)
                {
                    return k;
                }
                else if(row[k] == localRow+1 && column[k] == localColumn +1)
                {
                    return k;
                }
            }

        }
        return -1;
    }


    public void record(Candidate candidate)
    {
        int i=this.size(); // i= index in this stack of next for the next candidate
        board [row[i]] [column[i]] = candidate; //x=row, y=column
        this.push(candidate);

    }

    public boolean complete()
    {
        return this.size()==8;
    }

    public void show()
    {
        System.out.println(this);
    }

    public Candidate eraseRecording()
    {
        int i=this.size()-1;           // i= index of the candidate that is removed from this Stack;
        board[row[i]][column[i]]=null; // remove candidate from board
        return this.pop();
    }

    // can be used in method isCorrect
    private char mustBeAdjacentTo(char card)
    {
        if (card=='A') return 'K';
        if (card=='K') return 'Q';
        if (card=='Q') return 'J';
        return '?'; //error
    }

    /**
     * Checks whether the rules below are fulfilled
     * For the positions that can be checked for solution sofar.
     * Rules:
     * Elke aas (ace) grenst (horizontaal of verticaal) aan een heer (king).
     * Elke heer grenst aan een vrouw (queen).
     * Elke vrouw grenst aan een boer (jack).
     * @return true if all checks are correct.
     */
    // uses methods borderCard and mustBeAdjacent to
    private boolean isCorrect() {
        //TODO
        return true;
    }


    /**
     * @return a representation of the solution on the board
     */
    public String toString(){
        //TODO
        return "";
    }

}