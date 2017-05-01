<#assign content>
<div id="nameField">
<div id="nameForm">
<h1>Welcome to Risk!</h1><br>
<h2>Please Enter a Name</h2><br>
<input type="text" autocomplete="off" id="nameInput"></text>
</form>
</div>
<div id="menuField">
<div id="wrapper">
<form id="game" autocomplete="off" class="well well-sm">
<div id="enter">
<h2>Enter a Match Name and a Number of Players</h2><br>
<input id="name" type="text" autocomplete="off"></input>
<input id="playerNum" type="text" autocomplete="off"></input>
<button id="maker">Make a game</button>
<div>
<div id="matches" class="list-group">
</div>
</form>
</div>
</div>
<div id="gameField">
<#include "map.ftl">
</div>
</#assign>
<#include "main.ftl">