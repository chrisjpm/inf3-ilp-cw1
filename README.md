# INF3 ILP Coursework 1 - Heatmap

## Implementation Task
Research projects in science often need to visualise data and results in order to make the information that they contain easier to understand both for the scientists on the project and in communicating the results of the project to members of the general public, science journalists, politicians, and other stakeholders. One commonly-used form of scientific visualisation is a heat map in which data values are represented as colours across a surface or area. The intention of this heat map is to visualise the predictions that the project researchers have made for the highest sensor reading which will be seen in each area of the drone confinement area, partitioned into a regular 10×10 grid.

The predictions of the researchers are input into your heat map application as a text file with 10 lines of text, each of which has 10 integer values separated by commas. The values are understood to be in the order of the most northerly values down to the most southerly values, reading each row of data containing the values from the west to the east, in order. These values are to be used to generate an output file in the default output directory called heatmap.geojson.

The output Geo-JSON document will use the values from the input to produce a 10 × 10 grid which covers the drone confinement area.

*The mapping from air quality sensor readings to marker colours and marker symbols:*

Range | RGB string | Colour name | Marker symbol
------|------------|-------------|--------------
0 ≤ x < 32 |#00ff00| Green |lighthouse
32 ≤ x < 64| #40ff00 |Medium Green |lighthouse
64 ≤ x < 96| #80ff00| Light Green| lighthouse
96 ≤ x < 128 |#c0ff00 |Lime Green| lighthouse
128 ≤ x < 160| #ffc000| Gold |danger
160 ≤ x < 192 |#ff8000 |Orange |danger
192 ≤ x < 224 |#ff4000 |Red / Orange |danger
224 ≤ x < 256 |#ff0000 |Red |danger
low battery |#000000 |Black |cross
not visited |#aaaaaa |Gray |no symbol


*Contents of `predictions.txt`:*
```
200, 255, 200, 255, 255, 200, 255, 200, 255, 255
200, 255, 255, 255, 255, 255, 255, 255, 255, 255
255, 255, 255, 220, 200, 220, 255, 255, 220, 255
200, 200, 200, 140, 140, 140, 200, 255, 255, 255
80, 80, 80, 100, 100, 140, 180, 255, 255, 220
40, 40, 60, 60, 60, 80, 120, 220, 255, 220
40, 40, 60, 60, 40, 40, 120, 200, 225, 255
40, 40, 60, 60, 40, 40, 120, 180, 220, 255
0, 0, 50, 75, 75, 100, 165, 200, 200, 255
0, 0, 50, 50, 75, 200, 200, 255, 255, 255
```

*Expected heatmap:*

![Heatmap](https://i.imgur.com/WOoc8XW.png)

## How To Run
In the root folder, run `java -jar target/heatmap-0.0.1-SNAPSHOT.jar predictions.txt` then locate the `heatmap.geojson` that was created (view right now in the target folder through GitHub). Upload this file to [GeoJSON](https://geojson.io/) to see the resulting heatmap.

---

### Final Grade
92% :tada:

[Feedback](https://github.com/chrisjpm/inf3-ilp-cw1/blob/master/feedback.txt) | [Auto Report](https://github.com/chrisjpm/inf3-ilp-cw1/blob/master/auto-report.txt) | [Auto Report (GeoJSON)](https://github.com/chrisjpm/inf3-ilp-cw1/blob/master/auto-report.geojson)
