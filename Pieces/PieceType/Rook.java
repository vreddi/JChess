package PieceType;
import java.util.ArrayList;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;

/**
 * This class describes the Chess-Piece "Rook" with all its functionalities.
 * 
 * @author Vishrut Reddi
 *
 */
public class Rook extends ChessPiece{
	
	
	/**
	 * Default Constructor
	 */
	public Rook(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new Rook to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public Rook(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This parameterized constructor sets the the position of the Rook associated
	 * with the Chess=Piece. NOTE that the position assigned to the Rook is just
	 * the position that the Rook is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Rook(int r, int c){
		
		this.setCurrentPosition(r, c);
	}

	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Rook associated with the Chess-Piece. NOTE that the position assigned to the Rook
	 * is just the position that the Rook is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Rook(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Rook associated with the Chess-Piece. NOTE that the position assigned to the Rook
	 * is just the position that the Rook is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public Rook(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	
	/**
	 * For the current position of the rook and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * downward direction.
	 * 
	 * @param board
	 * @return nextMoves (A List with Valid Down Moves)
	 */
	public ArrayList<int[]> getValidDownMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int rookCurPos[] = this.getCurrentPosition();
		int checkRow = rookCurPos[0] - 1;		//Start Checking from one row below Rook's Current Row
		int checkCol = rookCurPos[1];
		
		while(checkRow >= 0){
			
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Attacking Move
			else if(board.getPieceAtSpot(checkRow, checkCol).getPieceColor() != this.getPieceColor()){
				
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);		
				break;
			}
			
			else
				break;
			
			checkRow--;
		}
		
		return nextMoves;
	}
	
	
	/**
	 * For the current position of the rook and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Upward direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid up moves)
	 */
	public ArrayList<int[]> getValidUpMoves(ChessBoard board){
			
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int rookCurPos[] = this.getCurrentPosition();
		int checkRow = rookCurPos[0] + 1;		//Start Checking from one row above Rook's Current Row
		int checkCol = rookCurPos[1];
		
		while(checkRow <= 7){
			
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Attacking Move
			else if(board.getPieceAtSpot(checkRow, checkCol).getPieceColor() != this.getPieceColor()){
				
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);	
				break;
			}
			else
				break;
			
			checkRow++;
		}

		return nextMoves;
	}
	
	
	/**
	 * 
	 * @param board
	 * @return nextMoves (List of valid right moves)
	 */
	public ArrayList<int[]> getValidRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int rookCurPos[] = this.getCurrentPosition();
		int checkRow = rookCurPos[0];
		//Start Checking from one Column to the right of Rook's Current Column
		int checkCol = rookCurPos[1] + 1;		
		
		while(checkCol <= 7){
			
			if(board.spotOpen(checkRow, checkCol)){
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Attacking Move
			else if(board.getPieceAtSpot(checkRow, checkCol).getPieceColor() != this.getPieceColor()){
				
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);	
				break;
			}
			else
				break;
				
			checkCol++;		
		}
		
		return nextMoves;
	}
	
	
	/**
	 * For the current position of the rook and the current situation the piece
	 * is in, all the valid moves are calculated. And all these valid spots are 
	 * stored in a List of moves. This method finds only the Valid Moves in the
	 * Left direction.
	 * 
	 * @param board
	 * @return nextMoves (List of valid left moves)
	 */
	public ArrayList<int[]> getValidLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int rookCurPos[] = this.getCurrentPosition();  
		int checkRow = rookCurPos[0];
		//Start Checking from one Column to the left of Rook's Current Column
		int checkCol = rookCurPos[1] - 1;		
		
		while(checkCol >= 0){
			
			//Normal Move
			if(board.spotOpen(checkRow, checkCol)){
				
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//Attacking Move
			else if(board.getPieceAtSpot(checkRow, checkCol).getPieceColor() != this.getPieceColor()){
				
				int validMove[] = {checkRow, checkCol};
				nextMoves.add(validMove);
				break;
			}
			else
				break;
				
			checkCol--;			
		}
		
		return nextMoves;
	}


	/**
	 * One by one finds all the valid moves for the Rook in the current position and in the
	 * current board state/situation.
	 * 
	 * @param board
	 * @return nextMoves (A List of all Moves)
	 */
	public ArrayList<int[]> getNextMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		
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
