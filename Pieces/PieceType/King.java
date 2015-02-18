package PieceType;
import Components.ChessPiece;
import Components.ChessBoard;
import Components.Player;

import java.util.ArrayList;


/**
 * This class describes the Chess-Piece "King" with all its functionalities.
 * 
 * @author Vishrut Reddi
 *
 */
public class King extends ChessPiece {

	/**
	 * Default Constructor
	 */
	public King(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new King to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public King(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	
	
	/**
	 * This parameterized constructor sets the position of the King associated with the
	 * Chess-Piece. NOTE that the position assigned to the King is just the position that
	 * the King is intended to be in. It has not been placed on the Chess Board with this
	 * constructor.
	 * 
	 * @param r
	 * @param c
	 */
	public King(int r, int c){
	
		this.setCurrentPosition(r, c);
	}
	
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * King associated with the Chess-Piece. NOTE that the position assigned to the King
	 * is just the position that the King is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public King(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * King associated with the Chess-Piece. NOTE that the position assigned to the King
	 * is just the position that the King is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public King(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Up direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidUpMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0] + 1;
		int checkCol = curPos[1];
		
		//Out of board attack, return empty list of valid moves
		if(checkRow > 7 || checkCol > 7)
			return nextMoves;
		
		//Normal Move
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		
		return nextMoves;
	}
	
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Up Right direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidUpRightMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0] + 1;
		int checkCol = curPos[1] + 1;
		
		
		//Out of board attack, return empty list of valid moves
		if(checkRow > 7 || checkCol > 7)
			return nextMoves;
		
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		return nextMoves;
	}
	
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Up Left direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidUpLeftMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0] + 1;
		int checkCol = curPos[1] - 1;
		
		
		//Out of board attack, return empty list of valid moves
		if(checkRow > 7 || checkCol < 0)
			return nextMoves;
		
		
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		
		return nextMoves;
	}
	
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Right direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidRightMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0];
		int checkCol = curPos[1] + 1;
		
		
		//Out of board attack, return empty list of valid moves
		if(checkCol > 7)
			return nextMoves;
				
				
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		
		return nextMoves;
	}
	
	
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Down direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidDownMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0] - 1;
		int checkCol = curPos[1];
		
		
		//Out of board attack, return empty list of valid moves
		if(checkCol < 0 || checkRow < 0)
			return nextMoves;
				
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		
		return nextMoves;
	}
	
	
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Down Right direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidDownRightMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0] - 1;
		int checkCol = curPos[1] + 1;
		
		
		//Out of board attack, return empty list of valid moves
		if(checkRow < 0 || checkCol > 7)
			return nextMoves;
		
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		
		return nextMoves;
	}
	
	
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Down Left direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidDownLeftMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0] - 1;
		int checkCol = curPos[1] - 1;
		
		
		//Out of board attack, return empty list of valid moves
		if(checkRow < 0 || checkCol < 0)
			return nextMoves;
				
				
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		
		return nextMoves;
	}
	
	
	
	
	
	/**
	 * For the current position of the King and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Left direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid moves)
	 */
	public ArrayList<int[]> getValidLeftMoves(ChessBoard board){
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int[] curPos = this.getCurrentPosition();
		int checkRow = curPos[0];
		int checkCol = curPos[1] - 1;
		
		
		//Out of board attack, return empty list of valid moves
		if(checkCol < 0)
			return nextMoves;
		
		//Can't move is it makes a check or check-mate situation
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		//Attacking Move
		else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);			
		}
		
		return nextMoves;
	}

	
	
	/**
	 * One by one finds all the valid moves for the King in the current position and in the
	 * current board state/situation.
	 * 
	 * @param board
	 * @return nextMoves (A List of all Moves)
	 */
	public ArrayList<int[]> getNextMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		//Valid UP moves
		nextMoves.addAll(getValidUpMoves(board));
		
		//Valid UP RIGHT moves
		nextMoves.addAll(getValidUpRightMoves(board));
		
		//Valid UP LEFT moves
		nextMoves.addAll(getValidUpLeftMoves(board));
		
		//Valid LEFT moves
		nextMoves.addAll(getValidLeftMoves(board));
		
		//Valid RIGHT moves
		nextMoves.addAll(getValidRightMoves(board));
		
		//Valid DOWN moves
		nextMoves.addAll(getValidDownMoves(board));
		
		//Valid DOWN RIGHT moves
		nextMoves.addAll(getValidDownRightMoves(board));
		
		//Valid DOWN LEFT moves
		nextMoves.addAll(getValidDownLeftMoves(board));
				
		return nextMoves;
	}
	
	
	
	
	
	/**
	 * Takes the ChessPiece and if the destination spot counts as a valid move,
	 * then this method moves that chess piece to that spot. This move could be a normal move
	 * or an attacking move. The method handles both cases.
	 * 
	 * @param r
	 * @param c
	 * @param board
	 * @param p
	 * 
	 * @return true/false
	 */
	public boolean moveTo(int r, int c, ChessBoard board, Player p){
		
int moveSpot[] = {r, c};
		
		ArrayList<int[]> validMoves = getNextMoves(board);
		
		//Move is Valid
		if(isInList(validMoves, moveSpot)){
			
			//If the Destination spot is unoccupied
			/* Simply Occupy */
			if(board.spotOpen(r,  c)){
				
				//Getting previous position (position before move)
				int prevPos[] = this.getCurrentPosition();
				int prevRow = prevPos[0];
				int prevCol = prevPos[1];
				
				//Removed from previous spot
				board.removePieceFromSpot(prevRow, prevCol);
				
				//Moved to new Spot
				board.setPieceAtSpot(r, c, this);
				
				/* If player is still under check :: Invalid move */
				if(p.isCheck(board)){
					
					//Log Invalid move in Console
					System.out.println();
					System.out.println("Invalid Move " + p + " is under Check!");
					System.out.println();
					
					//Removed from new spot
					board.removePieceFromSpot(r	, c);
					//Set on the old spot
					board.setPieceAtSpot(prevRow, prevCol, this);
					
					return false;
				}
			}
			
			//If the Destination spot is occupied by an enemy unit
			/* Kill and Occupy */
			else{
				
				if(board.getPieceAtSpot(r, c).getPieceColor() == PieceColor.WHITE){
				
					p.getOpponent().removePieceFromList(board.getPieceAtSpot(r, c));		//Remove from Alive List
					board.addToGraveyard(PieceColor.WHITE, board.getPieceAtSpot(r, c));		//Added to Grave-Yard
				}
					
				else{
					p.getOpponent().removePieceFromList(board.getPieceAtSpot(r, c));		//Remove from Alive List
					board.addToGraveyard(PieceColor.BLACK, board.getPieceAtSpot(r, c));		//Added to Grave-Yard			
				}
				
				int prevPos[] = this.getCurrentPosition();
				int prevRow = prevPos[0];
				int prevCol = prevPos[1];
				//Removed from previous spot
				board.removePieceFromSpot(prevRow, prevCol);

				//Moved to new Spot
				board.setPieceAtSpot(r, c, this);
				
				/* If player is still under check :: Invalid move */
				if(p.isCheck(board)){
					
					
					ChessPiece revivedUnit;
					
					//Reviving the last piece put in the grave-yard
					if(board.getPieceAtSpot(r, c).getPieceColor() == PieceColor.WHITE)
						revivedUnit = board.popGraveyard(PieceColor.BLACK);
					else
						revivedUnit = board.popGraveyard(PieceColor.WHITE);
					
					//Log Invalid move in Console
					System.out.println();
					System.out.println("Invalid Move " + p + " is under Check!");
					System.out.println();
					
					//Removed from new spot
					board.removePieceFromSpot(r	, c);
					//Set on the old spot
					board.setPieceAtSpot(prevRow, prevCol, this);
					
					board.setPieceAtSpot(r, c, revivedUnit);
					
					return false;
				}
			}

		}
		
		else{
			System.out.println("\n Invalid Move!");
			return false;
		}
		
		return true;
	}
}
