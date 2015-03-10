package PiecesTest;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;
import Components.ChessPiece.PieceColor;
import PieceType.King;
import PieceType.Queen;
import PieceType.Rook;

/**
 * This is the Test File for King.java. Every functionality/method of the King Class
 * is tested in this class with edge cases.
 * 
 * @author Vishrut Reddi
 *
 */
public class KingTest extends TestCase {

	/**
	 * To pass this test the constructor responsible to create a King with 
	 * a color passed in as parameter should actually be of that color in the 
	 * game.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor1() throws Exception{
		
		King k1 = new King(PieceColor.WHITE);
		King k2 = new King(PieceColor.BLACK);
		
		assertEquals(PieceColor.WHITE, k1.getPieceColor());
		assertEquals(PieceColor.BLACK, k2.getPieceColor());
	}
	
	
	/**
	 * To pass this test the constructor responsible for setting the current
	 * position of the King should actually set the current position at the spot
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
		
		King k1 = new King(0,5);
		int kingCurPos[] = k1.getCurrentPosition();
		assertEquals(row, kingCurPos[0]);
		assertEquals(col, kingCurPos[1]);
		assertEquals(k1.getPieceColor(), null);
	}
	
	
	/**
	 * To pass this test the created King through the constructor taking in a color,
	 * row and column as King's attributes should actually match the Objects properties.
	 * Both the color and current position is verified for this test. If all match only then 
	 * the test passes. NOTE: by setting the position of the piece it is no actually
	 * set on the Chess Board.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor3() throws Exception{
		
		King k1 = new King(PieceColor.WHITE, 0, 0);
		King k2 = new King(PieceColor.BLACK, 7, 0);
		int king1CurPos[] = k1.getCurrentPosition();
		int king2CurPos[] = k2.getCurrentPosition();
		
		/* Tests for WHITE King */
		assertEquals(0, king1CurPos[0]);
		assertEquals(0, king1CurPos[1]);
		assertEquals(PieceColor.WHITE, k1.getPieceColor());
		
		/* Tests for BLACK King */
		assertEquals(7, king2CurPos[0]);
		assertEquals(0, king2CurPos[1]);
		assertEquals(PieceColor.BLACK, k2.getPieceColor());
	}
	
	/* ------------------------------------------- KING MOVEMENT TESTS BEGINS HERE -----------------------------------------------*/
	
	/**
	 * This test checks if the King can move in the Up Direction or not. It does not account for if moving
	 * in the up direction proceeds in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {6, 5};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidUpMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(6, 5, enemy);
		
		testMoves = k1.getValidUpMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(6, 5);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(6, 5, friendly);
		
		testMoves = k1.getValidUpMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * This test checks if the King can move in the Up Right Direction or not. It does not account for if moving
	 * in the up right direction results in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpRightMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {6, 6};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidUpRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(6, 6, enemy);
		
		testMoves = k1.getValidUpRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(6, 6);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(6, 6, friendly);
		
		testMoves = k1.getValidUpRightMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	
	/**
	 * This test checks if the King can move in the Up Left Direction or not. It does not account for if moving
	 * in the up left direction results in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidUpLeftMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {6, 4};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidUpLeftMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(6, 4, enemy);
		
		testMoves = k1.getValidUpLeftMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(6, 4);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(6, 4, friendly);
		
		testMoves = k1.getValidUpLeftMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	
	
	/**
	 * This test checks if the King can move in the Right Direction or not. It does not account for if moving
	 * in the right direction results in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidRightMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {5, 6};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(5, 6, enemy);
		
		testMoves = k1.getValidRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(5, 6);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(5, 6, friendly);
		
		testMoves = k1.getValidRightMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	
	/**
	 * This test checks if the King can move in Left Direction or not. It does not account for if moving
	 * in the left direction results in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidLeftMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {5, 4};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidLeftMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(5, 4, enemy);
		
		testMoves = k1.getValidLeftMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(5, 4);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(5, 4, friendly);
		
		testMoves = k1.getValidLeftMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	
	
	/**
	 * This test checks if the King can move in the Down Direction or not. It does not account for if moving
	 * in the down direction proceeds in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {4, 5};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidDownMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(4, 5, enemy);
		
		testMoves = k1.getValidDownMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(4, 5);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(4, 5, friendly);
		
		testMoves = k1.getValidDownMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	/**
	 * This test checks if the King can move in the Down Right Direction or not. It does not account for if moving
	 * in the down right direction results in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownRightMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {4, 6};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidDownRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(4, 6, enemy);
		
		testMoves = k1.getValidDownRightMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(4, 6);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(4, 6, friendly);
		
		testMoves = k1.getValidDownRightMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	
	
	/**
	 * This test checks if the King can move in the Down Left Direction or not. It does not account for if moving
	 * in the down left direction results in a check/threat condition. That is tested by some other method.
	 * Attacking and Normal moves are checked here.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetValidDownLeftMoves() throws Exception{
		
		ArrayList<int[]> expectedMoves = new ArrayList<int[]>();		//Used for Expected Moves
		ArrayList<int[]> testMoves = new ArrayList<int[]>();			//used for Moves that are under Test
		
		ChessBoard board1 = new ChessBoard();
		
		King k1 = new King(PieceColor.WHITE);
		board1.setPieceAtSpot(5, 5, k1);
		
		//Sub-Test: 1 (Empty Spot)
		int correctMove[] = {4, 4};
		expectedMoves.add(correctMove);
		
		testMoves = k1.getValidDownLeftMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 2 (Enemy Unit Occupied the Spot)
		Rook enemy = new Rook(PieceColor.BLACK);
		board1.setPieceAtSpot(4, 4, enemy);
		
		testMoves = k1.getValidDownLeftMoves(board1);
		
		for(int i = 0; i < testMoves.size(); i++){
			int nextMove[] = testMoves.get(i);
			int nextExpectedMove[] = expectedMoves.get(i);
			
			assertEquals(nextMove[0], nextExpectedMove[0]);
			assertEquals(nextMove[1], nextExpectedMove[1]);
		}
		
		//Sub-Test: 3 (Friendly Unit Occupied the Spot)
		board1.removePieceFromSpot(4, 4);
		Queen friendly = new Queen(PieceColor.WHITE);
		
		board1.setPieceAtSpot(4, 4, friendly);
		
		testMoves = k1.getValidDownLeftMoves(board1);
		
		assertEquals(0, testMoves.size());
		
	}
	
	/* ------------------------------------------- KING MOVEMENT TESTS ENDS HERE -----------------------------------------------*/
	
	/**
	 * In this test case the new game board with pieces at their initial position is set. 
	 * The King's movement from a valid source to destination is tested. For the King, normal moves
	 * are checked including normal move and out of board move. The Attacking move and threat case
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

		ChessPiece king = board.getPieceAtSpot(0, 4);
	
		int kingCurPos[] = king.getCurrentPosition();
		
		//Logging state in Console
		board.printBoardState();
		
		ChessPiece friendlyPawn = board.getPieceAtSpot(1, 4);
		board.removePieceFromSpot(1, 4);
		board.setPieceAtSpot(2, 0, friendlyPawn);
		
		//Logging state in Console
		System.out.println("\n Made space for the King to Move... \n");
		board.printBoardState();
		
		//Sub-Test 1: Checking King's Normal Move
		boolean moveSuccessful = king.validMoveTo(1, 4, board, p1);
		
		//Logging Board State in Console
		board.printBoardState();
		
		assertEquals(true, moveSuccessful);
		assertEquals(1, kingCurPos[0]);
		assertEquals(4, kingCurPos[1]);
		
		//Sub-Test 2: Invalid King Move
		moveSuccessful = king.validMoveTo(3, 4, board, p1);
		
		//Logging Board State in Console
		board.printBoardState();
		
		assertEquals(false, moveSuccessful);
		assertEquals(1, kingCurPos[0]);
		assertEquals(4, kingCurPos[1]);
		
		//Sub-Test 3: Normal Move Diagonal 
		moveSuccessful = king.validMoveTo(2, 5, board, p1);
		assertEquals(true, moveSuccessful);
		assertEquals(2, kingCurPos[0]);
		assertEquals(5, kingCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();
		
		/* Set King Piece on the edge of the board */
		board.removePieceFromSpot(2, 5);
		board.setPieceAtSpot(3, 7, king);
				
		//Logging state in Console
		System.out.println("\n Moving the King to a new place... \n");
		board.printBoardState();
				
		//Sub-Test 4: Invalid Move :: Moving piece off the board
		moveSuccessful = king.validMoveTo(3, 8, board, p1);
		assertEquals(false, moveSuccessful);
		assertEquals(3, kingCurPos[0]);
		assertEquals(7, kingCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();
	}
	
	
	/**
	 * In this test case the King is faced with a threat condition i.e when the King is under Check.
	 * This test makes sure the correct implementation of the King's movement under this threat case.
	 * The King cannot ignore the check condition. He must eliminate the condition or he cannot move, 
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
		ChessPiece king = board.getPieceAtSpot(0, 4);
				
		board.removePieceFromSpot(0, 4);
		board.setPieceAtSpot(3, 2, king);
		
		board.removePieceFromSpot(7, 7);
		board.setPieceAtSpot(3, 3, enemyRook);
		
		//Logging state in Console
		System.out.println("\n Moving Pieces to create an attack scene...");
		board.printBoardState();
		
		//WHITE King is under Check
		//Sub-test 1: King dodging the Check Case without attacking
		boolean moveSuccess = king.validMoveTo(4, 2, board, p1); 
		int kingCurPos[] = king.getCurrentPosition();
		assertEquals(true, moveSuccess);
		assertEquals(4, kingCurPos[0]);
		assertEquals(2, kingCurPos[1]);
		
		//Logging State in Console
		board.printBoardState();
		
		//Enemy Rook Checks again
		enemyRook.validMoveTo(4, 3, board, p2);
		
		//Logging state in Console
		System.out.println("\n Now the Enemy Rook Moves near the King to Check him again...");
		board.printBoardState();
				
		//Sub-Test 2: King moves back and does an invalid move as he is still under check
		moveSuccess = king.validMoveTo(4, 1, board, p1); 
		kingCurPos = king.getCurrentPosition();
		assertEquals(false, moveSuccess);
		assertEquals(4,kingCurPos[0]);
		assertEquals(2, kingCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();
		
		//Sub-Test 3: King kills the enemy Rook and eliminates the check
		moveSuccess = king.validMoveTo(4, 3, board, p1);
		assertEquals(true, moveSuccess);
		assertEquals(4,kingCurPos[0]);
		assertEquals(3, kingCurPos[1]);
		
		//Logging state in Console
		board.printBoardState();		
		p1.printStatus(board);
		p2.printStatus(board);
	}


}
