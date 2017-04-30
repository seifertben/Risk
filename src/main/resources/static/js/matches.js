$(window).keydown(function(event){
  if (event.keyCode == 13) {
    event.preventDefault();
    return false;
  }
});

const MESSAGE_TYPE = {
  CONNECT: 0,
  REMOVE: 1,
  START: 2,
  JOIN: 3,
  CREATE: 4,
  CHANGE: 5,
  DESTROY: 6,
  WINNER: 7,
  LOSER: 8,
  HANDOUT_CARD: 9,
  NO_CARDS_LEFT: 10,
  PREVIOUS_ACTION: 11,
  VALID_ACTIONS: 12,
  ERROR: 13,
  MOVE: 14,
  MESSAGE: 15
};

const MOVE_TYPES = {
  SETUP: 0,
  SETUP_REINFORCE: 1,
  TURN_IN_CARD: 2,
  REINFORCE: 3,
  CHOOSE_ATTACK_DIE: 4,
  CHOOSE_DEFEND_DIE: 5,
  CLAIM_TERRITORY: 6,
  MOVE_TROOPS: 7,
  SKIP: 8
};

let conn;
let myId;
let myName;
let players = [];
let idToName = [];
let nameToId = [];
let colors = [];
const $maker = $("#maker");
let availableForClaim = [];
let terToSol = [];
let terToPlace = new Map();
let colorMap = new Map();
let phase;
let bolstering;
let placeMax;
let placed;
let myCards = [];
let attackFrom;
let attackTo;
let terToDie;
let terToTar;
let attackables;
let defending;

const setup_matches = () => {

  //conn = new WebSocket("ws://107.170.49.223/matches");
  conn = new WebSocket("ws://localhost:4567/matches");
  conn.onerror = err => {
    console.log('Connection error:', err);
  };

  conn.onmessage = msg => {
    const data = JSON.parse(msg.data);
    switch (data.type) {
      default:
        console.log('Unknown message type!', data.type);
        break;

      case MESSAGE_TYPE.CONNECT:
        myId = data.id;
        break;

      case MESSAGE_TYPE.CHANGE:
        document.getElementById(data.oldGameId).innerHTML = data.oldMatchName + ": " + data.oldPlayerNum + "/" + data.oldLobbySize;
        document.getElementById(data.newGameId).innerHTML = data.newMatchName + ": " + data.newPlayerNum + "/" + data.newLobbySize;
        break;

      case MESSAGE_TYPE.REMOVE:
        document.getElementById(data.gameId).innerHTML = data.matchName + ": " + data.playerNum + "/" + data.lobbySize;
        break;

      case MESSAGE_TYPE.CREATE:
        let game = document.createElement("BUTTON");
        game.id = data.gameId;
        game.name = data.matchName;
        document.getElementById("matches").appendChild(game);
        document.getElementById(game.id).innerHTML = data.matchName + ": " + data.playerNum + "/" + data.lobbySize;
        document.getElementById(game.id).value = data.lobbySize;
        game.onclick = join_match;
        break;

      case MESSAGE_TYPE.START:
        document.getElementById("gameField").style.display = "inline";
        document.getElementById("menuField").style.display = "none";
        document.getElementById(data.gameId).remove();
        players.push(data.player0id);
        idToName[data.player0id] = data.player0name;
        colors[data.player0id] = "red";
        players.push(data.player1id);
        idToName[data.player1id] = data.player1name;
        colors[data.player1id] = "blue";
        if (data.player2id != null) {
            players.push(data.player2id);
            idToName[data.player2id] = data.player2name;
            colors[data.player2id] = "green";
        }
        if (data.player3id != null) {
            players.push(data.player3id);
            idToName[data.player3id] = data.player3name;
            colors[data.player3id] = "purple";
        }
        if (data.player4id != null) {
            players.push(data.player4id);
            idToName[data.player4id] = data.player4name;
            colors[data.player4id] = "orange";
        }
        if (data.player5id != null) {
            players.push(data.player5.id);
            idToName[data.player5id] = data.player5name;
            colors[data.player5id] = "yellow";
        }
        createPlayer(data.playerNum);
    	setUp();

      //set colors for chat.
      for(i=0; i<players.length; i++){
        colorMap.set(players[i], colors[players[i]]);
      }

      //Set name message for header.
      let h1 = document.getElementsByTagName('h1').item(0);
      h1.innerHTML += ", " + myName;


    	phase = "setup";
        break;

      case MESSAGE_TYPE.DESTROY:
        $("#" + data.gameId).remove();
        break;

      case MESSAGE_TYPE.MESSAGE:
        getMessage(data.playerId, data.message);
        break;

      case MESSAGE_TYPE.PREVIOUS_ACTION:
        switch(data.moveType){
          case MOVE_TYPES.SETUP:
        	document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Just Selected " + idToData[data.territoryId].name;
            make_selection(data.movePlayer, data.territoryId);
            break;
          case MOVE_TYPES.SETUP_REINFORCE:
            document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Just Bolstered " + idToData[data.territoryId].name;
            make_selection(data.movePlayer, data.territoryId);
            break;
          case MOVE_TYPES.REINFORCE:
        	document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Bolstered Their Territories";
            document.getElementById("bolsters").style.display = "none";
            document.getElementById("selecting").style.display = "none";
        	make_bolster(data.movePlayer, JSON.parse(data.territories));
        	break;
          case MOVE_TYPES.CHOOSE_ATTACK_DIE:
            document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Is Attacking <b>" 
            + idToData[data.attackTo].name + "</b> from <b>" + idToData[data.attackFrom].name + "</b>";
          	let result = " ";
          	let roll = JSON.parse(data.roll);
          	for (let index = 0; index < roll.length; index++) {
          	  result += roll[index] + " ";
          	}
            document.getElementById("bolsters").innerHTML = idToName[data.movePlayer] + " Rolled " + result + "<br>";
        	break;
          case MOVE_TYPES.CHOOSE_DEFEND_DIE:
        	let def = " ";
        	let stall = JSON.parse(data.roll);
          	for (let index = 0; index < stall.length; index++) {
              def += stall[index] + " ";
            }
            document.getElementById("bolsters").innerHTML += idToName[data.defender] + "</b> Rolled " + def + "<br><b>"
            + idToName[data.attacker] + "</b> Lost " + data.attackerTroopsLost + " Troops<br><b>"
            + idToName[data.defender] + "</b> Lost " + data.defenderTroopsLost + " Troops<br>";
            if (data.defenderLostTerritory) {
              document.getElementById("prevMove").innerHTML = 
            	  "<b>" + idToName[data.attacker] + "</b> has Claimed " + idToData[data.defendTerritory].name + "!<br>";
            }
            changeTerritoryStatus(data.attacker, -1 * data.attackerTroopsLost,
                    idToData[data.defendTerritory], colors[data.attacker], colors[data.attacker]);
            changeTerritoryStatus(data.defender, -1 * data.defenderTroopsLost,
                    idToData[data.defendTerritory], colors[data.attacker], colors[data.attacker]);
        }
        break;

      case MESSAGE_TYPE.VALID_ACTIONS:
        switch(data.moveType) {
          case MOVE_TYPES.SETUP:
          	document.getElementById("phase").innerHTML = "Select Territories";
          	if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
          		document.getElementById("turn").innerHTML = "Your Turn";          		
          	} else {
               document.getElementById("turn").style.fontWeight = "normal";
          		document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
          	}
          	if (data.playerId == myId) {
              availableForClaim = JSON.parse(data.selectable);
              map.addListener("clickMapObject", select_territory);
              let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.SETUP, "playerId": myId, "territoryId": availableForClaim[0]};
              conn.send(JSON.stringify(mess));
              availableForClaim = [];
          	}
            break;

          case MOVE_TYPES.SETUP_REINFORCE:
            document.getElementById("phase").innerHTML = "Bolster Territories";
          	if (data.playerId == myId) {
                document.getElementById("turn").style.fontWeight = "bold";
          		document.getElementById("turn").innerHTML = "Your Turn";          		
          	} else {
                document.getElementById("turn").style.fontWeight = "normal";
          		document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
          	}
          	if (data.playerId == myId) {
          	  phase = "setup_reinforce";
              document.getElementById("bolsters").innerHTML = data.troopsToPlace + " Troops Left to Place";  
              availableForClaim = JSON.parse(data.territories);
              map.addListener("clickMapObject", select_territory);
              if (data.troopsToPlace > 0) {
                let mess = {"type": MESSAGE_TYPE.MOVE,
                "moveType": MOVE_TYPES.SETUP_REINFORCE,
                "playerId": myId, 
                "territoryId": availableForClaim[0]
              };
              
              conn.send(JSON.stringify(mess));
              availableForClaim = [];
              }
          	}
            break;

          case MOVE_TYPES.REINFORCE:
            document.getElementById("phase").innerHTML = "Prepare for Battle!";

        	if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn";
        	  document.getElementById("bolsters").style.display = "inline";  
              document.getElementById("phase").innerHTML = "Prepare for Battle!";
        	  document.getElementById("bolsters").style.display = "inline";  
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
              document.getElementById("n").appendChild(selecting);
           	  document.getElementById("n").appendChild(reinforcer);
              document.getElementById("n").appendChild(deinforcer);
              document.getElementById("n").appendChild(confirm);
              reinforcer.onclick = place_troop;
              deinforcer.onclick = remove_troop;
              confirm.onclick = confirm_move;
              terToPlace = new Map();
              placeMax = data.troopsToPlace;
              placed = 0;
        	  phase = "reinforce";
              document.getElementById("bolsters").innerHTML = data.troopsToPlace + " Troops Left to Place";  
              availableForClaim = JSON.parse(data.territories);
              map.addListener("clickMapObject", select_territory);
        	} else {
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
              document.getElementById("bolsters").innerHTML = idToName[data.playerId] + " is Placing Reinforcements";  
        	}
            break;

          case MOVE_TYPES.TURN_IN_CARD:
          	if (data.playerId == myId) {
                document.getElementById("turn").style.fontWeight = "bold";
        		document.getElementById("turn").innerHTML = "Your Turn";          		
        	} else {
              document.getElementById("turn").style.fontWeight = "normal";
        		document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
        	}
        	
        	break;
          case MOVE_TYPES.CHOOSE_ATTACK_DIE:
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn";        
              phase = "attacking";
              attackables = [];
        	  document.getElementById("bolsters").style.display = "inline";
              document.getElementById("bolsters").innerHTML = "Which of your Territories is going to Attack?<br>";
              terToDie = JSON.parse(data.maxDieRoll);
              terToTar = JSON.parse(data.whoCanAttack);
              for (ter in terToDie) {
                availableForClaim.push(ter);
              }
              $("#attack").show();
              document.getElementById("attack").onclick = attack_territory;
              $("#resetAttackMove").show();
              map.addListener("clickMapObject", select_territory);
            } else {
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
           	}
            break;
          case MOVE_TYPES.CHOOSE_DEFEND_DIE:
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            if (data.playerId == myId) {
              document.getElementById("phase").innerHTML = "Defend Yourself!";
              defending = data.defendTerritory;
              document.getElementById("bolsters").style.display = "inline";
              document.getElementById("attacking").innerHTML = "You Are Under Attack! Select Dice Number to Defend With!<br>";
              document.getElementById("attacking").style.display = "inline";
              document.getElementById("defend").style.display = "inline";
              let sideNav = $("#n");
              let dice = "";
              for (let index = 1; index <= data.maxDieRoll; index++) {
            	if (index == data.maxDieRoll) {
                  dice += "<option value=" + index.toString() + " selected='selected'>" + index.toString() + "</option>";
            	} else {
                  dice += "<option value=" + index.toString() + ">" + index.toString() + "</option>";
            	}
              }
              sideNav.append("<select id='diceChoice'>" + dice + "</select>");
              document.getElementById("defend").onclick = defend_territory;
            }
        	break;
          case MOVE_TYPES.MOVE_TROOPS:
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            break;
        }
        break;
   
    }
  };
}

function defend_territory() {
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CHOOSE_DEFEND_DIE,
		  "playerId": myId, "numberDieToRoll": document.getElementById("diceChoice").value};
  document.getElementById("diceChoice").remove();
  document.getElementById("defend").style.display = "none";
  document.getElementById("attacking").style.display = "none";
  conn.send(JSON.stringify(mess));
}

function attack_territory() {
  if (attackFrom != null && attackTo != null) {
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CHOOSE_ATTACK_DIE, 
    		"playerId": myId, "attackTerritory": attackFrom, "defendTerritory": attackTo, 
    		"numberDieToRoll": document.getElementById("diceChoice").value};
    document.getElementById("attack").style.display = "none";
    document.getElementById("attacking").style.display = "none";
    document.getElementById("resetAttackMove").style.display = "none";
    document.getElementById("diceChoice").remove();
    conn.send(JSON.stringify(mess));
  }
}

const skip_phase = event => {
  event.preventDefault();
  if (phase == "turnin" || phase == "moveTroops") {
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.SKIP, "playerId": myId};
    conn.send(JSON.stringify(mess));
  }
}

const confirm_move = event => {
  event.preventDefault();
  if (phase == "reinforce" && placed == placeMax) {
	sparcify();
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.REINFORCE, "playerId": myId, "territories": Array.from(terToPlace)};
    document.getElementById("selecting").style.display = "none";
    document.getElementById("reinforcer").remove();
    document.getElementById("deinforcer").remove();
    document.getElementById("confirm").remove(); 
    availableForClaim = [];
    conn.send(JSON.stringify(mess));
  }
}

function guid() {
  function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
      .toString(16)
      .substring(1);
  }
  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
    s4() + '-' + s4() + s4() + s4();
}

window.onkeyup = function(e) {
  var key = e.keyCode ? e.keyCode : e.which;
     if (key == 13 && myName == null && document.getElementById("nameInput").value != "") {
       myName = document.getElementById("nameInput").value;
       document.getElementById("nameField").style.display = "none";
       document.getElementById("menuField").style.display = "inline";
     }
  }

const create_match = event => {
  event.preventDefault();
  let mess = {"type" : MESSAGE_TYPE.CREATE, "gameId" : guid(),
      "lobbySize" : document.getElementById("playerNum").value, "matchName" : document.getElementById("name").value}

  document.getElementById("name").value = "";
  conn.send(JSON.stringify(mess));
}

const join_match = event => {
  event.preventDefault();
  let game = event.target;
  let mess = {"type" : MESSAGE_TYPE.JOIN, "playerId" : myId, "gameId" : game.id, "playerName" : myName};
  conn.send(JSON.stringify(mess));
}

$(document).ready(function() {
  setup_matches();
  

});

$maker.click(create_match);