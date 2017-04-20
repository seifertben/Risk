<#assign content>
<div id="menuField">
  <div id="wrapper">
  <form id="game" class="well well-sm">
    <div id="enter">
    <h2>Enter a Match Name and a Number of Players</h2><br>
    <input id="name" type="text" autocomplete="off"></input>
    <input id="playerNum" type="number" min="3" max="6" value="3"></input>
    <button id="maker">Make a game</button>
    </div>
    <div id="matches" class="list-group">
    </div>
  </form>
  </div>
</div>
<div id="gameField">
  <#include "risk.ftl">
</div>
</#assign>
<#include "main.ftl">