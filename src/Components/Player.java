package Components;

import java.util.ArrayList;

import Components.ChessPiece.PieceColor;
import UI.ChessBoardView;


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
	
	ArrayList<ChessPiece> prevAlivePieces = new ArrayList<ChessPiece>();		//List of alive pieces in the previous move
	
	
	
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
	 * When the Player wants to start fresh, then all the pieces from the grave-yard
	 * must be removed and all the pieces must exist in the alive list. This function creates
	 * a new alive list which would be empty. The board needs to be set to fill in the pieces.
	 * 
	 * Note: The board can be filled by startNewGame() in ChessBoard.java
	 */
	public void startFresh(){
		
		alivePieces = new ArrayList<ChessPiece>();
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
	 * Sets the opponent player for the current player.
	 * 
	 * @param oppo
	 */
	public void setOpponent(Player oppo){
		
		this.opponent = oppo;
	}
	
	/**
	 * This method Undo all the Components of the Player.
	 */
	public void playerUndo(){
		
		//Create Previous List of alive pieces
		setPrevAliveList(alivePieces);
		
		//Clearing the list
		alivePieces.clear();
		
		//Adding previous state alive pieces to the current list
		for(ChessPiece piece : prevAlivePieces){
			alivePieces.add(piece);
		}
	}
	
	
	/**
	 * Creates a new list of alive pieces for a particular player. All the pieces on the board,
	 * currently alive for the required player are returned in a list.
	 * 
	 * @param board
	 * @return List of alive pieces for a player
	 */
	public ArrayList<ChessPiece> getCurAliveList(ChessBoard board){
		
		ArrayList<ChessPiece> newAliveList = new ArrayList<ChessPiece>();
		
		for(int row = 0; row < 8; row ++){
			for(int col = 0; col < 8; col ++){
				
				//If Player's Piece is found and the spot is not empty
				if(!board.spotOpen(row, col) && this.team == board.getPieceAtSpot(row, col).getPieceColor()){
					newAliveList.add(board.getPieceAtSpot(row, col));
				}
			}
		}
		return newAliveList;
	}
	
	
	/**
	 * This method helps store the alive pieces for a previous move for the player.
	 * This method would be helpful for if the player chooses to undo his/her move.
	 * This method basically takes the a list of pieces and makes that list the previously
	 * alive pieces list.
	 * 
	 * NOTE: Make sure to call this method BEFORE a player makes a move.
	 */
	public void setPrevAliveList(ArrayList<ChessPiece> aliveList){
		
		//Clear the previously stored pieces
		prevAlivePieces.clear();
		
		//Store the pieces in the previous alive list
		for(ChessPiece piece : aliveList){
			prevAlivePieces.add(piece);
		}
	}
	
	
	/**
	 * This method provides the list of alive chess pieces on the board for the player
	 * in the players previous move.
	 * 
	 * @return
	 */
	public ArrayList<ChessPiece> getPrevAliveList(){
		
		return prevAlivePieces;
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
		int[] kingPos = this.getKingsCurPosition();
		
		for(ChessPiece piece : enemy.alivePieces){
			
			for(int[] move : piece.getNextValidMoves(board)){

				if(move[0] == kingPos[0] && move[1] == kingPos[1]){
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
	public boolean isCheckMate(ChessBoard board, ChessBoardView view){
		
		Player p1 = this;
		Player p2 = this.getOpponent();
		for(ChessPiece piece : alivePieces){
			
			for(int[] move : piece.getNextValidMoves(board)){
				
				board.setPreviousStateBoard();
				p1.setPrevAliveList(p1.getCurAliveList(board));
				p2.setPrevAliveList(p2.getCurAliveList(board));
				
				if(piece.validMoveTo(move[0], move[1], board, this)){
					
					System.out.println(piece.getClass());
					board.boardUndo(p1, p2);
					p1.playerUndo();
					p2.playerUndo();
					
					return false;				
					
				}
			}
		}
		
		
		return true;
		
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
	
	
	public void printStatus(ChessBoard board){
		
		System.out.println();
		System.out.println("--------------------------" + this + "PIECES STATUS--------------------------------");
		
		//Get the List of Dead Pieces for the player
		ArrayList<ChessPiece> graveyard = board.getGraveyard(team);
		
		for(ChessPiece p : alivePieces){		
				int curPos[] = p.getCurrentPosition();
				System.out.println("" + p.getClass() + ": ALIVE @ (" + curPos[0] +"," + curPos[1] + ")");
		
		}
		for(ChessPiece p : graveyard){			
				System.out.println(p.getClass() + ": DEAD X_X");			
		}
		
		System.out.println("-------------------------------------------------------------------------");
		
	}
}
