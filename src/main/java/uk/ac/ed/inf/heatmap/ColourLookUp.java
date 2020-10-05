package uk.ac.ed.inf.heatmap;

public class ColourLookUp {
	public String[][] colours;

	// Define constructor
	public ColourLookUp() {
		this.colours = new String[10][10];
	}

	// Getters
	public String[][] getColours() {
		return this.colours;
	}

	// Methods
	// Assign the hex value colour of the int in the predictions file
	public void assignColours(int[][] preds) {
		for (var i = 0; i < 10; i++) {
			for (var j = 0; j < 10; j++) {
				if (preds[i][j] >= 0 && preds[i][j] < 32) {
					this.colours[i][j] = "#00ff00";
				} else if (preds[i][j] >= 32 && preds[i][j] < 64) {
					this.colours[i][j] = "#40ff00";
				} else if (preds[i][j] >= 64 && preds[i][j] < 96) {
					this.colours[i][j] = "#80ff00";
				} else if (preds[i][j] >= 96 && preds[i][j] < 128) {
					this.colours[i][j] = "#c0ff00";
				} else if (preds[i][j] >= 128 && preds[i][j] < 160) {
					this.colours[i][j] = "#ffc000";
				} else if (preds[i][j] >= 160 && preds[i][j] < 192) {
					this.colours[i][j] = "#ff8000";
				} else if (preds[i][j] >= 192 && preds[i][j] < 224) {
					this.colours[i][j] = "#ff4000";
				} else if (preds[i][j] >= 224 && preds[i][j] < 256) {
					this.colours[i][j] = "#ff0000";
				}
			}
		}
	}

}