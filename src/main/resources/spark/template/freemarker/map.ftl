<div class="container-fluid text-center">
  <div class="row content">

  	<div class="col-sm-2 sidenav" id = "leftNav">
    <div id="chatting" class="list-group">
    </div>
    <textarea name="message" value="" id="messageField"
     placeholder="Enter Message!"></textarea>
    <Button id = "chatButton">Send</Button>
    <button type = 'button' id = 'gameMute' class='btn btn-success'> Mute</button>
    <div id="lastTurn" class="list-group"></div>
    </div>
  	<div id="centerDiv" class="col-sm-8 text-left">

  	  <h1 id="inGame">Welcome to Risk</h1>

  	  <hr>
  	  <link rel="stylesheet" href="https://www.amcharts.com/lib/3/ammap.css" type="text/css" media="all" />
  	  <script src="https://www.amcharts.com/lib/3/ammap.js"></script>
  	  <script src="https://www.amcharts.com/lib/3/maps/js/continentsLow.js"></script>
  	  <div id="mapdiv" style="width: 100%; height: 500px;"></div></div>
  <div class="col-sm-2 sidenav" id="n">
  <div id="playerList"></div>
  <div id="gameUpdates"></div>
  <div id="endSection"></div>
  </div>

  <!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close" id = "closer">&times;</span>
    <p id = "datadump"></p>
    <p id = "territories"></p><br />
    <p id = "continents"></p><br />
    <p id = "totaltroops"></p><br />
  </div>
</div>

  <div class = "footer-content">
  <div class = "card-footer" id = "card-footer">
  <ul id="cards">
  </ul>
  </div>
  </div>

</div>


