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

document.getElementById("gameField").style.display = "none";
document.getElementById("menuField").style.display = "none";

let conn;
let myId;
let myName;
let players = [];
let idToName = [];
let nameToId = [];
let colors = [];
const $maker = $("#maker");
let availableForClaim = [];
let phase;
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
    	phase = "setup";
//    	activateDropDown(2);
//    	replaceField();
//    	replaceTransferListField();
//    	//changePlayerImage(player2, "white", "blue");
//
//    	  // populateTransferList(10);
//    	// addcard();
//    	// addcard();
//    	// addcard();
//    	$("#transferconfirm").on("click", confirmTransfer);
//    	$("#diceconfirm").on("click", confirmDice);
//    	$("#turnInCards").on( "click", turnInCards);
//    	console.log($(".card"));
//    	$('.card').click(function() {
//    		console.log(this.style.borderColor);
//    			console.log(this.style.borderStyle);
//    	if (this.style.borderStyle !== "solid") {
//        this.style.borderStyle = "solid";
//       	this.style.borderColor = "black";
//       	console.log("if");
//       	   }
//       else {
//       		console.log("else");
//       		 this.style.borderStyle = "none";
//       		this.style.borderColor = "none";
//       }
//    });
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
            make_selection(data.movePlayer, data.territoryId);
            break;
          case MOVE_TYPES.SETUP_REINFORCE:
        	console.log(data.movePlayer);
            make_selection(data.movePlayer, data.territoryId);
            break;
        }
        break;

      case MESSAGE_TYPE.VALID_ACTIONS:
        switch(data.moveType) {
          case MOVE_TYPES.SETUP:
          	document.getElementById("phase").innerHTML = "Select Territories";
          	if (data.playerId == myId) {
          		document.getElementById("turn").innerHTML = "Your Turn";          		
          	} else {
          		document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
          	}
          	if (data.playerId == myId) {
              availableForClaim = JSON.parse(data.selectable);
              map.addListener("clickMapObject", select_territory);
              let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.SETUP, "playerId": myId, "territoryId": availableForClaim[0]};
              conn.send(JSON.stringify(mess));
          	}
            break;

          case MOVE_TYPES.SETUP_REINFORCE:
            document.getElementById("phase").innerHTML = "Reinforce Territories";
          	if (data.playerId == myId) {
          		document.getElementById("turn").innerHTML = "Your Turn";          		
          	} else {
          		document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
          	}
          	if (data.playerId == myId) {
          	  phase = "setup_reinforce"
          	  console.log(data);
              availableForClaim = JSON.parse(data.territories);
              console.log(availableForClaim);
              map.addListener("clickMapObject", bolster_territory);
          	}
            break;
        }
        break;
    }
  };
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