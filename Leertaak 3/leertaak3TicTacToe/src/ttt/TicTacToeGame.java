package ttt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
class TicTacToe
{
	private static final int HUMAN        = 0; 
	private static final int COMPUTER     = 1; 
	public  static final int EMPTY        = 2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;

	private int [ ] [ ] board = new int[ 3 ][ 3 ];
    private Random random = new Random();
	private int side = random.nextInt(2);
	private int position = UNCLEAR;
	private char computerChar, humanChar;

	private static ArrayList<int[]> winConditions = new ArrayList<>();

	// Constructor
	public TicTacToe( ) {
		clearBoard();
		createConditions();
		initSide();
	}
	
	private void initSide() {
	    if (this.side==COMPUTER) { computerChar='X'; humanChar='O'; }
		else                     { computerChar='O'; humanChar='X'; }
    }
    
    public void setComputerPlays() {
        this.side=COMPUTER;
        initSide();
    }
    
    public void setHumanPlays() {
        this.side=HUMAN;
        initSide();
    }

	public boolean computerPlays() {
	    return side==COMPUTER;
	}

	public int chooseMove()
	{
	    //Best best=chooseMove(COMPUTER);
	    //return best.row*3+best.column;
	    return chooseMove(COMPUTER);
    }
    
    // Find optimal move
	private int chooseMove( int side )
	{
	//	int opp;              // The other side
	//	Best reply;           // Opponent's best reply
	//	int simpleEval;       // Result of an immediate evaluation
	//	int bestRow = 0;
	//	int bestColumn = 0;
	//	int value;

		//if( ( simpleEval = positionValue( ) ) != UNCLEAR )
		//	return new Best( simpleEval );

		// TODO: implementeren m.b.v. recursie/backtracking
		int MaxMoves = 0;
		int bestMove = 0;
		HashMap<Integer, Integer> log = new HashMap<>();
		for(int[] i : winConditions)
		{
			for(int j: i)
			{
				switch(j)
				{
					case 0:
						incrementInHashMap(log, j);
					case 1:
						incrementInHashMap(log, j);
					case 2:
						incrementInHashMap(log, j);
					case 3:
						incrementInHashMap(log, j);
					case 4:
						incrementInHashMap(log, j);
					case 5:
						incrementInHashMap(log, j);
					case 6:
						incrementInHashMap(log, j);
					case 7:
						incrementInHashMap(log, j);
					case 8:
						incrementInHashMap(log, j);
				}
				if(log.containsKey(j))
				{
					if(log.get(j) > MaxMoves )
					{
						bestMove = j;
						MaxMoves = log.get(j);
					}
				}
			}
		}
	    return bestMove;
    }

	private void incrementInHashMap(HashMap<Integer, Integer> log, int move)
	{
		int currentCount = 0;
		if(moveOk(move))
		{
			if(log.containsKey(move))
			{
				currentCount = log.get(move);
				currentCount++;
			}
			else
			{
				currentCount++;
			}
			log.put(move, currentCount);
		}
	}
   
    //check if move ok
    public boolean moveOk(int move) {
 		return (move>=0 && move <=8 && getBoardValue(move) == EMPTY);
    }
    
    // play move
    public void playMove(int move) {
		if(moveOk(move)) {
			board[move/3][move%3] = this.side;
			if (side==COMPUTER) this.side=HUMAN;  else this.side=COMPUTER;
		}
		else {
			System.out.println("Illegal move");
		}
	}


	// Simple supporting routines
	private void clearBoard( )
	{
		Arrays.fill(board[0], 2);
		Arrays.fill(board[1], 2);
		Arrays.fill(board[2], 2);
	}

	/**
	 * Checks if board is full.
	 * @return Boolean indicating if board is full or not.
	 */
	private boolean boardIsFull( ) {
		boolean boardIsFull = true;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(squareIsEmpty(i, j)) {
					boardIsFull = false;
					break;
				}
			}
		}
		return boardIsFull;
	}

	/**
	 * 	Returns whether 'side' has won in this position.
	 * 	@param side The side which should be checked.
	 */
	private boolean isAWin(int side) {
	    for(int[] condition: winConditions) {
			if(getBoardValue(condition[0]) == side &&  getBoardValue(condition[1]) == side && getBoardValue(condition[2]) == side) {
				return true;
			}
		}
	    return false;
    }

	/**
	 * 	Play a move, possibly clearing a square.
	 */
	private void place( int row, int column, int piece ) {
		board[row][column] = piece;
	}

	/**
	 * Checks if a spot on the board is empty or not.
	 * @param row Row coordinate
	 * @param column Column coordinate
	 * @return
	 */
	private boolean squareIsEmpty(int row, int column) {
		return board[row][column] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	private int positionValue() {
		// TODO:
		return UNCLEAR;
	}
	
	
	public String toString() {
	    //TODO:
		String plate = "";
		for(int[] row : board) {
			for(int value : row) {
				if(value == 2) {
					plate += ". ";
				} else {
					plate += value + " ";
				}
			}
			plate += "\n";
		}
		return plate;
		//return "...\n...\n...\n";
	}  
	
	public boolean gameOver() {
	    this.position = positionValue();
	    return this.position != UNCLEAR;
    }
    
    public String winner() {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }

	private void createConditions() {
		//Horizontaal
		winConditions.add(new int[]{0,1,2});
		winConditions.add(new int[]{3,4,5});
		winConditions.add(new int[]{6,7,8});
		//Verticaal
		winConditions.add(new int[]{0,3,6});
		winConditions.add(new int[]{1,4,7});
		winConditions.add(new int[]{2,5,8});
		// Diagonaal
		winConditions.add(new int[]{0,4,8});
		winConditions.add(new int[]{2,4,6});
	}

	private int getBoardValue(int move) {
		return board[move/3 ][ move%3 ];
	}
	
	private class Best {
       int row;
       int column;
       int val;

       public Best( int v )
         { this( v, 0, 0 ); }
      
       public Best( int v, int r, int c )
        { val = v; row = r; column = c; }
    }

	class Test extends TestCase {
		private TicTacToe ttt = new TicTacToe();

		public Test() {
		}

		private int testChooseMove() {

			return 0;
		}

		private boolean testIsAWin() {
			return false;
		}

		private int testPositionValue() {
			return -1;
		}

	}
}

