<#assign content>
<form>
<input id="name" type="text"></input>
</form>
<form id="game" method="GET" action="/risk">
<button id="maker">Make a game</button>
<input id="playerNum" type="number" min="3" max="6" value="3"></input>
</form>
<form id="matches">
</form>
</#assign>
<#include "main.ftl">