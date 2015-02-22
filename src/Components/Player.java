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
	 * Sets the opponent player for the current player.
	 * 
	 * @param oppo
	 */
	public void setOpponent(Player oppo){
		
		this.opponent = oppo;
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
	
	//EMERGENCY:: NEEDS FIXING!!!
	/**
	 * Depending on the current board state/situation, this method returns a 
	 * true or false result as to the player under attack is currently check-mate or 
	 * not.
	 * 
	 * @param board
	 * @return
	 */
	public boolean isCheckMate(ChessBoard board){
		
		ChessBoard emulatedBoard = new ChessBoard();
		Player emulatedP1 = new Player();			/* Trying to get out of CHeck */
		Player emulatedP2 = new Player();			/* Trying for Check-Mate */
		
		emulatedBoard = board.deepCopyBoard();
		emulatedP1.setOpponent(emulatedP2);
		emulatedP2.setOpponent(emulatedP1);
		
		//Copying Player Teams
		emulatedP1.team = this.getPlayerColor();
		emulatedP2.team = this.getOpponent().getPlayerColor();
		
		//Creating Alive List of Pieces for the new Emulated Copied Board
		emulatedP1.alivePieces = this.getCurAliveList(emulatedBoard);
		emulatedP2.alivePieces = this.getOpponent().getCurAliveList(emulatedBoard);
		
		for(ChessPiece piece : emulatedP1.alivePieces){
			
			//emulatedBoard = board.shallowCopyBoard();
			
			for(int[] move : piece.getNextValidMoves(emulatedBoard)){
				
				piece.validMoveTo(move[0], move[1], emulatedBoard, emulatedP1);
				
				System.out.println("Move Tried: " + piece.getClass() + " " + move[0] + "," + move[1]);
				emulatedBoard.printBoardState();
				emulatedP1.printStatus(emulatedBoard);
				emulatedP2.printStatus(emulatedBoard);
				
				if(!emulatedP1.isCheck(emulatedBoard))
					return false;
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
