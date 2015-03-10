package UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

/**
 * This file represents the class MenuView. This is part of the View where every view component of the
 * Menu Window is drawn and placed in the Menu Window.
 * 
 * @author Vishrut Reddi
 *
 */
public class MenuView{
	
	//Main Window
	public JFrame menuWindow = new JFrame("JChess - Menu");
	
    //Button Sub-Menu, Top Panel in the Menu Window
    JPanel buttonPanel = new JPanel();
    
    //Content Holder for the Main Window
    JPanel contentPane;
    
    //To show the black character faces of pieces and their alive/death info
    JPanel blackPiecePanel = new JPanel();
    
    //To show the black character faces of pieces and their alive/death info
    JPanel whitePiecePanel = new JPanel();
    
    //Buttons for the Button Sub-Menu
    JButton newGame = new JButton("New Game");
	JButton undo = new JButton("Undo");
	JButton forfeit = new JButton("Forfeit");
	JButton exitGame = new JButton("Quit");
	JButton specialGameBtn = new JButton("New Special Game");
	JButton changeNames = new JButton("Change Names");
	

	//Used to identify if the Game is Special or Normal
	private boolean specialGame;
	
	//For Unique Player Names
	JLabel whiteTeamName, blackTeamName;
	private String whiteName, blackName;
	
	/* Character Face References */
	JLabel whiteKing, whiteQueen, whiteKnight, whiteRook, whiteBishop, whitePawn;
	JLabel blackKing, blackQueen, blackKnight, blackRook, blackBishop, blackPawn;
	
	/* Label String inputs for displaying Alive Count of each Piece on the UI */
	public String whiteKingAliveCount, whiteQueenAliveCount, whiteBishopAliveCount, whiteRookAliveCount, whiteKnightAliveCount, whitePawnAliveCount;
	public String blackKingAliveCount, blackQueenAliveCount, blackBishopAliveCount, blackRookAliveCount, blackKnightAliveCount, blackPawnAliveCount;
	
	/* Piece Count References */
	public JLabel whiteKingCount, whiteQueenCount, whiteBishopCount, whiteKnightCount, whiteRookCount, whitePawnCount;
	public JLabel blackKingCount, blackQueenCount, blackBishopCount, blackKnightCount, blackRookCount, blackPawnCount;
	
	/* Turn/Win/Loss Information for Display */
	public String message = "Turn: Player 1";
	JLabel messageSlot;
	
	/* Score */
	public String score;
	public int player1Score, player2Score;
	JLabel scoreSlot;

	
	/**
	 * Default Constructor, which shows the default view when a new game starts.
	 * The players have default names. Player 1 is called Light Kingdom and Player 2
	 * is called Dark Kingdom
	 */
	public MenuView(){
		
		/* Menu Window and Menu Window Content Area Attributes Configurations */
		menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuWindow.setBounds(100, 100, 635, 700);
		menuWindow.setLocation(800, 0);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		menuWindow.setContentPane(contentPane);		
		menuWindow.setResizable(false);
		
		/* Adding Buttons to the Content Area */
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.add(newGame);
		buttonPanel.add(undo);
		buttonPanel.add(forfeit);
		buttonPanel.add(specialGameBtn);
		buttonPanel.add(changeNames);
		buttonPanel.add(exitGame);
		
		/* Default Names */
		whiteName = "Light Kingdom";
		blackName = "Dark Kingdom";	
		
		/* Setting Both Players with score 0 */
		player1Score = 0;
		player2Score = 0;
		
		/* Score Starts with 0-0 */
		score = "" + player1Score + "-" + player2Score;
		/* Create Components and Place them in the Content Area */
		addWhiteCharacters();
		addBlackCharacters();
		addMessage();
		addScore();
		
		
		//Make Window Visible by Default
		menuWindow.setVisible(true);
	}
	

	
	/**
	 * Special Constructor, which starts a special game and shows the special game view.
	 * The players have default names. Player 1 is called Light Kingdom and Player 2
	 * is called Dark Kingdom
	 */
	public MenuView(boolean isSpecialGame){
		
		//Setting the normal or special game
		setSpecialGame(isSpecialGame);
		
		menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuWindow.setBounds(100, 100, 635, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		menuWindow.setContentPane(contentPane);
		
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.add(newGame);
		buttonPanel.add(undo);
		buttonPanel.add(forfeit);
		buttonPanel.add(specialGameBtn);
		buttonPanel.add(exitGame);
		
		
		addWhiteCharacters();
		addBlackCharacters();
		addMessage();
		
		menuWindow.setVisible(true);
	}


	
	/**
	 * Adds the Current Score for the 2 players playing the Chess Game on the Menu
	 * Screen. 
	 */
	public void addScore(){
		
		JPanel scoreBoard = new JPanel();
		contentPane.add(scoreBoard, BorderLayout.CENTER);
		
		scoreSlot = new JLabel(score);
		scoreSlot.setFont(new Font("Lucida Grande", Font.PLAIN, 37));
		scoreSlot.setForeground(Color.BLACK);
		scoreBoard.add(scoreSlot);
	}
	
	/**
	 * Updates/adds the Message in the lower part of the Menu Window.
	 * The message-board which contains the message is repainted with this method, which
	 * helps in updating the message in the message board.
	 */
	public void addMessage(){
		
		JPanel messageBoard = new JPanel();
		contentPane.add(messageBoard, BorderLayout.SOUTH);
		
		messageSlot = new JLabel(message);
		messageSlot.setFont(new Font("Lucida Grande", Font.PLAIN, 37));
		messageSlot.setForeground(Color.BLACK);
		messageBoard.add(messageSlot);
	}
	
	
	
	
	/**
	 * Strings which control the names of the players can be changed using this method.
	 * The parameters are used for the new player names.
	 * 
	 * @param player1Name
	 * @param player2Name
	 */
	public void setPlayerNames(String player1Name, String player2Name){
		
		whiteName = player1Name;
		blackName = player2Name;
	}
	
	
	
	
	/**
	 * Adds the White Team Character Faces in the Menu Window. It takes care if the game is special or not.
	 * If it is then Empress and Princess character faces appear instead of Knight and Bishop. Otherwise
	 * Normal set of character Faces are shown in the Menu WIndow.
	 */
	public void addWhiteCharacters(){
		
		JPanel lightKingdom = new JPanel();
		contentPane.add(lightKingdom, BorderLayout.WEST);
		
		//Add the Team Name
		whiteTeamName = new JLabel(whiteName);
		
		
		/* Adding Character Images for Each Piece */
		whiteKing = new JLabel("");
		Image whiteKingCharImage = new ImageIcon(this.getClass().getResource("Resources/WHITE_KING.png")).getImage();
		whiteKingCharImage = whiteKingCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		whiteKing.setIcon(new ImageIcon(whiteKingCharImage));
		
		whiteKingCount = new JLabel(whiteKingAliveCount);
		
		whiteQueen = new JLabel("");
		Image whiteQueenCharImage = new ImageIcon(this.getClass().getResource("Resources/WHITE_QUEEN.png")).getImage();
		whiteQueenCharImage = whiteQueenCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		whiteQueen.setIcon(new ImageIcon(whiteQueenCharImage));
		
		whiteQueenCount = new JLabel(whiteQueenAliveCount);
		
		whiteKnight = new JLabel("");
		Image whiteknightCharImage = new ImageIcon(this.getClass().getResource("Resources/WHITE_KNIGHT.png")).getImage();
		whiteknightCharImage = whiteknightCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		whiteKnight.setIcon(new ImageIcon(whiteknightCharImage));
		
		whiteKnightCount = new JLabel(whiteKnightAliveCount);
		
		whiteBishop = new JLabel("");
		if(!specialGame){
			Image whitebishopCharImage = new ImageIcon(this.getClass().getResource("Resources/WHITE_BISHOP.png")).getImage();
			whitebishopCharImage = whitebishopCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
			whiteBishop.setIcon(new ImageIcon(whitebishopCharImage));	
		}
		else{
			Image whitebishopCharImage = new ImageIcon(this.getClass().getResource("Resources/WHITE_PRINCESS.png")).getImage();
			whitebishopCharImage = whitebishopCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
			whiteBishop.setIcon(new ImageIcon(whitebishopCharImage));	
		}	
		
		whiteBishopCount = new JLabel(whiteBishopAliveCount);
		
		whiteRook = new JLabel("");
		Image whiteRookCharImage = new ImageIcon(this.getClass().getResource("Resources/WHITE_ROOK.png")).getImage();
		whiteRookCharImage = whiteRookCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		whiteRook.setIcon(new ImageIcon(whiteRookCharImage));
		
		whiteRookCount = new JLabel(whiteRookAliveCount);
		
		whitePawn = new JLabel("");
		Image whitePawnCharImage = new ImageIcon(this.getClass().getResource("Resources/WHITE_PAWN.png")).getImage();
		whitePawnCharImage = whitePawnCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		whitePawn.setIcon(new ImageIcon(whitePawnCharImage));
		
		whitePawnCount = new JLabel(whitePawnAliveCount);	
		
		/* Positioning method for CHaracter Faces */
		createWhiteGridLayout(lightKingdom);
	}
	
	
	
	
	/**
	 * Places the components of the Black Team Player in the right position in the Menu Window
	 * 
	 * @param lightKingdom
	 */
	public void createWhiteGridLayout(JPanel lightKingdom){
		
		GroupLayout gl_lightKingdom = new GroupLayout(lightKingdom);
		
		/* Making a Layout for Positioning the CHaracter Faces */
		gl_lightKingdom.setHorizontalGroup(
			gl_lightKingdom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lightKingdom.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_lightKingdom.createParallelGroup(Alignment.LEADING)
						.addComponent(whiteTeamName)
						.addGroup(gl_lightKingdom.createSequentialGroup()
							.addComponent(whiteKing)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(whiteKingCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_lightKingdom.createSequentialGroup()
							.addComponent(whiteQueen, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(whiteQueenCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_lightKingdom.createSequentialGroup()
							.addComponent(whiteKnight, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(whiteKnightCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_lightKingdom.createSequentialGroup()
							.addComponent(whiteBishop, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(whiteBishopCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_lightKingdom.createSequentialGroup()
							.addComponent(whiteRook, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(whiteRookCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_lightKingdom.createSequentialGroup()
							.addComponent(whitePawn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(whitePawnCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		gl_lightKingdom.setVerticalGroup(
				gl_lightKingdom.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_lightKingdom.createSequentialGroup()
						.addGap(15)
						.addComponent(whiteTeamName)
						.addGroup(gl_lightKingdom.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(whiteKing, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addGap(39)
								.addComponent(whiteKingCount)))
						.addGroup(gl_lightKingdom.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(whiteQueen, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addGap(40)
								.addComponent(whiteQueenCount)))
						.addGroup(gl_lightKingdom.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(whiteKnight, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(whiteBishop, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addGap(37)
								.addComponent(whiteKnightCount)
								.addGap(69)
								.addComponent(whiteBishopCount)))
						.addGroup(gl_lightKingdom.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(whiteRook, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(whitePawn, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_lightKingdom.createSequentialGroup()
								.addGap(41)
								.addComponent(whiteRookCount)
								.addGap(67)
								.addComponent(whitePawnCount)))
						.addContainerGap(100, Short.MAX_VALUE))
			);
			
		//Add this Layout
		lightKingdom.setLayout(gl_lightKingdom);
		
	}
	
	
	/**
	 * Adds the Black Team Character Faces in the Menu Window. It takes care if the game is special or not.
	 * If it is then Empress and Princess character faces appear instead of Knight and Bishop. Otherwise
	 * Normal set of character Faces are shown in the Menu WIndow.
	 */
	public void addBlackCharacters(){
		
		JPanel darkKingdom = new JPanel();
		contentPane.add(darkKingdom, BorderLayout.EAST);
		
		/* Adding the Team Name */
		blackTeamName = new JLabel(blackName);
		
		/* Adding the Character Face Images */
		blackKing = new JLabel("");
		Image blackKingCharImage = new ImageIcon(this.getClass().getResource("Resources/BLACK_KING.png")).getImage();
		blackKingCharImage = blackKingCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		blackKing.setIcon(new ImageIcon(blackKingCharImage));
		
		blackKingCount = new JLabel(blackKingAliveCount);
		
		blackQueen = new JLabel("");
		Image blackQueenCharImage = new ImageIcon(this.getClass().getResource("Resources/BLACK_QUEEN.png")).getImage();
		blackQueenCharImage = blackQueenCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		blackQueen.setIcon(new ImageIcon(blackQueenCharImage));
		
		blackQueenCount = new JLabel(blackQueenAliveCount);
		
		blackKnight = new JLabel("");
		Image blackknightCharImage = new ImageIcon(this.getClass().getResource("Resources/BLACK_KNIGHT.png")).getImage();
		blackknightCharImage = blackknightCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		blackKnight.setIcon(new ImageIcon(blackknightCharImage));
		
		blackKnightCount = new JLabel(blackKnightAliveCount);
		
		blackBishop = new JLabel("");
		if(!specialGame){
			Image blackbishopCharImage = new ImageIcon(this.getClass().getResource("Resources/BLACK_BISHOP.png")).getImage();
			blackbishopCharImage = blackbishopCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
			blackBishop.setIcon(new ImageIcon(blackbishopCharImage));	
		}
		else{
			Image blackbishopCharImage = new ImageIcon(this.getClass().getResource("Resources/BLACK_PRINCESS.png")).getImage();
			blackbishopCharImage = blackbishopCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
			blackBishop.setIcon(new ImageIcon(blackbishopCharImage));	
		}	
		
		blackBishopCount = new JLabel(blackBishopAliveCount);
		
		blackRook = new JLabel("");
		Image blackRookCharImage = new ImageIcon(this.getClass().getResource("Resources/BLACK_ROOK.png")).getImage();
		blackRookCharImage = blackRookCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		blackRook.setIcon(new ImageIcon(blackRookCharImage));
		
		blackRookCount = new JLabel(blackRookAliveCount);
		
		blackPawn = new JLabel("");
		Image blackPawnCharImage = new ImageIcon(this.getClass().getResource("Resources/BLACK_PAWN.png")).getImage();
		blackPawnCharImage = blackPawnCharImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
		blackPawn.setIcon(new ImageIcon(blackPawnCharImage));
		
		blackPawnCount = new JLabel(blackPawnAliveCount);
		
		/* Positioning Method Invoked */
		createBlackGridLayout(darkKingdom);
	}
	
	
	
	/**
	 * Places the components of the Black Team Player in the right position in the Menu Window
	 * 
	 * @param darkKingdom
	 */
	public void createBlackGridLayout(JPanel darkKingdom){
		
		/* Making a Layout for Positioning the CHaracter Faces */
		GroupLayout gl_darkKingdom = new GroupLayout(darkKingdom);
		gl_darkKingdom.setHorizontalGroup(
			gl_darkKingdom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_darkKingdom.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(gl_darkKingdom.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_darkKingdom.createSequentialGroup()
							.addComponent(blackKnightCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(blackKnight, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_darkKingdom.createSequentialGroup()
							.addComponent(blackBishopCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(blackBishop, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_darkKingdom.createSequentialGroup()
							.addComponent(blackPawnCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(blackPawn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_darkKingdom.createSequentialGroup()
							.addComponent(blackRookCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(blackRook, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_darkKingdom.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_darkKingdom.createSequentialGroup()
								.addComponent(blackQueenCount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(blackQueen, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_darkKingdom.createSequentialGroup()
								.addComponent(blackKingCount, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(blackKing, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
						.addComponent(blackTeamName, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_darkKingdom.setVerticalGroup(
			gl_darkKingdom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_darkKingdom.createSequentialGroup()
					.addGap(14)
					.addComponent(blackTeamName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_darkKingdom.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_darkKingdom.createSequentialGroup()
							.addGap(12)
							.addComponent(blackKing, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(blackQueen, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(blackKnight, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(blackBishop, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(blackRook, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(blackPawn, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_darkKingdom.createSequentialGroup()
							.addGap(40)
							.addComponent(blackKingCount)
							.addGap(63)
							.addComponent(blackQueenCount)
							.addGap(69)
							.addComponent(blackKnightCount)
							.addGap(70)
							.addComponent(blackBishopCount)
							.addGap(68)
							.addComponent(blackRookCount)
							.addGap(64)
							.addComponent(blackPawnCount)))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		
		//Add this Layout
		darkKingdom.setLayout(gl_darkKingdom);
		
	}
	
	
	
	/**
	 * Marked with true or false, this method tells the MenuWindow if the game is 
	 * special or normal so that the view of the menu window can be set accordingly.
	 */
	public void setSpecialGame(boolean val){
		specialGame = val;
	}

		
	/**
	 * Provide reference to the New Game Button in the Menu Window
	 * @return New Game Button
	 */
	public JButton getNewGameButton(){
		return newGame;
	}
	
		
	/**
	 * Provide reference to the Change Name Button in the Menu Window
	 * @return Change Name Button
	 */
	public JButton getChangeNamesButton(){
		return changeNames;
	}
	
		
	/**
	 * Provide reference to the Special Game Button in the Menu Window
	 * @return New Special Game Button
	 */
	public JButton getSpecialGameButton(){
		return specialGameBtn;
	}
	

	/**
	 * Provide reference to the Undo Move Button in the Menu Window
	 * @return Undo Move Button
	 */
	public JButton getUndoButton(){
		return undo;
	}
	
	
	
	/**
	 * Provide reference to the Forfeit Button in the Menu Window
	 * @return Forfeit Button
	 */
	public JButton getForfeitButton(){
		return forfeit;
	}
	
	
	
	/**
	 * Provide reference to the Exit Game Button in the Menu Window
	 * @return Exit Game Button
	 */
	public JButton getExitGameButton(){
		return exitGame;
	}

}
