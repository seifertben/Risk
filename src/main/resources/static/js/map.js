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