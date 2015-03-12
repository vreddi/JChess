package GameFlow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.ChessPiece.PieceColor;
import Components.Player;
import UI.ChessBoardView;
import UI.MenuView;

/**
 * This file represents the class Game Controller. This is the class that updates/modifies the view. This is the
 * class that uses the logic to project the intension visually to the players. The logic control is also handled 
 * by the Game Controller.
 * 
 * @author Vishrut Reddi
 *
 */
public class GameController{
	
	private Player curPlayer;		/*Current Player reference with the Turn */
	
	private ChessPiece selectedPiece;		/* Currently selected Chess Piece on the Chess Board Reference */

	private ChessBoard board = new ChessBoard();		/* Reference to the Game Chess Board */
	
	private ChessBoardView gameView = new ChessBoardView();		/* Reference to the View of the Chess Board */
	
	private MenuView menuView = new MenuView();			/* Reference to the View of the Menu */
	
	int number = 1;			/* Used to keep track of the Player Number */
	
	boolean undoAlreadyPressed = true; 		/* Used to track if the Undo Button is pressed once or not */
	
	
	
	/**
	 * Get the reference of the Chess Board in play with this method.
	 * 
	 * @return Chess Board in play
	 */
	public ChessBoard getGameChessBoard(){
		return board;
	}
	
	public ChessBoardView getChessBoardView(){
		return gameView;
	}
	
	public MenuView getMenuView(){
		return menuView;
	}
	
	
	/**
	 * 
	 * @param menuView
	 * @param board
	 * @param p1
	 * @param p2
	 * @param boardView
	 */
	public void initializeMenuButtons(MenuView menuView, ChessBoard board, Player p1, Player p2, ChessBoardView boardView){
		
		JButton newGame = menuView.getNewGameButton();
		JButton exitGame = menuView.getExitGameButton();
		JButton forfeit = menuView.getForfeitButton();
		JButton undo = menuView.getUndoButton();
		JButton specialGame = menuView.getSpecialGameButton();
		JButton changeNames = menuView.getChangeNamesButton();
		
		newGame.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				menuView.setSpecialGame(false);
				boardView.setSpecialGame(false);
				boardView.createImages();
				board.setNewGame(p1, p2);
				setCurPlayer(p1);
				boardView.updateView(board);
				menuView.addBlackCharacters();
				menuView.addWhiteCharacters();
				number = 1;
				menuView.message = "Turn: Player " + number;
				menuView.addMessage();
			}
		});
		
		
		exitGame.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				System.exit(0);
			}
		});
		
		changeNames.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				//Get Player Names
				String player1Name = JOptionPane.showInputDialog("Enter Player 1 Name");
				String player2Name = JOptionPane.showInputDialog("Enter Player 2 Name");
				
				//Change Player Names
				changePlayerNames(player1Name, player2Name);
				
				//Update View
				menuView.addBlackCharacters();
				menuView.addWhiteCharacters();
			}
		});
		
		undo.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				if(undoAlreadyPressed)
					JOptionPane.showMessageDialog(menuView.menuWindow, "Undo Not Allowed Now");
					
				else{
					
					//Set the Previous Player to be the Current Player Again
					setCurPlayer(curPlayer.getOpponent());
					changePlayerTurnName();
					board.boardUndo(p1, p2);
					p1.playerUndo();
					p2.playerUndo();
					boardView.updateView(board);
					
					
					
					//Setting the usage of Undo Complete
					undoAlreadyPressed = true;
				}
				
				
			}
		});
		
		
		specialGame.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				
				board.setNewSpecialGame(p1, p2);
				setCurPlayer(p1);
				menuView.setSpecialGame(true);
				menuView.addBlackCharacters();
				menuView.addWhiteCharacters();
				boardView.setSpecialGame(true);
				boardView.createImages();
				boardView.updateView(board);
				menuView.addBlackCharacters();
				menuView.addWhiteCharacters();
				boardView.updateView(board);
				number = 1;
				menuView.message = "Turn: Player " + number;
				menuView.addMessage();
			}
		});
		
		
		forfeit.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				changePlayerTurnName();
				
				//Add Winning Message
				menuView.addMessage();
				
				//Increase the Score for the Winning Player
				if(number == 1)
					menuView.player1Score++;
				else 
					menuView.player2Score++;
				
				//Add the New Score to the Menu View
				updateScore();
				
				//Start a New Game
				menuView.setSpecialGame(false);
				boardView.setSpecialGame(false);
				boardView.createImages();
				board.setNewGame(p1, p2);
				setCurPlayer(p1);
				boardView.updateView(board);
				menuView.addBlackCharacters();
				menuView.addWhiteCharacters();
				menuView.message = "Player " + number + " Won Last Game!";
				number = 1;
				menuView.addMessage();

			}
		});

	}
	
	/**
	 * 
	 * @return
	 */
	public Player getCurPlayer(){
		return curPlayer;
	}
	
	
	
	/**
	 * 
	 * @param curP
	 */
	public void setCurPlayer(Player curP){
		curPlayer = curP;
	}
	
	/**
	 * 
	 * @param player1Name
	 * @param player2Name
	 */
	public void changePlayerNames(String player1Name, String player2Name){
		
		menuView.setPlayerNames(player1Name, player2Name);
	}

	
	/**
	 * 
	 * @param button
	 * @param p1
	 * @param p2
	 * @param boardView
	 */
	//TODO: Check the Movement messages made on the Menu VIew. Invalid Pops up when it is not supposed to.
	public void initializeChessBoardButtons(ChessBoard board, Player p1, Player p2, ChessBoardView boardView){
		
		
		JButton spot[][] = boardView.getChessBoardSpots();
		
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col++){
				
				JButton curSpot = spot[row][col];
				
				spot[row][col].addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e){
						
						int[] buttonPos = boardView.getSpotPosition(curSpot);
						
						
						if(p1.isCheck(board)){
							
							if(p1.isCheckMate(board, boardView)){
								
								menuView.player2Score++;
								updateScore();
								menuView.message = "Checkmate: Player 2 Won";
								menuView.addMessage();
							}
							
						}
						
						if(p2.isCheck(board)){
							
							if(p2.isCheckMate(board, boardView)){
								
								menuView.player1Score++;
								updateScore();
								menuView.message = "Checkmate: Player 1 Won";
								menuView.addMessage();
							}
							
						}
						
						if(board.spotOpen(buttonPos[0], buttonPos[1])){
							
							//Move the Piece
							if(boardView.isHighlighted(curSpot)){
								
								board.setPreviousStateBoard();
								p1.setPrevAliveList(p1.getCurAliveList(board));
								p2.setPrevAliveList(p2.getCurAliveList(board));
			
								if(selectedPiece.validMoveTo(buttonPos[0], buttonPos[1], board, curPlayer)){
									setCurPlayer(curPlayer.getOpponent());
									changePlayerTurnName();
								}
								
								
	
								
								updatePlayerStats(p1, menuView);
								updatePlayerStats(p2, menuView);
								menuView.addWhiteCharacters();
								menuView.addBlackCharacters();
								boardView.updateView(board);
								
								//Updating the allowance of Undo
								undoAlreadyPressed = false;
							}
							else{
								String mssg = "Invalid";
								menuView.message = mssg;
								menuView.addMessage();
							}
							
							selectedPiece = null;
							boardView.unhighlightAllSpots();
						}
						//Attacking Move
						else if(board.getPieceAtSpot(buttonPos[0], buttonPos[1]).getPieceColor() != curPlayer.getPlayerColor()){
							
							board.setPreviousStateBoard();
							p1.setPrevAliveList(p1.getCurAliveList(board));
							p2.setPrevAliveList(p2.getCurAliveList(board));
							
							if(selectedPiece == null){
								String mssg = "Not Your Move!";
								menuView.message = mssg;
								menuView.addMessage();
							}
							else if(selectedPiece.validMoveTo(buttonPos[0], buttonPos[1], board, curPlayer)){
								setCurPlayer(curPlayer.getOpponent());
								changePlayerTurnName();
								//Updating the allowance of Undo
								undoAlreadyPressed = false;
							}
							else{
								String mssg = "Invalid Attack Move";
								menuView.message = mssg;
								menuView.addMessage();
							}
							
							updatePlayerStats(p1, menuView);
							updatePlayerStats(p2, menuView);
							menuView.addWhiteCharacters();
							menuView.addBlackCharacters();
							boardView.updateView(board);
							selectedPiece = null;
							boardView.unhighlightAllSpots();
						}
						
						else{
							
							updatePlayerStats(p1, menuView);
							updatePlayerStats(p2, menuView);
							menuView.addWhiteCharacters();
							menuView.addBlackCharacters();
							selectedPiece = board.getPieceAtSpot(buttonPos[0], buttonPos[1]);
							boardView.unhighlightAllSpots();
							boardView.highlightValidSpots(selectedPiece, board);
						}
					}
				});
			}
		} 
	}

	
	
	
	/**
	 * Reflects the New Players Turn by displaying a new message on the Menu
	 * Screen. This is helpful for the players as they can keep track of the turns
	 * in their chess game through the menu window.
	 */
	public void changePlayerTurnName(){
		
		if(number == 1)
			number = 2;
		else 
			number = 1;
		
		menuView.message = "Turn: Player " + number;
		menuView.addMessage();
	}
	
	
	/**
	 * Reflects the current score for the Players on the Menu Screen. This method updates
	 * that score if a player wins/loses or if a player forfeits. If the Players choose to restart
	 * the game then the score remains the same.
	 */
	public void updateScore(){
		
		menuView.score = "" + menuView.player1Score + " - " + menuView.player2Score; 
		menuView.addScore();
	}
	
	/**
	 * Updates the Chess-Piece Alive count on the menu screen and projects it through 
	 * Menu-View for a given player.
	 * 
	 * @param p
	 * @param stats
	 */
	public void updatePlayerStats(Player p, MenuView stats){
		
		ArrayList<ChessPiece> playerAlivePieces = p.getCurAliveList(board);
		
		
	    int pawnAliveCount = 0;
	    int queenAliveCount = 0;
	    int knightAliveCount = 0;
	    int kingAliveCount = 0;
	    int bishopAliveCount = 0;
	    int rookAliveCount = 0;
	    
		for(ChessPiece alive : playerAlivePieces){
			
			if(alive.getClass().equals(PieceType.Pawn.class)){				
				pawnAliveCount += 1;
			}
			else if(alive.getClass().equals(PieceType.Knight.class)){
				knightAliveCount += 1;
			}
			else if(alive.getClass().equals(PieceType.Bishop.class)){
				bishopAliveCount += 1;
			}
			else if(alive.getClass().equals(PieceType.Princess.class)){
				bishopAliveCount += 1;
			}
			else if(alive.getClass().equals(PieceType.Empress.class)){
				knightAliveCount += 1;
			}
			else if(alive.getClass().equals(PieceType.Rook.class)){
				rookAliveCount += 1;
			}
			else if(alive.getClass().equals(PieceType.Queen.class)){
				queenAliveCount += 1;
			}
			else{
				kingAliveCount += 1;
			}
			
		}
		
		if(p.getPlayerColor() == PieceColor.WHITE){
			
			stats.whitePawnAliveCount = "" + pawnAliveCount +"/8";
			stats.whiteBishopAliveCount = "" + bishopAliveCount +"/2";
			stats.whiteKnightAliveCount = "" + knightAliveCount +"/2";
			stats.whiteKingAliveCount = "" + kingAliveCount +"/1";
			stats.whiteQueenAliveCount = "" + queenAliveCount +"/1";
			stats.whiteRookAliveCount = "" + rookAliveCount +"/2";
		}
		else{
			stats.blackPawnAliveCount = "" + pawnAliveCount +"/8";
			stats.blackBishopAliveCount = "" + bishopAliveCount +"/2";
			stats.blackKnightAliveCount = "" + knightAliveCount +"/2";
			stats.blackKingAliveCount = "" + kingAliveCount +"/1";
			stats.blackQueenAliveCount = "" + queenAliveCount +"/1";
			stats.blackRookAliveCount = "" + rookAliveCount +"/2";
		}
		
		
	}
	
}


