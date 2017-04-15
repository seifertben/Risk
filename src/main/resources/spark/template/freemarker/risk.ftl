<#assign content>
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav"></div>
	
    <div class="col-sm-8 text-left"> 
      <h1>Welcome to Risk</h1>
      <hr>
      <link rel="stylesheet" href="https://www.amcharts.com/lib/3/ammap.css" type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/ammap.js"></script>
<script src="https://www.amcharts.com/lib/3/maps/js/continentsLow.js"></script>
<div id="mapdiv" style="width: 1000px; height: 300px;"></div></div>
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
</#assign>
<#include "main.ftl">