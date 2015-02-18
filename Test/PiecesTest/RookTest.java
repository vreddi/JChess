/**
 * 
 */
package PiecesTest;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.ChessPiece.PieceColor;
import Components.Player;
import PieceType.Pawn;
import PieceType.Rook;

/**
 * This is the Test File for Rook.java. Every functionality/method of the Rook Class
 * is tested in this class with edge cases.
 * 
 * @author Vishrut Reddi 
 */
public class RookTest extends TestCase {

	
	/**
	 * To pass this test the constructor responsible to create a Rook with 
	 * a color passed in as parameter should actually be of that color in the 
	 * game.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor1() throws Exception{
		
		Rook r1 = new Rook(PieceColor.WHITE);
		Rook r2 = new Rook(PieceColor.BLACK);
		
		assertEquals(PieceColor.WHITE, r1.getPieceColor());
		assertEquals(PieceColor.BLACK, r2.getPieceColor());
	}
	
	
	/**
	 * To pass this test the constructor responsible for setting the current
	 * position of the Rook should actually set the current position at the spot
	 * indicated by the parameters of the constructor row and column. If they match the real
	 * position then the test passes. NOTE: by setting the position of the piece it is no actually
	 * set on the Chess Board.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor2() throws Exception{
		int row = 0;
		int col = 5;
		
		Rook r1 = new Rook(0,5);
		int rookCurPos[] = r1.getCurrentPosition();
		assertEquals(row, rookCurPos[0]);
		assertEquals(col, rookCurPos[1]);
		assertEquals(r1.getPieceColor(), null);
	}
	
	
	/**
	 * To pass this test the created Rook through the constructor taking in a color,
	 * row and column as Rooks attributes should actually match the Objects properties.
	 * Both the color and current position is verified for this test. If all match only then 
	 * the test passes. NOTE: by setting the position of the piece it is no actually
	 * set on the Chess Board.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor3() throws Exception{
		
		Rook r1 = new Rook(PieceColor.WHITE, 0, 0);
		Rook r2 = new Rook(PieceColor.BLACK, 7, 0);
		int rook1CurPos[] = r1.getCurrentPosition();
		int rook2CurPos[] = r2.getCurrentPosition();
		
		/* Tests for WHITE Rook */
		assertEquals(0, rook1CurPos[0]);
		assertEquals(0, rook1CurPos[1]);
		assertEquals(PieceColor.WHITE, r1.getPieceColor());
		
		/* Tests for BLACK Rook */
		assertEquals(7, rook2CurPos[0]);
		assertEquals(0, rook2CurPos[1]);
		assertEquals(PieceColor.BLACK, r2.getPieceColor());
	}
	
	
	
	
	/* --------------------------CHECKING VALID DOWN MOVES (4 CASES)-----------------------------*/
	/* -----------------------------------DOWN TESTS START HERE------------------------------------ */	
	/**
	 * In this case the entire Chess Board is Empty. The Rook is placed somewhere in the middle 
	 * of the board. The Rook would continue moving down until he reaches the end of the
	 * board. The Expected and Test Case should match to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 1:: Empty Board */
		/* ------------------------------------- */
		ChessBoard board1 = new ChessBoard();	
		Rook r1 = new Rook(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, r1);
		
		for(int row = 2; row >= 0 ; row--){
			int nextMove[] = {row, 3};
			expectedMoves.add(nextMove);
		}
		
		testMoves = r1.getValidDownMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
	}
	
	
	/**
	 * The Rook in this test case is at the down edge of the board already. There is no more
	 * down for him to go. Therefore the Rook is left with no more valid down moves. Hence the
	 * list of valid down moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMove2() throws Exception{
		
		//There are no expected moves in this case. There is no move possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Rook is at row 0 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Rook r2 = new Rook(PieceColor.WHITE, 0, 0);
		board2.setPieceAtSpot(0, 0, r2);

		testMoves = r2.getValidDownMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an enemy unit. So a list of all valid moves in the downward 
	 * direction should be returned by the method under test containing all the open
	 * spots up to and including the enemy unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMove3() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 3: Enemy Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board3 = new ChessBoard();
		Rook r3 = new Rook(PieceColor.WHITE, 7, 5);
		Rook r3Enemy = new Rook(PieceColor.BLACK, 3, 5);
		board3.setPieceAtSpot(7, 5, r3);
		board3.setPieceAtSpot(3, 5, r3Enemy);

		testMoves = r3.getValidDownMoves(board3);
		
		for(int row = 6; row >= 3 ; row--){
			int nextMove[] = {row, 5};
			expectedMoves.add(nextMove);
		}
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an friendly unit. So a list of all valid moves in the down
	 * direction should be returned by the method under test containing all the open
	 * spots up to and not including the friendly unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMove4() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 4: Friendly Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board4 = new ChessBoard();
		Rook r4 = new Rook(PieceColor.WHITE, 7, 5);
		Rook r4Friendly = new Rook(PieceColor.WHITE, 3, 5);
		board4.setPieceAtSpot(7, 5, r4);
		board4.setPieceAtSpot(3, 5, r4Friendly);
		
		testMoves = r4.getValidDownMoves(board4);
		
		for(int row = 6; row >= 4 ; row--){
			int nextMove[] = {row, 5};
			expectedMoves.add(nextMove);
		}
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}	
	/* -----------------------------------DOWN TESTS END HERE------------------------------------ */


	
	
	
	/* --------------------------CHECKING VALID UP MOVES (4 CASES)-----------------------------*/
	/* -----------------------------------UP TESTS START HERE------------------------------------ */
	/**
	 * In this case the entire Chess Board is Empty. The Rook is placed somewhere in the middle 
	 * of the board. The Rook would continue moving Up until he reaches the end of the
	 * board. The Expected and Test Case should match to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 1:: Empty Board */
		/* ------------------------------------- */
		ChessBoard board1 = new ChessBoard();	
		Rook r1 = new Rook(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, r1);
		
		for(int row = 4; row <= 7 ; row++){
			int nextMove[] = {row, 3};
			expectedMoves.add(nextMove);
		}
		
		testMoves = r1.getValidUpMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
	}
	
	
	/**
	 * The Rook in this test case is at the up edge of the board already. There is no more
	 * up for him to go. Therefore the Rook is left with no more valid up moves. Hence the
	 * list of valid up moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMoves2() throws Exception{
		
		//There is no expected move in this case. No move is possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Rook is at row 7 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Rook r2 = new Rook(PieceColor.WHITE, 7, 0);
		board2.setPieceAtSpot(7, 0, r2);
	
		testMoves = r2.getValidUpMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an enemy unit. So a list of all valid moves in the upward 
	 * direction should be returned by the method under test containing all the open
	 * spots up to and including the enemy unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMoves3() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 3: Enemy Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board3 = new ChessBoard();
		Rook r3 = new Rook(PieceColor.WHITE, 0, 5);
		Rook r3Enemy = new Rook(PieceColor.BLACK, 3, 5);
		board3.setPieceAtSpot(0, 5, r3);
		board3.setPieceAtSpot(3, 5, r3Enemy);
		
		testMoves = r3.getValidUpMoves(board3);
		
		for(int row = 1; row <= 3 ; row++){
			int nextMove[] = {row, 5};
			expectedMoves.add(nextMove);
		}
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an friendly unit. So a list of all valid moves in the up
	 * direction should be returned by the method under test containing all the open
	 * spots up to and not including the friendly unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMoves4() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 4: Friendly Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board4 = new ChessBoard();
		Rook r4 = new Rook(PieceColor.WHITE, 0, 5);
		Rook r4Friendly = new Rook(PieceColor.WHITE, 5, 5);
		board4.setPieceAtSpot(0, 5, r4);
		board4.setPieceAtSpot(5, 5, r4Friendly);

		testMoves = r4.getValidUpMoves(board4);
		
		for(int row = 1; row <= 4 ; row++){
			int nextMove[] = {row, 5};
			expectedMoves.add(nextMove);
		}
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	/* -----------------------------------UP TESTS END HERE------------------------------------ */


	
	
	
	
	
	/* --------------------------CHECKING VALID LEFT MOVES (4 CASES)-----------------------------*/
	/* -----------------------------------LEFT TESTS START HERE------------------------------------ */
	/**
	 * In this case the entire Chess Board is Empty. The Rook is placed somewhere in the middle 
	 * of the board. The Rook would continue moving left until he reaches the end of the
	 * board. The Expected and Test Case should match to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidLeftMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 1:: Empty Board */
		/* ------------------------------------- */
		ChessBoard board1 = new ChessBoard();	
		Rook r1 = new Rook(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, r1);
		
		//Filling in expect results
		for(int col = 2; col >= 0 ; col--){
			int nextMove[] = {3, col};
			expectedMoves.add(nextMove);
		}
		
		testMoves = r1.getValidLeftMoves(board1);
		
		//Comparing the expected and the test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	/**
	 * The Rook in this test case is at the left edge of the board already. There is no more
	 * left for him to go. Therefore the Rook is left with no more valid left moves. Hence the
	 * list of valid left moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidLeftMoves2() throws Exception{
		

		//There is no expected move in this case. No move is possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Rook is at column 0 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Rook r2 = new Rook(PieceColor.WHITE, 7, 0);
		board2.setPieceAtSpot(7, 0, r2);
	
		testMoves = r2.getValidLeftMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an enemy unit. So a list of all valid moves in the left
	 * direction should be returned by the method under test containing all the open
	 * spots up to and including the enemy unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidLeftMoves3() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 3: Enemy Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board3 = new ChessBoard();
		Rook r3 = new Rook(PieceColor.WHITE, 0, 5);
		Rook r3Enemy = new Rook(PieceColor.BLACK, 0, 2);
		board3.setPieceAtSpot(0, 5, r3);
		board3.setPieceAtSpot(0, 2, r3Enemy);
		
		testMoves = r3.getValidLeftMoves(board3);
		
		//Filling in expected result
		for(int col = 4; col >= 2 ; col--){
			int nextMove[] = {0, col};
			expectedMoves.add(nextMove);
		}
		
		//Comparing expected and test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an friendly unit. So a list of all valid moves in the left
	 * direction should be returned by the method under test containing all the open
	 * spots up to and not including the friendly unit's spot to pass this test. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidLeftMoves4() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 4: Friendly Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board4 = new ChessBoard();
		Rook r4 = new Rook(PieceColor.WHITE, 0, 5);
		Rook r4Friendly = new Rook(PieceColor.WHITE, 0, 3);
		board4.setPieceAtSpot(0, 5, r4);
		board4.setPieceAtSpot(0, 3, r4Friendly);

		testMoves = r4.getValidLeftMoves(board4);
		
		//Filling in expected results
		for(int col = 4; col >= 4 ; col--){
			int nextMove[] = {0, col};
			expectedMoves.add(nextMove);
		}
		
		//Comparing expected and test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	/* -----------------------------------LEFT TESTS END HERE------------------------------------ */

	
	
	
	
	
	
	/* --------------------------CHECKING VALID RIGHT MOVES (4 CASES)-----------------------------*/
	/* -----------------------------------RIGHT TESTS START HERE------------------------------------ */
	
	/**
	 * In this case the entire Chess Board is Empty. The Rook is placed somewhere in the middle 
	 * of the board. The Rook would continue moving right until he reaches the end of the
	 * board. The Expected and Test Case should match to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidRightMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 1:: Empty Board */
		/* ------------------------------------- */
		ChessBoard board1 = new ChessBoard();	
		Rook r1 = new Rook(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, r1);
		
		//Filling in expect results
		for(int col = 4; col <= 7 ; col++){
			int nextMove[] = {3, col};
			expectedMoves.add(nextMove);
		}
		
		testMoves = r1.getValidRightMoves(board1);
		
		//Comparing the expected and the test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	/**
	 * The Rook in this test case is at the right edge of the board already. There is no more
	 * right for him to go. Therefore the Rook is left with no more valid right moves. Hence the
	 * list of valid right moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidRightMoves2() throws Exception{
		

		//There is no expected move in this case. No move is possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Rook is at column 7 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Rook r2 = new Rook(PieceColor.WHITE, 7, 7);
		board2.setPieceAtSpot(7, 7, r2);
	
		testMoves = r2.getValidRightMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an enemy unit. So a list of all valid moves in the right
	 * direction should be returned by the method under test containing all the open
	 * spots up to and including the enemy unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidRightMoves3() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 3: Enemy Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board3 = new ChessBoard();
		Rook r3 = new Rook(PieceColor.WHITE, 0, 4);
		Rook r3Enemy = new Rook(PieceColor.BLACK, 0, 6);
		board3.setPieceAtSpot(0, 4, r3);
		board3.setPieceAtSpot(0, 6, r3Enemy);
		
		testMoves = r3.getValidRightMoves(board3);
		
		//Filling in expected result
		for(int col = 5; col <= 6 ; col++){
			int nextMove[] = {0, col};
			expectedMoves.add(nextMove);
		}
		
		//Comparing expected and test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	/**
	 * In this test case the Rook is moving through a path where his path is 
	 * intercepted by an friendly unit. So a list of all valid moves in the right
	 * direction should be returned by the method under test containing all the open
	 * spots up to and not including the friendly unit's spot to pass this test. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidRightMoves4() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 4: Friendly Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board4 = new ChessBoard();
		Rook r4 = new Rook(PieceColor.WHITE, 0, 2);
		Rook r4Friendly = new Rook(PieceColor.WHITE, 0, 6);
		board4.setPieceAtSpot(0, 5, r4);
		board4.setPieceAtSpot(0, 6, r4Friendly);

		testMoves = r4.getValidRightMoves(board4);
		
		//Filling in expected results
		for(int col = 3; col <= 5 ; col++){
			int nextMove[] = {0, col};
			expectedMoves.add(nextMove);
		}
		
		//Comparing expected and test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	/* -----------------------------------RIGHT TESTS END HERE------------------------------------ */

	
	
	/**
	 * This test passes if the Rook for his current position returns a list of all the
	 * moves (valid) he can make. The List should contain Up moves first, followed by
	 * right moves, then down moves and then finally left moves.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetNextMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* SIMPLE EMTY BOARD TEST (Sub-parts have already been tested) */
		/* ------------------------------------- */
		ChessBoard board = new ChessBoard();
		Rook r = new Rook(PieceColor.WHITE, 4, 4);
		board.setPieceAtSpot(4, 4, r);
		
		testMoves = r.getNextMoves(board);
		
		//Filling in expected result (UP)
		for(int row = 5; row <= 7 ; row++){
			int nextMove[] = {row, 4};
			expectedMoves.add(nextMove);
		}
		
		//Filling in expected result (RIGHT)
		for(int col = 5; col <= 7 ; col++){
			int nextMove[] = {4, col};
			expectedMoves.add(nextMove);
		}
				
		//Filling in expected result (DOWN)
		for(int row = 3; row >= 0 ; row--){
			int nextMove[] = {row, 4};
			expectedMoves.add(nextMove);
		}
				
		//Filling in expected result (LEFT)
		for(int col = 3; col >= 0 ; col--){
			int nextMove[] = {4, col};
			expectedMoves.add(nextMove);
		}				
						
		//Comparing expected and test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	/**
	 * In this test case the new game board with pieces at their initial position is set. 
	 * The Rooks movement from a valid source to destination is tested. For the Rook, the attacking
	 * move is checked, a normal move is checked and then an out of board move is tested.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMoveTo1() throws Exception{
		
		ChessBoard board = new ChessBoard();
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		//Setting Opponents
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		board.setNewGame(p1, p2);
		
		//Assigning test type in console
		System.out.println("\n ------------------------- testMoveTo1 ------------------------------- \n");

		Pawn friendly = (Pawn)board.getPieceAtSpot(1, 7);
		board.removePieceFromSpot(1, 7);
		board.setPieceAtSpot(3, 3, friendly);
		
		ChessPiece whiteRook = board.getPieceAtSpot(0, 7);
		
		//Logging state in Console
		board.printBoardState();
		
		boolean moveSuccessful = whiteRook.validMoveTo(4, 7, board, p1);
		
		board.printBoardState();
		
		assertEquals(true, moveSuccessful);
	
		int rookCurPos[] = whiteRook.getCurrentPosition();

		assertEquals(4, rookCurPos[0]);
		assertEquals(7, rookCurPos[1]);
		
		//Attacking Move
		moveSuccessful = whiteRook.validMoveTo(6, 7, board, p1);
		assertEquals(true, moveSuccessful);
		
		//Logging state in Console
		board.printBoardState();
		
		//Moving off the board
		moveSuccessful = whiteRook.validMoveTo(6, 8, board, p1);
		assertEquals(false, moveSuccessful);
		
		//Logging state in Console
		board.printBoardState();
	}
	
	
	/**
	 * In this test case the Rook is faced with a threat condition i.e when the King is under Check.
	 * This test makes sure the correct implementation of the Rook's movement under this threat case.
	 * The Rook cannot ignore the check condition. He must eliminate the condition or he cannot move, 
	 * some-other move needs to be planned.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMoveTo2() throws Exception{
		
		ChessBoard board = new ChessBoard();
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		board.setNewGame(p1, p2);
		
		//Setting Opponents
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		//Logging test type in console
		System.out.println("\n ------------------------- testMoveTo2 ------------------------------- \n");
		
		//Logging state in Console
		board.printBoardState();
		
		//Setting situation
		ChessPiece enemyRook = board.getPieceAtSpot(7, 7);
		ChessPiece friendlyRook = board.getPieceAtSpot(0,  7);
		ChessPiece friendlyPawn = board.getPieceAtSpot(1, 4);
		
		board.removePieceFromSpot(1, 4);
		board.setPieceAtSpot(2, 1, friendlyPawn);
		
		board.removePieceFromSpot(7, 7);
		board.setPieceAtSpot(5, 4, enemyRook);
		
		board.removePieceFromSpot(0, 7);
		board.setPieceAtSpot(5, 7, friendlyRook);
		
		//Logging state in Console
		System.out.println("\n Moving Pieces to create a threat scene...");
		board.printBoardState();
		
		
		//Now the WHITE player is in check condition
		//Sub-Test 1: Rook ignores the check condition by attacking
		boolean moveSuccess = friendlyRook.validMoveTo(6, 7, board, p1); 
		int curPos[] = friendlyRook.getCurrentPosition();
		assertEquals(false, moveSuccess);
		assertEquals(5,curPos[0]);
		assertEquals(7, curPos[1]);
		
		//Logging state in Console
		board.printBoardState();
		
		//Sub-Test 2: Rook ignores the check condition by doing a normal move
		moveSuccess = friendlyRook.validMoveTo(4, 7, board, p1);
		assertEquals(false, moveSuccess);
		assertEquals(5,curPos[0]);
		assertEquals(7, curPos[1]);
		
		//Logging state in Console
		board.printBoardState();
		
		//Sub-Test 3: Rook attacks the Enemy Rook eliminating the threat.
		moveSuccess = friendlyRook.validMoveTo(5, 4, board, p1);
		assertEquals(true, moveSuccess);
		assertEquals(5,curPos[0]);
		assertEquals(4, curPos[1]);
		
		//Logging state in Console
		board.printBoardState();		
	}
	
	
}
