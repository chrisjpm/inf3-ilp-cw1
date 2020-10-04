package uk.ac.ed.inf.heatmap;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Chris (s1839592)
 *
 */
public class App {
	// Methods
	// Write to file
	static void writeToFile(String heatmap) {
		try {
			FileWriter myWriter = new FileWriter("heatmap.geojson");
			myWriter.write(heatmap);
			myWriter.close();
			System.out.println("Heatmap GeoJson successfully created!"
					+ "\n------------------------------------------");
		} catch (IOException e) {
			System.out.println(">> FAILURE: Heatmap GeoJson wasn't created!"
					+ "\n------------------------------------------");
			e.printStackTrace();
		}
	}

	// Main
	// Creating a 10x10 heatmap, colour coded on air pollution quality
	public static void main(String[] args) {
		// PREDICTIONS
		// Read in predictions to an int array, size 10x10
		var predPath = args[0];
		var read = new ReadPreds();
		read.read(predPath);
		var preds = read.getPreds2dArr();

		// Look up colours of predictions
		var colours = new ColourLookUp();
		colours.assignColours(preds);
		var predColours = colours.getColours();

		// CREATE HEATMAP
		var map = new Map();
		map.createMap(predColours);
		var heatmap = map.getHeatmap().toJson();

		// Write the heatmap to a GeoJson file
		writeToFile(heatmap);
	}
}