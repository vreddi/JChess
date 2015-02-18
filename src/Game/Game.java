/**
 * 
 */
package Game;

import Components.ChessBoard;
import Components.ChessPiece.PieceColor;
import Components.Player;

/**
 * @author Vishrut Reddi
 *
 */
public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ChessBoard board = new ChessBoard();
		
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		board.setNewGame(p1, p2);
		
		

	}

}
