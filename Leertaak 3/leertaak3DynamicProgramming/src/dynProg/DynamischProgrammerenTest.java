package dynProg;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DynamischProgrammerenTest {
	int[] numbers = null;
	int sum;
	Solver solver;

	@Test
	public void testRecursive(){
		solver = new RecursiveSolver();
		doTest();
	}

	@Test
	public void testBottomUp(){
		solver = new BottomUpSolver();
		doTest();
	}

	@Test
	public void testTopDown(){
		solver = new TopDownSolver();
		doTest();
	}

	private void doTest(){
		// 3+5+9=17
		assertTrue( solver.solve( new int[]{3,5,7,9,11}, 17 ));
		// Lukt niet
		assertFalse( solver.solve( new int[]{2,4}, 5 ));
		// E�n te weinig
		assertFalse( solver.solve( new int[]{1,1,2,2,3,3,4,4,5,5}, 31 ) );
		// Precies goed
		assertTrue( solver.solve( new int[]{1,1,1,2,2,3,3,4,4,5,5}, 31 ) );
	}


}
