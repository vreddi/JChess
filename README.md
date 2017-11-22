# JChess

JChess is a Chess Library built in Java.
Sample application available.

<h3> GUI Test Plan</h3>

When a New Game is Started from the Menu Window then the Chess-Board Window should display all the pieces in their initial position for both the players as shown below:

![Alt Text](https://github.com/vreddi/JChess/blob/master/SnapShots/Chess-Game.png)

The Player who owns the current Turn is displayed on the Menu Window for a better experience. This provides assistance to the player as well as keeps tracks of turns. The Menu Window also shows how many pieces are still on the board of each kind i.e how many pieces are alive.The order of displayed characters is as follows:
<ul>
  <li>KING</li>
  <li>QUEEN</li>
  <li>KNIGHT</li>
  <li>BISHOP</li>
  <li>ROOK</li>
  <li>PAWN</li>
</ul>

(Note: In the special game mode the Bishop is replaced by the princess and the Knight is replaced by the Empress.)

![Alt Text](https://github.com/vreddi/JChess/blob/master/SnapShots/Menu1.png)

On the Chess-Board Window if a Piece is selected i.e clicked upon then that particular piece’s valid moves are highlighted in Green on the chess-board and all the valid attacking moves are highlighted in Red.

![Alt Text](https://github.com/vreddi/JChess/blob/master/SnapShots/ChessMove.png)

If a Player moves in his opponents turn then the game would not let him/her do that, as expected. The GUI presents a message ‘Not Your Move!’ to indicate that.

![Alt Text](https://github.com/vreddi/JChess/blob/master/SnapShots/Menu2.png)

If players want to change their names then they can do so by clicking on the change name button. Both players will have to change name for the change name function to work. (Note: The default names are Light Kingdom and Dark Kingdom.)

![Alt Text](https://github.com/vreddi/JChess/blob/master/SnapShots/Menu3.png)
