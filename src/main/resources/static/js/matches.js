

const MESSAGE_TYPE = {
  CONNECT: 0,
  REMOVE: 1,
  START: 2,
  JOIN: 3,
  ADD: 4,
  CREATE: 5,
  CHECK: 6,
  CHANGE: 7,
  DESTROY: 8
};

let conn;
let myId;
const $maker = $("#maker");
let matches = {};

const setup_matches = () => {

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
        let Cindex = matches[data.oldGameId].indexOf(data.playerId);
    	if (matches[data.oldGameId].length == 1) {
      	  matches[data.oldGameId] = [];
      	} else {
      	  matches[data.oldGameId].splice(Cindex, 1);
      	}
        break;

      case MESSAGE_TYPE.REMOVE:
    	let Rindex = matches[data.gameId].indexOf(data.playerId);
    	if (matches[data.gameId].length == 1) {
    	  matches[data.gameId] = [];
    	} else {
    	  matches[data.gameId].splice(Rindex, 1);
    	}
		break;

      case MESSAGE_TYPE.ADD:
        if (!matches[data.gameId].includes(data.playerId)) {
    	    matches[data.gameId].push(data.playerId);
    	}
    	break;

      case MESSAGE_TYPE.CREATE:
   		let game = document.createElement("BUTTON");
   		game.id = data.gameId;
   		game.name = data.matchName;
   		document.getElementById("matches").appendChild(game);
   		document.getElementById(game.id).innerHTML = data.matchName + "'s Game: " + data.playerNum + "/" + data.lobbySize;
   		document.getElementById(game.id).value = data.lobbySize;

   		if (matches[game.id] == null) {
   			matches[game.id] = [];
   		}

		if (!matches[game.id].includes(data.playerList.player0)
				&& data.playerList.player0 != undefined) {
			matches[game.id].push(data.playerList.player0)
		}
		if (!matches[game.id].includes(data.playerList.player1)
				&& data.playerList.player1 != undefined) {
			matches[game.id].push(data.playerList.player1)
		}
		if (!matches[game.id].includes(data.playerList.player2)
				&& data.playerList.player2 != undefined) {
			matches[game.id].push(data.playerList.player2)
		}
		if (!matches[game.id].includes(data.playerList.player3)
				&& data.playerList.player3 != undefined) {
			matches[game.id].push(data.playerList.player3)
		}
		if (!matches[game.id].includes(data.playerList.player4)
				&& data.playerList.player4 != undefined) {
			matches[game.id].push(data.playerList.player4)
		}
		if (!matches[game.id].includes(data.playerList.player5)
				&& data.playerList.player5 != undefined) {
			matches[game.id].push(data.playerList.player5)
		}

   		game.onclick = join_match;
    	break;

      case MESSAGE_TYPE.CHECK:
        if (matches[data.gameId].length == document.getElementById(data.gameId).value) {
          let mess = {"type" : MESSAGE_TYPE.START, "gameId" : data.gameId};
          conn.send(JSON.stringify(mess));
        }
        break;

      case MESSAGE_TYPE.START:
        document.getElementById("game").submit();
        break;

      case MESSAGE_TYPE.DESTROY:
        $("#" + data.gameId).remove();
        matches[data.gameId] = null;
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

const create_match = event => {
	event.preventDefault();
	let mess = {"type" : MESSAGE_TYPE.CREATE, "gameId" : guid(), "lobbySize" : document.getElementById("playerNum").value, "matchName" : document.getElementById("name").value}
	conn.send(JSON.stringify(mess));
}

const join_match = event => {
	event.preventDefault();
	let game = event.target;
	let mess = {"type" : MESSAGE_TYPE.JOIN, "playerId" : myId, "gameId" : game.id};
	conn.send(JSON.stringify(mess));
}

$(document).ready(function() {
  setup_matches();
});

$maker.click(create_match);