package uk.ac.ed.inf.heatmap;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Chris (s1839592)
 *
 */
public class App {
	public static void main(String[] args) {
		// PREDICTIONS
		// Read in predictions to an int array, size 10x10
		var predPath = args[0];
		var r = new ReadPreds();
		r.read(predPath);
		var preds = r.getPreds2dArr();

		// Look up colours of predictions
		var c = new ColourLookUp();
		c.setColours(preds);
		var colours = c.getColours();

		// CREATE HEATMAP
		var map = new Map();
		map.createMap(colours);
		var heatmap = map.getHeatmap().toJson();
		
		try {
			FileWriter myWriter = new FileWriter("heatmap.geojson");
			myWriter.write(heatmap);
			myWriter.close();
			System.out.println("Heatmap GeoJson successfully created!");
		} catch (IOException e) {
			System.out.println(">> FAILURE: Heatmap GeoJson wasn't created!");
			e.printStackTrace();
		}

		System.out.println(heatmap);

	}
}