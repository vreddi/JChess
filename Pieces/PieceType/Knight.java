package PieceType;
import java.util.ArrayList;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;




/**
 * This class describes the Chess-Piece "Knight" with all its functionalities.
 * 
 * @author Vishrut Reddi
 *
 */
public class Knight extends ChessPiece{

	/**
	 * 
	 */
	public Knight(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new Knight to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public Knight(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	
	
	/**
	 * This parameterized constructor sets the position of the Knight associated with the
	 * Chess-Piece. NOTE that the position assigned to the Knight is just the position
	 * that the Knight is intended to be in. It has not been placed on the Chess Board
	 * with this constructor.
	 * 
	 * @param r
	 * @param c
	 */
	public Knight(int r, int c){
		
		this.setCurrentPosition(r, c);
	}
	
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Knight associated with the Chess-Piece. NOTE that the position assigned to the Knight
	 * is just the position that the Knight is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Knight(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Knight associated with the Chess-Piece. NOTE that the position assigned to the Knight
	 * is just the position that the Knight is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public Knight(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	
	
	/**
	 * Finds all the valid move spots for the Knight in the Upper Right Side
	 * Quadrant of the Knight's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();
		int checkRow = curPos[0];
		int checkCol = curPos[1];
		
		//Move 1 :: UP -> UP -> RIGHT
		checkRow += 2;
		checkCol += 1;
		if(checkRow <= 7 && checkCol <= 7){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		//Move 2 :: RIGHT -> RIGHT -> UP
		checkRow = curPos[0] + 1;
		checkCol = curPos[1] + 2;
		if(checkRow <= 7 && checkCol <= 7){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			
		}
		
		return nextMoves;
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Knight in the Upper Left Side
	 * Quadrant of the Knight's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();
		int checkRow = curPos[0];
		int checkCol = curPos[1];
		
		//Move 1 :: UP -> UP -> LEFT
		checkRow += 2;
		checkCol -= 1;
		if(checkRow <= 7 && checkCol >= 0){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		//Move 2 :: LEFT -> LEFT -> UP
		checkRow = curPos[0] + 1;
		checkCol = curPos[1] - 2;
		if(checkRow <= 7 && checkCol >= 0){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			
		}
		
		return nextMoves;
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Knight in the Lower Right Side
	 * Quadrant of the Knight's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();
		int checkRow = curPos[0];
		int checkCol = curPos[1];
		
		//Move 1 :: DOWN -> DOWN -> RIGHT
		checkRow -= 2;
		checkCol += 1;
		if(checkRow >= 0 && checkCol <= 7){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		//Move 2 :: RIGHT -> RIGHT -> DOWN
		checkRow = curPos[0] - 1;
		checkCol = curPos[1] + 2;
		if(checkRow >= 0 && checkCol <= 7){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			
		}
		
		return nextMoves;
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Knight in the Lower Left Side
	 * Quadrant of the Knight's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();
		int checkRow = curPos[0];
		int checkCol = curPos[1];
		
		//Move 1 :: DOWN -> DOWN -> LEFT
		checkRow -= 2;
		checkCol -= 1;
		if(checkRow >= 0 && checkCol >= 0){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
		}
		
		//Move 2 :: LEFT -> LEFT -> DOWN
		checkRow = curPos[0] - 1;
		checkCol = curPos[1] - 2;
		if(checkRow >= 0 && checkCol >= 0){
			
			//Spot is unoccupied, then the move is valid
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Spot is occupied by an Enemy Unit, then the move is valid
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			
		}
		
		return nextMoves;
	}
	
	
	
	
	/**
	 * One by one finds all the valid moves for the Knight in the current position and in the
	 * current board state/situation.
	 * 
	 * @param board
	 * @return nextMoves (A List of all Moves)
	 */
	public ArrayList<int[]> getNextMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		//Valid UP moves
		nextMoves.addAll(getValidUpRightMoves(board));
		
		//Valid RIGHT moves
		nextMoves.addAll(getValidUpLeftMoves(board));
		
		//Valid DOWN moves
		nextMoves.addAll(getValidDownRightMoves(board));
		
		//Valid LEFT moves
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
