/**
 * 
 */
package ComponentsTest;

import junit.framework.TestCase;

import org.junit.Test;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.ChessPiece.PieceColor;
import Components.Player;

/**
 * @author vishrutreddi
 *
 */
public class PlayerTest extends TestCase {

	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsCheckMate() throws Exception{
		
		ChessBoard board = new ChessBoard();
		
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		board.setNewGame(p1, p2);
		
		assertEquals(false, p1.isCheckMate(board));
		assertEquals(false, p2.isCheckMate(board));
		
		ChessPiece friendlyPawn = board.getPieceAtSpot(1, 4);
		board.removePieceFromSpot(1, 4);
		board.setPieceAtSpot(2, 1, friendlyPawn);
		
		ChessPiece enemyRook = board.getPieceAtSpot(7, 7);
		
		board.removePieceFromSpot(7, 7);
		board.setPieceAtSpot(5, 4, enemyRook);
		
		assertEquals(true, p1.isCheckMate(board));
	}

}
