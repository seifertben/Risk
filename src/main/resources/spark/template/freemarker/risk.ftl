<#assign content>

<div id="nameField">

  <form id="nameForm">
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
        <input id="playerNum" type="number" min="2" max="6" value="2"></input>
        <button id="maker">Make a game</button>
      </div>
    <div id="matches" class="list-group">
    </div>
  </div>
</div>
<div id="muter">
<button type = 'button' id = 'homeMute' class='btn btn-success'> Mute</button>
<audio  id = "mainMenuMusic" loop autoplay="true" src="https://ia801000.us.archive.org/21/items/RichardWagnerTheRideOfTheValkyriesFromApocalypseNow/Richard%20Wagner%20-%20The%20Ride%20of%20the%20Valkyries%20(From%20Apocalypse%20Now).mp3">
</audio>
</div>
<div id="gameField">
<#include "map.ftl">
</div>

</#assign>
<#include "main.ftl">