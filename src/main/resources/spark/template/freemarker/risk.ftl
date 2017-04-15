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
    .drop{
      position: relative;
      float: left;  
      padding-right: 10px;  
      padding-left: 0; 
      text-decoration: none;
      /*background-color: blue;*/
    }
    .card {
      position: relative;
      float: left;  
      padding-right: 10px;  
      padding-left: 0; 
      text-decoration: none;
    }
    .w3-card-4 {
      background-color: blue;
    }
    .w3-container-w3-blue {
      background-color: blue;
    }
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    ul {
      list-style-type: none;
    }
    /*.star {
      font-size: x-large;
    }
    */
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
    .well-sm[id=player1]{
      float: left;
        color: blue;
        width: 30%;
    }
    .well-sm[id=player2]{
      float: left;
		background-color: rgb(22, 105, 173);
		color: white;
    width: 30%;
	}
	.well-sm[id=player3]{
    float: left;
	    color: green;
      width: 30%;
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
      <div class="well well-sm" id="player1">
      Player One
      </div>
      <div class="well well-sm" id="player2">
      Player Two
      </div>
      <div class="well well-sm" id="player3">
      Player Three
      </div>
        <p>You are attacking Player 2!</p>
        <p>You have 5 soldiers in Russia</p>
     <div class="btn-group">
  <button type="button" class="btn btn-primary">Select the amount of dice to roll</button>
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li class= "drop"><a href="#">1</a></li>
    <li class = "drop"><a href="#">2</a></li>
      <li class = "drop"><a href="#">3</a></li>
  </ul>
</div><br>
      <button type="button" class="btn btn-danger">Attack</button>
<p>Attacker's role</p>
    <ul id = "blackRole">
      <li><img src="file:./black4.png"></li>
      <li><img src="./black3.png"></li>
       <li><img src="./black3.png"></li>
       </ul>
       <p>Defender's role</p>
       <ul id = "blackRole">
      <li><img src="./red6.png"></li>
      <li><img src="./red4.png"></li>
       </ul>
       <br><p>You lost 1 soldier</p>
       <p>Player 2 lost 1 soldier</p>
  </div>
</div>

<footer class="container-fluid text-center">

<ul id= "cards">
  <li class = "card"><div class="w3-card-4">

<header class="w3-container-w3-blue">
  <h1>Card 1</h1>
</header>

<div class="w3-container">
  <p id = "star">*</p>
</div>

<footer class="w3-container-w3-blue">
  <h5>Turn in this card for renforcements!</h5>
</footer>

</div></li>
<li class = "card"><div class="w3-card-4">

<header class="w3-container-w3-blue">
  <h1>Card 2</h1>
</header>

<div class="w3-container">
  <p id = "star">**</p>
</div>

<footer class="w3-container-w3-blue">
  <h5>Turn in this card for reinforcements!</h5>
</footer>

</div></li>
<li class = "card"><div class="w3-card-4">

<header class="w3-container-w3-blue">
  <h1>Card 3</h1>
</header>

<div class="w3-container">
  <p id = "star">**</p>
</div>

<footer class="w3-container-w3-blue">
  <h5>Turn in this card for reinforcements!</h5>
</footer>

</div></li>
<li class = "card"><div class="w3-card-4">

<header class="w3-container-w3-blue">
  <h1>Card 4</h1>
</header>

<div class="w3-container">
  <p id = "star">*</p>
</div>

<footer class="w3-container-w3-blue">
  <h5>Turn in this card for reinforcements!</h5>
</footer>

</div></li>
<li class = "card"><div class="w3-card-4">

<header class="w3-container-w3-blue">
  <h1>Card 5</h1>
</header>

<div class="w3-container">
  <p id = "star">**</p>
</div>

<footer class="w3-container-w3-blue">
  <h5>Turn in this card for reinforcements!</h5>
</footer>

</div></li>
</ul>
</footer>
</body>
</html>