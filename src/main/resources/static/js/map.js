var icon= "M21.25,8.375V28h6.5V8.375H21.25zM12.25,28h6.5V4.125h-6.5V28zM3.25,28h6.5V12.625h-6.5V28z";
AmCharts.makeChart( "mapdiv", {
  /**
   * this tells amCharts it's a map
   */
  "type": "map",
  "mouseWheelZoomEnabled": true,
  /**
   * create data provider object
   * map property is usually the same as the name of the map file.
   * getAreasFromMap indicates that amMap should read all the areas available
   * in the map data and treat them as they are included in your data provider.
   * in case you don't set it to true, all the areas except listed in data
   * provider will be treated as unlisted.
   */

  "dataProvider": {
    "map": "continentsLow",
     images: [ {
      "latitude": 38.392017, 
      "longitude": -85.047551,
      // "svgPath": icon,
      "type": "circle",
      "color": "#CC0000",
      "scale": 0.5,
      "label": "E. US",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Eastern United States",
      "description": "Occupied by Player 1. Soldiers: 5"
    },
    {
      "latitude": 41.187449,
      "longitude":  -114.449540,
      "type": "circle" ,
      "color": "#CC0000",
      "scale": 0.5,
      "label": "W. US",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Western United States",
      "description": "Occupied by Player 1. Soldiers: 5"
    } ,
     {
      "latitude": 50.206416,
      "longitude":   -75.586515,
      "type": "circle" ,
      "color": "#CC0000",
      "scale": 0.5,
      "label": "Quebec",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Quebec",
      "description": "Occupied by Player 1. Soldiers: 5"
    },
     
    {
      "latitude": 50.899997, 
      "longitude":   -87.521976,
      "type": "circle" ,
      "color": "#CC0000",
      "scale": 0.5,
      "label": "Ontario",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Ontario",
      "description": "Occupied by Player 1. Soldiers: 5"
    }
     ],
    "areas": [
      {
        "id": "europe",
        "color": "#18b238"
      },
      {
        "id": "asia",
        "color": "#cccc1c"
      },
      {
        "id": "africa",
        "color": "#18b238"
      },
      {
        "id": "south_america",
        "color": "#1b2ad1"
      },
      {
        "id": "north_america",
        "color": "#1b2ad1"
      },
      {
        "id": "australia",
        "color": "#cccc1c"
      }
    ]
  },

  /**
   * create areas settings
   * autoZoom set to true means that the map will zoom-in when clicked on the area
   * selectedColor indicates color of the clicked area.
   */
  "areasSettings": {
    "autoZoom": true,
    "selectedColor": "#CC0000"
    
  },
  

  /**
   * let's say we want a small map to be displayed, so let's create it
   */
  "smallMap": {}
} );