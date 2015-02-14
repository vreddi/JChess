package Components;

import java.util.ArrayList;

import Components.ChessPiece.PieceColor;


/**
 * This class represents the player playing the chess game with all his/her
 * functionalities.
 * 
 * @author vishrutreddi
 *
 */
public class Player {
	
	
	PieceColor team;		//Team WHITE or Team BLACK, Used to specify Player1 or Player2
	
	Player opponent; 		//Reference to the other player he/she is playing against
	
	ArrayList<ChessPiece> alivePieces = new ArrayList<ChessPiece>();	//List of alive pieces for the player
	
	
	/**
	 * Default Constructor
	 */
	public Player(){		
		team = null;
	}
	
	
	/**
	 * Creates a new player for the chess game with the specified color of
	 * Chess Pieces. The color is passed as a parameter to the constructor.
	 * 
	 * @param color
	 */
	public Player(PieceColor color){
		
		team = color;
	}

	
	
	
	/**
	 * Creates a new player for the chess game with the specified color of
	 * Chess Pieces. The color is passed as a parameter to the constructor.
	 * The player is also created with a predefined opponent which is also
	 * passed in as a parameter
	 * 
	 * @param color
	 * @param op
	 */
	public Player(PieceColor color, Player op){
		
		team = color;
		opponent = op;
	}
	
	
	
	
	/**
	 * Get the team of the player or the color of the pieces he/she is using
	 * in the chess game.
	 * 
	 * @return team
	 */
	public PieceColor getPlayerColor(){
		return team;
	}
	

	
	
	/**
	 * Adds the passed parameter ChessPiece to the list of alive pieces on the board
	 * for the player.
	 * 
	 * @param p
	 */
	public void recruitPiece(ChessPiece p){
	
		alivePieces.add(p);		
	}	
	
	
	
	/**
	 * Finds and returns the players opponent reference.
	 * 
	 * @return opponent
	 */
	public Player getOpponent(){
		return opponent;
	}
	
	
	
	
	/**
	 * From the alive list of the player, it finds the current position of the
	 * player's King Piece on the board. This position is returned. If king is not
	 * found then null is returned.
	 * 
	 * @return kingCurPos/null
	 */
	public int[] getKingsCurPosition(){
		
		for(ChessPiece piece : this.alivePieces){
			
			if(piece.getClass().equals(PieceType.King.class)){
				
				int kingCurPos[] = piece.getCurrentPosition();
				return kingCurPos;			
			}
			
		}
		
		return null;			//No King found		
	}

	
	
	/**
	 * Depending on the current board state/situation, this method returns a 
	 * true or false result as to the player under attack is currently checked or 
	 * not.
	 * 
	 * @param board
	 * @return true/false
	 */
	public boolean isCheck(ChessBoard board){
		
		Player enemy = this.getOpponent();
		int[] kingsPos = this.getKingsCurPosition();
		
		for(ChessPiece piece : enemy.alivePieces){
			
			for(int[] move : piece.getNextValidMoves(board)){
				
				if(move[0] == kingsPos[0] && move[1] == kingsPos[1]){
					return true;
				}
				
			}
			
		}	
		
		return false;
	}
	
	
	
	/**
	 * Depending on the current board state/situation, this method returns a 
	 * true or false result as to the player under attack is currently check-mate or 
	 * not.
	 * 
	 * @param board
	 * @return
	 */
	public boolean isCheckMate(ChessBoard board){
		
		for(ChessPiece piece : this.alivePieces){
			
			//If moves exist for any Piece
			for(int[] move : piece.getNextValidMoves(board)){			
				
				ChessBoard checkBoard = board;
				
				if(piece.validMoveTo(move[0], move[1], checkBoard, this)){
					
					/* If not in Check with the Move i.e Not CheckMate*/
					if(!this.isCheck(checkBoard))
						return false;
				}
				
			}
			
		}	
		
		if(this.isCheck(board))
			return true;
		
		//Stale-mate has occurred
		else
			return false;
		
	}


	
	/**
	 * Given a piece passed in as a parameter to the method, that piece is removed
	 * from the current alive list of pieces of the player.
	 * 
	 * @param p
	 */
	public void removePieceFromList(ChessPiece p){
		this.alivePieces.remove(p);
	}
}
