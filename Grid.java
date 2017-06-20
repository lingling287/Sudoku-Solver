package sudoku;

import java.util.*;

public class Grid {
	private int[][] values;

	// Dots in input strings become 0s in values[][].
	public Grid(String[] rows) {
		values = new int[9][9];
		for (int j = 0; j < 9; j++) {
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i = 0; i < 9; i++) {
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}

	// Constructor that takes in a grid as an argument, duplicates the grid.
	// Used to generate next grids for solving purposes
	private Grid(Grid grid) {
		values = new int[9][9];

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				values[i][j] = grid.values[i][j];
	}

	// Returns true if every cell member of values[][] is a digit from 1-9.
	public boolean isFull() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (values[i][j] == 0)
					return false;
		return true;
	}

	// Gets the x,y position of an empty cell
	// used for generating next grids for solving purposes
	private int[] nextEmptySpace() {
		int[] coord = new int[2];

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (values[i][j] == 0) {
					coord[0] = i;
					coord[1] = j;
					return coord;
				}
		return null;
	}

	// gets all possible next grids
	// fills next empty space with a number 1 - 9
	public ArrayList<Grid> next9Grids() {
		if (this.isFull())
			return null;

		int[] emptySpace = nextEmptySpace();
		int x = emptySpace[0];
		int y = emptySpace[1];
		ArrayList<Grid> next = new ArrayList<>();
		for (int n = 1; n <= 9; n++) {
			Grid g = new Grid(this);
			g.values[x][y] = n;
			next.add(g);
		}
		return next;
	}

	// checks to see if there is a repeated value in the list
	// that is a non zero number
	private boolean nonZeroRepeat(ArrayList<Integer> list) {
		for (int i = 0; i < 9; i++) {
			int num1 = list.get(i);
			for (int j = 0; j < 9; j++) {
				int num2 = list.get(j);
				if (i != j)
					if (num1 != 0 && num2 != 0)
						if (num1 == num2)
							return true;
			}
		}
		return false;
	}

	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// zone contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	public boolean isLegal() {
		if (!rowIsLegal())
			return false;
		if (!columnIsLegal())
			return false;
		if (!zoneIsLegal())
			return false;

		return true;
	}

	// checks if the row is legal
	private boolean rowIsLegal() {
		for (int i = 0; i < 9; i++) {
			ArrayList<Integer> rows = new ArrayList<>();
			for (int j = 0; j < 9; j++)
				rows.add(values[i][j]);
			if (nonZeroRepeat(rows))
				return false;
		}
		return true;
	}

	// checks if the column is legal
	private boolean columnIsLegal() {
		for (int j = 0; j < 9; j++) {
			ArrayList<Integer> column = new ArrayList<>();
			for (int i = 0; i < 9; i++)
				column.add(values[i][j]);
			if (nonZeroRepeat(column))
				return false;
		}
		return true;
	}

	// checks if each 9 zones are legal
	private boolean zoneIsLegal() {
		ArrayList<Integer> zone1 = new ArrayList<>();
		ArrayList<Integer> zone2 = new ArrayList<>();
		ArrayList<Integer> zone3 = new ArrayList<>();
		ArrayList<Integer> zone4 = new ArrayList<>();
		ArrayList<Integer> zone5 = new ArrayList<>();
		ArrayList<Integer> zone6 = new ArrayList<>();
		ArrayList<Integer> zone7 = new ArrayList<>();
		ArrayList<Integer> zone8 = new ArrayList<>();
		ArrayList<Integer> zone9 = new ArrayList<>();

		for (int i = 0; i <= 2; i++)
			for (int j = 0; j <= 2; j++) {
				zone1.add(values[i][j]);
				zone2.add(values[i + 3][j]);
				zone3.add(values[i + 6][j]);
				zone4.add(values[i][j + 3]);
				zone5.add(values[i + 3][j + 3]);
				zone6.add(values[i + 6][j + 3]);
				zone7.add(values[i][j + 6]);
				zone8.add(values[i + 3][j + 6]);
				zone9.add(values[i + 6][j + 6]);
			}

		if (nonZeroRepeat(zone1)) return false;
		if (nonZeroRepeat(zone2)) return false;
		if (nonZeroRepeat(zone3)) return false;
		if (nonZeroRepeat(zone4)) return false;
		if (nonZeroRepeat(zone5)) return false;
		if (nonZeroRepeat(zone6)) return false;
		if (nonZeroRepeat(zone7)) return false;
		if (nonZeroRepeat(zone8)) return false;
		if (nonZeroRepeat(zone9)) return false;
		
		return true;
	}

	// Returns true if x is a Grid and, for every (i,j),
	// x.values[i][j] == this.values[i][j].
	public boolean equals(Object x) {
		Grid that = (Grid) x;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (this.values[i][j] != that.values[i][j])
					return false;
		return true;
	}

	// Converts object to a readable string for viewing purposes
	public String toString() {
		String s = "";
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char) ('0' + n);
			}
			s += "\n";
		}
		return s;
	}
}