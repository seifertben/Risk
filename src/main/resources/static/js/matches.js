$(window).keydown(function(evt){
	if (event.keyCode == 13) {
		event.preventDefault();
		return false;
	}
});

const MESSAGE_TYPE = {
  SELECT: 0,
  SETUP_REINFORCE: 1,
  REINFORCE: 2,
  TURN_IN_CARD: 3,
  ATTACK: 4,
  DEFEND: 5,
  CLAIM_TERRITORY: 6,
  MOVE_TROOPS: 7,
  CONNECT: 8,
  REMOVE: 9,
  START: 10,
  JOIN: 11,
  CREATE: 12,
  CHANGE: 13,
  DESTROY: 14,
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
let currentPlayer;

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
        colors[data.player0name] = "red";
        players.push(data.player1name);
        idToName[data.player1id] = data.player1name;
        nameToId[data.player1name] = data.player1id;
        colors[data.player1name] = "blue";
        if (data.player2id != null) {
            players.push(data.player2name);
            idToName[data.player2id] = data.player2name;
            nameToId[data.player2name] = data.player2id;
            colors[data.player2name] = "green";
        }
        if (data.player3id != null) {
            players.push(data.player3name);
            idToName[data.player3id] = data.player3name;
            nameToId[data.player3name] = data.player3id;
            colors[data.player3name] = "purple";
        }
        if (data.player4id != null) {
            players.push(data.player4name);
            idToName[data.player4id] = data.player4name;
            nameToId[data.player4name] = data.player4id;
            colors[data.player4name] = "orange";
        }
        if (data.player5id != null) {
            players.push(data.player5);
            idToName[data.player5id] = data.player5name;
            nameToId[data.player5name] = data.player5id;
            colors[data.player5name] = "yellow";
        }
        createPlayer(data.playerNum, players);
        break;

      case MESSAGE_TYPE.DESTROY:
        $("#" + data.gameId).remove();
        break;

      case MESSAGE_TYPE.CLAIM_TERRITORY:
        currentPlayer = null;
        make_selection(data.playerId, data.territoryId);
        break;
      case MESSAGE_TYPE.SELECT:
    	  //PICK UP HERE
    	currentPlayer = data.playerId;
    	console.log(currentPlayer);
        map.addListener("clickMapObject", select_territory);
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