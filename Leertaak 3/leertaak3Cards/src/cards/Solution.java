package cards;
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
	private char[] occupiedCardNames = new char[8];
	int [][] check = {{},{},{1},{0},{2},{3,4},{6},{5,7}};

	private Integer [][] occupied = new Integer [8][2];
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
	public Solution(){
     }


    // Checks whether a candidate with card CardChar is in
    // an adjacent position of the board position (row, column)
    // @param row, column, candidate
    // @return Boolean indicating if cardChar is found.
    // can be used in the methods fits and isCorrect
    private boolean bordersCard(int row, int column, char cardChar){
		char ruleCard = mustBeAdjacentTo(cardChar);
        Candidate current = board[row][column];
        int coordinateOne = row -1;
        int coordinateTwo = row + 1;
        int coordinateThree = column -1;
        int coordinateFour = column +1;
        if(coordinateOne >= 0)
        {
            Candidate left = board[coordinateOne][column];
        }
        if(coordinateTwo >= 0)
        {
            Candidate right = board[coordinateTwo][column];
        }
        if(coordinateThree >= 0)
        {
            Candidate down = board[row][coordinateThree];
        }
        if(coordinateFour >= 0)
        {
            Candidate top = board[row][coordinateFour];
        }


        return true;
    }


    /**
     * Checks whether candidate card of same kind.
     * Checks whether by placing candidate the solution sofar still complies with the rules
     * @param candidate
     * @return boolean indicating whether this candidate can be put in the
     * next free position.
     */
    public boolean fits(Candidate candidate){
		Integer[] availble = new Integer[2];
		int k =0;
		for(Integer [] i: occupied)
		{
            for(Integer  j : i)
			{
				if(j !=null)
				{
					availble = i;
					break;
				}
			}
			if(availble.length == 2)
			{	//Do adjacent check
				if (bordersCard(availble[0], availble[1], candidate.getCardChar()))
				{
					occupiedCardNames[k] = candidate.getCardChar();
					occupied[k] = availble;
				}
            }
            k++;
		}
        return true;
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