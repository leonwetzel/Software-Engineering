package ttt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
class TicTacToe
{
	public  static final int HUMAN        = 0;
	public  static final int COMPUTER     = 1;
	public  static final int EMPTY        = 2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;

	private int[][] board = new int[ 3 ][ 3 ];
    private Random random = new Random();
	private int side = random.nextInt(2);
	private int position = UNCLEAR;
	private char computerChar, humanChar;

	private static ArrayList<int[]> winConditions = new ArrayList<>();

	// Constructor
	public TicTacToe( )
	{
		clearBoard( );
		createConditions();
		initSide();
	}
	
	private void initSide()
	{
	    if (this.side==COMPUTER) { computerChar='X'; humanChar='O'; }
		else                     { computerChar='O'; humanChar='X'; }
    }
    
    public void setComputerPlays()
    {
        this.side=COMPUTER;
        initSide();
    }
    
    public void setHumanPlays()
    {
        this.side=HUMAN;
        initSide();
    }

	public boolean computerPlays()
	{
	    return side==COMPUTER;
	}

	public int chooseMove()
	{
	    //Best best=chooseMove(COMPUTER);
	    //return best.row*3+best.column;
		if(findWin(COMPUTER) >= 0)
		{
			return findWin(COMPUTER);
		}
		if(findWin(HUMAN) >= 0)
		{
			return findWin(HUMAN);
		}
		return chooseMove(COMPUTER);
    }
    
    // Find optimal move
	private int chooseMove(int side)
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
			if(conditionIsValid(i, side))
			{
				for (int j : i) {
					switch (j) {
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
					if (log.containsKey(j)) {
						if (log.get(j) > MaxMoves) {
							bestMove = j;
							MaxMoves = log.get(j);
						}
					}
				}
			}
		}
		if(log.size() == 0)
		{
			return fillHole();
		}
	    return bestMove;
    }

	private int fillHole()
	{
		for(int i = 0; i <board.length ;i++)
		{
			for(int j = 0;j < board.length; j++)
			{
				if(board[i][j] == EMPTY)
				{
					return (3*i)+j;
				}
			}
		}
		System.out.println("No holes found");
		return 0;
	}

	private int findWin(int side)
	{
		int win = -1;
		for(int[] i: winConditions)
		{
			int value1 = returnBoardValue(i[0]);
			int value2 = returnBoardValue(i[1]);
			int value3 = returnBoardValue(i[2]);
			if(value1 == side && value2 == side && value3 == EMPTY) {
				return i[2];
			} else if(value1 == side && value2 == EMPTY && value3 == side) {
				return i[1];
			} else if(value1 == EMPTY && value2 == side && value3 == side) {
				return i[0];
			}
		}
		return win;
	}

	private boolean conditionIsValid(int[] condition, int side) {
		if(returnBoardValue(condition[0]) != side && returnBoardValue(condition[0]) !=EMPTY ) {
			return false;
		} else if(returnBoardValue(condition[1]) != side && returnBoardValue(condition[1]) !=EMPTY ) {
			return false;
		} else if(returnBoardValue(condition[2]) != side &&  returnBoardValue(condition[2]) !=EMPTY ) {
			return false;
		}
		return true;
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

	/**
	 * 	check if move ok
	 */
    public boolean moveOk(int move) {
 		return (move>=0 && move <=8 && getBoardValue(move) == EMPTY);
    }

	/**
	 * 	play move
 	 */
    public void playMove(int move) {
		if(moveOk(move)) {
			board[move/3][move%3] = this.side;
			if (side==COMPUTER) this.side=HUMAN;  else this.side=COMPUTER;
		} else {
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
	public boolean isAWin(int side) {
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
	 * @param row Row coordinate.
	 * @param column Column coordinate.
	 * @return Boolean indicating if square is empty or not.
	 */
	private boolean squareIsEmpty(int row, int column) {
		return board[row][column] == EMPTY;
	}

	/**
	 * 	Compute static value of current position (win, draw, etc.)
	 * 	@return Position
	 */
	public int positionValue() {
		return position;
	}

	/**
	 * Displays the state of the board.
	 * @return State of the board.
	 */
	public String toString() {
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
	}  
	
	public boolean gameOver() {
		if(isAWin(COMPUTER))
		{
			System.out.println("The Computer has won");
			return true;
		}
		if(isAWin(HUMAN))
		{
			System.out.println("You won");
			return true;
		}
		if(boardIsFull())
		{
			System.out.println("Draw");
			return true;
		}
	    this.position = positionValue();
	    return this.position != UNCLEAR;
    }
    
    public String winner() {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }

	private void createConditions() {
		// Horizontaal
		winConditions.add(new int[]{0,1,2});
		winConditions.add(new int[]{3,4,5});
		winConditions.add(new int[]{6,7,8});
		// Verticaal
		winConditions.add(new int[]{0,3,6});
		winConditions.add(new int[]{1,4,7});
		winConditions.add(new int[]{2,5,8});
		// Diagonaal
		winConditions.add(new int[]{0,4,8});
		winConditions.add(new int[]{2,4,6});
	}

	private int returnBoardValue(int coordinaat)
	{
		return board[coordinaat/3][coordinaat%3];
	}

	private int getBoardValue(int move) {
		return board[move/3][move%3];
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
}

