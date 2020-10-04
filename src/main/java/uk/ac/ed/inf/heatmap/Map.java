package uk.ac.ed.inf.heatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mapbox.geojson.*;

public class Map {
	public LineString boundriesLs;
	public Polygon sq;
	public FeatureCollection heatmap;

	// Define constructor
	public Map() {
	}

	// Getters
	public LineString getBoundriesLs() {
		return this.boundriesLs;
	}

	public Polygon getSq() {
		return this.sq;
	}
	
	public FeatureCollection getHeatmap() {
		return this.heatmap;
	}

	// Methods
	public void createMap(String[][] colours) {
		var pointsLng = new double[11];
		var pointsLat = new double[11];

		var diffLng = (-3.184319 - (-3.192473)) / 10;
		var diffLat = (55.942617 - 55.946233) / 10;

		pointsLng[0] = -3.192473;
		pointsLat[0] = 55.946233;

		for (var i = 1; i < 11; i++) {
			pointsLng[i] = pointsLng[i - 1] + diffLng;
			pointsLat[i] = pointsLat[i - 1] + diffLat;
		}

		var boundriesPl = new ArrayList<>(
				Arrays.asList(Point.fromLngLat(-3.192473, 55.946233), // F. Hill
						Point.fromLngLat(-3.184319, 55.946233), // KFC
						Point.fromLngLat(-3.184319, 55.942617), // Bus Stop
						Point.fromLngLat(-3.192473, 55.942617), // Meadows
						Point.fromLngLat(-3.192473, 55.946233))); // Forest Hill

		var features = new Feature[100];
		var counter = 0;
		for (var i = 0; i < 10; i++) {
			for (var j = 0; j < 10; j++) {
				var squarePts = new ArrayList<>(
						Arrays.asList(Point.fromLngLat(pointsLng[i], pointsLat[j]),
								Point.fromLngLat(pointsLng[i+1], pointsLat[j]),
								Point.fromLngLat(pointsLng[i+1], pointsLat[j+1]),
								Point.fromLngLat(pointsLng[i], pointsLat[j+1]),
								Point.fromLngLat(pointsLng[i], pointsLat[j])));
				
				var squarePoly = Polygon.fromLngLats(List.of(squarePts));
				var squareGeo = (Geometry) squarePoly;
				var squareFt = Feature.fromGeometry(squareGeo);
				squareFt.addStringProperty("fill", colours[j][i]);
				squareFt.addNumberProperty("opacity", 0.7);
				features[counter] = squareFt;
				counter++;
			}
		}
		// this.boundriesLs = LineString.fromLngLats(boundriesPl);
		// var boundriesGeo = (Geometry) this.boundriesLs;
		// var boundriesFt = Feature.fromGeometry(boundriesGeo);
		// features[100] = boundriesFt;	
		this.heatmap = FeatureCollection.fromFeatures(features);

		// Print a completion message
		System.out.println("Heatmap complete!");
	}

}