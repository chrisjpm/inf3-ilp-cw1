package uk.ac.ed.inf.heatmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Read the contents of args[0], the predictions.txt, then map the values to an
// int array 
public class ReadPreds {
	private int[][] preds;

	// Define constructor
	public ReadPreds() {
		this.preds = new int[App.DIM_GRID][App.DIM_GRID];
	}

	// Getters
	public int[][] getPreds2dArr() {
		return this.preds;
	}

	// Methods
	// Read in the predictions
	public void read(String filepath) {
		var predsLoc = new File(filepath);

		try (var s = new Scanner(predsLoc)) {
			while (s.hasNextLine()) {
				for (var i = 0; i < App.DIM_GRID; i++) {
					// Remove whitespace and take new values at commas
					String[] line = s.nextLine().replace("\\s", "").split(", ");
					for (var j = 0; j < App.DIM_GRID; j++) {
						this.preds[i][j] = Integer.parseInt(line[j]);
					}
				}
			}

			// Print a success message
			System.out.println("------------------------------------------\n"
					+ "Predictions complete!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();

			// Print a failure message
			System.out.println("------------------------------------------\n"
					+ ">> FAILURE: Precitions were not read!");
		}

	}

}