/**
 * 
 */
package ComponentsTest;

import junit.framework.TestCase;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece.PieceColor;
import Components.Player;
import PieceType.King;
import PieceType.Rook;

/**
 * @author vishrutreddi
 *
 */
public class ChessBoardTest extends TestCase {
	
	@Test
	public void testConstructor() throws Exception{
		
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		ChessBoard board = new ChessBoard(true, p1, p2);
		
		/* Testing Empty spots on a starting board */
		for(int row = 3; row < 6; row++){
			for(int col = 0; col < 8; col++){
				assertEquals(null, board.getPieceAtSpot(row, col));
			}
		}
		
		/* Testing WHITE pieces Positions on a starting board */
		assertNotNull(board.getPieceAtSpot(0, 0));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 0).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 1));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 1).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 2));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 2).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 3));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 3).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 4));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 4).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 5));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 5).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 6));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 6).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 7));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 7).getPieceColor());
		for(int col = 0; col < 8; col++){
			assertNotNull(board.getPieceAtSpot(1, col));
			assertEquals(PieceColor.WHITE,board.getPieceAtSpot(1, col).getPieceColor());
		}
	
		
		/* Testing BLACK pieces Positions on a starting board */
		assertNotNull(board.getPieceAtSpot(7, 0));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 0).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 1));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 1).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 2));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 2).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 3));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 3).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 4));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 4).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 5));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 5).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 6));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 6).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 7));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 7).getPieceColor());
		for(int col = 0; col < 8; col++){
			assertNotNull(board.getPieceAtSpot(6, col));
			assertEquals(PieceColor.BLACK,board.getPieceAtSpot(6, col).getPieceColor());
		}	
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected= IndexOutOfBoundsException.class)
	public void testSetPieceAtSpot() throws Exception{
		ChessBoard board = new ChessBoard();
		King k = new King();
		int row = 5;
		int col = 7;
		
		assertEquals(board.getPieceAtSpot(row, col), null);
		board.setPieceAtSpot(row, col, k);
		assertEquals(board.getPieceAtSpot(row, col), k);
	}

	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected= IndexOutOfBoundsException.class)
	public void testSpotOpen() throws Exception{
		ChessBoard board = new ChessBoard();
		Rook r = new Rook(2, 1);					/* Taking a sample Chess-Piece for test */
		board.setPieceAtSpot(2, 1, r);
		
		assertEquals(true, board.spotOpen(5, 5));		/* Case if spot is open */
		assertEquals(false, board.spotOpen(2, 1));		/* Case if spot is occupied by a piece */
		
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected= IndexOutOfBoundsException.class)
	public void testGetPieceAtSpot() throws Exception{
		ChessBoard board = new ChessBoard();
		Rook r = new Rook(7, 7);					/* Taking a sample Chess-Piece for test */
		r.setPieceColor(PieceColor.WHITE);
		board.setPieceAtSpot(7, 7, r);
		
		assertEquals(r, board.getPieceAtSpot(7, 7));		/* Getting Piece from an occupied spot */
		assertEquals(PieceColor.WHITE, board.getPieceAtSpot(7,  7).getPieceColor());		/* Verifying for the same piece */
		assertEquals(null, board.getPieceAtSpot(0, 0));			/* Getting Piece from an unoccupied spot */
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCleanBoard() throws Exception{
		
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		ChessBoard board = new ChessBoard(true, p1, p2);
		
		/*Set the board for play with pieces in initial position */
		board.cleanBoard();
		
		/* Checking if different spots are empty/unoccupied */
		assertEquals(null, board.getPieceAtSpot(0, 0));
		assertEquals(null, board.getPieceAtSpot(1, 7));
		assertEquals(null, board.getPieceAtSpot(1, 4));
		assertEquals(null, board.getPieceAtSpot(7, 0));
		assertEquals(null, board.getPieceAtSpot(6, 7));
		assertEquals(null, board.getPieceAtSpot(6, 4));
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testResetPieces() throws Exception{
	
		ChessBoard board = new ChessBoard();
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		Rook r1 = new Rook(PieceColor.WHITE, 5, 5);
		Rook r2 = new Rook(PieceColor.BLACK, 5, 6);
		
		/* Set piece at (5, 5) and (5,6)*/
		board.setPieceAtSpot(5, 5, r1);
		board.setPieceAtSpot(5, 6, r2);
		
		board.resetPieces(PieceColor.WHITE, p1);
		assertEquals(null, board.getPieceAtSpot(5, 5));
		assertNotNull(board.getPieceAtSpot(0, 0));
		
		board.resetPieces(PieceColor.BLACK, p2);
		assertEquals(null, board.getPieceAtSpot(5, 6));
		assertNotNull(board.getPieceAtSpot(7, 0));
				
	}
	
	/**
	 * Visually See the Result on the Console to verify the test. (Manual Testing)
	 * @throws Exception
	 */
	@Test
	public void testCopyBoard() throws Exception{
		ChessBoard copy;
		ChessBoard board = new ChessBoard();
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		//Logging in Console
		System.out.println("\n ------------------------- testCopyBoard() ------------------------- \n");
		
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		//Setting new chess game between Player p1 and Player p2
		board.setNewGame(p1, p2);
		
		board.getPieceAtSpot(1, 4).validMoveTo(3, 4, board, p1);
		board.getPieceAtSpot(6,  5).validMoveTo(4, 5, board, p2);
		
		//Login state in console
		System.out.println("Original: \n");
		board.printBoardState();
		
		copy = board.deepCopyBoard();
		
		//Login state in console
		System.out.println("\n Copied: \n");
		copy.printBoardState();		
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetNewGame() throws Exception{
		
		ChessBoard board = new ChessBoard();
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		board.setNewGame(p1, p2);
		
		/* Testing Empty spots on a starting board */
		for(int row = 3; row < 6; row++){
			for(int col = 0; col < 8; col++){
				assertEquals(null, board.getPieceAtSpot(row, col));
			}
		}
		
		/* Testing WHITE pieces Positions on a starting board */
		assertNotNull(board.getPieceAtSpot(0, 0));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 0).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 1));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 1).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 2));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 2).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 3));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 3).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 4));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 4).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 5));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 5).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 6));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 6).getPieceColor());
		assertNotNull(board.getPieceAtSpot(0, 7));
		assertEquals(PieceColor.WHITE,board.getPieceAtSpot(0, 7).getPieceColor());
		for(int col = 0; col < 8; col++){
			assertNotNull(board.getPieceAtSpot(1, col));
			assertEquals(PieceColor.WHITE,board.getPieceAtSpot(1, col).getPieceColor());
		}
	
		
		/* Testing BLACK pieces Positions on a starting board */
		assertNotNull(board.getPieceAtSpot(7, 0));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 0).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 1));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 1).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 2));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 2).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 3));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 3).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 4));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 4).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 5));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 5).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 6));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 6).getPieceColor());
		assertNotNull(board.getPieceAtSpot(7, 7));
		assertEquals(PieceColor.BLACK,board.getPieceAtSpot(7, 7).getPieceColor());
		for(int col = 0; col < 8; col++){
			assertNotNull(board.getPieceAtSpot(6, col));
			assertEquals(PieceColor.BLACK,board.getPieceAtSpot(6, col).getPieceColor());
		}
	}

}
