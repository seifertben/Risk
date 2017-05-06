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


let cardID = 0;
let start = false;
let prevMessage = undefined;
let prevMove = undefined;
let body = $('#background');

 let now = -1;

let imageList = ["url('https://s-media-cache-ak0.pinimg.com/originals/f6/ee/d2/f6eed2fd34fd0d5d8e17fe417c288dba.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/originals/53/2c/22/532c224459ada029dfb2db7be6165cde.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/originals/15/d0/41/15d041870d416ac9647203e96b4ab78b.jpg')","url('https://www.dal.ca/content/dam/dalhousie/images/fass/classics/Rome%20battle.jpg')", "url('http://i.imgur.com/yG3BO.jpg')"
,"url('https://fthmb.tqn.com/toprD0AfngOnFREScDpSg-5bl-g=/1172x796/filters:no_upscale():fill(FFCC00,1)/about/battle-of-ascalon-large-56a61c243df78cf7728b63a7.jpg')", 
"url('https://s-media-cache-ak0.pinimg.com/originals/cc/f0/11/ccf011df7a8f59a31efc28e35ec0a655.jpg')",
 "url('http://www.museumofthecity.org/wp-content/uploads/2013/07/history_timeline_photo_09_1.jpg')", "url('http://www.imgbase.info/images/safe-wallpapers/miscellaneous/historical/42834_historical_medieval_battle.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/736x/4f/3f/49/4f3f49b4b8945fe107d68163df14be7a.jpg')", "url('https://s-media-cache-ak0.pinimg.com/originals/7e/5d/a8/7e5da8f7f966ec2e946e205fcdfe5d02.jpg')", "url('https://s-media-cache-ak0.pinimg.com/originals/91/c2/ad/91c2adff774b415f4cd3f7204efff1a9.jpg')", 
"url('http://www.rmg.co.uk/sites/default/files/styles/slider/public/Loutherbourg-Spanish_Armada.jpg?itok=_-MaaV38')", "url('https://i.ytimg.com/vi/GvPXCnrAtMo/maxresdefault.jpg')", 
"url('http://s3.amazonaws.com/mtv-main-assets/files/resources/surrender_of_lord_cornwallis-capitol-web.jpg')",  "url('http://www.hms-victory.com/sites/default/files/images/D.jpg')",
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
    if (message.toLowerCase().includes("<script>") || message.toLowerCase().includes("</script>")) {
      message = "HAXORZ";
    }
    if (message.toLowerCase().includes("fuck")) {
      message = "Wishing you the best!";
    }
    if (message.toLowerCase().includes("shit")) {
      message = "Mommy says I shouldn't say swears...";
    }
    if (message.toLowerCase().includes("ass")) {
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
    $('#messageField').val("");
    let mess = {"type" : MESSAGE_TYPE.MESSAGE, "message": message, "playerId": myId};
    conn.send(JSON.stringify(mess));
}
let isplaying = false;
document.getElementById("chatButton").onclick = sendMessage;
setInterval(slideshow, 6000);

  $("#transferconfirm").on("click", confirmTransfer);

  defaultPause();
  $("#mute").on("click", changeMusicStatus);
  $("#diceconfirm").on("click", confirmDice);

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

  
function confirmTransfer() {
  if ($("#transferDropDownText").text() !== "Select troops to move to conquered territory") {
    $("#transfergroup").hide();

  }
}
function confirmDice() {
  if ($("#dropdown").text() !== "Select the amount of dice to roll") {
    $("#dropdowngroup").hide();

  }
}

function defaultPause() {
   document.getElementById('mainMenuMusic').pause();
}

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

function setUp () {
  $sideNav = $('#gameUpdates');
  $endNav = $('#endSection');
  $sideNav.append($("<p id = 'phase'></p>"));
  $sideNav.append($("<p id = 'turn' c></p>"));
  $sideNav.append($("<p id = 'bolsters'></p>"));
  $sideNav.append($("<p id = 'selecting'></p>"));
  $sideNav.append($("<p id = 'attacking'></p>"));
  $sideNav.append($("<button type='button' id = 'resetAttackMove' class='btn btn-danger'>Reset Attack Move</button>"));
  $sideNav.append($("<button type='button' id = 'attack' class='btn btn-danger'>Attack!</button>"));
  $sideNav.append($("<button type='button' id = 'defend' class='btn btn-danger'>Defend!</button>"));
  $endNav.append($("<button type='button' id = 'skip' class='btn btn-danger'>End Turn</button>"));
  $sideNav.append($("<button type='button' id = 'resetMoveTroops'class='btn btn-danger'>Reset Move Troops</button>"));
  $sideNav.append($("<select id='attackerNumberDie'> </select>"));
  $sideNav.append($("<select id='defenderNumberDie'> </select>"));
  $sideNav.append($("<select id='moveTroopsNumber'> </select>"));
  let reinforcer = document.createElement("BUTTON");
  let deinforcer = document.createElement("BUTTON");
  let confirm = document.createElement("BUTTON");
  selecting.innerHTML = "Select A Territory to Reinforce";
  reinforcer.id = "reinforcer";
  deinforcer.id = "deinforcer";
  reinforcer.innerHTML = "Place a Troop";
  deinforcer.innerHTML = "Recall a Troop";
  confirm.id = "confirm";
  confirm.innerHTML = "Confirm Placements";
  document.getElementById("gameUpdates").appendChild(reinforcer);
  document.getElementById("gameUpdates").appendChild(deinforcer);
  document.getElementById("gameUpdates").appendChild(confirm);

  $sideNav.append($("<p id = 'available'></p>"));
  $sideNav.append($("<select id='clickList'></select>"));
  $sideNav.append($("<button type='button' id = 'simSel' class='btn btn-danger'>Select From List</button>"));
  reinforcer.onclick = place_troop;
  deinforcer.onclick = remove_troop;
  confirm.onclick = confirm_move;
  document.getElementById("resetAttackMove").onclick = reset_attack;
  document.getElementById("skip").onclick = skip_phase;
  document.getElementById("resetMoveTroops").onclick = reset_move_troops;
  document.getElementById("simSel").onclick = simClick;
  let claim = document.createElement("BUTTON");
  claim.id = "claimTerritory";
  claim.innerHTML = "Claim Territory!";
  claim.onclick = claim_terr;
  document.getElementById("gameUpdates").appendChild(claim);
  let moveTroops = document.createElement("BUTTON");
  moveTroops.id = "moveTroops";
  moveTroops.innerHTML = "Confirm Troop Movements";
  moveTroops.onclick = move_troops;
  document.getElementById("gameUpdates").appendChild(moveTroops);
  $('#bottom').append($("<button type='button' id = 'turnInCards' class='btn btn-success'>Turn In Cards</button>"));
    $("#turnInCards").on( "click", turnInCards);   
    hideAll();
}

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

function addcard(number) {
  let card;
  if (number ===1) {
    card = $("<li class ='card one' onclick = 'clickOnCard(this)'><div class='w3-card-4'><header class='w3-container-w3-blue'><h1>Card</h1></header><div class='w3-container'><p id = 'star'>*</p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>");
    card.attr("id", cardID.toString());
    cardID++;    
    $('#cards').append(card);
    }
    if (number ===2) {
      card = $("<li class ='card two' onclick = 'clickOnCard(this)'><div class='w3-card-4'><header class='w3-container-w3-blue'><h1>Card</h1></header><div class='w3-container'><p id = 'star'>**</p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>");
    card.attr("id", cardID.toString());
    cardID++;  
    $('#cards').append(card);
    }
}

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
      document.getElementById('totaltroops').innerHTML = "Has this many troop in total: " + currPlayerInfo.totalNumberTroops;

      document.getElementById('myModal').style.display = "block";
    }

    document.getElementById("closer").onclick = function() {
      document.getElementById('myModal').style.display = "none";
    }
  }
}

let count = 0;
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
