/**
 * 
 */
package PiecesTest;

import junit.framework.TestCase;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;
import Components.ChessPiece.PieceColor;
import PieceType.Bishop;

/**
 * This is the Test File for Bishop.java. Every functionality/method of the Bishop Class
 * is tested in this class with edge cases.
 * 
 * @author Vishrut Reddi
 *
 */
public class BishopTest extends TestCase {

	/**
	 * To pass this test the constructor responsible to create a Bishop with 
	 * a color passed in as parameter should actually be of that color in the 
	 * game.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor1() throws Exception{
		
		Bishop b1 = new Bishop(PieceColor.WHITE);
		Bishop b2 = new Bishop(PieceColor.BLACK);
		
		assertEquals(PieceColor.WHITE, b1.getPieceColor());
		assertEquals(PieceColor.BLACK, b2.getPieceColor());
	}
	
	
	/**
	 * To pass this test the constructor responsible for setting the current
	 * position of the Bishop should actually set the current position at the spot
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
		
		Bishop b1 = new Bishop(0,5);
		int bishopCurPos[] = b1.getCurrentPosition();
		assertEquals(row, bishopCurPos[0]);
		assertEquals(col, bishopCurPos[1]);
		assertEquals(b1.getPieceColor(), null);
	}
	
	
	/**
	 * To pass this test the created Bishop through the constructor taking in a color,
	 * row and column as Bishop's attributes should actually match the Objects properties.
	 * Both the color and current position is verified for this test. If all match only then 
	 * the test passes. NOTE: by setting the position of the piece it is no actually
	 * set on the Chess Board.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructor3() throws Exception{
		
		Bishop b1 = new Bishop(PieceColor.WHITE, 0, 0);
		Bishop b2 = new Bishop(PieceColor.BLACK, 7, 0);
		int bishop1CurPos[] = b1.getCurrentPosition();
		int bishop2CurPos[] = b2.getCurrentPosition();
		
		/* Tests for WHITE Bishop */
		assertEquals(0, bishop1CurPos[0]);
		assertEquals(0, bishop1CurPos[1]);
		assertEquals(PieceColor.WHITE, b1.getPieceColor());
		
		/* Tests for BLACK Bishop */
		assertEquals(7, bishop2CurPos[0]);
		assertEquals(0, bishop2CurPos[1]);
		assertEquals(PieceColor.BLACK, b2.getPieceColor());
	}
	
	
	/* MOVEMENTS ARE ALREADY TESTED IN QueenTest.java */
	/* Queen's movements are inherited from Bishop */
	
	/**
	 * In this test case all the normal valid Moves are checked for the Bishop and the
	 * state of the board is verified to check if the Bishop actually shifted places.
	 * Out of board moves is checked here, Jumping moved is checked here and definitely
	 * normal straight and diagonal moves are checked here. Attacking Moves are handled in
	 * another test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMoveTo1() throws Exception{
		
		ChessBoard board = new ChessBoard();
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		board.setNewGame(p1, p2);
		
		System.out.println("\n ---------------------- testMoveTo1 ------------------------------------ \n");
		
		//Log Board State in Console
		board.printBoardState();
		
		ChessPiece friendlyPawn = board.getPieceAtSpot(1, 4);
		ChessPiece bishop = board.getPieceAtSpot(0, 5);
		
		friendlyPawn.validMoveTo(3, 4, board, p1);
		
		//Log Board State in Console
		board.printBoardState();
		
		//Sub-Test 1: Normal Diagonal Move
		boolean moveSuccess = bishop.validMoveTo(4, 1, board, p1);
		int bishopCurPos[] = bishop.getCurrentPosition();
		assertEquals(true, moveSuccess);
		assertEquals(4, bishopCurPos[0]);
		assertEquals(1, bishopCurPos[1]);
		
		//Log Board State in Console
		board.printBoardState();
		
		//Sub-Test 2: Out of board Move
		moveSuccess = bishop.validMoveTo(2, -1, board, p1);
		assertEquals(false, moveSuccess);
		assertEquals(4, bishopCurPos[0]);
		assertEquals(1, bishopCurPos[1]);
		
		//Log Board State in Console
		board.printBoardState();
		
		//Sub-Test 3: Invalid Move (Jumping Move)
		ChessPiece enemyPawn = board.getPieceAtSpot(6, 2);
		enemyPawn.validMoveTo(5, 2, board, p1);
		
		//Logging Board State
		System.out.println("\n Enemy Pawn Moved now to block Bishops top right path... \n");
		board.printBoardState();
		
		moveSuccess = bishop.validMoveTo(6, 3, board, p1);
		assertEquals(false, moveSuccess);
		assertEquals(4, bishopCurPos[0]);
		assertEquals(1, bishopCurPos[1]);
		
		//Log Board State in Console
		board.printBoardState();
		
	}

	
	/**
	 * In this test case all the attacking valid Moves are checked for the Bishop and the
	 * state of the board is verified to check if the Bishop actually shifted places.
	 * This test makes sure the correct implementation of the Bishop's movement under this threat case.
	 * The Bishop cannot ignore the check condition. She must eliminate the condition or he cannot move, 
	 * some-other move needs to be planned.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMoveTo2() throws Exception{
		
		ChessBoard board = new ChessBoard();
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		board.setNewGame(p1, p2);
		
		System.out.println("\n ---------------------- testMoveTo2 ------------------------------------ \n");
		
		//Log Board State in Console
		board.printBoardState();
		
		ChessPiece friendlyPawn = board.getPieceAtSpot(1, 4);
		ChessPiece bishop = board.getPieceAtSpot(0, 5);
		ChessPiece enemyRook = board.getPieceAtSpot(7, 7);
		
		
		board.removePieceFromSpot(1, 4);
		board.setPieceAtSpot(2, 0, friendlyPawn);
		
		board.removePieceFromSpot(7, 7);
		board.setPieceAtSpot(2, 4, enemyRook);
		
		board.removePieceFromSpot(0, 5);
		board.setPieceAtSpot(4, 2, bishop);
		
		//Log Board State in Console
		System.out.println("\n Setting initial Scene.... \n");
		board.printBoardState();
		
		//Sub-Test 1: Ignoring Check Condition Invalid Move
		boolean moveSuccess = bishop.validMoveTo(5, 1, board, p1);
		int bishopCurPos[] = bishop.getCurrentPosition();
		assertEquals(false, moveSuccess);
		assertEquals(4, bishopCurPos[0]);
		assertEquals(2, bishopCurPos[1]);
		
		
		//Log Board State in Console
		board.printBoardState();
		
		//Sub-Test 2: Attack Enemy Rook to Protect the King
		moveSuccess = bishop.validMoveTo(2, 4, board, p1);
		bishopCurPos = bishop.getCurrentPosition();
		assertEquals(true, moveSuccess);
		assertEquals(2, bishopCurPos[0]);
		assertEquals(4, bishopCurPos[1]);
		
		//Log Board State in Console
		board.printBoardState();
		//Log Opponent Piece Status
		p1.printStatus(board);
		p2.printStatus(board);
		
	}
}
