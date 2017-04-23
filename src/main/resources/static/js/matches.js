//document.getElementById("myBtn").disabled = true;

$(window).keydown(function(evt){
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
  ENTER: 7
};

document.getElementById("gameField").style.display = "none";
document.getElementById("menuField").style.display = "none";

let conn;
let myId;
let myName;
const $maker = $("#maker");

const setup_matches = () => {

  conn = new WebSocket("ws://107.170.49.223/matches");
  //conn = new WebSocket("ws://localhost:4567/matches");
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
        let players = [];
        players.push(data.player0);
        players.push(data.player1);
        players.push(data.player2);
        if (data.player3 != null) {
            players.push(data.player3);     	
        }
        if (data.player4 != null) {
            players.push(data.player4);     	
        }
        if (data.player5 != null) {
            players.push(data.player5);     	
        }
        createPlayer(data.playerNum, players);
        break;

      case MESSAGE_TYPE.DESTROY:
        $("#" + data.gameId).remove();
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

	   if (key == 13 && myName == null) {
	       myName = document.getElementById("nameInput").value;
	       document.getElementById("nameField").style.display = "none";
	       document.getElementById("menuField").style.display = "inline";
	   }
	}

const create_match = event => {
	event.preventDefault();
	let mess = {"type" : MESSAGE_TYPE.CREATE, "gameId" : guid(), "lobbySize" : document.getElementById("playerNum").value, "matchName" : document.getElementById("name").value}
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