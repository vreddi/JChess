/**
 * 
 */
package PiecesTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;
import Components.ChessPiece.PieceColor;
import PieceType.Knight;
import PieceType.Rook;

/**
 * This is the Test File for Knight.java. Every functionality/method of the Knight Class
 * is tested in this class with edge cases.
 * 
 * @author Vishrut Reddi
 *
 */
public class KnightTest {

	
	
	/**
	 * To pass this test the constructor responsible to create a Knight with 
	 * a color passed in as parameter should actually be of that color in the 
	 * game.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor1() throws Exception{
		
		Knight k1 = new Knight(PieceColor.WHITE);
		Knight k2 = new Knight(PieceColor.BLACK);
		
		assertEquals(PieceColor.WHITE, k1.getPieceColor());
		assertEquals(PieceColor.BLACK, k2.getPieceColor());
	}
	
	
	/**
	 * To pass this test the constructor responsible for setting the current
	 * position of the Knight should actually set the current position at the spot
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
		
		Knight k1 = new Knight(0,5);
		int knightCurPos[] = k1.getCurrentPosition();
		assertEquals(row, knightCurPos[0]);
		assertEquals(col, knightCurPos[1]);
		assertEquals(k1.getPieceColor(), null);
	}
	
	
	/**
	 * To pass this test the created Knight through the constructor taking in a color,
	 * row and column as Knight's attributes should actually match the Objects properties.
	 * Both the color and current position is verified for this test. If all match only then 
	 * the test passes. NOTE: by setting the position of the piece it is no actually
	 * set on the Chess Board.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor3() throws Exception{
		
		Knight k1 = new Knight(PieceColor.WHITE, 0, 0);
		Knight k2 = new Knight(PieceColor.BLACK, 7, 0);
		int knight1CurPos[] = k1.getCurrentPosition();
		int knight2CurPos[] = k2.getCurrentPosition();
		
		/* Tests for WHITE Knight */
		assertEquals(0, knight1CurPos[0]);
		assertEquals(0, knight1CurPos[1]);
		assertEquals(PieceColor.WHITE, k1.getPieceColor());
		
		/* Tests for BLACK Knight */
		assertEquals(7, knight2CurPos[0]);
		assertEquals(0, knight2CurPos[1]);
		assertEquals(PieceColor.BLACK, k2.getPieceColor());
	}





	/* ------------------------------------------- KNIGHT MOVEMENT TESTS BEGINS HERE -----------------------------------------------*/

	/**
	 * In this test case the Chess-Board would initially be empty, henceforth 2 valid moves
	 * are expected by the knight in the up right quadrant of its current position. Then the second
	 * sub-part of the test would place some pieces in the path of the Kight's movement to test the
	 * Knights Jumping over pieces.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpRightMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		//Sub-Test: 1 (Empty Chess Board)
		Knight k1 = new Knight(PieceColor.WHITE);		//2 Up-Right Moves exist
		board1.setPieceAtSpot(0, 1, k1);
		
		Knight k2 = new Knight(PieceColor.BLACK);		//No Up-Right Moves possible
		board1.setPieceAtSpot(7, 1, k2);
		
		
		testMoves = k1.getValidUpRightMoves(board1);
		
		int correctMove1[] = {2, 2};
		int correctMove2[] =  {1, 3};
		
		//Adding expected moves to the list
		expectedMoves.add(correctMove1);
		expectedMoves.add(correctMove2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = k2.getValidUpRightMoves(board1);
		
		assertEquals(0, testMoves.size());
		
		//Sub-Test: 2 (Placing Pieces in the way)/ Testing Jumping Case
		
		Rook blocker1 = new Rook(PieceColor.BLACK);
		Rook blocker2 = new Rook(PieceColor.WHITE);
		
		board1.setPieceAtSpot(1, 1, blocker1);
		board1.setPieceAtSpot(0, 3, blocker2);
		
		testMoves = k1.getValidUpRightMoves(board1);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
					
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}

	
	
	/**
	 * In this Test case we check the Knight's Attacking Move and a Normal Move if the destination spot
	 * for the Knight's current move is blocked by a friendly unit. To pass this test, the test results
	 * should match the expected result.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpRightMoves2() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board2 = new ChessBoard();
		
		Knight k1 = new Knight(PieceColor.WHITE);
		board2.setPieceAtSpot(2, 2, k1);
		
		Knight friendly = new Knight(PieceColor.WHITE);
		Knight enemy = new Knight(PieceColor.BLACK);
		
		board2.setPieceAtSpot(4, 3, friendly);
		board2.setPieceAtSpot(3, 4, enemy);
		
		int correctMove[] = {3, 4};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidUpRightMoves(board2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
							
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	
	/**
	 * In this test case the Chess-Board would initially be empty, henceforth 2 valid moves
	 * are expected by the knight in the up left quadrant of its current position. Then the second
	 * sub-part of the test would place some pieces in the path of the Kight's movement to test the
	 * Knights Jumping over pieces.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpLeftMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		//Sub-Test: 1 (Empty Chess Board)
		Knight k1 = new Knight(PieceColor.WHITE);		//2 Up-Right Moves exist
		board1.setPieceAtSpot(0, 6, k1);
		
		Knight k2 = new Knight(PieceColor.BLACK);		//No Up-Right Moves possible
		board1.setPieceAtSpot(7, 6, k2);
		
		
		testMoves = k1.getValidUpLeftMoves(board1);
		
		int correctMove1[] = {2, 5};
		int correctMove2[] =  {1, 4};
		
		//Adding expected moves to the list
		expectedMoves.add(correctMove1);
		expectedMoves.add(correctMove2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = k2.getValidUpLeftMoves(board1);
		
		assertEquals(0, testMoves.size());
		
		//Sub-Test: 2 (Placing Pieces in the way)/ Testing Jumping Case
		
		Rook blocker1 = new Rook(PieceColor.BLACK);
		Rook blocker2 = new Rook(PieceColor.WHITE);
		
		board1.setPieceAtSpot(1, 6, blocker1);
		board1.setPieceAtSpot(0, 4, blocker2);
		
		testMoves = k1.getValidUpLeftMoves(board1);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
					
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}

	
	
	/**
	 * In this Test case we check the Knight's Attacking Move and a Normal Move if the destination spot
	 * for the Knight's current move is blocked by a friendly unit. To pass this test, the test results
	 * should match the expected result.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpLeftMoves2() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board2 = new ChessBoard();
		
		Knight k1 = new Knight(PieceColor.WHITE);
		board2.setPieceAtSpot(2, 5, k1);
		
		Knight friendly = new Knight(PieceColor.WHITE);
		Knight enemy = new Knight(PieceColor.BLACK);
		
		board2.setPieceAtSpot(4, 4, friendly);
		board2.setPieceAtSpot(3, 3, enemy);
		
		int correctMove[] = {3, 3};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidUpLeftMoves(board2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
							
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	
	/**
	 * In this test case the Chess-Board would initially be empty, henceforth 2 valid moves
	 * are expected by the knight in the down right quadrant of its current position. Then the second
	 * sub-part of the test would place some pieces in the path of the Kight's movement to test the
	 * Knights Jumping over pieces.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownRightMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		//Sub-Test: 1 (Empty Chess Board)
		Knight k1 = new Knight(PieceColor.WHITE);		//2 Up-Right Moves exist
		board1.setPieceAtSpot(0, 1, k1);
		
		Knight k2 = new Knight(PieceColor.BLACK);		//No Up-Right Moves possible
		board1.setPieceAtSpot(7, 1, k2);
		
		
		testMoves = k2.getValidDownRightMoves(board1);
		
		int correctMove1[] = {5, 2};
		int correctMove2[] =  {6, 3};
		
		//Adding expected moves to the list
		expectedMoves.add(correctMove1);
		expectedMoves.add(correctMove2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = k1.getValidDownRightMoves(board1);
		
		assertEquals(0, testMoves.size());
		
		//Sub-Test: 2 (Placing Pieces in the way)/ Testing Jumping Case
		
		Rook blocker1 = new Rook(PieceColor.BLACK);
		Rook blocker2 = new Rook(PieceColor.WHITE);
		
		board1.setPieceAtSpot(5, 1, blocker1);
		board1.setPieceAtSpot(7, 2, blocker2);
		
		testMoves = k2.getValidDownRightMoves(board1);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
					
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}

	
	
	/**
	 * In this Test case we check the Knight's Attacking Move and a Normal Move if the destination spot
	 * for the Knight's current move is blocked by a friendly unit. To pass this test, the test results
	 * should match the expected result.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownRightMoves2() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board2 = new ChessBoard();
		
		Knight k1 = new Knight(PieceColor.WHITE);
		board2.setPieceAtSpot(5, 2, k1);
		
		Knight friendly = new Knight(PieceColor.WHITE);
		Knight enemy = new Knight(PieceColor.BLACK);
		
		board2.setPieceAtSpot(4, 4, friendly);
		board2.setPieceAtSpot(3, 3, enemy);
		
		int correctMove[] = {3, 3};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidDownRightMoves(board2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
							
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	
	
	
	/**
	 * In this test case the Chess-Board would initially be empty, henceforth 2 valid moves
	 * are expected by the knight in the down left quadrant of its current position. Then the second
	 * sub-part of the test would place some pieces in the path of the Kight's movement to test the
	 * Knights Jumping over pieces.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownLeftMoves1() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		//Sub-Test: 1 (Empty Chess Board)
		Knight k1 = new Knight(PieceColor.WHITE);		//2 Up-Right Moves exist
		board1.setPieceAtSpot(0, 6, k1);
		
		Knight k2 = new Knight(PieceColor.BLACK);		//No Up-Right Moves possible
		board1.setPieceAtSpot(7, 6, k2);
		
		
		testMoves = k2.getValidDownLeftMoves(board1);
		
		int correctMove1[] = {5, 5};
		int correctMove2[] =  {6, 4};
		
		//Adding expected moves to the list
		expectedMoves.add(correctMove1);
		expectedMoves.add(correctMove2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		testMoves = k1.getValidDownLeftMoves(board1);
		
		assertEquals(0, testMoves.size());
		
		//Sub-Test: 2 (Placing Pieces in the way)/ Testing Jumping Case
		
		Rook blocker1 = new Rook(PieceColor.BLACK);
		Rook blocker2 = new Rook(PieceColor.WHITE);
		
		board1.setPieceAtSpot(7, 5, blocker1);
		board1.setPieceAtSpot(5, 6, blocker2);
		
		testMoves = k2.getValidDownLeftMoves(board1);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
					
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
			
	}

	
	
	/**
	 * In this Test case we check the Knight's Attacking Move and a Normal Move if the destination spot
	 * for the Knight's current move is blocked by a friendly unit. To pass this test, the test results
	 * should match the expected result.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownLeftMoves2() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board2 = new ChessBoard();
		
		Knight k1 = new Knight(PieceColor.WHITE);
		board2.setPieceAtSpot(5, 2, k1);
		
		Knight friendly = new Knight(PieceColor.WHITE);
		Knight enemy = new Knight(PieceColor.BLACK);
		
		board2.setPieceAtSpot(3, 1, friendly);
		board2.setPieceAtSpot(4, 0, enemy);
		
		int correctMove[] = {4, 0};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidDownLeftMoves(board2);
		
		//Checking Expectation with the Test Result
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
							
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
	}
	/* ------------------------------------------- KNIGHT MOVEMENT TESTS ENDS HERE -----------------------------------------------*/
	
	
	/**
	 * In this test case the new game board with pieces at their initial position is set. 
	 * The Knight's movement from a valid source to destination is tested. For the Knight, normal moves
	 * are checked including normal move, Jumping Move and out of board move. THe Attacking move and threat case
	 * is tested in some other test.
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

		ChessPiece knight = board.getPieceAtSpot(0, 1);
	
		int knightCurPos[] = knight.getCurrentPosition();
		
		//Logging state in Console
		board.printBoardState();
		
		//Sub-Test 1: Checking Jumping Knight Move
		boolean moveSuccessful = knight.validMoveTo(2, 2, board, p1);
		
		//Logging Board State in Console
		board.printBoardState();
		
		assertEquals(true, moveSuccessful);
		assertEquals(2, knightCurPos[0]);
		assertEquals(2, knightCurPos[1]);
		
		//Sub-Test 2: Normal Move
		moveSuccessful = knight.validMoveTo(3, 0, board, p1);
		assertEquals(true, moveSuccessful);
		assertEquals(3, knightCurPos[0]);
		assertEquals(0, knightCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();
		
		//Sub-Test 3: Invalid Move 
		moveSuccessful = knight.validMoveTo(4, 3, board, p1);
		assertEquals(false, moveSuccessful);
		assertEquals(3, knightCurPos[0]);
		assertEquals(0, knightCurPos[1]);
		
		//Sub-Test 4: Moving off the board and also invalid attack move
		moveSuccessful = knight.validMoveTo(5, -1, board, p1);
		knightCurPos = knight.getCurrentPosition();
		assertEquals(false, moveSuccessful);
		assertEquals(3, knightCurPos[0]);
		assertEquals(0, knightCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();
	}
	
	
	/**
	 * In this test case the Knight is faced with a threat condition i.e when the King is under Check.
	 * This test makes sure the correct implementation of the Knight's movement under this threat case.
	 * The Knight cannot ignore the check condition. He must eliminate the condition or he cannot move, 
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
		ChessPiece friendlyPawn = board.getPieceAtSpot(1, 4);
		ChessPiece enemyQueen = board.getPieceAtSpot(7, 3);
		ChessPiece knight = board.getPieceAtSpot(0, 1);
		
		
		board.removePieceFromSpot(1, 4);
		board.setPieceAtSpot(2, 0, friendlyPawn);
		
		board.removePieceFromSpot(7, 7);
		board.setPieceAtSpot(2, 2, enemyRook);
		
		//Logging state in Console
		System.out.println("\n Moving Pieces to create an attack scene...");
		board.printBoardState();
		
		//Normal Attack by knight
		boolean moveSuccess = knight.validMoveTo(2, 2, board, p1); 
		int knightCurPos[] = knight.getCurrentPosition();
		assertEquals(true, moveSuccess);
		assertEquals(2, knightCurPos[0]);
		assertEquals(2, knightCurPos[1]);
		
		//Logging State in Console
		board.printBoardState();
		
		board.removePieceFromSpot(7, 3);
		board.setPieceAtSpot(5, 4, enemyQueen);
		
		//Logging state in Console
		System.out.println("\n Moving Pieces to create a threat scene...");
		board.printBoardState();
				
		//WHITE King is under check
		//Sub-Test 1: Knight ignores the check condition by doing a normal move
		moveSuccess = knight.validMoveTo(4, 3, board, p1); 
		knightCurPos = knight.getCurrentPosition();
		assertEquals(false, moveSuccess);
		assertEquals(2,knightCurPos[0]);
		assertEquals(2, knightCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();
		
		//Sub-Test 2: Knight Blocks the Enemy Queen eliminating the threat.
		moveSuccess = knight.validMoveTo(3, 4, board, p1);
		assertEquals(true, moveSuccess);
		assertEquals(3,knightCurPos[0]);
		assertEquals(4, knightCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();		
		p1.printStatus(board);
		p2.printStatus(board);
	}
}