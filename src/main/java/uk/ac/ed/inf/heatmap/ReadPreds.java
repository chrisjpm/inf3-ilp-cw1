package uk.ac.ed.inf.heatmap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

// Read the contents of args[0], the predictions.txt, then map the values to an
// int array 
public class ReadPreds {

	public String preds;
	public int[] predsArr;
	public int[][] preds2dArr;

	// Init
	public ReadPreds() {
		this.preds = "";
		this.predsArr = new int[100];
		this.preds2dArr = new int[10][10];
	}

	// Getters
	public String getPreds() {
		return this.preds;
	}

	public int[] getPredsArr() {
		return this.predsArr;
	}
	
	public int[][] getPreds2dArr() {
		return this.preds2dArr;
	}

	// Methods
	// Write all predictions to a string and adding a ", " after each new line,
	// bar the final line
	public void read(String filepath) {
		var predsLoc = new File(filepath);

		try (var s = new Scanner(predsLoc)) {
			var ultimate = "";
	        var penultimate = "";
			while (s.hasNextLine()) {
				penultimate = ultimate;
				this.preds += penultimate + ", ";
				ultimate = s.nextLine();
			}
			this.preds += ultimate;
			
			// Print a success message
			System.out.println("Predictions complete!\n...");
		} catch (IOException e) {
			e.printStackTrace();
			
			// Print a failure message
			System.out.println(">> FAILURE: Precitions were not read!\n...");
		}		

		// Use regex to filter just the int values, then map to an int array
		var p = Pattern.compile("[0-9]+");
		var m = p.matcher(this.preds);
		this.predsArr = m.results().map(MatchResult::group)
				.mapToInt(Integer::parseInt).toArray();
		
		// Convert to a 2d array for convenience
		var counter = 0;
		for (var i = 0; i < 10; i++) {
			for (var j = 0; j < 10; j++) {
				this.preds2dArr[i][j] = this.predsArr[counter];
				counter++;
			}
		}
	}

}