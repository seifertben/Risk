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
  MOVE: 14
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
        players.push(data.player0name);
        idToName[data.player0id] = data.player0name;
        nameToId[data.player0name] = data.player0id;
        colors[data.player0id] = "red";
        players.push(data.player1name);
        idToName[data.player1id] = data.player1name;
        nameToId[data.player1name] = data.player1id;
        colors[data.player1id] = "blue";
        if (data.player2id != null) {
            players.push(data.player2name);
            idToName[data.player2id] = data.player2name;
            nameToId[data.player2name] = data.player2id;
            colors[data.player2id] = "green";
        }
        if (data.player3id != null) {
            players.push(data.player3name);
            idToName[data.player3id] = data.player3name;
            nameToId[data.player3name] = data.player3id;
            colors[data.player3id] = "purple";
        }
        if (data.player4id != null) {
            players.push(data.player4name);
            idToName[data.player4id] = data.player4name;
            nameToId[data.player4name] = data.player4id;
            colors[data.player4id] = "orange";
        }
        if (data.player5id != null) {
            players.push(data.player5);
            idToName[data.player5id] = data.player5name;
            nameToId[data.player5name] = data.player5id;
            colors[data.player5id] = "yellow";
        }
        createPlayer(data.playerNum);
    	setUp();

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

//      case MESSAGE_TYPE.SELECT:
//        //PICK UP HERE
//      currentPlayer = data.playerId;
//      console.log(currentPlayer);
//        map.addListener("clickMapObject", select_territory);
//        break;
//        

      case MESSAGE_TYPE.PREVIOUS_ACTION:
        switch(data.moveType){
          case MOVE_TYPES.SETUP:
            make_selection(data.playerId, data.territoryId);
          break;
          }
        break;
      case MESSAGE_TYPE.VALID_ACTIONS:
        switch(data.moveType) {
          case MOVE_TYPES.SETUP:
            availableForClaim = JSON.parse(data.selectable);
            map.addListener("clickMapObject", select_territory);
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