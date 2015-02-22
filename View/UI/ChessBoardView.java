package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;




/**
 * View for the Chess-Board is created here. The GUI is established within this class.
 * @author Vishrut Reddi
 *
 */

public class ChessBoardView implements MouseListener{
	
	
	private JPanel gameScreen;
	private JButton[][] chessBoardSpot;
	private Image[][] chessPieceImage;
	private JPanel chessBoard;
	private JFrame gameContainer;
	
	public ChessBoardView(){
		
		/* To get the Color inside the JButtons for Some Platforms */
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e){
			
		}
		
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
		
		createBoardSpots();
		
		gameContainer.getContentPane().add(gameScreen, BorderLayout.CENTER);
		
		
		
		
	}
	
	
	private final void createImages() {
		
		chessPieceImage = new Image[2][6];
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImage[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
	
	
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
				
				if(row == 1){
					chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[1][5]));
				}
				else if(row == 6){
					chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[0][5]));
				}
				else if(row == 0){
					if(col == 0 || col == 7)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[1][2]));
					else if(col == 1 || col == 6)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[1][3]));
					else if(col == 2 || col == 5)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[1][4]));
					else if(col == 3)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[1][0]));
					else if(col == 4)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[1][1]));
				}
				else if(row == 7){
					if(col == 0 || col == 7)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[0][2]));
					else if(col == 1 || col == 6)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[0][3]));
					else if(col == 2 || col == 5)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[0][4]));
					else if(col == 3)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[0][0]));
					else if(col == 4)
						chessBoardSpot[row][col].setIcon(new ImageIcon(chessPieceImage[0][1]));
				}
				
				chessBoard.add(chessBoardSpot[row][col]);
				}
			}
	}
	
	
	public Color getSpotColor(int row, int col){
		
		if((row + col) % 2 == 0)
			return new Color(0, 0, 0);
		else
			return new Color(255, 255, 255);
	}

	
	public static void main(String args[]){

		ChessBoardView b = new ChessBoardView();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		chessBoardSpot[1][1].setBackground(Color.GREEN);
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		chessBoardSpot[1][1].setBackground(Color.BLACK);
	}
}