package PieceType;
import java.util.ArrayList;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;


/**
 * This class describes the Chess-Piece "Queen" with all its functionalities.
 * 
 * @author Vishrut Reddi
 *
 */
public class Queen extends ChessPiece{
	
	/**
	 * Default Constructor
	 */
	public Queen(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new Queen to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public Queen(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	
	/**
	 * This parameterized constructor sets the the position of the Queen associated
	 * with the Chess-Piece. NOTE that the position assigned to the Queen is just
	 * the position that the Queen is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Queen(int r, int c){
		
		this.setCurrentPosition(r, c);
	}
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Queen associated with the Chess-Piece. NOTE that the position assigned to the Queen
	 * is just the position that the Queen is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Queen(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Queen associated with the Chess-Piece. NOTE that the position assigned to the Queen
	 * is just the position that the Queen is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public Queen(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	
	
	
	
	/**
	 * Queen inherits the same kind of Up moves from the Rook, therefore an emulated Queen is created
	 * to mimic the Rook's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidUpMoves(ChessBoard board){
				
		int curPos[] = this.getCurrentPosition();
		Rook emulatedQueen = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidUpMoves(board);
	}
	
	
	
	
	/**
	 * Queen inherits the same kind of Down moves from the Rook, therefore an emulated Queen is created
	 * to mimic the Rook's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidDownMoves(ChessBoard board){
				
		int curPos[] = this.getCurrentPosition();
		Rook emulatedQueen = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidDownMoves(board);
	}

	
	
	
	/**
	 * Queen inherits the same kind of Right moves from the Rook, therefore an emulated Queen is created
	 * to mimic the Rook's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidRightMoves(ChessBoard board){
				
		int curPos[] = this.getCurrentPosition();
		Rook emulatedQueen = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidRightMoves(board);
	}

	
	
	/**
	 * Queen inherits the same kind of Left moves from the Rook, therefore an emulated Queen is created
	 * to mimic the Rook's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidLeftMoves(ChessBoard board){
				
		int curPos[] = this.getCurrentPosition();
		Rook emulatedQueen = new Rook(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidLeftMoves(board);
	}
	
	
	
	
	/**
	 * Queen inherits the same kind of Up Right moves from the Bishop, therefore an emulated Queen is created
	 * to mimic the Bishop's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidUpRightMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedQueen = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidUpRightMoves(board);
	}
	
	
	
	
	/**
	 * Queen inherits the same kind of Up Left moves from the Bishop, therefore an emulated Queen is created
	 * to mimic the Bishop's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidUpLeftMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedQueen = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidUpLeftMoves(board);
	}
	
	
	
	
	/**
	 * Queen inherits the same kind of Down Right moves from the Bishop, therefore an emulated Queen is created
	 * to mimic the Bishop's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidDownRightMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedQueen = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidDownRightMoves(board);
	}
	
	
	
	
	/**
	 * Queen inherits the same kind of Down Left moves from the Bishop, therefore an emulated Queen is created
	 * to mimic the Bishop's movement for the Queen's spot to get the list of valid moves.
	 * 
	 * @param board
	 * @return nextMoves
	 */
	public ArrayList<int[]> getValidDownLeftMoves(ChessBoard board){
		
		int curPos[] = this.getCurrentPosition();
		Bishop emulatedQueen = new Bishop(this.getPieceColor(), curPos[0], curPos[1]);
		
		return emulatedQueen.getValidDownLeftMoves(board);
	}
	
	
		
	/**
	 * One by one finds all the valid moves for the Queen in the current position and in the
	 * current board state/situation.
	 * 
	 * @param board
	 * @return
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
		
		//Valid TOP RIGHT DIAGONAL moves
		nextMoves.addAll(getValidUpRightMoves(board));
		
		//Valid TOP LEFT DIAGONAL moves
		nextMoves.addAll(getValidUpLeftMoves(board));
				
		//Valid BOTTOM RIGHT DIAGONAL moves
		nextMoves.addAll(getValidDownRightMoves(board));
				
		//Valid BOTTOM LEFT DIAGONAL moves
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
