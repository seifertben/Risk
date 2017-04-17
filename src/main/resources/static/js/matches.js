

const MESSAGE_TYPE = {
  CONNECT: 0,
  REMOVE: 1,
  START: 2,
  JOIN: 3,
  ADD: 4,
  CREATE: 5,
  CHECK: 6,
  CHANGE: 7
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
        match[data.oldGameId].filter(function(i) {
        	return i != data.playerId;
        });
        break;

      case MESSAGE_TYPE.REMOVE:
		break;

      case MESSAGE_TYPE.ADD:
        if (!matches[data.gameId].includes(data.playerId)) {
    	    matches[data.gameId].push(data.playerId);
    	}
    	break;

      case MESSAGE_TYPE.CREATE:
   		let game = document.createElement("BUTTON");
   		game.id = data.gameId;

   		document.getElementById("matches").appendChild(game);  
   		document.getElementById(game.id).innerHTML = "New Game";
   		document.getElementById(game.id).value = document.getElementById("playerNum").value;

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
            let mess = {"type" : "2", "gameId" : data.gameId};
        	conn.send(JSON.stringify(mess));
        }
        break;

      case MESSAGE_TYPE.START:
        document.getElementById("game").submit();
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
	let mess = {"type" : "5", "gameId" : guid()}
	conn.send(JSON.stringify(mess));
}

const join_match = event => {
	event.preventDefault();
	let game = event.target;
	let mess = {"type" : "3", "playerId" : myId, "gameId" : game.id};
	conn.send(JSON.stringify(mess));
}

$(document).ready(function() {
  setup_matches();
});

$maker.click(create_match);