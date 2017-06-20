package sudoku;

import java.util.*;

public class Solver {
	private Grid problem;
	private ArrayList<Grid> solutions;

	// creates an object with a Grid argument to solve
	public Solver(Grid problem) {
		this.problem = problem;
	}

	// solves the puzzle using the recurse method
	public void solve() {
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}

	// Standard backtracking recursive solver.
	private void solveRecurse(Grid grid) {
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
			return;
		else if (eval == Evaluation.ACCEPT)
			solutions.add(grid);
		else {
			ArrayList<Grid> next = grid.next9Grids();

			for (Grid g : next)
				solveRecurse(g);
		}
	}

	// Returns ABANDON if the grid is illegal.
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	public Evaluation evaluate(Grid grid) {
		if (!grid.isLegal())
			return Evaluation.ABANDON;
		else if (grid.isFull())
			return Evaluation.ACCEPT;
		else
			return Evaluation.CONTINUE;
	}

	// returns the solution
	public ArrayList<Grid> getSolutions() {
		return solutions;
	}

	public static void main(String[] args) {
		System.out.println("Starting\n");

		Grid g = TestGridSupplier.getPuzzle3();
		Grid s = TestGridSupplier.getSolution3();
		Solver solver = new Solver(g);
		solver.solve();

		ArrayList<Grid> solution = solver.getSolutions();

		System.out.println(g);

		for (Grid j : solution) {
			System.out.println(j);
			System.out.println(j.equals(s));
		}
	}
}
