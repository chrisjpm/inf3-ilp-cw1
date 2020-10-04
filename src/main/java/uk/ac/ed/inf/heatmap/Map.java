package uk.ac.ed.inf.heatmap;

import com.mapbox.geojson.Feature;

public class Map {
	
	public Map() {
		
	}
	
	public Feature createMap() {
		System.out.println(
				"-------------------------------\n" + "Creating GeoJson!\n...");
		
		// Setting boundaries of drone flight area
		var mapjson = "{" + "'type': 'Feature'," + "'properties': {},"
				+ "'geometry': {" + "'type': 'LineString'," + "'coordinates': ["
				+ "[-3.192473, 55.946233]," + "[-3.184319,55.946233],"
				+ "[-3.184319,55.942617]," + "[-3.192473,55.942617],"
				+ "[-3.192473,55.946233]" + "]" + "}" + "}";

		// Create feature for boundaries
		Feature feature = Feature.fromJson(mapjson);
		
		// Print a completion message
				System.out.println("Boundires feature complete!\n...");
				
		return feature;
	}

}