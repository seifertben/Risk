//ceach number represents a territory 
const TerritoryEnum = {
  ALASKA: 0,
  ONTARIO: 1,
  CENTRAL_AMERICA: 2,
  EASTERN_US: 3,
  WESTERN_US: 4,
  GREENLAND: 5,
  NORTHWEST_TERRITORY: 6,
  ALBERTA: 7,
  QUEBEC: 8,
  VENEZUELA: 9, 
  PERU: 10,
  BRAZIL: 11,
  ARGENTINA: 12,
  NORTH_AFRICA: 13,
  EGYPT: 14,
  EAST_AFRICA: 15,
  CENTRAL_AFRICA: 16,
  SOUTH_AFRICA: 17,
  MADAGASCAR: 18,
  ICELAND: 19,
  GREAT_BRITIAN: 20,
  WESTERN_EUROPE: 21,
  NORTHERN_EUROPE: 22,
  SCANDINAVIA: 23,
  SOUTHERN_EUROPE: 24,
  RUSSIA: 25,
  AFGHANISTAN: 26,
  CHINA: 27,
  INDIA: 28,
  IRKUTSK: 29,
  JAPAN: 30,
  KAMACHATKA: 31,
  MIDDLE_EAST: 32,
  MONGOLIA: 33,
  SOUTHEAST_ASIA: 34,
  SIBERIA: 35,
  URAL: 36,
  YAKUTSK: 37,
  EASTERN_AUSTRALIA: 38,
  INDONESIA: 39,
  NEW_GUINEA: 40,
  WESTERN_AUSTRALIA: 41
};
//maps an int ot a name
const terrToName = {
  0: "Alaska", 1: "Ontario", 2: "Central America", 3: "Eastern US", 4: "Western US",
  5: "Greenland", 6: "Northwest Territory", 7: "Alberta", 8: "Quebec", 9: "Venezuela",
  10: "Peru", 11: "Brazil", 12: "Argentina", 13: "North Africa", 14: "Egypt", 15: "East Africa",
  16: "Central Africa", 17: "South Africa", 18: "Madagascar", 19: "Iceland", 20: "Great Britian",
  21: "Western Europe", 22: "Northern Europe", 23: "Scandinavia", 24: "Southern Europe",
  25: "Russia", 26: "Afghanistan", 27: "China", 28: "India", 29: "Irkutsk", 30: "Japan",
  31: "Kamachatka", 32: "Middle East", 33: "Mongolia", 34: "Southeast Asia", 35: "Siberia",
  36: "Ural", 37: "Yakutsk", 38: "Eastern Australia", 39: "Indonesia", 40: "New Guinea",
  41: "Western Australia"};

//gives each card a card id by incrementing this variable
let cardID = 0;
//whether the game has started 
let start = false;
let prevMessage = undefined;
let prevMove = undefined;
//background div
let body = $('#background');

 let now = -1;
//array of image urls for hte slideshow on the hompage and slideshow
let imageList = ["url('https://s-media-cache-ak0.pinimg.com/originals/f6/ee/d2/f6eed2fd34fd0d5d8e17fe417c288dba.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/originals/53/2c/22/532c224459ada029dfb2db7be6165cde.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/originals/15/d0/41/15d041870d416ac9647203e96b4ab78b.jpg')", "url('https://www.dal.ca/content/dam/dalhousie/images/fass/classics/Rome%20battle.jpg')", "url('https://s-media-cache-ak0.pinimg.com/originals/4f/12/54/4f12540ed158356e679d4808e1c961cf.jpg')"
,"url('https://fthmb.tqn.com/toprD0AfngOnFREScDpSg-5bl-g=/1172x796/filters:no_upscale():fill(FFCC00,1)/about/battle-of-ascalon-large-56a61c243df78cf7728b63a7.jpg')", 
"url('https://s-media-cache-ak0.pinimg.com/originals/cc/f0/11/ccf011df7a8f59a31efc28e35ec0a655.jpg')",
 "url('http://www.museumofthecity.org/wp-content/uploads/2013/07/history_timeline_photo_09_1.jpg')", "url('http://www.realmofhistory.com/wp-content/uploads/2016/06/10-facts-imperial-roman-legionary-770x437.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/736x/4f/3f/49/4f3f49b4b8945fe107d68163df14be7a.jpg')", "url('https://s-media-cache-ak0.pinimg.com/originals/7e/5d/a8/7e5da8f7f966ec2e946e205fcdfe5d02.jpg')", "url('https://s-media-cache-ak0.pinimg.com/originals/91/c2/ad/91c2adff774b415f4cd3f7204efff1a9.jpg')", 
"url('http://www.rmg.co.uk/sites/default/files/styles/slider/public/Loutherbourg-Spanish_Armada.jpg?itok=_-MaaV38')", "url('https://i.ytimg.com/vi/GvPXCnrAtMo/maxresdefault.jpg')", 
"url('http://s3.amazonaws.com/mtv-main-assets/files/resources/surrender_of_lord_cornwallis-capitol-web.jpg')", "url('http://www.hms-victory.com/sites/default/files/images/D.jpg')",
"url('http://www.britishbattles.com/wp-content/uploads/2017/02/Charge_of_the_French_Cuirassiers_at_Waterloo-Henri-F%C3%A9lix-Emmanuel-Philippoteaux.jpg')", 
"url('https://userscontent2.emaze.com/images/7518df8e-7872-4fa4-87ce-0264c2418005/9293405ab76210dc084d21b92bac733a.jpg')", "url('http://www.toledomuseum.org/wordpress/wp-content/uploads/Civil-War_1.jpg')","url('https://weaponsandwarfare.files.wordpress.com/2016/10/1280px-ljb9_-_zimmer.jpg')", "url('http://www.historyonthenet.com/wp-content/uploads/2016/12/135151-004-0D4D550E.jpg')", 
"url('http://i.imgur.com/raE3EQw.jpg')", "url('http://cdn.thedailybeast.com/content/dailybeast/articles/2013/05/12/world-war-ii-s-strangest-battle-when-americans-and-germans-fought-together/jcr:content/image.img.2000.jpg/1380849710571.cached.jpg')"
, "url('https://d11in36igezwwb.cloudfront.net/texts/images/000/000/930/original/Approaching_Omaha.jpg?1489685651')", "url('https://upload.wikimedia.org/wikipedia/commons/f/fc/UH-1D_helicopters_in_Vietnam_1966.jpg')", "url('https://upload.wikimedia.org/wikipedia/commons/0/04/USAF_F-16A_F-15C_F-15E_Desert_Storm_edit2.jpg')"
, "url('http://pop.h-cdn.co/assets/16/01/980x490/landscape-1452205198-gettyimages-107900765.jpg')"];
slideshow();
const sendMessage = event => {
  if (event !==undefined) {
    event.preventDefault();
  }
    let  message = $('#messageField').val();
    //filters out script text 
    if (message.toLowerCase().includes("<script>") || message.toLowerCase().includes("</script>")) {
      message = "HAXORZ";
    }
    //if statements that filters out swera
    if (message.toLowerCase().includes("fuck")) {
      message = "Wishing you the best!";
    }
    if (message.toLowerCase().includes("shit")) {
      message = "Mommy says I shouldn't say swears...";
    }
    if (message.toLowerCase().trim() == "ass") {
      message = "Wow! GG.";
    }
    if (message.toLowerCase().includes("dick")) {
      message = "Catan is a pretty cool game.";
    }
    if (message.toLowerCase().includes("crap")) {
      message = "Lolwut?!11!?";
    }
    if (message.toLowerCase().includes("cunt")) {
      message = "MEMEZ!!!";
    }
    //emptys message field
    $('#messageField').val("");
    //sends message to the backend
    let mess = {"type" : MESSAGE_TYPE.MESSAGE, "message": message, "playerId": myId};
    conn.send(JSON.stringify(mess));
}
// if music is playing 
let isplaying = false;
document.getElementById("chatButton").onclick = sendMessage;
setInterval(slideshow, 6000);
  
  $("#transferconfirm").on("click", confirmTransfer);
  //music is off by default
  defaultPause();
  $("#mute").on("click", changeMusicStatus);
  $("#diceconfirm").on("click", confirmDice);
/**
pauses/plays music based on whether the isPlaying variable is true/false
**/
 function changeMusicStatus() {
   if (isplaying) {
     document.getElementById('mainMenuMusic').pause();
     isplaying = !isplaying;
   }
   else {
     document.getElementById('mainMenuMusic').play();
     isplaying = !isplaying;
   }
 }

/**
if moving troops to conquered territory is finished, the entire div is hidden
**/
function confirmTransfer() {
  if ($("#transferDropDownText").text() !== "Select troops to move to conquered territory") {
    $("#transfergroup").hide();

  }
}
/**
if selecting dice is finished, the entire div is hidden
**/
function confirmDice() {
  if ($("#dropdown").text() !== "Select the amount of dice to roll") {
    $("#dropdowngroup").hide();

  }
}

function defaultPause() {
   document.getElementById('mainMenuMusic').pause();
}
/**
changes background image in home/lobby every 5 seconds
**/
function slideshow() {
  if (!start) {
    now = (now+1) % (imageList.length) ;
      body.css('background-image', imageList[now]);
      body.fadeIn(1000);
      setTimeout(function(){body.fadeOut(1000)}, 5000);
    }
}   

$("#playerNum").keypress(function (evt) {
    evt.preventDefault();
});
/**
this functions creates all of the html tags needed for the game and hddes all of the tags not needed
**/
function setUp () {
  $sideNav = $('#gameUpdates');
  $endNav = $('#endSection');
  $topNav = $("#inGame");
  $topNav.append($("<p id = 'phase'></p>"));
  $topNav.append($("<p id = 'turn' c></p>"));
  $sideNav.append($("<p id = 'bolsters'></p>"));
  $sideNav.append($("<p id = 'selecting'></p>"));
  $sideNav.append($("<p id = 'attacking'></p>"));
  $endNav.append($("<button type='button' id = 'resetAttackMove' class='attackbtn'>Reset Attack Move</button>"));
  $sideNav.append($("<button type='button' id = 'attack' class='attackbtn'>Attack!</button>"));
  $sideNav.append($("<button type='button' id = 'defend' class='attackbtn'>Defend!</button>"));
  $endNav.append($("<button type='button' id = 'resetMoveTroops' class='attackbtn'>Reset Move Troops</button>"));
  $endNav.append($("<button type='button' id = 'skip' class='attackbtn'>End Turn</button>"));
  $sideNav.append($("<select id='attackerNumberDie'> </select>"));
  $sideNav.append($("<select id='defenderNumberDie'> </select>"));
  $sideNav.append($("<select id='moveTroopsNumber'> </select>"));
  $sideNav.append($("<button id='reinforcer' class='regbtn'>Place a Troop</button>"));
  $sideNav.append($("<button id='deinforcer' class='regbtn'>Recall a Troop</button>"));
  $sideNav.append($("<button id='confirm' class='regbtn'>Confirm Placements</button>"));

  selecting.innerHTML = "Select A Territory to Reinforce";

  document.getElementById("reinforcer").onclick = place_troop;
  document.getElementById("deinforcer").onclick = remove_troop;
  document.getElementById("confirm").onclick = confirm_move;
  document.getElementById("resetAttackMove").onclick = reset_attack;
  document.getElementById("skip").onclick = skip_phase;
  document.getElementById("resetMoveTroops").onclick = reset_move_troops;

  $sideNav.append($("<button id='claimTerritory' class='regbtn'>Claim Territory</button>"));
  document.getElementById("claimTerritory").onclick = claim_terr;
  
  $sideNav.append($("<button id='moveTroops' class='regbtn'>Confirm Movement</button>"));
  document.getElementById("moveTroops").onclick = move_troops;

  $sideNav.append($("<p id = 'available'></p>"));
  $sideNav.append($("<select id='clickList'></select>"));
  $sideNav.append($("<button type='button' id = 'simSel' class='regbtn'>Select From List</button>"));
  document.getElementById("simSel").onclick = simClick;
  $endNav.append($("<button type='button' id = 'turnInCards' class='regbtn'>Turn In Cards</button>"));
  $("#turnInCards").on( "click", turnInCards);   
  hideAll();
  $('#card-footer').css({"display":"none"});
}
/**
hides all of the tags below
**/
function hideAll() {
  $("#attack").hide();
  $("#defend").hide();
  $("#clickList").hide();
  $("#simSel").hide();
  $("#skip").hide();
  $("#attackerRollText").hide();
  $("#defenderRollText").hide();
  $("#blackRoll").hide();
  $("#redRoll").hide();
  $("#attackLoss").hide();
  $("#defendLoss").hide();
  $("#attackingWho").hide();
  $("#defenderStatus").hide();
  $("#attackerStatus").hide();
  $("#dropdownbutton").hide();
  $("#soldierOptions").hide();
  $("#transfergroup").hide();
  $("#dropdowngroup").hide();
  $("#resetTransfer").hide();
  $("#resetAttackMove").hide();
  $("#resetMoveTroops").hide();
  $("#attackerNumberDie").hide();
  $("#defenderNumberDie").hide();
  $("#moveTroopsNumber").hide();
  $("#available").hide();
  $("#selecting").hide();
  $("#turnInCards").hide();
  document.getElementById("reinforcer").style.display = "none";
  document.getElementById("deinforcer").style.display = "none";
  document.getElementById("confirm").style.display = "none";   
  document.getElementById("attacking").style.display = "none";
  document.getElementById("claimTerritory").style.display = "none";
  document.getElementById("moveTroops").style.display = "none";
}
/**
based on wether a card is a 2 star card or 1 star card, a ard will be created with those attributes
**/

function addcard(number) {
	let card;
	if (number ===1) {
		card = $("<li class ='card one' onclick = 'clickOnCard(this)'><div class='w3-card-4'><header class='w3-container-w3-blue'><h1 id='cardtitle'>Card</h1></header><div class='w3-container'><p id = 'star'><img src='https://img.clipartfest.com/c1754dbc38b0148860fbeef2e3833c6c_stars-clipart-on-transparent-star-clipart-transparent-background_800-766.png' alt='Star Icon' style='width:30px;height:30px;'></p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>");
		card.attr("id", cardID.toString());
	  $('#cards').append(card);
    cardID++;   

    }
    if (number ===2) {
      card = $("<li class ='card two' onclick = 'clickOnCard(this)'><div class='w3-card-4'><header class='w3-container-w3-blue'><h1 id='cardtitle'>Card</h1></header><div class='w3-container'><p id = 'star'><img src='https://img.clipartfest.com/c1754dbc38b0148860fbeef2e3833c6c_stars-clipart-on-transparent-star-clipart-transparent-background_800-766.png' alt='Star Icon' style='width:30px;height:30px;'><img src='https://img.clipartfest.com/c1754dbc38b0148860fbeef2e3833c6c_stars-clipart-on-transparent-star-clipart-transparent-background_800-766.png' alt='Star Icon' style='width:30px;height:30px;'></p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>");
	  card.attr("id", cardID.toString());
	  $('#cards').append(card);
    cardID++;   
    }
}
/**
creates player buttons with names and sets up data for player info when player clicks on a specific player name 
**/
function createPlayer(number) {
  for (let i = 0; i < number; i++) {
    let currDiv = $("<div id = 'names'></div>");
    let text =  $("<span id = 'name'></span>");
    currDiv.attr("class", "well well-sm");
    const player = i+1;
    let string = players[i];
    text.html(idToName[players[i]]);
    currDiv.attr("id", string);
    currDiv.append(text);
    $("#playerList").append(currDiv);
    let currPlayerInfo = {playerName: idToName[players[i]], totalNumberTroops: undefined, continents: undefined, terrsTroops: undefined};
    playerInfo[string] = currPlayerInfo ;


    document.getElementById(string).style.backgroundColor = colors[players[i]];
    document.getElementById(string).style.font = "bold 12px/30px Georgia, serif";
    document.getElementById(string).style.color = "white";
    document.getElementById(string).style.cursor = "pointer";

    document.getElementById(string).onmouseover = function() {
      document.getElementById(string).style.backgroundColor = "black";
      document.getElementById(string).style.transition = "all 1s";
    }
    document.getElementById(string).onmouseout = function() {
      document.getElementById(string).style.backgroundColor = colors[players[i]];
      document.getElementById(string).style.transition = "all 1s";
   }

    document.getElementById(string).onclick = function() {
      document.getElementById('datadump').innerHTML = "PLAYER PROFILE FOR: " + idToName[string];
      let territoryString = "Occupies these territories: <br>";
       let currPlayerInfo  = playerInfo[string];
       let territoryTroopInfo = JSON.parse(currPlayerInfo.terrsTroops);
      for (let key in territoryTroopInfo) {
        territoryString += (key +  " " + territoryTroopInfo[key] + "<br>");

      }
      let continentInfo = JSON.parse(currPlayerInfo.continents);
      let continentString = "";
      for (let i = 0; i <continentInfo.length; i ++) {
          continentString += ( "<br> " + continentInfo[i]);
      }
      if (continentInfo.length ===0) {
        continentString = " none";
      }
      document.getElementById('territories').innerHTML = territoryString;
      document.getElementById('continents').innerHTML = "Possesses these continents:" + continentString;
      document.getElementById('totaltroops').innerHTML = "Has this many troops in total: " + currPlayerInfo.totalNumberTroops;
      document.getElementById('myModal').style.display = "block";
    }
    document.getElementById("closer").onclick = function() {
    let color = document.getElementById(string).style.backgroundColor;
    document.getElementById(string).style.backgroundColor = color;
    document.getElementById('myModal').style.display = "none";
    }
  }
}

let count = 0;
//used to flash message
function blink(selector){
  while(count<6) {
  $(selector).fadeOut('slow', function(){
      $(this).fadeIn('slow', function(){
          blink(this);
      });
  });
  count++;
  }
}
/**
this emits the message to every single mplayer
**/
function getMessage(player, message) {
  //assign color to message box.

  let string; 
  if (player === myId) {
    string = "Me: " + message;
  }
  else {
    string = idToName[player] + ": " + message;
  }
  $li = $("<li class = 'chat'></li>");
  $li.html(string);

  $("#chatting").append($li);

  $li.css("border", "1px solid " + colorMap.get(player).toString());
    $li.css("border-left", "6px solid " + colorMap.get(player).toString());
    count = 0;
    blink($li);
    prevMessage = $li;

    $("#chatting").scrollTop($("#chatting")[0].scrollHeight);
}

//listen for enter on messaging.
document.querySelector("#messageField").addEventListener("keyup", function (e) {
    let key = e.keyCode;
    if (key === 13) { 
      sendMessage();
    }
});


//two click functions below hide/show cards depending on whether the div containing the cards is displayed or not
$("#showcards").click(
  function() {
    if (document.getElementById("card-footer").style.display === 'none') {
        document.getElementById("card-footer").style.display = 'block';
    } else {
        document.getElementById("card-footer").style.display = 'none';
    }
  }
);

$("#hideCards").click(
  function() {
    if (document.getElementById("card-footer").style.display === 'none') {
        document.getElementById("card-footer").style.display = 'block';
    } else {
        document.getElementById("card-footer").style.display = 'none';
    }
  }
);

//gives player a chance to stay in game if they accidentally 
window.onbeforeunload = function() {
  return "Leaving will end the game for everyone.";
};
