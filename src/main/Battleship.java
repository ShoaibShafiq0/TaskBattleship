package main;

import java.util.Scanner;

public class Battleship {

	// States of ships on board
	private final char WATER = '.';
	private final char SHIP = 's';
	private final char HIT = 'x';

	/**
	 * Max number of ships to be placed
	 */
	private final int MAX_SHIPS = 5;

	/**
	 * Max size of how many block a ship can consume on th board
	 */
	private final int SHIP_SIZE = 3;

	private char[][] board;
	private int sunkenShips = 0;
	private Scanner input;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Battleship().run();
	}

	private void run() {

		input = new Scanner(System.in);

		creatGameBoard();
		printBoard();
		addBattleShips();
		System.out.println("\n**** Game Begins ****\n");

		/*
		 * Calculate total number of hits each ship can take on the board
		 * to avoid looping through the board to verify if a player has won or lost.
		 */
		int totalHits = MAX_SHIPS * SHIP_SIZE;

		while (sunkenShips != totalHits) {

			System.out.print("Enter attack row (1-10): ");
			int x = input.nextInt();

			System.out.print("Enter attack column (1-10): ");
			int y = input.nextInt();

			// Decrement values for easier index referencing
			x--;
			y--;

			if (isValidAttack(x, y)) {
				takeAttack(x, y);
			}

		}
		System.out.println("You Lost, thanks for playing");
		input.close();
	}

	private void takeAttack(int x, int y) {
		// TODO Auto-generated method stub
		switch (board[x][y]) {

		case WATER:
			System.out.println("MISS");
			break;
		case SHIP:
			board[x][y] = HIT;
			System.out.println("HIT");
			System.out.println();
			printBoard();
			System.out.println();
			/*
			 *  Increment count for each hit 
			 *  to avoid looping again and again to validate board
			 */
			sunkenShips++;
			break;
		default:
			System.out.println("HIT");
		}

	}

	private void addBattleShips() {
		// TODO Auto-generated method stub

		System.out.println();
		System.out.println("***** Add battleships on the board *****");

		for (int i = 0; i < MAX_SHIPS; i++) {
			System.out.println();

			System.out.print("Enter row position for (" + (i + 1) + " of " + MAX_SHIPS + ") ship placement (1-10): ");
			int x = input.nextInt();

			System.out.print("Enter column position for (" + (i + 1) + " of " + MAX_SHIPS + ") ship placement (1-10): ");
			int y = input.nextInt();

			// Decrement values for easier index referencing
			x--;
			y--;

			if (isPlacementValid(x, y)) {
				/*
				 * place battleship horizontally on the board based on the given constraints
				 */
				board[x][y] = SHIP;
				board[x][y + 1] = SHIP;
				board[x][y + 2] = SHIP;
				System.out.println("Ship placed at (" + (x + 1) + "," + (y + 1) + ") position on the board.");

			} else {
				// Reset the counter to prompt position again
				if (i == 0) {
					i = -1;
				} else {
					i--;
				}
			}
		}

		System.out.println();
		printBoard();
	}

	/*
	 * Provided game constraints state the ships will be place horizontally on the
	 * board, so we only have to check horizontally for valid placement
	 */
	private boolean isPlacementValid(int x, int y) {
		// Board boundary check, if the provided placement is within board
		if ((x < 0 || x > 9) || (y < 0 || y > 7)) {
			System.out.println("Invalid position to insert battleship. Must be with in the constraints of the board");
			return false;
		}

		// Peek forward if any point doesn't overlap with other ship
		if (board[x][y] == WATER && board[x][y + 1] == WATER && board[x][y + 2] == WATER) {
			return true;
		}

		System.out.println("The current position overlaps with another ship");
		return false;
	}

	private boolean isValidAttack(int x, int y) {
		// Board boundary check, if the provided placement is within board
		if ((x < 0 || x > 9) || (y < 0 || y > 9)) {
			System.out.println("Invalid attack position, position must be within the board's context");
			return false;
		}

		return true;
	}

	private void creatGameBoard() {
		// TODO Auto-generated method stub
		if (board == null)
			board = new char[10][10];

		// Fill board with '.' meaning blanks or miss
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = WATER;
			}
		}
	}

	private void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(" " + board[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * @return the board
	 */
	public char[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(char[][] board) {
		this.board = board;
	}

}
