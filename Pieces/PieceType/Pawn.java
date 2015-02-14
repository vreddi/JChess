package PieceType;
import java.util.ArrayList;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;

/**
 * 
 */

/**
 * This class describes the Chess-Piece "Pawn" with all its functionalities.
 * 
 * @author Vishrut Reddi
 *
 */
public class Pawn extends ChessPiece {

	
	/**
	 * Default Constructor
	 */
	public Pawn(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new Pawn to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public Pawn(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	
	/**
	 * This parameterized constructor sets the position of the Pawn associated with the
	 * Chess-Piece. NOTE that the position assigned to the Pawn is just the position
	 * that the Pawn is intended to be in. It has not been placed on the Chess Board
	 * with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Pawn(int r, int c){
		
		this.setCurrentPosition(r, c);
	}
	
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Pawn associated with the Chess-Piece. NOTE that the position assigned to the Pawn
	 * is just the position that the Pawn is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Pawn(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Pawn associated with the Chess-Piece. NOTE that the position assigned to the Pawn
	 * is just the position that the Pawn is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public Pawn(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	
	
	/**
	 * Checks if the pawn is in its initials position and if it moves
	 * then that move will be his first move. This done to detect the 2-step move
	 * Availability.
	 * 
	 * @return true/false
	 */
	public boolean isFirstMove(){
		
		int curPos[] = this.getCurrentPosition();
		
		if(this.getPieceColor() == PieceColor.WHITE && curPos[0] == 1)
			return true;
		
		else if(this.getPieceColor() == PieceColor.BLACK && curPos[0] == 6)
			return true;
		
		else
			return false;
	}

	
	
	
	/**
	 * For the current position of the pawn and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Up direction ONLY.
	 * 
	 * Intended Method for WHITE Pawn.
	 * 
	 * @param board
	 * @return nextMoves (A List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpMoves(ChessBoard board){
			
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int pawnCurPos[] = this.getCurrentPosition();
		int checkRow = pawnCurPos[0] + 1;		//Start Checking from one row above Pawn's Current Row
		int checkCol = pawnCurPos[1];
		
		//Case where the Pawn Moves off the board
		if(checkRow > 7 || checkCol > 7 || checkCol < 0){
			return nextMoves;
		}
		
		//Check if above spot is open
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		
		checkRow++;
		
		//Check if its the first move so that the Pawn can move 2 steps ahead (both spots in-front should be open)
		if(this.isFirstMove() && board.spotOpen(checkRow, checkCol) && board.spotOpen(checkRow - 1, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		
		return nextMoves;
	}
	
	
	
	/**
	 * For the current position of the pawn and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Down direction ONLY.
	 * 
	 * Intended Method for BLACK Pawn.
	 * 
	 * @param board
	 * @return nextMoves (A List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownMoves(ChessBoard board){
			
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int pawnCurPos[] = this.getCurrentPosition();
		int checkRow = pawnCurPos[0] - 1;		//Start Checking from one row below Pawn's Current Row
		int checkCol = pawnCurPos[1];
		
		//Case where the Pawn Moves off the board
		if(checkRow < 0 || checkCol > 7 || checkCol < 0){
			return nextMoves;
		}
		
		//Check if above spot is open
		if(board.spotOpen(checkRow, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		
		checkRow--;
		
		//Check if its the first move so that the Pawn can move 2 steps ahead (both spots in-front should be open)
		if(this.isFirstMove() && board.spotOpen(checkRow, checkCol) && board.spotOpen(checkRow + 1, checkCol)){
			int validMove[] = {checkRow, checkCol};
			nextMoves.add(validMove);
		}
		
		return nextMoves;
	}

	
	/**
	 * Checks if the above right side Diagonal spot is a valid move for the Pawn. Verifies if that
	 * spot contains an enemy unit for attacking. In that cases the Pawn would have a valid
	 * diagonal move. If the condition holds true the List of the valid-move is returned.
	 * 
	 * Intended Method for a WHITE Pawn.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidUpRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int PawnCurPos[] = this.getCurrentPosition();
		
		int checkRow = PawnCurPos[0] + 1;
		int checkCol = PawnCurPos[1] + 1;		
		
		//Out of board attack, return empty list of valid moves
		if(checkRow > 7 || checkCol > 7)
			return nextMoves;
		
		//To avoid Null Pointer Exception
		if(board.getPieceAtSpot(checkRow, checkCol) != null){
			//If Diagonal Spot has an Enemy Unit
			if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		return nextMoves;
	}
	
	
	
	/**
	 * Checks if the bottom right side Diagonal spot is a valid move for the Pawn. Verifies if that
	 * spot contains an enemy unit for attacking. In that cases the Pawn would have a valid
	 * diagonal move. If the condition holds true the List of the valid-move is returned.
	 * 
	 * Intended Method for a BLACK Pawn.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidDownRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int PawnCurPos[] = this.getCurrentPosition();
		
		int checkRow = PawnCurPos[0] - 1;
		int checkCol = PawnCurPos[1] + 1;		
		
		//Out of board attack, return empty list of valid moves
		if(checkRow < 0 || checkCol > 7)
			return nextMoves;
		
		//To avoid Null Pointer Exception
		if(board.getPieceAtSpot(checkRow, checkCol) != null){
			//If Diagonal Spot has an Enemy Unit
			if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		return nextMoves;
	}
	

	
	
	/**
	 * Checks if the above left side Diagonal spot is a valid move for the Pawn. Verifies if that
	 * spot contains an enemy unit for attacking. In that cases the Pawn would have a valid
	 * diagonal move. If the condition holds true the List of the valid-move is returned.
	 * 
	 * Intended Method for a WHITE Pawn.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidUpLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int PawnCurPos[] = this.getCurrentPosition();
		
		int checkRow = PawnCurPos[0] + 1;
		int checkCol = PawnCurPos[1] - 1;	
		
		//Out of board attack, return empty list of valid moves
		if(checkRow > 7 || checkCol < 0)
			return nextMoves;
		
		//To avoid Null Pointer Exception
		if(board.getPieceAtSpot(checkRow, checkCol) != null){
			//If Diagonal Spot has an Enemy Unit
			if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		return nextMoves;
	}
	
	
	/**
	 * Checks if the bottom left side Diagonal spot is a valid move for the Pawn. Verifies if that
	 * spot contains an enemy unit for attacking. In that cases the Pawn would have a valid
	 * diagonal move. If the condition holds true the List of the valid-move is returned.
	 * 
	 * Intended Method for a BLACK Pawn.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidDownLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int PawnCurPos[] = this.getCurrentPosition();
		
		int checkRow = PawnCurPos[0] - 1;
		int checkCol = PawnCurPos[1] - 1;	
		
		//Out of board attack, return empty list of valid moves
		if(checkRow < 0 || checkCol < 0)
			return nextMoves;
		
		//To avoid Null Pointer Exception
		if(board.getPieceAtSpot(checkRow, checkCol) != null){
			//If Diagonal Spot has an Enemy Unit
			if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		return nextMoves;
	}
	
	
	
	
	/**
	 * One by one finds all the valid moves for the Pawn in the current position and in the
	 * current board state/situation.
	 * 
	 * @param board
	 * @return nextMoves (A List of all Moves)
	 */
	public ArrayList<int[]> getNextMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		//Piece is WHITE
		if(this.getPieceColor() == PieceColor.WHITE){
			
			//Valid UP moves
			nextMoves.addAll(getValidUpMoves(board));
			
			//Valid RIGHT DIAGONAL moves
			nextMoves.addAll(getValidUpRightMoves(board));
			
			//Valid LEFT DIAGONAL moves
			nextMoves.addAll(getValidUpLeftMoves(board));
		}
		//Piece is BLACK
		else{
			
			//Valid UP moves
			nextMoves.addAll(getValidDownMoves(board));
			
			//Valid RIGHT DIAGONAL moves
			nextMoves.addAll(getValidDownRightMoves(board));
			
			//Valid LEFT DIAGONAL moves
			nextMoves.addAll(getValidDownLeftMoves(board));
		}
		
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
		
		//Storing previous state
		ChessBoard prevState = board;
		
		ArrayList<int[]> validMoves = getNextMoves(board);
		
		//Move is Valid
		if(validMoves.contains(moveSpot)){
			
			//If the Destination spot is unoccupied
			/* Simply Occupy */
			if(board.spotOpen(r,  c)){
				
				int prevPos[] = this.getCurrentPosition();
				//Removed from previous spot
				board.setPieceAtSpot(prevPos[0], prevPos[1], null);
				
				//Moved to new Spot
				board.setPieceAtSpot(r, c, this);
				
				/* If player is still under check :: Invalid move */
				if(p.isCheck(board)){
					System.out.println("Invalid Move");
					board = prevState;
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
				//Removed from previous spot
				board.setPieceAtSpot(prevPos[0], prevPos[1], null);

				//Moved to new Spot
				board.setPieceAtSpot(r, c, this);
				
				/* If player is still under check :: Invalid move */
				if(p.isCheck(board)){
					System.out.println("Invalid Move");
					board = prevState;
					
					//Reviving the last piece put in the grave-yard
					if(board.getPieceAtSpot(r, c).getPieceColor() == PieceColor.WHITE)
						board.popGraveyard(PieceColor.WHITE);
					else
						board.popGraveyard(PieceColor.BLACK);
					
					return false;
				}
			}

		}
		
		else{
			System.out.println("Invalid Move - Destination does not exist");
			return false;
		}
		
		return true;
	}
}
