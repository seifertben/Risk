<!DOCTYPE html>
<html lang="en">
<head>
  <title>Risk</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    .well[id=player1]{
        color: blue;
    }
    .well[id=player2]{
		background-color: rgb(22, 105, 173);
		color: white;
	}
	.well[id=player3]{
	    color: green;
	}
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body>

<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav"></div>
	
    <div class="col-sm-8 text-left"> 
      <h1>Welcome to Risk</h1>
      <hr>
<link rel="stylesheet" href="https://www.amcharts.com/lib/3/ammap.css" type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/ammap.js"></script>
<script src="https://www.amcharts.com/lib/3/maps/js/continentsLow.js"></script>
<div id="mapdiv" style="width: 1000px; height: 300px;"><script>AmCharts.makeChart( "mapdiv", {
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
} );</script></div></div>
    <div class="col-sm-2 sidenav">
      <div class="well" id="player1">
      Player One
      </div>
      <div class="well" id="player2">
      Player Two
      </div>
      <div class="well" id="player3">
      Player Three
      </div>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
</footer>
</body>
</html>