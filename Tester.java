package sudoku;

import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		ArrayList<String> puzzle = new ArrayList<>();

		System.out.println("Enter blank spaces with '.'");
		for (int i = 1; i <= 9; i++) {
			System.out.println("Enter line " + i + " of Soduko puzzle");
			String scan = in.nextLine();
			puzzle.add(scan);
		}
		in.close();

		String grid[] = new String[puzzle.size()];
		for (int i = 0; i < puzzle.size(); i++)
			grid[i] = puzzle.get(i);

		System.out.println();
		Grid r = new Grid(grid);
		Solver s = new Solver(r);

		System.out.println(r + "\n" + "solving..\n");

		s.solve();
		ArrayList<Grid> list = s.getSolutions();

		for (Grid a : list)
			System.out.println(a);
	}
}