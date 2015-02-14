/**
 * 
 */
package PiecesTest;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece.PieceColor;
import PieceType.Pawn;
import PieceType.Rook;

/**
 * @author vishrutreddi
 *
 */
public class PawnTest extends TestCase{


	/**
	 * To pass this test the constructor responsible to create a Pawn with 
	 * a color passed in as parameter should actually be of that color in the 
	 * game.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor1() throws Exception{
		
		Pawn p1 = new Pawn(PieceColor.WHITE);
		Pawn p2 = new Pawn(PieceColor.BLACK);
		
		assertEquals(PieceColor.WHITE, p1.getPieceColor());
		assertEquals(PieceColor.BLACK, p2.getPieceColor());
	}
	
	
	/**
	 * To pass this test the constructor responsible for setting the current
	 * position of the Pawn should actually set the current position at the spot
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
		
		Pawn p1 = new Pawn(0,5);
		int pawnCurPos[] = p1.getCurrentPosition();
		assertEquals(row, pawnCurPos[0]);
		assertEquals(col, pawnCurPos[1]);
		assertEquals(p1.getPieceColor(), null);
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
		
		Pawn p1 = new Pawn(PieceColor.WHITE, 0, 0);
		Pawn p2 = new Pawn(PieceColor.BLACK, 7, 0);
		int pawn1CurPos[] = p1.getCurrentPosition();
		int pawn2CurPos[] = p2.getCurrentPosition();
		
		/* Tests for WHITE Pawn */
		assertEquals(0, pawn1CurPos[0]);
		assertEquals(0, pawn1CurPos[1]);
		assertEquals(PieceColor.WHITE, p1.getPieceColor());
		
		/* Tests for BLACK Pawn */
		assertEquals(7, pawn2CurPos[0]);
		assertEquals(0, pawn2CurPos[1]);
		assertEquals(PieceColor.BLACK, p2.getPieceColor());
	}
	
	
	/**
	 * Tests if the Pawn under test if moved would count as the first
	 * move or not.
	 * 
	 * @throws Exception
	 */
	public void testIsFirstMove() throws Exception{
		
		ChessBoard board = new ChessBoard();
		
		Pawn p1 = new Pawn(PieceColor.WHITE, 1, 1);		//Starting Position
		board.setPieceAtSpot(1, 1, p1);
		Pawn p2 = new Pawn(PieceColor.WHITE, 2, 1);		//Not a Starting position
		board.setPieceAtSpot(2, 1, p2);
		Pawn p3 = new Pawn(PieceColor.BLACK, 6, 1);		//Starting Position
		board.setPieceAtSpot(6, 1, p3);
		Pawn p4 = new Pawn(PieceColor.BLACK, 4, 5);		//Not a Starting position
		board.setPieceAtSpot(4, 5, p4);
		
		assertEquals(true, p1.isFirstMove());
		assertEquals(false, p2.isFirstMove());
		assertEquals(true, p3.isFirstMove());
		assertEquals(false, p4.isFirstMove());
		
	}

	/* ######################### WHITE PAWN MOVEMENT TESTS START HERE ########################## */
	
	/* --------------------------CHECKING VALID UP MOVES (3 CASES)-----------------------------*/
	/* -----------------------------------UP TESTS START HERE------------------------------------ */	
	/**
	 * In this case the entire Chess Board is Empty. The Pawn is placed somewhere in the middle 
	 * of the board. The Pawn can only move one step/spot ahead. There are no other enemy units
	 * on the board and this Pawn cannot move 2 spots ahead as this is not his first move.
	 * Later a blocker is added to block the path of the pawn so that there are moves left for
	 * the pawn.
	 * The Expected and Test Case should match to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 1:: Empty Board (1st Move) */
		/* ------------------------------------- */
		ChessBoard board1 = new ChessBoard();	
		Pawn p1 = new Pawn(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, p1);
		
		int correctMoves[] = {4, 3};
		expectedMoves.add(correctMoves);
		
		testMoves = p1.getValidUpMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test (Blocking Pawn's Path)
		Pawn blocker = new Pawn(PieceColor.BLACK, 4, 3);	
		board1.setPieceAtSpot(4, 3, blocker);
		testMoves = p1.getValidUpMoves(board1);
		
		assertEquals(0, testMoves.size());			//No moves should exist
	}
	
	
	/**
	 * The Pawn in this test case is at the top right corner of the board . There is no more
	 * moves left for him. Therefore the Pawn is left with no more valid down moves. Hence the
	 * list of valid down moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMove2() throws Exception{
		
		//There are no expected moves in this case. There is no move possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Pawn is at Row 7 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Pawn p2 = new Pawn(PieceColor.WHITE, 7, 7);
		board2.setPieceAtSpot(7, 7, p2);

		testMoves = p2.getValidUpMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
		
	
	/**
	 * The Pawn here would be test for his extra special move ability. The 'First-Move'.
	 * All the cases would be covered in here, where the piece is blocked and where the Piece is
	 * not blocked.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMove3() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board3 = new ChessBoard();
		
		//Sub-Part 1: No Blockage in path 
		Pawn p = new Pawn (PieceColor.WHITE);
		board3.setPieceAtSpot(1, 1, p);
		
		int correctMove[] = {2, 1};
		expectedMoves.add(correctMove);
		int correctMove2[] = {3, 1};
		expectedMoves.add(correctMove2);
		
		testMoves = p.getValidUpMoves(board3);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Part 2: Blockage one spot ahead
		Pawn blocker = new Pawn(PieceColor.BLACK);
		board3.setPieceAtSpot(2, 1, blocker);
			
		testMoves = p.getValidUpMoves(board3);
		
		assertEquals(0, testMoves.size());		//No Moves should exist
		
		
		//Sub-Part 3: Blockage two spots ahead
		board3.setPieceAtSpot(3, 1, blocker);
		board3.removePieceFromSpot(2, 1);		//Removing previous blocker from the board
		
		expectedMoves.remove(1);
		
		testMoves = p.getValidUpMoves(board3);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
	}
	/* -----------------------------------UP TESTS END HERE------------------------------------ */
	
	
	/* --------------------------CHECKING VALID ATTACKING MOVES (2 CASES)-----------------------------*/
	/* -----------------------------------ATTACKING TESTS START HERE------------------------------------ */
	
	/**
	 * Pawns Upward Right Diagonal Attack Moves are tested here. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpRightMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		Pawn p1 = new Pawn(PieceColor.WHITE);		//Will Attack p2
		board1.setPieceAtSpot(1, 5, p1);
		Pawn p2 = new Pawn(PieceColor.BLACK);
		board1.setPieceAtSpot(2, 6, p2);
		Pawn p3 = new Pawn(PieceColor.WHITE);		//Will Attack p4
		board1.setPieceAtSpot(2, 2, p3);
		Pawn p4 = new Pawn(PieceColor.BLACK);
		board1.setPieceAtSpot(3, 3, p4);
		Pawn p5 = new Pawn (PieceColor.WHITE);		//Will Have No Attack Moves
		board1.setPieceAtSpot(6, 6, p5);
		
		int correctMove1[] = {2, 6};
		expectedMoves.add(correctMove1);
		
		testMoves = p1.getValidUpRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		expectedMoves.remove(0);
		int correctMove2[] = {3, 3};
		expectedMoves.add(correctMove2);
		
		testMoves = p3.getValidUpRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = p5.getValidUpRightMoves(board1);
		
		assertEquals(0, testMoves.size());		
	}
	
	
	
	
	/**
	 * Pawns Upward Left Diagonal Attack Moves are tested here.
	 * 
	 * @throws Exception
	 */
	@Test 
	public void testGetValidUpLeftMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board2 = new ChessBoard();
		
		Pawn p1 = new Pawn(PieceColor.WHITE);		//Will Attack p2
		board2.setPieceAtSpot(1, 5, p1);
		Pawn p2 = new Pawn(PieceColor.BLACK);
		board2.setPieceAtSpot(2, 4, p2);
		Pawn p3 = new Pawn(PieceColor.WHITE);		//Will Attack p4
		board2.setPieceAtSpot(2, 2, p3);
		Pawn p4 = new Pawn(PieceColor.BLACK);
		board2.setPieceAtSpot(3, 1, p4);
		Pawn p5 = new Pawn (PieceColor.WHITE);		//Will Have No Attack Moves
		board2.setPieceAtSpot(6, 6, p5);
		
		int correctMove1[] = {2, 4};
		expectedMoves.add(correctMove1);
		
		testMoves = p1.getValidUpLeftMoves(board2);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		expectedMoves.remove(0);
		int correctMove2[] = {3, 1};
		expectedMoves.add(correctMove2);
		
		testMoves = p3.getValidUpLeftMoves(board2);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = p5.getValidUpLeftMoves(board2);
		
		assertEquals(0, testMoves.size());
	}
	
	/* -----------------------------------ATTACKING TESTS END HERE------------------------------------ */
	
	/* ######################### WHITE PAWN MOVEMENT TESTS END HERE ########################## */
	
	
	
	/* ######################### BLACK PAWN MOVEMENT TESTS START HERE ########################## */
	
	/* --------------------------CHECKING VALID DOWN MOVES (3 CASES)-----------------------------*/
	/* -----------------------------------UP TESTS START HERE------------------------------------ */	
	/**
	 * In this case the entire Chess Board is Empty. The Pawn is placed somewhere in the middle 
	 * of the board. The Pawn can only move one step/spot ahead. There are no other enemy units
	 * on the board and this Pawn cannot move 2 spots ahead as this is not his first move.
	 * Later a blocker is added to block the path of the pawn so that there are moves left for
	 * the pawn.
	 * The Expected and Test Case should match to pass this test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 1:: Empty Board (1st Move) */
		/* ------------------------------------- */
		ChessBoard board1 = new ChessBoard();	
		Pawn p1 = new Pawn(PieceColor.WHITE, 3, 3);
		board1.setPieceAtSpot(3, 3, p1);
		
		int correctMoves[] = {2, 3};
		expectedMoves.add(correctMoves);
		
		testMoves = p1.getValidDownMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test (Blocking Pawn's Path)
		Pawn blocker = new Pawn(PieceColor.BLACK, 2, 3);	
		board1.setPieceAtSpot(2, 3, blocker);
		testMoves = p1.getValidDownMoves(board1);
		
		assertEquals(0, testMoves.size());			//No moves should exist
	}
	
	
	/**
	 * The Pawn in this test case is at the bottom right corner of the board . There is no more
	 * moves left for him. Therefore the Pawn is left with no more valid down moves. Hence the
	 * list of valid down moves is empty or does not exist. To pass this test an empty list of
	 * moves should be received from the method under test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMove2() throws Exception{
		
		//There are no expected moves in this case. There is no move possible
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		/* TEST CASE 2: Pawn is at Row 0 */
		/* ------------------------------------- */
		ChessBoard board2 = new ChessBoard();
		Pawn p2 = new Pawn(PieceColor.WHITE, 0, 7);
		board2.setPieceAtSpot(0, 7, p2);

		testMoves = p2.getValidDownMoves(board2);
		
		assertEquals(0, testMoves.size());
		
	}
	
		
	
	/**
	 * The Pawn here would be test for his extra special move ability. The 'First-Move'.
	 * All the cases would be covered in here, where the piece is blocked and where the Piece is
	 * not blocked.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMove3() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board3 = new ChessBoard();
		
		//Sub-Part 1: No Blockage in path 
		Pawn p = new Pawn (PieceColor.WHITE);
		board3.setPieceAtSpot(6, 1, p);
		
		int correctMove[] = {5, 1};
		expectedMoves.add(correctMove);
		int correctMove2[] = {4, 1};
		expectedMoves.add(correctMove2);
		
		testMoves = p.getValidDownMoves(board3);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Part 2: Blockage one spot ahead
		Pawn blocker = new Pawn(PieceColor.BLACK);
		board3.setPieceAtSpot(5, 1, blocker);
			
		testMoves = p.getValidDownMoves(board3);
		
		assertEquals(0, testMoves.size());		//No Moves should exist
		
		
		//Sub-Part 3: Blockage two spots ahead
		board3.setPieceAtSpot(4, 1, blocker);
		board3.removePieceFromSpot(5, 1);			//Removing previous blocker from the board
		
		expectedMoves.remove(1);
		
		testMoves = p.getValidDownMoves(board3);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
	}
	/* -----------------------------------UP TESTS END HERE------------------------------------ */
	
	
	/* --------------------------CHECKING VALID ATTACKING MOVES (2 CASES)-----------------------------*/
	/* -----------------------------------ATTACKING TESTS START HERE------------------------------------ */
	
	/**
	 * Pawns Downward Right Diagonal Attack Moves are tested here. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownRightMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		Pawn p1 = new Pawn(PieceColor.WHITE);		//Will Attack p2
		board1.setPieceAtSpot(6, 5, p1);
		Pawn p2 = new Pawn(PieceColor.BLACK);
		board1.setPieceAtSpot(5, 6, p2);
		Pawn p3 = new Pawn(PieceColor.WHITE);		//Will Attack p4
		board1.setPieceAtSpot(5, 2, p3);
		Pawn p4 = new Pawn(PieceColor.BLACK);
		board1.setPieceAtSpot(4, 3, p4);
		Pawn p5 = new Pawn (PieceColor.WHITE);		//Will Have No Attack Moves
		board1.setPieceAtSpot(2, 6, p5);
		
		int correctMove1[] = {5, 6};
		expectedMoves.add(correctMove1);
		
		testMoves = p1.getValidDownRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		expectedMoves.remove(0);
		int correctMove2[] = {4, 3};
		expectedMoves.add(correctMove2);
		
		testMoves = p3.getValidDownRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = p5.getValidDownRightMoves(board1);
		
		assertEquals(0, testMoves.size());		
	}
	
	
	
	
	/**
	 * Pawns Downward Left Diagonal Attack Moves are tested here.
	 * 
	 * @throws Exception
	 */
	@Test 
	public void testGetValidDownLeftMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board2 = new ChessBoard();
		
		Pawn p1 = new Pawn(PieceColor.WHITE);		//Will Attack p2
		board2.setPieceAtSpot(6, 5, p1);
		Pawn p2 = new Pawn(PieceColor.BLACK);
		board2.setPieceAtSpot(5, 3, p2);
		Pawn p3 = new Pawn(PieceColor.WHITE);		//Will Attack p4
		board2.setPieceAtSpot(5, 2, p3);
		Pawn p4 = new Pawn(PieceColor.BLACK);
		board2.setPieceAtSpot(4, 1, p4);
		Pawn p5 = new Pawn (PieceColor.WHITE);		//Will Have No Attack Moves
		board2.setPieceAtSpot(2, 6, p5);
		
		int correctMove1[] = {5, 3};
		expectedMoves.add(correctMove1);
		
		testMoves = p1.getValidDownLeftMoves(board2);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		expectedMoves.remove(0);
		int correctMove2[] = {4, 1};
		expectedMoves.add(correctMove2);
		
		testMoves = p3.getValidDownLeftMoves(board2);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
		
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = p5.getValidDownLeftMoves(board2);
		
		assertEquals(0, testMoves.size());
	}
	
	/* -----------------------------------ATTACKING TESTS END HERE------------------------------------ */
	
	/* ######################### BLACK PAWN MOVEMENT TESTS END HERE ########################## */
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMoveTo() throws Exception{
		
	}

}
