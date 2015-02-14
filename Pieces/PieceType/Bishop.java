package PieceType;
import java.util.ArrayList;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.Player;


/**
 * This class describes the Chess-Piece "Bishop" with all its functionalities.
 * 
 * @author Vishrut Reddi
 *
 */
public class Bishop extends ChessPiece{

	/**
	 * Default Constructor
	 */
	public Bishop(){
		
		this.setPieceColor(null);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This constructor sets the new Bishop to by whatever color is specified.
	 * The current position of intent is (-1, -1) which does not exist. it 
	 * only acts as a place holder. With this current position the piece is not
	 * yet set on the board.
	 * 
	 * @param color
	 */
	public Bishop(PieceColor color){
		
		this.setPieceColor(color);
		this.setCurrentPosition(-1, -1);
	}
	
	/**
	 * This parameterized constructor sets the the position of the Bishop associated
	 * with the Chess-Piece. NOTE that the position assigned to the Bishop is just
	 * the position that the Bishop is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param r
	 * @param c
	 */
	public Bishop(int r, int c){
		
		this.setCurrentPosition(r, c);
	}
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Bishop associated with the Chess-Piece. NOTE that the position assigned to the Bishop
	 * is just the position that the Bishop is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor.
	 * 
	 * @param color
	 * @param r
	 * @param c
	 */
	public Bishop(PieceColor color, int r, int c){
		
		this.setPieceColor(color);
		this.setCurrentPosition(r, c);
	}
	
	
	
	/**
	 * This parameterized constructor sets the assigned color and the position of the 
	 * Bishop associated with the Chess-Piece. NOTE that the position assigned to the Bishop
	 * is just the position that the Bishop is intended to be in. It has not been placed on 
	 * the Chess Board with this constructor. The Position this time is passed in the form 
	 * of an Array.
	 * 
	 * @param color
	 * @param curPos
	 */
	public Bishop(PieceColor color, int[] curPos){
		
		this.setPieceColor(color);
		this.setCurrentPosition(curPos[0], curPos[1]);
	}
	
	/**
	 * Finds valid spots for the Bishop to move to in the Upper Right Diagonal from
	 * the Bishop's current Position. A Valid Move should contain a spot which is either 
	 * unoccupied and there is no piece in between the Diagonal movement of the Bishop or if 
	 * the destination is occupied by an enemy unit and there are no pieces in between
	 * the diagonal movement of the Bishop.
	 * 
	 * @param board
	 * @return nextMoves (A List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();			/* Get Bishop's Current Position on the Board */
		int checkRow = curPos[0] + 1;
		int checkCol = curPos[1] + 1;
		
		while(checkRow <= 7 && checkCol <= 7){
			
			//If spot is unoccupied then it is a valid move
			if(board.spotOpen(checkRow, checkCol)){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//If the spot is occupied by an Enemy Unit then the spot is a valid move
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
				break;			/* Can't Move any further than that */
			}
			//If the Spot is occupied by a Friendly Unit then further than that
			//there are no valid moves
			else
				break;
			
			//Move to the next Top Right Diagonal Spot
			checkRow++;
			checkCol++;
		}
		
		return nextMoves;
	}
	
	
	/**
	 * Finds valid spots for the Bishop to move to in the Upper Left Diagonal from
	 * the Bishop's current Position. A Valid Move should contain a spot which is either 
	 * unoccupied and there is no piece in between the Diagonal movement of the Bishop or if 
	 * the destination is occupied by an enemy unit and there are no pieces in between
	 * the diagonal movement of the Bishop.
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidUpLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();			/* Get Bishop's Current Position on the Board */
		int checkRow = curPos[0] + 1;
		int checkCol = curPos[1] - 1;
		
		while(checkRow <= 7 && checkCol >= 0){
			
			//If spot is unoccupied then it is a valid move
			if(board.spotOpen(checkRow, checkCol)){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//If the spot is occupied by an Enemy Unit then the spot is a valid move
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
				break;
			}
			//If the Spot is occupied by a Friendly Unit then further than that
			//there are no valid moves
			else
				break;
			
			//Move to the next Top Left Diagonal Spot
			checkRow++;
			checkCol--;
		}
		
		return nextMoves;
	}
	
	
	/**
	 * Finds valid spots for the Bishop to move to in the Lower Right Diagonal from
	 * the Bishop's current Position. A Valid Move should contain a spot which is either 
	 * unoccupied and there is no piece in between the Diagonal movement of the Bishop or if 
	 * the destination is occupied by an enemy unit and there are no pieces in between
	 * the diagonal movement of the Bishop.
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownRightMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();			/* Get Bishop's Current Position on the Board */
		int checkRow = curPos[0] - 1;
		int checkCol = curPos[1] + 1;
		
		while(checkRow >= 0 && checkCol <= 7){
			
			//If spot is unoccupied then it is a valid move
			if(board.spotOpen(checkRow, checkCol)){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//If the spot is occupied by an Enemy Unit then the spot is a valid move
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
				break;
			}
			//If the Spot is occupied by a Friendly Unit then further than that
			//there are no valid moves
			else
				break;
			
			//Move to the next Bottom Right Diagonal Spot
			checkRow--;
			checkCol++;
		}
		
		return nextMoves;
	}
	
	
	/**
	 * Finds valid spots for the Bishop to move to in the Lower Left Diagonal from
	 * the Bishop's current Position. A Valid Move should contain a spot which is either 
	 * unoccupied and there is no piece in between the Diagonal movement of the Bishop or if 
	 * the destination is occupied by an enemy unit and there are no pieces in between
	 * the diagonal movement of the Bishop.
	 * 
	 * @param board
	 * @return nextMoves (List of Valid Moves)
	 */
	public ArrayList<int[]> getValidDownLeftMoves(ChessBoard board){
		
		ArrayList<int[]> nextMoves = new ArrayList<int[]>();
		int curPos[] = this.getCurrentPosition();			/* Get Bishop's Current Position on the Board */
		int checkRow = curPos[0] - 1;
		int checkCol = curPos[1] - 1;
		
		while(checkRow >= 0 && checkCol >= 0){
			
			//If spot is unoccupied then it is a valid move
			if(board.spotOpen(checkRow, checkCol)){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
			}
			//If the spot is occupied by an Enemy Unit then the spot is a valid move
			else if(this.getPieceColor() != board.getPieceAtSpot(checkRow, checkCol).getPieceColor()){
				int[] validMove = {checkRow, checkCol};
				nextMoves.add(validMove);
				break;
			}
			//If the Spot is occupied by a Friendly Unit then further than that
			//there are no valid moves
			else
				break;
			
			//Move to the next Bottom Left Diagonal Spot
			checkRow--;
			checkCol--;
		}
		
		return nextMoves;
	}
	
	
	/**
	 * One by one finds all the valid moves for the Bishop in the current position and in the
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
