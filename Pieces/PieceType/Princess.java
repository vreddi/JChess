/**
 * 
 */
package PieceType;

import java.util.ArrayList;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;

/**
 * This class represents the custom piece 'Princess'. All the implementation of its attributes
 * and functionalities can be found here. Princess is a special piece and it inherits its movements
 * from the Princess and the Bishop. She has to protect her father(King) in this war. Usually she does
 * not appear in a normal game of chess, but due to here exceptional battle skills, she is called upon
 * to unleash the wrath.
 *  
 * @author Vishrut Reddi
 *
 */
public class Princess extends ChessPiece {

	
	/**
	 * Default Constructor
	 */
	public Princess(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new Princess to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public Princess(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	
	
	/**
	 * This parameterized constructor sets the position of the Princess associated with the
	 * Chess-Piece. NOTE that the position assigned to the Princess is just the position
	 * that the Princess is intended to be in. It has not been placed on the Chess Board
	 * with this constructor.
	 * 
	 * @param r
	 * @param c
	 */
	public Princess(int r, int c){
		
		this.setCurrentPosition(r, c);
	}
	
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Princess associated with the Chess-Piece. NOTE that the position assigned to the Princess
	 * is just the position that the Princess is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Princess(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Princess associated with the Chess-Piece. NOTE that the position assigned to the Princess
	 * is just the position that the Princess is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public Princess(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	
	
	/**
	 * Finds all the valid move spots for the Princess in the Upper Right Side
	 * Quadrant of the Princess's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight + Bishop
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedBPrincess = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		Knight emulatedKPrincess = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		
		nextMoves.addAll(emulatedBPrincess.getValidUpRightMoves(board));
		nextMoves.addAll(emulatedKPrincess.getValidUpRightMoves(board));
		return nextMoves;
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Princess in the Upper Left Side
	 * Quadrant of the Princess's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight + Bishop
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedBPrincess = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		Knight emulatedKPrincess = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		
		nextMoves.addAll(emulatedBPrincess.getValidUpLeftMoves(board));
		nextMoves.addAll(emulatedKPrincess.getValidUpLeftMoves(board));
		return nextMoves;
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Princess in the Lower Right Side
	 * Quadrant of the Princess's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight + Bishop
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedBPrincess = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		Knight emulatedKPrincess = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		
		nextMoves.addAll(emulatedBPrincess.getValidDownRightMoves(board));
		nextMoves.addAll(emulatedKPrincess.getValidDownRightMoves(board));
		return nextMoves;
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Princess in the Lower Left Side
	 * Quadrant of the Princess's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight + Bishop
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedBPrincess = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		Knight emulatedKPrincess = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		
		nextMoves.addAll(emulatedBPrincess.getValidDownLeftMoves(board));
		nextMoves.addAll(emulatedKPrincess.getValidDownLeftMoves(board));
		return nextMoves;
	}
	
	
	/**
	 * One by one finds all the valid moves for the Princess in the current position and in the
	 * current board state/situation.
	 * 
	 * @param board
	 * @return nextMoves (A List of all Moves)
	 */
	public ArrayList<int[]> getNextMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
		//Valid UP RIGHT moves
		nextMoves.addAll(getValidUpRightMoves(board));
		
		//Valid UP LEFT moves
		nextMoves.addAll(getValidUpLeftMoves(board));
		
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
