package ttt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Leon on 3-3-2016.
 */
public class TestTicTacToeGame {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsAWin() {
		TicTacToe t = new TicTacToe();
		Assert.assertFalse(t.isAWin(TicTacToe.COMPUTER));
		Assert.assertFalse(t.isAWin(TicTacToe.HUMAN));
	}

	@Test
	public void testPositionValue() {
		TicTacToe game = new TicTacToe();
		assertEquals(game.positionValue(),TicTacToe.UNCLEAR);
		game.setComputerPlays();
		game.playMove(6);
		game.playMove(0);
		assertEquals(game.positionValue(),TicTacToe.UNCLEAR);
		game.playMove(7);
		assertEquals(game.positionValue(),TicTacToe.COMPUTER_WIN);
	}

	@Test
	public void testChooseMove() {
		TicTacToe game = new TicTacToe();
		game.setComputerPlays();
		game.playMove(0);
		game.playMove(3);
		game.playMove(1);
		game.playMove(4);
		assertEquals(game.chooseMove(),5);
	}
}
