/**
 * 
 */
package PieceType;

import java.util.ArrayList;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;

/**
 * A skilled warrior, who rarely takes part in wars. But is called upon service by the King. 
 * The great Empress. She has developed her skills in battle by learning from the Master Knight
 * and Master Rook at a very early age. All the functionalities and implementation for the Empress
 * is available here.
 * 
 * @author Vishrut Reddi
 *
 */
public class Empress extends ChessPiece{

	/**
	 * Default Constructor
	 */
	public Empress(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new Empress to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public Empress(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	
	
	/**
	 * This parameterized constructor sets the position of the Empress associated with the
	 * Chess-Piece. NOTE that the position assigned to the Empress is just the position
	 * that the Empress is intended to be in. It has not been placed on the Chess Board
	 * with this constructor.
	 * 
	 * @param r
	 * @param c
	 */
	public Empress(int r, int c){
		
		this.setCurrentPosition(r, c);
	}
	
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Empress associated with the Chess-Piece. NOTE that the position assigned to the Empress
	 * is just the position that the Empress is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Empress(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Empress associated with the Chess-Piece. NOTE that the position assigned to the Empress
	 * is just the position that the Empress is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public Empress(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	
	
	/**
	 * Finds all the valid move spots for the Empress in the Upper Right Side
	 * Quadrant of the Empress's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpRightMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Knight emulatedKEmpress = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidUpRightMoves(board);
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Empress in the Upper Left Side
	 * Quadrant of the Empress's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpLeftMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Knight emulatedKEmpress = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidUpLeftMoves(board);
	}
	
	
	
	
	/**
	 * Finds all the valid move spots for the Empress in the Lower Right Side
	 * Quadrant of the Empress's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownRightMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Knight emulatedKEmpress = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidDownRightMoves(board);
	}
	
	
	
	/**
	 * Finds all the valid move spots for the Empress in the Lower Left Side
	 * Quadrant of the Empress's current position. There will be at-most 2 valid moves
	 * in this Quadrant.
	 * 
	 * Inherited Moves from Knight
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownLeftMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Knight emulatedKEmpress = new Knight(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidDownLeftMoves(board);
	}
	
	
	
	
	/**
	 * For the current position of the Empress and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Up direction.
	 * 
	 * Inherited Moves from Rook
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Rook emulatedKEmpress = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidUpMoves(board);
	}
	
	
	
	/**
	 * For the current position of the Empress and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Down direction.
	 * 
	 * Inherited Moves from Rook
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Rook emulatedKEmpress = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidDownMoves(board);
	}
	
	
	
	/**
	 * For the current position of the Empress and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Left direction.
	 * 
	 * Inherited Moves from Knight
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidLeftMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Rook emulatedKEmpress = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidLeftMoves(board);
	}
	
	
	
	/**
	 * For the current position of the Empress and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Right direction.
	 * 
	 * Inherited Moves from Knight
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidRightMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Rook emulatedKEmpress = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		return emulatedKEmpress.getValidRightMoves(board);
	}
	
	
	/**
	 * One by one finds all the valid moves for the Empress in the current position and in the
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
		
		//Valid UP moves
		nextMoves.addAll(getValidUpMoves(board));
				
		//Valid RIGHT moves
		nextMoves.addAll(getValidRightMoves(board));
				
		//Valid DOWN moves
		nextMoves.addAll(getValidDownMoves(board));
				
		//Valid LEFT moves
		nextMoves.addAll(getValidLeftMoves(board));
		
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
