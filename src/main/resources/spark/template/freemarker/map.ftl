<div id="gameField">
<div class="container-fluid text-center">
  <div class="row content">

  	<div class="col-sm-2 sidenav" id = "leftNav">
  	<p>Chat With Other Players!</p>
    <div id="chatting" class="list-group">
    </div>
    <textarea name="message" value="" id="messageField"
     placeholder="Enter Message!"></textarea>
    <Button id = "chatButton">Send</Button>


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
  <p>Match Log</p>
  <div id="lastTurn" class="list-group"></div>
  <div id="gameUpdates"></div>
  <div id="endSection"></div>
  <button type="button" id="showcards">Show my Cards!</button>
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


  <div class = "footer-content" id = "card-footer">
  <div class = "card-footer">
  <button type="button" id="hideCards">Hide</button>
  <h3>Your Cards:</h3>
  <ul id="cards">
  </ul>
  </div>
  </div>
</div>




  <!-- The Modal -->
<div id="loserModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close" id = "closeLoser" "text-right">&times;</span>
    <p id = "loser" style = "text-decoration: none"></p>
    <div class ="modal-footer">
  		<div class="col-sm-offset-5 col-sm-2 text-center">
  			<button type="button" id="loserMain">Back to Main Menu?</button>
  		</div>
    </div>
  </div>
</div>

  <!-- The Modal -->
<div id="winnerModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <p id = "winner" style = "text-decoration: none"></p>
    <div class ="modal-footer">
    	<div class="col-sm-offset-5 col-sm-2 text-center">
  			<button type="button" id="winnerMain">Go to Main Menu</button>
  		</div>
    </div>
  </div>
</div>

</div>

<footer class="container-fluid text-center" id="bottom">
<ul id="cards">
</ul>
</footer>
</div></div>
<div id="muter">
      <label class="switch">
      <input id="mute" type="checkbox">
      <div class="slider round"></div>
      <span class="slider-inner"></span>
    </label>
    </div>
