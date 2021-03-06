package Components;
import java.util.ArrayList;

import Components.ChessPiece.PieceColor;
import PieceType.Bishop;
import PieceType.Empress;
import PieceType.King;
import PieceType.Knight;
import PieceType.Pawn;
import PieceType.Princess;
import PieceType.Queen;
import PieceType.Rook;

/**
 * Implementing the Chess Board as a 2D Array. On this 2D array of 'ChessPiece' Objects
 * if a spot on the 2D array is null then the spot is empty and no Piece lies on it
 * otherwise if the ChessPiece Object is on the spot then that ChessBoard spot is 
 * currently occupied by that particular ChessPiece Object.
 */

/**
 * @author Vishrut Reddi
 *
 */
public class ChessBoard {
	
	//Main 8x8 Board
	ChessPiece[][] board = new ChessPiece[8][8];
	
	//Grave-yards for each player when pieces are killed
	ArrayList<ChessPiece> whiteGraveyard = new ArrayList<ChessPiece>();
	ArrayList<ChessPiece> blackGraveyard = new ArrayList<ChessPiece>();
	
	
	//ChessBoard 1 move behind: 8x8 Board
	//every block stores a code specifying each piece
	String[][] prevBoard = new String[8][8];
		
	//Grave-yards for each player when pieces are killed (A move behind)
	ArrayList<ChessPiece> prevWhiteGraveyard = new ArrayList<ChessPiece>();
	ArrayList<ChessPiece> prevBlackGraveyard = new ArrayList<ChessPiece>();
	
		
	/**
	 * Default Constructor
	 */
	public ChessBoard(){
	}
	
	
	
	/**
	 * By providing a true parameter the Chess Board is preset with all the WHITE
	 * and BLACK pieces in their initial positions for a new game. The game is 
	 * set for the 2 players p1 and p2.
	 * 
	 * @param setBoard
	 * @param p1
	 * @param p2
	 */
	public ChessBoard(boolean setBoard, Player p1, Player p2){
		
		if(setBoard){
			//Set White Pieces
			resetPieces(ChessPiece.PieceColor.WHITE, p1);
		
			//Set Black Pieces
			resetPieces(ChessPiece.PieceColor.BLACK, p2);
		}
		
	}

	
	
	
	/**
	 * Checks if a spot on the Chess-Board is unoccupied. Spot is determined by
	 * the input parameter of row number(r) and column number(c). A true/false answer is 
	 * returned depending on the spot being occupied or unoccupied.
	 * 
	 * @param r
	 * @param c
	 * @return true/false
	 */
	public boolean spotOpen(int r, int c){

		 if(board[r][c] == null)
			 return true;
		
		 return false;
	}
	
	
	
	/**
	 * Undo Every Component of the Chess Board with this method
	 * -Chess Board Pieces
	 * -White Player's Grave-Yard
	 * -Black Player's Grave-Yard
	 * 
	 * @param Player whitePlayer
	 * @param Player blackPlayer
	 */
	public void boardUndo(Player whitePlayer, Player blackPlayer){
		
		//Undo the Board State
		makeBoardUndo(whitePlayer, blackPlayer);
		
		//Undo the Grave-yards
		whiteGraveyard.clear();
		blackGraveyard.clear();
		
		//Update White Grave-Yard
		for(ChessPiece piece : prevWhiteGraveyard)
				whiteGraveyard.add(piece);
		
		//Update Black Grave-Yard
		for(ChessPiece piece : prevBlackGraveyard)
			blackGraveyard.add(piece);
	}
	
	
	
	/**
	 * This method makes the current board set itself according to the encoding
	 * given in the previous state of the chess board one move ago.
	 * 
	 * @param Player (whitePlayer)
	 * @param Player (blackPlayer)
	 */
	public void makeBoardUndo(Player whitePlayer, Player blackPlayer){
		
		//Collect Pieces from White Alive List
		for(ChessPiece piece : whitePlayer.alivePieces){
			
			for(int row = 7; row >= 0; row--){
				for(int col = 0; col < 8; col++){
					
					if(prevBoard[row][col].	compareToIgnoreCase(piece.getPieceCode()) == 0)
						setPieceAtSpot(row, col, piece);
					
				}
			}
		}

		//Collect Pieces from white Grave-yard
		for(ChessPiece piece : whiteGraveyard){
			
			for(int row = 7; row >= 0; row--){
				for(int col = 0; col < 8; col++){
					
					if(prevBoard[row][col].	compareToIgnoreCase(piece.getPieceCode()) == 0)
						setPieceAtSpot(row, col, piece);
				}
			}
		}
		
		//Collect Pieces from black alive list
		for(ChessPiece piece : blackPlayer.alivePieces){
			
			for(int row = 7; row >= 0; row--){
				for(int col = 0; col < 8; col++){
					
					if(prevBoard[row][col].	compareToIgnoreCase(piece.getPieceCode()) == 0)
						setPieceAtSpot(row, col, piece);
				}
			}
		}
		
		//Collect Pieces from black Grave-yard
		for(ChessPiece piece : blackGraveyard){
			
			for(int row = 7; row >= 0; row--){
				for(int col = 0; col < 8; col++){
					
					if(prevBoard[row][col].	compareToIgnoreCase(piece.getPieceCode()) == 0)
						setPieceAtSpot(row, col, piece);
				}
			}
		}
		
		//Fill in empty Spots
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col++){
				
				//Previous Board Spot is Empty
				if(prevBoard[row][col].	compareToIgnoreCase("Empty") == 0)
					board[row][col] = null;
				
			}
		}
	}

	
	/**
	 * Gets the Encoding for Each Piece and translate Each piece with its encoding.
	 * 
	 * @param piece
	 * @return
	 */
	public int getPieceEncoding(ChessPiece piece){
		
		if(piece.getClass().equals(PieceType.King.class))
			return 1;

		else if(piece.getClass().equals(PieceType.Queen.class))
			return 0;
				
		else if(piece.getClass().equals(PieceType.Bishop.class))
			return 4;
		
		else if(piece.getClass().equals(PieceType.Knight.class))
			return 3;
		
		else if(piece.getClass().equals(PieceType.Pawn.class))
			return 5;
		
		else 
			return 2;
	}
	
	
	
	/**
	 * This method sets the previous state of the board. Previous is only one move behind.
	 * This is useful if the player chooses to undo his/her move. If yes then the main Game Board
	 * becomes the previous Chess Board.
	 */
	public void setPreviousStateBoard(){
		
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col++){
				
				if(!this.spotOpen(row, col)){
					
					//Get Piece Reference at that Spot
					ChessPiece piece = this.getPieceAtSpot(row,  col);
					String code = piece.getPieceCode();
					
					prevBoard[row][col] = code;
				}
					
				//Spot not open
				else{
					prevBoard[row][col] = "empty";
				}
			}
		}
	}
	
	
	
	/**
	 * This method sets the previous grave-yard, which is used for if the player chooses to
	 * undo his/her move. The list (grave-yard) and the player passed in as parameters determines
	 * what the previous grave-yard will be and who's grave-yard will it be respectively.
	 * 
	 * NOTE: Invoke this method before the player makes the move to save previous state
	 * 
	 * @param graveyard
	 * @param p
	 */
	public void setPrevGraveyard(ArrayList<ChessPiece> graveyard, Player p){
		
		PieceColor playerColor = p.getPlayerColor();
		
		//Undo WHITE Player's Move
		if(playerColor == PieceColor.WHITE){
			
			//Clear the previously stored previous grave-yard
			prevWhiteGraveyard.clear();
			
			//Save pieces for the previous state
			for(ChessPiece piece : graveyard){
				
				prevWhiteGraveyard.add(piece);
			}
			
		}
		
		//Undo BLACK Player's Move
		else{
			
			//Clear the previously stored previous grave-yard
			prevBlackGraveyard.clear();
			
			//Save pieces for the previous state
			for(ChessPiece piece : graveyard){
				
				prevBlackGraveyard.add(piece);
			}
		}
		
	}

	
	
	/**
	 * Depending on who's move it is, which is recognized by the passed in parameter
	 * Player, his/her previous state grave-yard is returned.
	 * 
	 * @param p
	 * @return
	 */
	public ArrayList<ChessPiece> getPrevGraveyard(Player p){
		
		//WHITE Player Requested
		if(p.getPlayerColor() == PieceColor.WHITE)
			return prevWhiteGraveyard;
		
		//BLACK Player Requested
		else
			return prevBlackGraveyard;
	}
	
	
	
	/**
	 * Given the row(r) and Column(c) number for a particular spot on the board
	 * the current ChessPiece placed on that spot is returned. If there is no ChessPiece 
	 * on that spot then null is returned. If searched for a spot outside the board
	 * then IndexOutOfBounds Exception would be thrown.
	 * 
	 * @param r
	 * @param c
	 * @return ChessPiece/null
	 */
	public ChessPiece getPieceAtSpot(int r, int c){
		
		return board[r][c];
	}

	
	
	
	
	/**
	 * Given a Chess-Piece and a spot with a row number and column number,
	 * the chess piece is set on the board at the specified position by also updating 
	 * the piece's current position. If there is a piece already on the spot then 
	 * the spot is overridden and the previous piece is lost.
	 * 
	 * @param r
	 * @param c
	 * @param p
	 */
	public void setPieceAtSpot(int r, int c, ChessPiece p){
		board[r][c] = p;
		p.setCurrentPosition(r, c);
	}
	
	
	/**
	 * The Piece is removed from the board, from the specified spot passed
	 * in as parameters. The piece removed can be lost if no previous reference to it
	 * exists. The piece is just removed and NOT SENT TO THE GRAVEYARD.
	 * 
	 * @param r
	 * @param c
	 */
	public void removePieceFromSpot(int r, int c){
		
		board[r][c] = null;
	}
	
	/**
	 * Cleans up the Chess-Board by removing all the Chess-Pieces
	 * from the board by pointing each spot to null. It will produce
	 * a blank empty board.
	 * 
	 */
	public void cleanBoard(){
		
		for(int row = 0; row < board.length; row++){
			for(int col = 0; col < board[0].length; col++){
				
				board[row][col] = null;
			}
		}
		
	}
	
	
	public ChessBoard shallowCopyBoard(){
		
		ChessBoard copy = new ChessBoard();
		
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				copy.board[row][col] = this.board[row][col];
			}
		}
		
		return copy;
	}
	
	/**
	 * Copies the original chess board state and creates a new chess board
	 * with the same state. THis Deep copy would create a new board with new individual pieces,
	 * such that edit to this wont effect or alter the original
	 * 
	 * @return Copy Chess Board
	 */
	public ChessBoard deepCopyBoard(){
		
		ChessBoard copy = new ChessBoard();
		
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(this.spotOpen(row, col)){
					copy.board[row][col] = null;
				}
				else{
					
					ChessPiece piece = this.getPieceAtSpot(row,  col);
					
					if(piece.getClass().equals(PieceType.King.class)){
						King king = new King(this.getPieceAtSpot(row, col).getPieceColor());
						copy.setPieceAtSpot(row, col, king);
					}
										
					else if(piece.getClass().equals(PieceType.Queen.class)){
						Queen queen = new Queen(this.getPieceAtSpot(row, col).getPieceColor());
						copy.setPieceAtSpot(row, col, queen);
					}
							
					else if(piece.getClass().equals(PieceType.Bishop.class)){
						Bishop bishop = new Bishop(this.getPieceAtSpot(row, col).getPieceColor());
						copy.setPieceAtSpot(row, col, bishop);
					}
					
					else if(piece.getClass().equals(PieceType.Knight.class)){
						Knight knight = new Knight(this.getPieceAtSpot(row, col).getPieceColor());
						copy.setPieceAtSpot(row, col, knight);
					}
					
					else if(piece.getClass().equals(PieceType.Pawn.class)){
						Pawn pawn = new Pawn(this.getPieceAtSpot(row, col).getPieceColor());
						copy.setPieceAtSpot(row, col, pawn);
					}
					
					else if(piece.getClass().equals(PieceType.Rook.class)){
						Rook rook = new Rook(this.getPieceAtSpot(row, col).getPieceColor());
						copy.setPieceAtSpot(row, col, rook);
					}
				}
			}
		}
		return copy;
	}
	
	
	
	/**
	 * Get the Grave-yard, for the list of all the pieces who are dead.The list is provided
	 * of white or black pieces based on the parameter passed.
	 * 
	 * @param color
	 * @return List of dead pieces
	 */
	public ArrayList<ChessPiece> getGraveyard(PieceColor color){
		
		if(color == PieceColor.WHITE)
			return whiteGraveyard;
		
		else
			return blackGraveyard;
	}
	
	
	/**
	 * Resets all the pieces of a particular which is specified i.e WHITE
	 * or BLACK. All the Pieces of the specified color are removed from the board
	 * and reset on the same board in their original starting position. The pieces of 
	 * color which is not specified remains undisturbed. The player with the respective
	 * pieces requesting reset also needs to be passed
	 * 
	 * @param color
	 * @param p
	 */
	public void resetPieces(ChessPiece.PieceColor color, Player p){
		
		//Remove all the pieces from Alive list if previously existed
		p.startFresh();
		
		//Remove all the Pieces from the Grave-yard if previously existed
		clearGraveyard(p.getPlayerColor());
		
		int firstRow;
		int secondRow;
		
		//Setting Rows for either BLACK or WHITE
		if(color == ChessPiece.PieceColor.WHITE){
			firstRow = 0;
			secondRow = 1;
		}
		else{
			firstRow = 7;
			secondRow = 6;
		}
		
		//Removing BLACK or WHITE Pieces from the board
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				
				if(board[row][col] != null)
				if(board[row][col].getPieceColor() == color)
					board[row][col] = null;
			}
		}

		//Setting new BLACK or WHITE Pieces
		board[firstRow][0] = new Rook(color, firstRow, 0);		
		board[firstRow][0].setPieceCode(""+ board[firstRow][0].getPieceColor() + "Rook1");
		p.recruitPiece(board[firstRow][0]);
		
		board[firstRow][1] = new Knight(color, firstRow, 1);
		board[firstRow][1].setPieceCode(""+ board[firstRow][1].getPieceColor() + "Knight1");
		p.recruitPiece(board[firstRow][1]);
		
		board[firstRow][2] = new Bishop(color, firstRow, 2);
		board[firstRow][2].setPieceCode(""+ board[firstRow][2].getPieceColor() + "Bishop1");
		p.recruitPiece(board[firstRow][2]);
		
		board[firstRow][3] = new Queen(color, firstRow, 3);
		board[firstRow][3].setPieceCode(""+ board[firstRow][3].getPieceColor() + "Queen");
		p.recruitPiece(board[firstRow][3]);
		
		board[firstRow][4] = new King(color, firstRow, 4);
		board[firstRow][4].setPieceCode(""+ board[firstRow][4].getPieceColor() + "King");
		p.recruitPiece(board[firstRow][4]);
		
		board[firstRow][5] = new Bishop(color, firstRow, 5);
		board[firstRow][5].setPieceCode(""+ board[firstRow][5].getPieceColor() + "Bishop2");
		p.recruitPiece(board[firstRow][5]);
		
		board[firstRow][6] = new Knight(color, firstRow, 6);
		board[firstRow][6].setPieceCode(""+ board[firstRow][6].getPieceColor() + "Knight2");
		p.recruitPiece(board[firstRow][6]);
		
		board[firstRow][7] = new Rook(color, firstRow, 7);
		board[firstRow][7].setPieceCode(""+ board[firstRow][7].getPieceColor() + "Rook2");
		p.recruitPiece(board[firstRow][7]);
		
		board[secondRow][0] = new Pawn(color, secondRow, 0);
		board[secondRow][0].setPieceCode(""+ board[secondRow][0].getPieceColor() + "Pawn1");
		p.recruitPiece(board[secondRow][0]);
		
		board[secondRow][1] = new Pawn(color, secondRow, 1);
		board[secondRow][1].setPieceCode(""+ board[secondRow][1].getPieceColor() + "Pawn2");
		p.recruitPiece(board[secondRow][1]);
		
		board[secondRow][2] = new Pawn(color, secondRow, 2);
		board[secondRow][2].setPieceCode(""+ board[secondRow][2].getPieceColor() + "Pawn3");
		p.recruitPiece(board[secondRow][2]);
		
		board[secondRow][3] = new Pawn(color, secondRow, 3);
		board[secondRow][3].setPieceCode(""+ board[secondRow][3].getPieceColor() + "Pawn4");
		p.recruitPiece(board[secondRow][3]);
		
		board[secondRow][4] = new Pawn(color, secondRow, 4);
		board[secondRow][4].setPieceCode(""+ board[secondRow][4].getPieceColor() + "Pawn5");
		p.recruitPiece(board[secondRow][4]);
		
		board[secondRow][5] = new Pawn(color, secondRow, 5);
		board[secondRow][5].setPieceCode(""+ board[secondRow][5].getPieceColor() + "Pawn6");
		p.recruitPiece(board[secondRow][5]);
		
		board[secondRow][6] = new Pawn(color, secondRow, 6);
		board[secondRow][6].setPieceCode(""+ board[secondRow][6].getPieceColor() + "Pawn7");
		p.recruitPiece(board[secondRow][6]);
		
		board[secondRow][7] = new Pawn(color, secondRow, 7);
		board[secondRow][7].setPieceCode(""+ board[secondRow][7].getPieceColor() + "Pawn8");
		p.recruitPiece(board[secondRow][7]);

	}
	
	
	
	
	/**
	 * Resets all the pieces of a particular which is specified i.e WHITE
	 * or BLACK. All the Pieces of the specified color are removed from the board
	 * and reset on the same board in their original starting position. The pieces of 
	 * color which is not specified remains undisturbed. The player with the respective
	 * pieces requesting reset also needs to be passed
	 * 
	 * @param color
	 * @param p
	 */
	public void resetSpecialGamePieces(ChessPiece.PieceColor color, Player p){
		
		//Remove all the pieces from Alive list if previously existed
		p.startFresh();
		
		//Remove all the Pieces from the Grave-yard if previously existed
		clearGraveyard(p.getPlayerColor());
		
		int firstRow;
		int secondRow;
		
		//Setting Rows for either BLACK or WHITE
		if(color == ChessPiece.PieceColor.WHITE){
			firstRow = 0;
			secondRow = 1;
		}
		else{
			firstRow = 7;
			secondRow = 6;
		}
		
		//Removing BLACK or WHITE Pieces from the board
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				
				if(board[row][col] != null)
				if(board[row][col].getPieceColor() == color)
					board[row][col] = null;
			}
		}

		//Setting new BLACK or WHITE Pieces
		board[firstRow][0] = new Rook(color, firstRow, 0);	
		board[firstRow][0].setPieceCode(""+ board[firstRow][0].getPieceColor() + "Rook1");
		p.recruitPiece(board[firstRow][0]);
		
		board[firstRow][1] = new Empress(color, firstRow, 1);
		board[firstRow][1].setPieceCode(""+ board[firstRow][1].getPieceColor() + "Empress1");
		p.recruitPiece(board[firstRow][1]);
		
		board[firstRow][2] = new Princess(color, firstRow, 2);
		board[firstRow][2].setPieceCode(""+ board[firstRow][2].getPieceColor() + "Princess1");
		p.recruitPiece(board[firstRow][2]);
		
		board[firstRow][3] = new Queen(color, firstRow, 3);
		board[firstRow][3].setPieceCode(""+ board[firstRow][3].getPieceColor() + "Queen");
		p.recruitPiece(board[firstRow][3]);
		
		board[firstRow][4] = new King(color, firstRow, 4);
		board[firstRow][4].setPieceCode(""+ board[firstRow][4].getPieceColor() + "King");
		p.recruitPiece(board[firstRow][4]);
		
		board[firstRow][5] = new Princess(color, firstRow, 5);
		board[firstRow][5].setPieceCode(""+ board[firstRow][5].getPieceColor() + "Princess2");
		p.recruitPiece(board[firstRow][5]);
		
		board[firstRow][6] = new Empress(color, firstRow, 6);
		board[firstRow][6].setPieceCode(""+ board[firstRow][6].getPieceColor() + "Empress2");
		p.recruitPiece(board[firstRow][6]);
		
		board[firstRow][7] = new Rook(color, firstRow, 7);
		board[firstRow][7].setPieceCode(""+ board[firstRow][7].getPieceColor() + "Rook2");
		p.recruitPiece(board[firstRow][7]);
		
		board[secondRow][0] = new Pawn(color, secondRow, 0);
		board[secondRow][0].setPieceCode(""+ board[secondRow][0].getPieceColor() + "Pawn1");
		p.recruitPiece(board[secondRow][0]);
		
		board[secondRow][1] = new Pawn(color, secondRow, 1);
		board[secondRow][1].setPieceCode(""+ board[secondRow][1].getPieceColor() + "Pawn2");
		p.recruitPiece(board[secondRow][1]);
		
		board[secondRow][2] = new Pawn(color, secondRow, 2);
		board[secondRow][2].setPieceCode(""+ board[secondRow][2].getPieceColor() + "Pawn3");
		p.recruitPiece(board[secondRow][2]);
		
		board[secondRow][3] = new Pawn(color, secondRow, 3);
		board[secondRow][3].setPieceCode(""+ board[secondRow][3].getPieceColor() + "Pawn4");
		p.recruitPiece(board[secondRow][3]);
		
		board[secondRow][4] = new Pawn(color, secondRow, 4);
		board[secondRow][4].setPieceCode(""+ board[secondRow][4].getPieceColor() + "Pawn5");
		p.recruitPiece(board[secondRow][4]);
		
		board[secondRow][5] = new Pawn(color, secondRow, 5);
		board[secondRow][5].setPieceCode(""+ board[secondRow][5].getPieceColor() + "Pawn6");
		p.recruitPiece(board[secondRow][5]);
		
		board[secondRow][6] = new Pawn(color, secondRow, 6);
		board[secondRow][6].setPieceCode(""+ board[secondRow][6].getPieceColor() + "Pawn7");
		p.recruitPiece(board[secondRow][6]);
		
		board[secondRow][7] = new Pawn(color, secondRow, 7);
		board[secondRow][7].setPieceCode(""+ board[secondRow][7].getPieceColor() + "Pawn8");
		p.recruitPiece(board[secondRow][7]);

	}

	
	

	/**
	 * Set the Chess Pieces in their original spots to start a New Game.
	 * When the game starts, the state of the chess-board expected is 
	 * produced here. The new game would be set for the 2 players p1 and p2.
	 * 
	 * @param p1
	 * @param p2   			 
	 */
	public void setNewGame(Player p1, Player p2){
		
		//Cleans the Chess Board by removing all the Chess Pieces
		cleanBoard();
		
		//Reset White Pieces
		resetPieces(ChessPiece.PieceColor.WHITE, p1);
		
		//Reset Black Pieces
		resetPieces(ChessPiece.PieceColor.BLACK, p2);
		
	}
	
	
	
	/**
	 * Set the Chess Pieces in their original spots to start a New Special Game.
	 * When the game starts, the state of the chess-board expected is 
	 * produced here. The new game would be set for the 2 players p1 and p2.
	 * Special Game replaces Bishop with Princess and the Knight with the Empress.
	 * 
	 * @param p1
	 * @param p2   			 
	 */
	public void setNewSpecialGame(Player p1, Player p2){
		
		//Cleans the Chess Board by removing all the Chess Pieces
		cleanBoard();
		
		//Reset White Pieces
		resetSpecialGamePieces(ChessPiece.PieceColor.WHITE, p1);
		
		//Reset Black Pieces
		resetSpecialGamePieces(ChessPiece.PieceColor.BLACK, p2);
		
	}
	

	
	/**
	 * Clears the grave-yard of the specified colored pieces passed in as parameter.
	 * The cleared out pieces are lost, they are NOT sent back to the player's alive list.
	 * 
	 * @param color
	 */
	public void clearGraveyard(PieceColor color){
		
		if(color == PieceColor.WHITE){
			whiteGraveyard = new ArrayList<ChessPiece>();
		}
		else{
			blackGraveyard = new ArrayList<ChessPiece>();
		}
	}
	
	
	/**
	 * Removes the Piece at the end of the grave-yard, or the most recent addition
	 * to the grave-yard.
	 * 
	 * @param color
	 * @return Recently killed ChessPiece
	 */
	public ChessPiece popGraveyard(PieceColor color){
		
		//R.I.P WHITE Piece
		if(color == PieceColor.WHITE)
			return whiteGraveyard.remove(whiteGraveyard.size() -1);
				
		//R.I.P BLACK Piece
		else
			return blackGraveyard.remove(blackGraveyard.size() - 1);
		
	}
	
	
	/**
	 * Adds the recent killed chess piece to its respective grave-yard. R.I.P
	 * 
	 * @param color
	 * @param deadPiece
	 */
	public void addToGraveyard(PieceColor color, ChessPiece deadPiece){
		
		//R.I.P WHITE Piece
		if(color == PieceColor.WHITE)
			whiteGraveyard.add(deadPiece);
		
		
		//R.I.P BLACK Piece
		else
			blackGraveyard.add(deadPiece);
		
	}
	
	
	/**
	 * 
	 */
	public void goToPreviousState(){
		
	}
	
	/**
	 * Logs the state of the Chess-Board on the Console. Displays every empty spot and
	 * every spot with corresponding Chess-Piece.
	 * 
	 */
	public void printBoardState(){
		
		System.out.println();
		
		for(int row = board.length - 1; row >= 0; row--){
			
			for(int col = 0; col < board[0].length; col++){
				
				if(this.spotOpen(row, col))
					System.out.print('-');
				else{
					
					ChessPiece piece = this.getPieceAtSpot(row,  col);
					
					if(piece.getClass().equals(PieceType.King.class))
						System.out.print('K');
						
					else if(piece.getClass().equals(PieceType.Queen.class))
							System.out.print('Q');
							
					else if(piece.getClass().equals(PieceType.Bishop.class))
						System.out.print('b');
					
					else if(piece.getClass().equals(PieceType.Knight.class))
						System.out.print('k');
					
					else if(piece.getClass().equals(PieceType.Pawn.class))
						System.out.print('p');
					
					else if(piece.getClass().equals(PieceType.Rook.class))
						System.out.print('r');
				}

				System.out.print(" ");
			}
			
			System.out.println();
		}
	}
	
	
}	
