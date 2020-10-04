package uk.ac.ed.inf.heatmap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

// Read the contents of args[0], the predictions.txt, then map the values to an
// int array 
public class ReadPreds {
	public int[][] preds2dArr;

	// Define constructor
	public ReadPreds() {
		this.preds2dArr = new int[10][10];
	}

	// Getters
	public int[][] getPreds2dArr() {
		return this.preds2dArr;
	}

	// Methods
	// Read in the predictions
	public void read(String filepath) {
		var preds = "";
		var predsArr = new int[100];
		var predsLoc = new File(filepath);

		// Write all predictions to a string and adding a ", " after each new
		// line, bar the final line
		try (var s = new Scanner(predsLoc)) {
			var ultimate = "";
			var penultimate = "";
			while (s.hasNextLine()) {
				penultimate = ultimate;
				preds += penultimate + ", ";
				ultimate = s.nextLine();
			}
			s.close();
			preds += ultimate;

			// Print a success message
			System.out.println("-------------------------------\n"
					+ "Predictions complete!");
		} catch (IOException e) {
			e.printStackTrace();

			// Print a failure message
			System.out.println("\"-------------------------------\\n"
					+ ">> FAILURE: Precitions were not read!");
		}

		// Use regex to filter just the int values, then map to an int array
		var p = Pattern.compile("[0-9]+");
		var m = p.matcher(preds);
		predsArr = m.results().map(MatchResult::group)
				.mapToInt(Integer::parseInt).toArray();

		// Convert to a 2d array for convenience
		var counter = 0;
		for (var i = 0; i < 10; i++) {
			for (var j = 0; j < 10; j++) {
				this.preds2dArr[i][j] = predsArr[counter];
				counter++;
			}
		}
	}

}