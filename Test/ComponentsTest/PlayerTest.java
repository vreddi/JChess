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
	public void testIsCheck() throws Exception{
		
		ChessBoard board = new ChessBoard();
		
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		board.setNewGame(p1, p2);
		
		assertEquals(false, p1.isCheck(board));
		assertEquals(false, p2.isCheck(board));
		
		ChessPiece friendlyPawn = board.getPieceAtSpot(1, 4);
		board.removePieceFromSpot(1, 4);
		board.setPieceAtSpot(2, 1, friendlyPawn);
		
		ChessPiece enemyRook = board.getPieceAtSpot(7, 7);
		
		board.removePieceFromSpot(7, 7);
		board.setPieceAtSpot(5, 4, enemyRook);
		
		//Logging Board State
		board.printBoardState();
		
		assertEquals(true, p1.isCheck(board));
		
		//Lets try a new game scene where check mate is tested
		ChessBoard board2 = new ChessBoard();
		
		Player whiteP = new Player(PieceColor.WHITE);
		Player blackP = new Player(PieceColor.BLACK);
		
		whiteP.setOpponent(blackP);
		blackP.setOpponent(whiteP);
		
		board2.setNewGame(whiteP, blackP);
		
		ChessPiece king = board2.getPieceAtSpot(0, 4);
		board2.removePieceFromSpot(0, 4);
		board2.setPieceAtSpot(4, 7, king);
		
		ChessPiece enemyRook1 = board2.getPieceAtSpot(7, 0);
		ChessPiece enemyRook2 = board2.getPieceAtSpot(7, 7);
		ChessPiece enemyQueen = board2.getPieceAtSpot(7, 3);
		board2.removePieceFromSpot(7, 0);
		board2.setPieceAtSpot(5, 7, enemyRook1);
		board2.removePieceFromSpot(7, 7);
		board2.setPieceAtSpot(3, 6, enemyRook2);	
		board2.removePieceFromSpot(7, 3);
		board2.setPieceAtSpot(4, 6, enemyQueen);
		
		//Logging Board State
		board2.printBoardState();	
		assertEquals(true, whiteP.isCheck(board2));
		
	}
	
	
	
	
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
		
		//Logging Board State
		board.printBoardState();
		
		assertEquals(false, p1.isCheckMate(board));
		
		//Lets try a new game scene where check mate is tested
		ChessBoard board2 = new ChessBoard();
		
		Player whiteP = new Player(PieceColor.WHITE);
		Player blackP = new Player(PieceColor.BLACK);
		
		whiteP.setOpponent(blackP);
		blackP.setOpponent(whiteP);
		
		board2.setNewGame(whiteP, blackP);
		
		ChessPiece king = board2.getPieceAtSpot(0, 4);
		board2.removePieceFromSpot(0, 4);
		board2.setPieceAtSpot(4, 7, king);
		
		ChessPiece enemyRook1 = board2.getPieceAtSpot(7, 0);
		ChessPiece enemyRook2 = board2.getPieceAtSpot(7, 7);
		ChessPiece enemyQueen = board2.getPieceAtSpot(7, 3);
		board2.removePieceFromSpot(7, 0);
		board2.setPieceAtSpot(5, 7, enemyRook1);
		board2.removePieceFromSpot(7, 7);
		board2.setPieceAtSpot(3, 6, enemyRook2);	
		board2.removePieceFromSpot(7, 3);
		board2.setPieceAtSpot(4, 6, enemyQueen);
		
		//Logging Board State
		board2.printBoardState();	
		assertEquals(true, whiteP.isCheckMate(board2));
		
	}

}
