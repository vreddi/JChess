package Game;

import Components.ChessBoard;
import Components.ChessPiece.PieceColor;
import Components.Player;
import GameFlow.GameController;
import UI.ChessBoardView;
import UI.MenuView;

/**
 * The File contains the main function which starts the execution of the game by creating all the initial component
 * instances. The Game Loop is started here and extends to the ActionListeners in the Game Controller Class.
 * 
 * @author Vishrut Reddi
 *
 */
public class Game {

	/**
	 * Main Function, starting the execution for the
	 * Chess-Game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//needed on mac os x
	    System.setProperty("apple.laf.useScreenMenuBar", "true");
	    
	    //Game Instance that controls the Chess-Game
		GameController game = new GameController();
		
		//Main Chess Board is created in Game-Controller
		ChessBoard board = game.getGameChessBoard();
		ChessBoardView boardView = game.getChessBoardView();
		MenuView menuView = game.getMenuView();
		
		//Creating Players for the Chess Game
		Player p1 = new Player(PieceColor.WHITE);
		Player p2 = new Player(PieceColor.BLACK);
		
		//Setting Opponents
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		//Initialize Menu Buttons and Chess Board SPots
		game.initializeMenuButtons(menuView, board, p1, p2, boardView);
		game.initializeChessBoardButtons(board, p1, p2, boardView);
		
		//Game Starts with the First Move Going to Player 1
		game.setCurPlayer(p1);
	
	}

	
	
	
}
