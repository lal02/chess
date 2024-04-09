package chessgame;

import gamefoundation.*;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
/**
 * This class can be used as main class for Console chess
 * It manages the gameloop and in it the user input
 *
 */
public class ConsoleChess extends Thread{
	private Board b;


	public static void main(String[] args) {
		Thread thread = new ConsoleChess();
		thread.start();	
	}

	public void startThread(){
		Thread thread = new ConsoleChess();
		thread.start();
	}

	/**
	 * The Thread function that contains the gameLoop.
	 * Prints the foundation.Board and updates it by using user-input from the console.
	 */
	public void run() {
		PrintStream outStream;
		try (Scanner sc = new Scanner(System.in)) {
			b = Board.getBoardInstance();
			while(true) {
				try {
					outStream = new PrintStream(System.out, true, "UTF-8");
					outStream.print(b);
					String input = sc.nextLine();
					Move m = parseInput(input);
					b.updateBoard(m, b.getBoard());

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
		}
		
	}
	
	
	/* whitePawn A2 A3 WHITE
	 * blackPawn H7 H6 BLACK
	 */
	
	/**
	 * Parses the String user-input move into a foundation.Move object.
	 * 
	 * @param input The user-input that contains the played move.
	 * @return The parsed foundation.Move Object
	 */
	private Move parseInput(String input) {
		String[] parts = input.split("\\s+", 4);
		return new Move(Piece.valueOf(parts[0]), Position.valueOf(parts[1]),Position.valueOf(parts[2]), PlayerColor.valueOf(parts[3]));
	}
	
	
}
