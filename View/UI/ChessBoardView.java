package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Components.ChessBoard;
import Components.ChessPiece;
import Components.ChessPiece.PieceColor;




/**
 * View for the Chess-Board is created here. The GUI for the chess-board is established within this class.
 * This class also contains just the interaction of the player with the spots in the chess-board, as the chess-board
 * is made up of 64 buttons. Rest of the interaction control is given to the Game Controller.
 * 
 * @author Vishrut Reddi
 *
 */

public class ChessBoardView{
	
	/* Chess Board Window Main Components */
	private JPanel gameScreen;
	private JButton[][] chessBoardSpot;
	private Image[][] chessPieceImage;
	private JPanel chessBoard;
	private JFrame gameContainer;
	
	//To detect if the game played is a normal or a special game
	private boolean specialGame;
	
	//These numbers are determined by each sub image in the sprite-sheet
	//as fitted in the 2D Images Array
	public static final int QUEEN = 0;
	public static final int KING = 1;
	public static final int ROOK = 2;
	public static final int KNIGHT = 3;
	public static final int BISHOP = 4;
	public static final int PAWN = 5;
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	
	
	/**
	 * Default Constructor, which projects a clean empty board visually without pieces and players attached to it.
	 * To begin a new game and change the View of the Chess Board through the controller, New Button from the Menu 
	 * Window needs to be pressed. Note: Special new Game can also be pressed for the special kind of chess game.
	 */
	public ChessBoardView(){
		
		/* To get the Color inside the JButtons for Some Platforms */
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e){
			
		}
		
		//Extract sub-images of chess pieces from an online source picture and stores it in a local array
		createImages();
		
		/* Configuring the chess game screen */
		gameScreen = new JPanel(new BorderLayout(3, 3));
		gameScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		chessBoard = new JPanel(new GridLayout(0, 9));
		
		gameScreen.add(chessBoard);
		
		gameContainer = new JFrame("JChess");
		gameContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameContainer.pack();
		gameContainer.setVisible(true);
		gameContainer.setSize(800, 800);
		
		/* Create Black and White Board Spots  and add it to the Chess Board */
		createBoardSpots();
		
		gameContainer.getContentPane().add(gameScreen, BorderLayout.CENTER);
				
	}

	
	
	/**
	 * Similar to the Default constructor, but this constructor indicates the Chess Board View to update its
	 * view and change the Piece Images for a special game. The special game is only activated when the object is
	 * created with a true parameter value.
	 * 
	 * @param isSpecialGame
	 */
	public ChessBoardView(boolean isSpecialGame){
		
		//Making the Game 'Special' or 'Normal'
		setSpecialGame(isSpecialGame);
		
		/* To get the Color inside the JButtons for Some Platforms */
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e){
			
		}
		
		//Extract sub-images of chess pieces from an online source picture and stores it in a local array
		//2 Chess pieces are extracted from some other image source to make them special pieces
		createImages();
		
		gameScreen = new JPanel(new BorderLayout(3, 3));
		gameScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		chessBoard = new JPanel(new GridLayout(0, 9));
		
		gameScreen.add(chessBoard);
		
		gameContainer = new JFrame("JChess");
		gameContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameContainer.pack();
		gameContainer.setVisible(true);
		gameContainer.setSize(800, 800);
		
		//Black and White Board Spots are created and added
		createBoardSpots();
		
		gameContainer.getContentPane().add(gameScreen, BorderLayout.CENTER);
				
	}

	
	
	
	/**
	 * Given a button reference to this method, this function returns the position of that spot
	 * in the Chess-Board.
	 * 
	 * @param button
	 * @return Position of a spot
	 */
	public int[] getSpotPosition(JButton button){
		
		//Create Position
		int[] pos = new int[2];
		
		//Find the Position of the spot
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col ++){
				
				if(chessBoardSpot[row][col].equals(button)){
					pos[0] = row;
					pos[1] = col;
				}
			}
		}
		
		return pos;
	}
	
	
	
	/**
	 * This method return the reference to all the Chess-Board spots.
	 * 
	 * @return Chess Board Spots (All)
	 */
	public JButton[][] getChessBoardSpots(){
		return chessBoardSpot;
	}
	
	
	
	/**
	 * This method extracts the images from and online picture source. It uses the online picture
	 * as a sprite sheet and extracts sub images for each piece. This method takes care if the game 
	 * is not normal and is special. If it is special then Bishop and Knight Images are replaced with 
	 * Princess and Empress Images.
	 * 
	 */
	public void createImages() {
		
		chessPieceImage = new Image[2][6];
        try {
        	//Online source for the CHess Piece Images
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int imageRow = 0; imageRow < 2; imageRow++) {
                for (int imageCol = 0; imageCol < 6; imageCol++) {
                    chessPieceImage[imageRow][imageCol] = bi.getSubimage(
                            imageCol * 64, imageRow * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Image Source Unavailable");
            System.exit(1);
        }
        
        //Getting Pictures from sprite sheet for special Pieces
        if(specialGame){
        	try{
        	//Online source for the special chess piece images
        	URL url = new URL("http://fc03.deviantart.net/fs70/f/2011/128/6/0/chess_pieces_by_atskaheart-d3fvk7i.png");
        	BufferedImage bi = ImageIO.read(url);
        	chessPieceImage[WHITE][BISHOP] = bi.getSubimage(145, 18, 70, 70);
        	chessPieceImage[BLACK][BISHOP] = bi.getSubimage(145, 107, 70, 70);
        	chessPieceImage[WHITE][KNIGHT] = bi.getSubimage(70, 18, 70, 70);
        	chessPieceImage[BLACK][KNIGHT] = bi.getSubimage(70, 107, 70, 70);
        	}
        	catch(Exception e){
        		e.printStackTrace();
        		System.out.println("Image Source Unavailable");
                System.exit(1);
        	}
        }
    }

	
	
	/**
	 * This method notifies the Board-View that the game being played on it is a special game.
	 * Later methods following this method execution behave as they would in a special game.
	 * 
	 * @param val
	 */
	public void setSpecialGame(boolean val){
		
		specialGame = val;
	}
	
	
	
	/**
	 * Creates the Black and White chess board spots on the chess boards. After the colored spot
	 * creation that spot is placed in its appropriate place on the chess board to make the board.
	 */
	public void createBoardSpots(){
		LayoutManager layout = new GridLayout(8, 8);
		chessBoard.setLayout(layout);
		
		chessBoardSpot = new JButton[8][8];
		
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col++){
				
				chessBoardSpot[row][col] = new JButton();
				chessBoardSpot[row][col].setOpaque(true);
				chessBoardSpot[row][col].setBackground(getSpotColor(row, col));
				chessBoardSpot[row][col].setForeground(getSpotColor(row, col));
				chessBoard.add(chessBoardSpot[row][col]);
				}
			}
	}
	
	
	
	/**
	 * In the chess game, the spot highlighting indicates valid normal and attacking moves.
	 * But once the move is done the un-highlighting of the board needs to be done. This method
	 * brings the spots to its original colors of Black and White.
	 */
	public void unhighlightAllSpots(){
		
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col++){
				
				chessBoardSpot[row][col].setBackground(getSpotColor(row, col));
				chessBoardSpot[row][col].setForeground(getSpotColor(row, col));
				}
			}
	}
	
	/**
	 * Recognizing appropriate valid normal and attacking spots for a selected chess piece, the board
	 * spots are highlighted green or red respectively. This method achieves that ambition.
	 * 
	 * @param piece
	 * @param board
	 */
	public void highlightValidSpots(ChessPiece piece, ChessBoard board){
		
		ArrayList<int[]> validNextMoves = piece.getNextValidMoves(board);
		
		for(int[] spot : validNextMoves){
			
			if(!board.spotOpen(spot[0], spot[1])){
				
				if(piece.getPieceColor() != board.getPieceAtSpot(spot[0], spot[1]).getPieceColor()){
					chessBoardSpot[spot[0]][spot[1]].setBackground(Color.RED);
				}
				else{
					chessBoardSpot[spot[0]][spot[1]].setBackground(Color.GREEN);
				}
			}
			else{
				chessBoardSpot[spot[0]][spot[1]].setBackground(Color.GREEN);
			}
		}
	}
	
	
	
	/**
	 * Checks the chess board if the given spot passed in as the parameter is currently highlighted.
	 * Depending on the answer a true or false value is sent back.
	 * 
	 * @param spot
	 * @return true/false
	 */
	public boolean isHighlighted(JButton spot){
	
		if(spot.getBackground() == Color.GREEN || spot.getBackground() == Color.RED)
			return true;
		
		return false;
	}
	
	
	
	/**
	 * This method grabs the images from the Image Array of chess pieces and places those images
	 * on its right spot. The View is set here for the initial normal game of chess.
	 */
	public void drawPiecesForNewGame(){
		
		for(int row = 7; row>= 0; row--){
			for(int col = 0; col < 8; col++){
				
				//WHITE Piece
				if(row == 1){
					chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[WHITE][PAWN]));
				}
				//BLACK piece
				else if(row == 6){
					chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[BLACK][PAWN]));
				}
				//WHITE piece
				else if(row == 0){
					if(col == 0 || col == 7)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[WHITE][ROOK]));
					else if(col == 1 || col == 6)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[WHITE][KNIGHT]));
					else if(col == 2 || col == 5)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[WHITE][BISHOP]));
					else if(col == 3)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[WHITE][QUEEN]));
					else if(col == 4)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[WHITE][KING]));
				}
				//BLACK piece
				else if(row == 7){
					if(col == 0 || col == 7)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[BLACK][ROOK]));
					else if(col == 1 || col == 6)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[BLACK][KNIGHT]));
					else if(col == 2 || col == 5)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[BLACK][BISHOP]));
					else if(col == 3)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[BLACK][QUEEN]));
					else if(col == 4)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[BLACK][KING]));
				}
			}
		}
	}
	
	
	
	/**
	 * Here the View of the ChessBoard is re-drawn depending on the logical change behind the board.
	 * If a piece dies, moves etc, those visual changes are changed with this method. This method updates the
	 * view so that the players can see their actions in real time.
	 * 
	 * @param board
	 */
	public void updateView(ChessBoard board){
		
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col++){
				
				if(!board.spotOpen(row, col)){
					
					PieceColor color = board.getPieceAtSpot(row, col).getPieceColor();
					int TYPE;
					if(color == PieceColor.BLACK)
						TYPE = 0;
					else
						TYPE = 1;
					
					if(board.getPieceAtSpot(row, col).getClass().equals(PieceType.Pawn.class)){
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][PAWN]));
					}
					else if(board.getPieceAtSpot(row, col).getClass().equals(PieceType.Rook.class)){
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][ROOK]));
					}
						
					else if(board.getPieceAtSpot(row, col).getClass().equals(PieceType.Princess.class)){
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][BISHOP]));
					}
					
					else if(board.getPieceAtSpot(row, col).getClass().equals(PieceType.Empress.class)){
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][KNIGHT]));
					}
					
					else if(board.getPieceAtSpot(row, col).getClass().equals(PieceType.Bishop.class)){
						
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][BISHOP]));
					}
						
					else if(board.getPieceAtSpot(row, col).getClass().equals(PieceType.Knight.class)){
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][KNIGHT]));			
					}
						
					else if(board.getPieceAtSpot(row, col).getClass().equals(PieceType.Queen.class)){
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][QUEEN]));
					}
					else{
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[TYPE][KING]));
					}
									
				}
				//Spot is empty
				else
					chessBoardSpot[row][col].setIcon(null);
			}
		}
		
	}
	
	
	
	/**
	 * This method returns the color of the requested spot sent in as parameters to this method.
	 * The spot at the requested row and column is assessed and the right color for that spot is returned.
	 * 
	 * @param row
	 * @param col
	 * @return Color of the Spot
	 */
	public Color getSpotColor(int row, int col){
		
		//Calculation for spot color
		if((row + col) % 2 == 0)
			return new Color(0, 0, 0);
		else
			return new Color(255, 255, 255);
	}

}