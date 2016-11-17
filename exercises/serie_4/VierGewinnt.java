// Lorenzo Wipfli 13-933-262
// Michael Senn, 16-126-880

/* ************************************************************************* *\
*                Programmierung 1 HS 2016 - Serie 4-1                         * 
\* ************************************************************************* */

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;


public class VierGewinnt
{

	public static final int COLS = 7;
	public static final int ROWS = 6;

	private Token[][] board = new Token[ COLS ][ ROWS ]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[ 2 ]; // two players

	private static final Logger logger = Logger.getLogger(VierGewinnt.class.getName());

	/** initialize board and players and start the game */
	public void play()
	{
		// Set to Level.INFO to get debug output
		logger.setLevel(Level.WARNING);

		// initialize the board
		for ( Token[] column : this.board ) {
			Arrays.fill( column, Token.empty );
		}

		/* initialize players */
		players[ 0 ] = new HumanPlayer();
		System.out.print( "Play against a human opponent? (y / n) " );
		String opponent = new Scanner( System.in ).nextLine().toLowerCase();
		while ( ( 1 != opponent.length() ) || ( -1 == ( "yn".indexOf ( opponent ) ) ) ) {
			System.out.print( "Can't understand your answer. Play against a human opponent? (y / n) " );
			opponent = new Scanner( System.in ).nextLine().toLowerCase();
		}
		if ( opponent.equals( "y" ) ) {
			players[ 1 ] = new HumanPlayer();
		} else {
			players[ 1 ] = new ComputerPlayer();
		}
		players[ 0 ].setToken( Token.player1 );
		players[ 1 ].setToken( Token.player2 );

		/* play... */
		boolean solved = false;
		int currentPlayer = ( new java.util.Random() ).nextInt( 2 );  //choose randomly who begins
		System.out.println( "current player: " + currentPlayer );
		int insertCol, insertRow; // starting from 0
		while ( !solved && !this.isBoardFull() ) {
			// get player's next "move"
			// note that we pass only a copy of the board as an argument,
			// otherwise the player would be able to manipulate the board and cheat!
			insertCol = players[ currentPlayer ].getNextColumn( getCopyOfBoard() );
			// insert the token and get the row where it landed
			insertRow = this.insertToken( insertCol, players[ currentPlayer ].getToken() );
			// check if the game is over
			solved = this.checkVierGewinnt( insertCol, insertRow );
			//switch to other player
			if ( !solved )
				currentPlayer = ( currentPlayer + 1 ) % 2;
		}
		System.out.println( displayBoard( this.board ) );
		if ( solved )
			System.out.println( "Player " + players[ currentPlayer ].getToken() + " wins!" );
		else
			System.out.println( "Draw! Game over." );
	}


	/**
	 * Inserts the token at the specified column (if possible)
	 * @param column the column to insert the token
	 * @param token the players token
	 * @return the row where the token landed 
	 */
	private int insertToken( int column, Token tok )
	{
		// Index exceeds board dimensions - abort.
		if (column > board.length - 1) {
			System.out.println(String.format("Column %s does not exist, highest available index is only %s.", column, board[column].length));
			System.exit(1);
		}
		// Starting at the lowest index - which is treated as the bottom-most row by the drawing code.
		for (int i = 0; i < board[column].length; i++) {
			Token placedToken = board[column][i];
			if (placedToken == Token.empty) {
				board[column][i] = tok;
				return i;
			}

		}
		// If we get until here, then the column has been full.
		System.out.println(String.format("Invavlid move detected: Column %s is already full.", column));
		System.exit(1);
		// Making the compiler happy...
		return -1;
	}


	/**
	 * Checks if every position is occupied 
	 * @returns true, iff the board is full.
	 */
	private boolean isBoardFull()
	{
		// Sufficient to check the topmost row of each column.
		for (Token[] col : board) {
			if (col[col.length - 1] == Token.empty) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Checks for at least four equal tokens in a row in
	 * either direction, starting from the given position. 
	 */
	private boolean checkVierGewinnt( int col, int row )
	{
		return checkVictoryHorizontal(col, row) ||
			checkVictoryVertical(col, row)  ||
			checkVictoryDiagonalAscending(col, row) ||
			checkVictoryDiagonalDescending(col, row);
	}

	private boolean checkVictoryHorizontal(int col, int row) {
		int tokCount = 1;
		Token tok = board[col][row];

		// Traversing left
		for (int traverseCol = col - 1; traverseCol >= 0; traverseCol--) {
			logger.info(String.format("[H] Checking field at %s/%s", traverseCol, row));
			if(board[traverseCol][row] == tok) {
				tokCount ++;
				logger.info(String.format("[H] Found matching token at %s/%s (Count: %s)", traverseCol, row, tokCount));
			} else {
				break;
			}
		}

		// Traversing right
		for (int traverseCol = col + 1; traverseCol < board.length; traverseCol++) {
			logger.info(String.format("[H] Checking field at %s/%s", traverseCol, row));
			if(board[traverseCol][row] == tok) {
				tokCount ++;
				logger.info(String.format("[H] Found matching token at %s/%s (Count: %s)", traverseCol, row, tokCount));
			} else {
				break;
			}
		}

		if (tokCount >= 4) {
			return true;
		}

		return false;
	}

	private boolean checkVictoryVertical(int col, int row) {
		int tokCount = 1;
		Token tok = board[col][row];

		// Traversing down
		for (int traverseRow = row - 1; traverseRow >= 0; traverseRow--) {
			logger.info(String.format("[V] Checking field at %s/%s", col, traverseRow));
			if(board[col][traverseRow] == tok) {
				tokCount ++;
				logger.info(String.format("[V] Found matching token at %s/%s (Count: %s)", col, traverseRow, tokCount));
			} else {
				break;
			}
		}

		// Traversing up
		for (int traverseRow = row + 1; traverseRow < board[col].length; traverseRow++) {
			logger.info(String.format("[V] Checking field at %s/%s", col, traverseRow));
			if(board[col][traverseRow] == tok) {
				tokCount ++;
				logger.info(String.format("[V] Found matching token at %s/%s (Count: %s)", col, traverseRow, tokCount));
			} else {
				break;
			}
		}


		if (tokCount >= 4) {
			return true;
		}

		return false;
	}

	private boolean checkVictoryDiagonalAscending(int col, int row) {
		int tokCount = 1;
		Token tok = board[col][row];

		// Traversing right-up
		for (int step = 1; col + step < board.length && row + step < board[1].length; step++) {
			int traverseRow = row + step;
			int traverseCol = col + step;
			logger.info(String.format("[DA] Checking field at %s/%s", traverseCol, traverseRow));
			if(board[traverseCol][traverseRow] == tok) {
				tokCount ++;
				logger.info(String.format("[DA] Found matching token at %s/%s (Count: %s)", traverseCol, traverseRow, tokCount));
			} else {
				break;
			}
		}

		// Traversing left-down
		for (int step = 1; col - step >= 0 && row - step >= 0; step++) {
			int traverseRow = row - step;
			int traverseCol = col - step;
			logger.info(String.format("[DA] Checking field at %s/%s", traverseCol, traverseRow));
			if(board[traverseCol][traverseRow] == tok) {
				tokCount ++;
				logger.info(String.format("[DA] Found matching token at %s/%s (Count: %s)", traverseCol, traverseRow, tokCount));
			} else {
				break;
			}
		}

		if (tokCount >= 4) {
			return true;
		}

		return false;
	}

	private boolean checkVictoryDiagonalDescending(int col, int row) {
		int tokCount = 1;
		Token tok = board[col][row];

		// Traversing right-down
		for (int step = 1; col + step < board.length && row - step >= 0; step++) {
			int traverseRow = row - step;
			int traverseCol = col + step;
			logger.info(String.format("[DD] Checking field at %s/%s", traverseCol, traverseRow));
			if(board[traverseCol][traverseRow] == tok) {
				tokCount ++;
				logger.info(String.format("[DD] Found matching token at %s/%s (Count: %s)", traverseCol, traverseRow, tokCount));
			} else {
				break;
			}
		}

		// Traversing left-up
		for (int step = 1; col - step >= 0 && row + step < board[1].length; step++) {
			int traverseRow = row + step;
			int traverseCol = col - step;
			logger.info(String.format("[DD] Checking field at %s/%s", traverseCol, traverseRow));
			if(board[traverseCol][traverseRow] == tok) {
				tokCount ++;
				logger.info(String.format("[DD] Found matching token at %s/%s (Count: %s)", traverseCol, traverseRow, tokCount));
			} else {
				break;
			}
		}

		if (tokCount >= 4) {
			return true;
		}

		return false;
	}


	/** Returns a (deep) copy of the board array */
	private Token[][] getCopyOfBoard()
	{
		Token[][] copiedBoard = new Token[ COLS ][ ROWS ];
		for ( int i = 0; i < copiedBoard.length; i++ ) {
			for ( int j = 0; j < copiedBoard[ i ].length; j++ ) {
				copiedBoard[ i ][ j ] = this.board[ i ][ j ];
			}
		}
		return copiedBoard;
	}


	/** returns a graphical representation of the board */
	public static String displayBoard( Token[][] myBoard )
	{
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for ( int col = 0; col < myBoard.length; col++ ) {
			rowDelimiter += "---+";
			rowNumbering += " " + ( col + 1 ) + "  ";
		}
		rowDelimiter += "\n";

		String rowStr;
		String presentation = rowDelimiter;
		for ( int row = myBoard[ 0 ].length - 1; row >= 0; row-- ) {
			rowStr = "| ";
			for ( int col = 0; col < myBoard.length; col++ ) {
				rowStr += myBoard[ col ][ row ].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}



	/** main method, starts the program */
	public static void main( String args[] )
	{
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}
