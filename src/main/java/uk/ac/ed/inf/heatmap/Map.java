package uk.ac.ed.inf.heatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mapbox.geojson.*;

public class Map {
	private FeatureCollection heatmap;
	private double pointLat1, pointLat2, pointLng1, pointLng2;

	// Getters
	public FeatureCollection getHeatmap() {
		return this.heatmap;
	}

	// Setters
	public void setPointLat1(double x) {
		this.pointLat1 = x;
	}

	public void setPointLat2(double x) {
		this.pointLat2 = x;
	}

	public void setPointLng1(double x) {
		this.pointLng1 = x;
	}

	public void setPointLng2(double x) {
		this.pointLng2 = x;
	}

	// Methods
	// Create a polygon for each cell of the 10x10 grid and assign it its air
	// pollution colour code
	public void createMap(String[][] colours) {
		var pointsLng = new double[App.DIM_GRID + 1];
		var pointsLat = new double[App.DIM_GRID + 1];

		// Find the divisions of the longitude and latitude
		var diffLng = (this.pointLng2 - (this.pointLng1)) / App.DIM_GRID;
		var diffLat = (this.pointLat2 - this.pointLat1) / App.DIM_GRID;

		// Most north west cell
		pointsLng[0] = this.pointLng1;
		pointsLat[0] = this.pointLat1;

		for (var i = 1; i < App.DIM_GRID + 1; i++) {
			pointsLng[i] = pointsLng[i - 1] + diffLng;
			pointsLat[i] = pointsLat[i - 1] + diffLat;
		}

		// Create a feature collection of all the cells and colour them
		var features = new Feature[App.DIM_GRID * App.DIM_GRID];
		var counter = 0;
		for (var i = 0; i < App.DIM_GRID; i++) {
			for (var j = 0; j < App.DIM_GRID; j++) {
				// Create polygon of cell
				// Indexed j,i so to go west to east, north to south
				var squarePts = new ArrayList<>(Arrays.asList(
						Point.fromLngLat(pointsLng[j], pointsLat[i]),
						Point.fromLngLat(pointsLng[j + 1], pointsLat[i]),
						Point.fromLngLat(pointsLng[j + 1], pointsLat[i + 1]),
						Point.fromLngLat(pointsLng[j], pointsLat[i + 1])));

				var squarePoly = Polygon.fromLngLats(List.of(squarePts));
				var squareGeo = (Geometry) squarePoly;
				var squareFt = Feature.fromGeometry(squareGeo);

				// Colour code the cell
				// Indexed i,j so to go left to right, top to bottom
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