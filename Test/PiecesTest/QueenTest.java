/**
 * 
 */
package PiecesTest;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece.PieceColor;
import PieceType.Queen;

/**
 * This is the Test File for Queen.java. Every functionality/method of the Queen Class
 * is tested in this class with edge cases.
 * 
 * @author Vishrut Reddi
 *
 */
public class QueenTest extends TestCase {

	/**
	 * To pass this test the constructor responsible to create a Queen with 
	 * a color passed in as parameter should actually be of that color in the 
	 * game.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor1() throws Exception{
		
		Queen q1 = new Queen(PieceColor.WHITE);
		Queen q2 = new Queen(PieceColor.BLACK);
		
		assertEquals(PieceColor.WHITE, q1.getPieceColor());
		assertEquals(PieceColor.BLACK, q2.getPieceColor());
	}
	
	
	/**
	 * To pass this test the constructor responsible for setting the current
	 * position of the Queen should actually set the current position at the spot
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
		
		Queen q1 = new Queen(0,5);
		int QueenCurPos[] = q1.getCurrentPosition();
		assertEquals(row, QueenCurPos[0]);
		assertEquals(col, QueenCurPos[1]);
		assertEquals(q1.getPieceColor(), null);
	}
	
	
	/**
	 * To pass this test the created Queen through the constructor taking in a color,
	 * row and column as Queens attributes should actually match the Objects properties.
	 * Both the color and current position is verified for this test. If all match only then 
	 * the test passes. NOTE: by setting the position of the piece it is no actually
	 * set on the Chess Board.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor3() throws Exception{
		
		Queen q1 = new Queen(PieceColor.WHITE, 0, 0);
		Queen q2 = new Queen(PieceColor.BLACK, 7, 0);
		int Queen1CurPos[] = q1.getCurrentPosition();
		int Queen2CurPos[] = q2.getCurrentPosition();
		
		/* Tests for WHITE Queen */
		assertEquals(0, Queen1CurPos[0]);
		assertEquals(0, Queen1CurPos[1]);
		assertEquals(PieceColor.WHITE, q1.getPieceColor());
		
		/* Tests for BLACK Queen */
		assertEquals(7, Queen2CurPos[0]);
		assertEquals(0, Queen2CurPos[1]);
		assertEquals(PieceColor.BLACK, q2.getPieceColor());
	}
	
	
	
	
	/* --------------------------CHECKING VALID DOWN MOVES (4 CASES)-----------------------------*/
	/* -----------------------------------DOWN TESTS START HERE------------------------------------ */	
	/**
	 * In this case the entire Chess Board is Empty. The Queen is placed somewhere in the middle 
	 * of the board. The Queen would continue moving down until she reaches the end of the
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
		Queen q1 = new Queen(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, q1);
		
		for(int row = 2; row >= 0 ; row--){
			int nextMove[] = {row, 3};
			expectedMoves.add(nextMove);
		}
		
		testMoves = q1.getValidDownMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
	}
	
	
	/**
	 * The Queen in this test case is at the down edge of the board already. There is no more
	 * down for her to go. Therefore the Queen is left with no more valid down moves. Hence the
	 * list of valid down moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMove2() throws Exception{
		
		//There are no expected moves in this case. There is no move possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Queen is at row 0 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Queen q2 = new Queen(PieceColor.WHITE, 0, 0);
		board2.setPieceAtSpot(0, 0, q2);

		testMoves = q2.getValidDownMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q3 = new Queen(PieceColor.WHITE, 7, 5);
		Queen q3Enemy = new Queen(PieceColor.BLACK, 3, 5);
		board3.setPieceAtSpot(7, 5, q3);
		board3.setPieceAtSpot(3, 5, q3Enemy);

		testMoves = q3.getValidDownMoves(board3);
		
		//Filling in Expected Moves
		for(int row = 6; row >= 3 ; row--){
			int nextMove[] = {row, 5};
			expectedMoves.add(nextMove);
		}
		
		//Comparing expected and test moves
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q4 = new Queen(PieceColor.WHITE, 7, 5);
		Queen q4Friendly = new Queen(PieceColor.WHITE, 3, 5);
		board4.setPieceAtSpot(7, 5, q4);
		board4.setPieceAtSpot(3, 5, q4Friendly);
		
		testMoves = q4.getValidDownMoves(board4);
		
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


	
	
	/* --------------------------CHECKING VALID DOWN LEFT MOVES (4 CASES)-----------------------------*/
	/* -----------------------------------DOWN LEFT TESTS START HERE------------------------------------ */	
	/**
	 * In this case the entire Chess Board is Empty. The Queen is placed somewhere in the middle 
	 * of the board. The Queen would continue moving down left diagonal until she reaches the end of the
	 * board. The Expected and Test Case should match to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownLeftMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 1:: Empty Board */
		/* ------------------------------------- */
		ChessBoard board1 = new ChessBoard();	
		Queen q1 = new Queen(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, q1);
		int col = 2;		//Checking column start point
		
		//Filling in expected moves
		for(int row = 2; row >= 0 ; row--){
			int nextMove[] = {row, col};
			expectedMoves.add(nextMove);
			col--;
			
			//Out of board reached by column first
			if(col < 0)
				break;
		}
		
		testMoves = q1.getValidDownLeftMoves(board1);
		
		//Comparing expected and test moves
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
	}
	
	
	/**
	 * The Queen in this test case is at the down left edge of the board already. There is no more
	 * down left for her to go. Therefore the Queen is left with no more valid down left diagonal moves. 
	 * Hence the list of valid down left moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownLeftMove2() throws Exception{
		
		//There are no expected moves in this case. There is no move possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Queen is at row 0 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Queen q2 = new Queen(PieceColor.WHITE, 0, 0);
		board2.setPieceAtSpot(0, 0, q2);

		testMoves = q2.getValidDownLeftMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
	 * intercepted by an enemy unit. So a list of all valid moves in the downward left
	 * direction should be returned by the method under test containing all the open
	 * spots up to and including the enemy unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownLeftMove3() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 3: Enemy Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board3 = new ChessBoard();
		Queen q3 = new Queen(PieceColor.WHITE, 7, 5);
		Queen q3Enemy = new Queen(PieceColor.BLACK, 3, 1);
		board3.setPieceAtSpot(7, 5, q3);
		board3.setPieceAtSpot(3, 1, q3Enemy);
		int col = 4;		//Check column starting point

		testMoves = q3.getValidDownLeftMoves(board3);
		
		//Filling in Expected Moves
		for(int row = 6; row >= 3 ; row--){
			int nextMove[] = {row, col};
			expectedMoves.add(nextMove);
			
			col--;
			//Out of board reached first by column
			if(col < 0)
				break;
		}
		
		//Comparing expected and test moves
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
	 * intercepted by an friendly unit. So a list of all valid moves in the down left
	 * direction should be returned by the method under test containing all the open
	 * spots up to and not including the friendly unit's spot to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownLeftMove4() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 4: Friendly Unit in the Way */
		/* ------------------------------------- */
		ChessBoard board4 = new ChessBoard();
		Queen q4 = new Queen(PieceColor.WHITE, 7, 5);
		Queen q4Friendly = new Queen(PieceColor.WHITE, 3, 1);
		board4.setPieceAtSpot(7, 5, q4);
		board4.setPieceAtSpot(3, 1, q4Friendly);
		int col = 4; 		//Checking column starting point
		
		testMoves = q4.getValidDownLeftMoves(board4);
		
		//Filling in the expected values
		for(int row = 6; row >= 4 ; row--){
			int nextMove[] = {row, col};
			expectedMoves.add(nextMove);
			
			col--;
			//If column goes out of the board
			if(col < 0)
				break;
		}
		
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}	
	/* -----------------------------------DOWN LEFT TESTS END HERE------------------------------------ */
	
	
	
	
	
	
	/* --------------------------CHECKING VALID UP MOVES (4 CASES)-----------------------------*/
	/* -----------------------------------UP TESTS START HERE------------------------------------ */
	/**
	 * In this case the entire Chess Board is Empty. The Queen is placed somewhere in the middle 
	 * of the board. The Queen would continue moving Up until she reaches the end of the
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
		Queen q1 = new Queen(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, q1);
		
		for(int row = 4; row <= 7 ; row++){
			int nextMove[] = {row, 3};
			expectedMoves.add(nextMove);
		}
		
		testMoves = q1.getValidUpMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
	}
	
	
	/**
	 * The Queen in this test case is at the up edge of the board already. There is no more
	 * up for her to go. Therefore the Queen is left with no more valid up moves. Hence the
	 * list of valid up moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMoves2() throws Exception{
		
		//There is no expected move in this case. No move is possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Queen is at row 7 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Queen q2 = new Queen(PieceColor.WHITE, 7, 0);
		board2.setPieceAtSpot(7, 0, q2);
	
		testMoves = q2.getValidUpMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q3 = new Queen(PieceColor.WHITE, 0, 5);
		Queen q3Enemy = new Queen(PieceColor.BLACK, 3, 5);
		board3.setPieceAtSpot(0, 5, q3);
		board3.setPieceAtSpot(3, 5, q3Enemy);
		
		testMoves = q3.getValidUpMoves(board3);
		
		//Filling in the expected moves
		for(int row = 1; row <= 3 ; row++){
			int nextMove[] = {row, 5};
			expectedMoves.add(nextMove);
		}
		
		//Comparing the expected and test moves
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q4 = new Queen(PieceColor.WHITE, 0, 5);
		Queen q4Friendly = new Queen(PieceColor.WHITE, 5, 5);
		board4.setPieceAtSpot(0, 5, q4);
		board4.setPieceAtSpot(5, 5, q4Friendly);

		testMoves = q4.getValidUpMoves(board4);
		
		//Filling in the expected moves
		for(int row = 1; row <= 4 ; row++){
			int nextMove[] = {row, 5};
			expectedMoves.add(nextMove);
		}
		
		//comparing the expected moves and the test moves
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
	 * In this case the entire Chess Board is Empty. The Queen is placed somewhere in the middle 
	 * of the board. The Queen would continue moving left until she reaches the end of the
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
		Queen q1 = new Queen(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, q1);
		
		//Filling in expect results
		for(int col = 2; col >= 0 ; col--){
			int nextMove[] = {3, col};
			expectedMoves.add(nextMove);
		}
		
		testMoves = q1.getValidLeftMoves(board1);
		
		//Comparing the expected and the test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	/**
	 * The Queen in this test case is at the left edge of the board already. There is no more
	 * left for her to go. Therefore the Queen is left with no more valid left moves. Hence the
	 * list of valid left moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidLeftMoves2() throws Exception{
		

		//There is no expected move in this case. No move is possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Queen is at column 0 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Queen q2 = new Queen(PieceColor.WHITE, 7, 0);
		board2.setPieceAtSpot(7, 0, q2);
	
		testMoves = q2.getValidLeftMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q3 = new Queen(PieceColor.WHITE, 0, 5);
		Queen q3Enemy = new Queen(PieceColor.BLACK, 0, 2);
		board3.setPieceAtSpot(0, 5, q3);
		board3.setPieceAtSpot(0, 2, q3Enemy);
		
		testMoves = q3.getValidLeftMoves(board3);
		
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
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q4 = new Queen(PieceColor.WHITE, 0, 5);
		Queen q4Friendly = new Queen(PieceColor.WHITE, 0, 3);
		board4.setPieceAtSpot(0, 5, q4);
		board4.setPieceAtSpot(0, 3, q4Friendly);

		testMoves = q4.getValidLeftMoves(board4);
		
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
	 * In this case the entire Chess Board is Empty. The Queen is placed somewhere in the middle 
	 * of the board. The Queen would continue moving right until she reaches the end of the
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
		Queen q1 = new Queen(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, q1);
		
		//Filling in expect results
		for(int col = 4; col <= 7 ; col++){
			int nextMove[] = {3, col};
			expectedMoves.add(nextMove);
		}
		
		testMoves = q1.getValidRightMoves(board1);
		
		//Comparing the expected and the test cases
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	/**
	 * The Queen in this test case is at the right edge of the board already. There is no more
	 * right for her to go. Therefore the Queen is left with no more valid right moves. Hence the
	 * list of valid right moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidRightMoves2() throws Exception{
		

		//There is no expected move in this case. No move is possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Queen is at column 7 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Queen q2 = new Queen(PieceColor.WHITE, 7, 7);
		board2.setPieceAtSpot(7, 7, q2);
	
		testMoves = q2.getValidRightMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q3 = new Queen(PieceColor.WHITE, 0, 4);
		Queen q3Enemy = new Queen(PieceColor.BLACK, 0, 6);
		board3.setPieceAtSpot(0, 4, q3);
		board3.setPieceAtSpot(0, 6, q3Enemy);
		
		testMoves = q3.getValidRightMoves(board3);
		
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
	 * In this test case the Queen is moving through a path where her path is 
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
		Queen q4 = new Queen(PieceColor.WHITE, 0, 2);
		Queen q4Friendly = new Queen(PieceColor.WHITE, 0, 6);
		board4.setPieceAtSpot(0, 5, q4);
		board4.setPieceAtSpot(0, 6, q4Friendly);

		testMoves = q4.getValidRightMoves(board4);
		
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
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMoveTo() throws Exception{
		
	}

}


