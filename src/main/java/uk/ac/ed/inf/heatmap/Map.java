package uk.ac.ed.inf.heatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mapbox.geojson.*;

public class Map {
	public FeatureCollection heatmap;

	// Getters
	public FeatureCollection getHeatmap() {
		return this.heatmap;
	}

	// Methods
	// Create a polygon for each cell of the 10x10 grid and assign it its air
	// pollution colour code
	public void createMap(String[][] colours) {
		var pointsLng = new double[11];
		var pointsLat = new double[11];

		// Find the divisions of the longitude and latitude
		var diffLng = (-3.192473 - (-3.184319)) / 10;
		var diffLat = (55.946233 - 55.942617) / 10;
 
		pointsLng[0] = -3.192473;
		pointsLat[0] = 55.946233;
		
		for (var i = 1; i < 11; i++) {
			pointsLng[i] = pointsLng[i - 1] + diffLng;
			pointsLat[i] = pointsLat[i - 1] + diffLat;
		}

		// Create a feature collection of all the cells and colour them
		var features = new Feature[100];
		var counter = 0;
		for (var i = 0; i < 10; i++) {
			for (var j = 0; j < 10; j++) {
				var squarePts = new ArrayList<>(Arrays.asList(
						Point.fromLngLat(pointsLng[i], pointsLat[j]),
						Point.fromLngLat(pointsLng[i], pointsLat[j + 1]),
						Point.fromLngLat(pointsLng[i + 1], pointsLat[j + 1]),
						Point.fromLngLat(pointsLng[i + 1], pointsLat[j]),
						Point.fromLngLat(pointsLng[i], pointsLat[j])));

				var squarePoly = Polygon.fromLngLats(List.of(squarePts));
				var squareGeo = (Geometry) squarePoly;
				var squareFt = Feature.fromGeometry(squareGeo);
				squareFt.addStringProperty("fill", colours[i][j]);
				squareFt.addNumberProperty("fill-opacity", 0.75);
				features[counter] = squareFt;
				counter++;
			}
		}
		this.heatmap = FeatureCollection.fromFeatures(features);

		// Print a completion message
		System.out.println("Heatmap complete!");
	}

}