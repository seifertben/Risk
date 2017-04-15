<!DOCTYPE html>
  <head>
    <title>Risk</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/html5bp.css">
    <link rel="stylesheet" href="css/main.css">
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
    .row.content {height: 530px}
    
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
     ${content}

     <!-- Again, we're serving up the unminified source for clarity. -->
     <script src="js/jquery-2.1.1.js"></script>
	 <script src="js/chat.js"></script>
     <script src="js/matches.js"></script>
     <script src="js/map.js"></script>
     <script>
$(document).ready(function() {
  setup_matches();
});
</script>

  </body>
  <!-- See http://html5boilerplate.com/ for a good place to start
       dealing with real world issues like old browsers.  -->
</html>
