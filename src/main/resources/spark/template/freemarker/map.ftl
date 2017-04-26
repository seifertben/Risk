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
  	  <div id="mapdiv" style="width: 1000ps; height: 400px;"></div></div>
  <div class="col-sm-2 sidenav" id="n">
  <div id="playerList"></div>
  </div>
</div>
<footer class="container-fluid text-center" id="bottom">
<ul id="cards">
</ul>
</footer>
</#assign>
<#include "main.ftl">