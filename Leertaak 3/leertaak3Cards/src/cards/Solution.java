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

    //
    // array with adjacent card positions lower than the card that is placed
    //                        0   1   2       3    4       5    6   7
    //int [] [] adjacent  = { {}, {}, {1}, {0,2}, {2},  {3,4}, {5},{5}  };
    //
    // array with all adjacent card positions
    //                         0    1        2        3      4          5     6   7
    int [] [] adjacent  = { {3}, {2}, {1,3,4}, {0,2,5}, {2,5},  {3,4,6,7}, {5},{5}  };
    //
    // array with positions to check for bordering
    //
    //  indices cards that must be checked.
    //  e.g. when the 5th card is placed the cards 3&4 can be checked
    //                 0   1  2   3   4     5   6    7
    int [][] check = {{},{},{1},{0},{2},{3,4},{6},{5,7}};


    public Solution() {
    }


    // Checks whether a candidate with card CardChar is in
    // an adjacent position of the board position (row, column)
    // @param row, column, candidate
    // @return Boolean indicating if cardChar is found.
    // can be used in the methods fits and isCorrect
    private boolean bordersCard(int index, char cardChar){
        if(mustBeAdjacentTo(cardChar) == '?') {
            return true;
        }

        if(board[row[index]][column[index]] != null) {
            return false;
        }

        boolean k = false;
        for(int i : adjacent[index]) {
            if(board[row[i]][column[i]] != null) {
                char card = board[row[i]][column[i]].getCardChar();
                if(card == cardChar ) {
                    return false;
                } else if (card == mustBeAdjacentTo(cardChar)) {
                    System.out.println("Borders correctly :D");
                    k = true;
                }
            }
        }
        return k;
    }

    /**
     * Checks whether candidate card of same kind.
     * Checks whether by placing candidate the solution sofar still complies with the rules
     * @param candidate
     * @return boolean indicating whether this candidate can be put in the
     * next free position.
     */
    public boolean fits(Candidate candidate) {
        char currentCard = candidate.getCardChar();
        for(int i = 0; i < row.length;i++)
        {
            if(bordersCard(i, currentCard))
            {
                board[row[i]][column[i]] = candidate;
                // System.out.println(candidate.getCardChar() + " - " + row[i] +  "," + column[i]);
                return true;
            }
        }
        return false;
    }

    public void record(Candidate candidate) {
        int i = this.size(); // i= index in this stack of next for the next candidate
        board [row[i]] [column[i]] = candidate; //x=row, y=column
        System.out.println(candidate.getCardChar() + " -> (" + row[i] + "," + column[i] + ")");
        this.push(candidate);
    }

    public boolean complete() {
        return this.size() == 8;
    }

    public void show() {
        System.out.println(this);
    }

    public Candidate eraseRecording() {
        int i = this.size()-1;           // i= index of the candidate that is removed from this Stack;
        board[row[i]][column[i]] = null; // remove candidate from board
        System.out.println("Truncated (" + row[i] + "," + column[i] + ")");
		return this.pop();
    }

    // can be used in method isCorrect
    private char mustBeAdjacentTo(char card) {
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
        // check of kaart grenst aan zichzelf...

        // check of kaart grenst aan gewenste kaart... (mustBeAdjacentTo())
        return true;
    }


    /**
     * @return a representation of the solution on the board
     */
    public String toString(){
        String s = "";
        for(Candidate i : this) {
            s += i.getCardChar() + "\n";
        }
        return s;
    }

}