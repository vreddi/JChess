package Components;

import java.util.ArrayList;

import PieceType.Bishop;
import PieceType.King;
import PieceType.Knight;
import PieceType.Pawn;
import PieceType.Queen;
import PieceType.Rook;

/**
 * Implements a Chess Piece and all its functionalities.
 * 
 * @author Vishrut Reddi
 *
 */

public class ChessPiece {
		

	public enum PieceColor{
		BLACK, WHITE
	}

	//Color of the Piece
	private PieceColor pieceColor;
	
	//Current Position of the CHess Piece
	// 0th ele -> row
	//1st ele -> col
	private int[] curPos = new int[2];
	
	
	/**
	 * Default Constructor
	 */
	public ChessPiece(){		
	}
	
	
	/**
	 * Returns the color of the Chess Piece.
	 * 
	 * @return pieceColor
	 */
	public PieceColor getPieceColor(){
		return pieceColor;
	}

	
	
	/**
	 * Sets the color of a Chess-Piece. The color can be either
	 * WHITE or BLACK.
	 * 
	 * @param color
	 */
	public void setPieceColor (PieceColor color){
		
		this.pieceColor = color;
	}
	
	
	/**
	 * Returns the current position of the Chess Piece
	 * 
	 * @return curPos
	 */
	public int[] getCurrentPosition(){		
		return this.curPos;
	}
	
	
	/**
	 * Sets the current Position of the Chess Piece.
	 * NOTE: It is not placed at the board at the same position
	 * with this method.
	 * 
	 * @param r
	 * @param c
	 */
	public void setCurrentPosition(int r, int c){
		this.curPos[0] = r;
		this.curPos[1] = c;
	}
	
	
	/**
	 * Finds the appropriate Child-Class to continue with the getNextMoves() method
	 * which returns a list of moves for the current player chess piece. The moves returned are
	 * all valid.  
	 *  
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getNextValidMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		/* Collect Current position of the Piece */
		int[] piecePos = this.getCurrentPosition();
		
		if(this.getClass().equals(PieceType.King.class)){
			King k = new King(this.getPieceColor(), piecePos[0], piecePos[1]);
			nextMoves = k.getNextMoves(board);
		}
		
		else if(this.getClass().equals(PieceType.Queen.class)){
			Queen q = new Queen(this.getPieceColor(), piecePos[0], piecePos[1]);
			nextMoves = q.getNextMoves(board);
		}
		
		
		else if(this.getClass().equals(PieceType.Bishop.class)){
			Bishop b = new Bishop(this.getPieceColor(), piecePos[0], piecePos[1]);
			nextMoves = b.getNextMoves(board);
		}
		
		else if(this.getClass().equals(PieceType.Knight.class)){
			Knight k = new Knight(this.getPieceColor(), piecePos[0], piecePos[1]);
			nextMoves = k.getNextMoves(board);
		}
		
		else if(this.getClass().equals(PieceType.Rook.class)){
			Rook r = new Rook(this.getPieceColor(), piecePos[0], piecePos[1]);
			nextMoves = r.getNextMoves(board);
		}
		else{
			Pawn p = new Pawn(this.getPieceColor(), piecePos[0], piecePos[1]);
			nextMoves = p.getNextMoves(board);
		}
		
		
		return nextMoves;
	}
	
	
	/**
	 * Finds the appropriate Child-Class to continue with the moveTo() method
	 * which moves the current players chess piece to the destination specified considering the
	 * destination to be valid.
	 * 
	 * @param board
	 * @return true/false
	 */
	public boolean validMoveTo(int row, int col, ChessBoard board, Player p){
				
		/* Collect Current position of the Piece */
		int[] piecePos = this.getCurrentPosition();
		
		if(this.getClass().equals(PieceType.King.class)){
			King k = new King(this.getPieceColor(), piecePos[0], piecePos[1]);
			return k.moveTo(row, col, board, p);
		}
		
		else if(this.getClass().equals(PieceType.Queen.class)){
			Queen q = new Queen(this.getPieceColor(), piecePos[0], piecePos[1]);
			return q.moveTo(row, col, board, p);
		}
		
		
		else if(this.getClass().equals(PieceType.Bishop.class)){
			Bishop b = new Bishop(this.getPieceColor(), piecePos[0], piecePos[1]);
			return b.moveTo(row, col, board, p);
		}
		
		else if(this.getClass().equals(PieceType.Knight.class)){
			Knight k = new Knight(this.getPieceColor(), piecePos[0], piecePos[1]);
			return k.moveTo(row, col, board, p);
		}
		
		else if(this.getClass().equals(PieceType.Rook.class)){
			Rook r = new Rook(this.getPieceColor(), piecePos[0], piecePos[1]);
			return r.moveTo(row, col, board, p);
		}
		else{
			Pawn s = new Pawn(this.getPieceColor(), piecePos[0], piecePos[1]);
			return s.moveTo(row, col, board, p);
		}
		
	}
}

