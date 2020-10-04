package uk.ac.ed.inf.heatmap;

import com.mapbox.geojson.*;

/**
 * 
 * @author Chris (s1839592)
 *
 */
public class App {
	public static void main(String[] args) {	
		// GEOJSON
		var map = new Map();
		map.createMap();

		// PREDICTIONS
		// Read in predictions to an int array, size 10x10
		var predPath = args[0];
		var r = new ReadPreds();	
		r.read(predPath);
		var preds = r.getPreds2dArr();

		// Look up colours of predictions
		var c = new ColourLookUp();
		c.lookUp(preds);
		var colours = c.getColours();
	
		// CREATE HEATMAP
		// Print a program completion message
		System.out.println("Finished!\n-------------------------------");

		System.out.println("Some tests:");
		System.out.println();
		System.out.println(preds[9].length);
		System.out.println(preds[0][0] + " " + preds[9][9] + " " + preds[5][1]);
		System.out.println(
				colours[0][0] + " " + colours[9][9] + " " + colours[5][1]);
		System.out.println(colours[9].length);
		

	}
}