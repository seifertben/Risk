// Make sure hitting enter in forms
// does not reload the page
$(window).keydown(function(event){
  if (event.keyCode == 13) {
    event.preventDefault();
    return false;
  }
});

// Different Message Types
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
  MESSAGE: 15,
  PLAYER_INFORMATION: 16,
  PING: 17
};

// Different move types
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

// Datastructures, buttons,
// ids, and names
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
let moveables;
let defending;
let claimed;
let claimedFrom;
let terrToReachableTerrs;
let terrToMaxTroopsMove;
let moveFrom;
let moveTo;
let canClick = false;
let cardsClicked = 0;
let playerInfo = {};

// Set up socket connections
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

      // Set this session's id
      case MESSAGE_TYPE.CONNECT:
        myId = data.id;
        break;

      // Update when a player switches lobbies
      case MESSAGE_TYPE.CHANGE:
        document.getElementById(data.oldGameId).innerHTML = data.oldMatchName + ": " + data.oldPlayerNum + "/" + data.oldLobbySize;
        document.getElementById(data.newGameId).innerHTML = data.newMatchName + ": " + data.newPlayerNum + "/" + data.newLobbySize;
        break;

      // Remove a player from a lobby
      case MESSAGE_TYPE.REMOVE:
        document.getElementById(data.gameId).innerHTML = data.matchName + ": " + data.playerNum + "/" + data.lobbySize;
        break;

      // Create a lobby
      case MESSAGE_TYPE.CREATE:
        let game = document.createElement("BUTTON");
        game.id = data.gameId;
        game.name = data.matchName;
        document.getElementById("matches").appendChild(game);
        document.getElementById(game.id).innerHTML = data.matchName + ": " + data.playerNum + "/" + data.lobbySize;
        document.getElementById(game.id).value = data.lobbySize;
        game.onclick = join_match;
        break;

      // Start a game
      case MESSAGE_TYPE.START:

    	// Display the board and hide the lobby menu
        document.getElementById("gameField").style.display = "inline-block";
        document.getElementById("menuField").style.display = "none";
        document.getElementById(data.gameId).remove();

        // Add players to the sidenav
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

        // Start the game
        start = true;
        //document.getElementById("homeMute").style.display = "none";
        $('#background').css('background-image', 'none');
        createPlayer(data.playerNum);
    	setUp();

        // Set colors for chat.
        for(i=0; i<players.length; i++){
          colorMap.set(players[i], colors[players[i]]);
        }

        // Set name message for header.
        let h1 = document.getElementById("inGame");
        h1.innerHTML += ", " + myName;

        // The initial phase is the setup phase
    	phase = "setup";
        break;

      // Remove a lobby entirely
      case MESSAGE_TYPE.DESTROY:
        $("#" + data.gameId).remove();
        break;

      // Handle a chat message
      case MESSAGE_TYPE.MESSAGE:
        getMessage(data.playerId, data.message);
        break;

      // Hand out a card
      case MESSAGE_TYPE.HANDOUT_CARD:
        if (data.playerId == myId) {
          let card = data.cardValue;
          addcard(card);
        }
        break;

      case MESSAGE_TYPE.PING:
        break;

      // Update player info ingame
      case MESSAGE_TYPE.PLAYER_INFORMATION:
        let currPlayer = playerInfo[data.playerId];
        currPlayer.terrsTroops = data.terrsTroops;
        currPlayer.totalNumberTroops = data.totalNumberTroops;
        currPlayer.continents = data.continents;
        break;

      // Handle previous moves
      case MESSAGE_TYPE.PREVIOUS_ACTION:

        switch(data.moveType){

          // If someone selected a territory in setup
          case MOVE_TYPES.SETUP:

        	// Update the board
        	document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Just Selected " + idToData[data.territoryId].name;
            addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
            make_selection(data.movePlayer, data.territoryId);
        	hideAll();
            break;

          // For a setup reinforcement
          case MOVE_TYPES.SETUP_REINFORCE:
            document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Just Bolstered " + idToData[data.territoryId].name;
             addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
            make_selection(data.movePlayer, data.territoryId);
        	hideAll();
            break;

          // For a normal reinforcement move
          case MOVE_TYPES.REINFORCE:
        	document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Bolstered Their Territories";
           addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
            document.getElementById("bolsters").style.display = "none";
            document.getElementById("selecting").style.display = "none";
        	make_bolster(data.movePlayer, JSON.parse(data.territories));
          	hideAll();
        	break;
          case MOVE_TYPES.TURN_IN_CARD:
            let cards = [];
            for (i in data.cards) {
              cards.push(i);
            }
            $('#cards li').each(function() {
              if (this.style.borderStyle === "solid") {
                let arr = this.className.split(" ");
                if (arr[1] == "one") {
                  if (data.cards.includes(1)) {
                    let index = cards.indexOf(1);
                    cards.splice(index, 1);
                    this.remove();
                  } else {
                    this.style.borderStyle = "none";
                  }
                } else {
                  if (myCards.includes(2)) {
                    let index = cards.indexOf(2);
                    cards.splice(index, 1);
                    this.remove();
                  } else {
                    this.style.borderStyle = "none";
                  }
                }
              }
            });
            myCards = [];
            hideAll();
            break;

          case MOVE_TYPES.CHOOSE_ATTACK_DIE:
            document.getElementById("prevMove").innerHTML = idToName[data.movePlayer] + " Is Attacking <b>" 
            + idToData[data.attackTo].name + "</b> from <b>" + idToData[data.attackFrom].name + "</b>";
             addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
          	let result = " ";
          	let roll = JSON.parse(data.roll);
          	for (let index = 0; index < roll.length; index++) {
          	  result += roll[index] + " ";
          	}
            document.getElementById("bolsters").innerHTML = idToName[data.movePlayer] + " Rolled " + result + "<br>";
             addBlink($("#bolsters"));
               setTimeout(function() {
                   removeBlink($("#bolsters")); 
                }, 4000);
        	break;
          case MOVE_TYPES.CHOOSE_DEFEND_DIE:
        	let def = " ";
        	let stall = JSON.parse(data.roll);
          	for (let index = 0; index < stall.length; index++) {
              def += stall[index] + " ";
            }
            document.getElementById("bolsters").innerHTML += idToName[data.defender] + "</b> Rolled " + def + "<br><b>"
             addBlink($("#turn"));
               setTimeout(function() {
                   removeBlink($("#turn")); 
                }, 4000);
            + idToName[data.attacker] + "</b> Lost " + data.attackerTroopsLost + " Troop(s)<br> <b>"
            + idToName[data.defender] + "</b> Lost " + data.defenderTroopsLost + " Troop(s)<br>";
             addBlink($("#bolsters"));
               setTimeout(function() {
                   removeBlink($("#bolsters")); 
                }, 4000);
            if (data.defenderLostTerritory) {
              if (data.attacker == myId) {
                document.getElementById("prevMove").innerHTML = 
                  "<b>You</b> have Claimed " + idToData[data.defendTerritory].name + "!<br>";
                  addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
              } else {
                document.getElementById("prevMove").innerHTML = 
                  "<b>" + idToName[data.attacker] + "</b> has Claimed " + idToData[data.defendTerritory].name + "!<br>";
                  addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
              }
            } else if (data.attackerTroopsLost > data.defenderTroopsLost) {
              if (data.attacker == myId) {
                document.getElementById("prevMove").innerHTML = 
                    "<b>You</b> Lost the Battle!<br>";
                    addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
              } else {
                document.getElementById("prevMove").innerHTML = 
                    "<b>" + idToName[data.attacker] + "</b> Lost the Battle!<br>";
                    addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
              }
            } else {
                if (data.attacker == myId) {
                    document.getElementById("prevMove").innerHTML = 
                        "<b>You</b> Won the Battle!<br>";
                        addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
                  } else {
                    document.getElementById("prevMove").innerHTML = 
                        "<b>" + idToName[data.attacker] + "</b> Won the Battle!<br>";
                        addBlink($("#prevMove"));
               setTimeout(function() {
                   removeBlink($("#prevMove")); 
                }, 4000);
                  }
            }
            changeTerritoryStatus(idToName[data.attacker], -1 * data.attackerTroopsLost,
                    idToData[data.attackTerritory], colors[data.attacker], colors[data.attacker]);
            changeTerritoryStatus(idToName[data.defender], -1 * data.defenderTroopsLost,
                    idToData[data.defendTerritory], colors[data.defender], colors[data.defender]);
        	hideAll();
            break;
          case MOVE_TYPES.CLAIM_TERRITORY:
          	changeTerritoryStatus(idToName[data.movePlayer], data.numberTroops, 
        			idToData[data.claimedTerritory], colors[data.movePlayer], colors[data.movePlayer]);
        	changeTerritoryStatus(idToName[data.movePlayer], -1 * data.numberTroops, 
        			idToData[data.claimedFrom], colors[data.movePlayer], colors[data.movePlayer]);
        	hideAll();
        	break;
          case MOVE_TYPES.MOVE_TROOPS:
            changeTerritoryStatus(idToName[data.movePlayer], -1 * data.numberTroops, 
              idToData[data.moveFrom], colors[data.movePlayer], colors[data.movePlayer]);
            changeTerritoryStatus(idToName[data.movePlayer], data.numberTroops, 
              idToData[data.moveTo], colors[data.movePlayer], colors[data.movePlayer]);
        	hideAll();
            break;
        }
        break;

      case MESSAGE_TYPE.VALID_ACTIONS:
        switch(data.moveType) {
          case MOVE_TYPES.SETUP:
        	console.log("setup");
          	document.getElementById("phase").innerHTML = "Select Territories";
            addBlink($("#phase"));
            setTimeout(function() {
              removeBlink($("#phase")); 
            }, 4000);
          	if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
          	  document.getElementById("turn").innerHTML = "Your Turn";
              addBlink($("#turn"));
              setTimeout(function() {
                removeBlink($("#turn")); 
              }, 4000);

              availableForClaim = JSON.parse(data.selectable);
              map.addListener("clickMapObject", select_territory);
              // AUTOPLAY
//              let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.SETUP, "playerId": myId, "territoryId": availableForClaim[0]};
//              conn.send(JSON.stringify(mess));
//              availableForClaim = [];
          	} else {
              document.getElementById("turn").style.fontWeight = "normal";
          	  document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
                   addBlink($("#turn"));
               setTimeout(function() {
                   removeBlink($("#turn")); 
                }, 4000);
          	}
            break;

          case MOVE_TYPES.SETUP_REINFORCE:

          	console.log("setup reinforce");
            document.getElementById("phase").innerHTML = "Bolster Territories";
                 addBlink($("#turn"));
               setTimeout(function() {
                   removeBlink($("#turn")); 
                }, 4000);
          	if (data.playerId == myId) {
                document.getElementById("turn").style.fontWeight = "bold";
          		document.getElementById("turn").innerHTML = "Your Turn";
              addBlink($("#turn"));
               setTimeout(function() {
                   removeBlink($("#turn")); 
                }, 4000);
            	  phase = "setup_reinforce";
                  document.getElementById("bolsters").innerHTML = data.troopsToPlace + " Troops Left to Place";  
                  availableForClaim = JSON.parse(data.territories);
                  map.addListener("clickMapObject", select_territory);
                  // AUTOPLAY
//                 if (data.troopsToPlace > 0) {
//                   let mess = {"type": MESSAGE_TYPE.MOVE,
//                   "moveType": MOVE_TYPES.SETUP_REINFORCE,
//                   "playerId": myId, 
//                   "territoryId": availableForClaim[0]
//                 };
//                 
//                 conn.send(JSON.stringify(mess));
//                 availableForClaim = [];
//               }
                  } else {
                document.getElementById("turn").style.fontWeight = "normal";
          		document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
               addBlink($("#turn"));
               setTimeout(function() {
                   removeBlink($("#turn")); 
                }, 4000);
          	}
            break;

          case MOVE_TYPES.TURN_IN_CARD:
          	console.log("turn in");
            if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn"; 
              document.getElementById("phase").innerHTML = "Hand in Cards";             
              $("#skip").text("Skip Handing in Cards?");
              $("#skip").show();
              $("#turnInCards").disabled = true;
              $("#turnInCards").addClass('disabled');
              $("#turnInCards").show();
              //document.getElementById("turnInCards").onclick = turnInCards;
              canClick = true;
              phase = "turnin";
            } else {
              document.getElementById("turn").style.fontWeight = "normal";
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
             
           }
           break;

          case MOVE_TYPES.REINFORCE:
          	console.log("reinforce");
          	// REMOVE SKIP PHASE
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            addBlink($("#phase"));
               setTimeout(function() {
                   removeBlink($("#phase")); 
                }, 4000);

        	if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn";
              addBlink($("#turn"));
               setTimeout(function() {
                   removeBlink($("#turn")); 
                }, 4000);
        	    document.getElementById("bolsters").style.display = "inline";  
              document.getElementById("phase").innerHTML = "Prepare for Battle!";
        	    document.getElementById("bolsters").style.display = "inline";  
        	    document.getElementById("reinforcer").disabled = true;
              document.getElementById("deinforcer").disabled = true;
              document.getElementById("confirm").disabled = true;
              $("#reinforcer").addClass('disabled');
              $("#deinforcer").addClass('disabled');
              $("#confirm").addClass('disabled');
              document.getElementById("deinforcer").style.display = "inline";
              document.getElementById("confirm").style.display = "inline"; 
              document.getElementById("reinforcer").style.display = "inline";
              document.getElementById("deinforcer").style.display = "inline";
              document.getElementById("confirm").style.display = "inline"; 
        	    $("#selecting").show();
        	    document.getElementById("selecting").innerHTML = "Select A Territory to Reinforce";
              addBlink($("#selecting"));
               setTimeout(function() {
                   removeBlink($("#selecting")); 
                }, 4000);
              terToPlace = new Map();
              placeMax = data.troopsToPlace;
              placed = 0;
        	    bolstering = null;
              phase = "reinforce";
              document.getElementById("bolsters").innerHTML = data.troopsToPlace + " Troops Left to Place";  
              availableForClaim = JSON.parse(data.territories);
              map.addListener("clickMapObject", select_territory);
        	} else {
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
              addBlink($("#turn"));
               setTimeout(function() {
                   removeBlink($("#turn")); 
                }, 4000);
              document.getElementById("bolsters").innerHTML = idToName[data.playerId] + " is Placing Reinforcements";  
              addBlink($("#bolsters"));
               setTimeout(function() {
                   removeBlink($("#bolsters")); 
                }, 4000);
        	}
            break;

          case MOVE_TYPES.CHOOSE_ATTACK_DIE:

          	console.log("attack");
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            if (data.playerId == myId) {
              phase = "attacking";
              attackables = [];
              attackTo = null;
              attackFrom = null;
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn";       
        	    document.getElementById("bolsters").style.display = "inline";
              document.getElementById("bolsters").innerHTML = "Which of your Territories is going to Attack?<br>";
               addBlink($("#bolsters"));
               setTimeout(function() {
                   removeBlink($("#bolsters")); 
                }, 4000);
              terToDie = JSON.parse(data.maxDieRoll);
              terToTar = JSON.parse(data.whoCanAttack);
              for (ter in terToDie) {
                availableForClaim.push(ter);
              }
              $("#attack").disabled = true;
              $("#attack").addClass('disabled');
              $("#attack").show();
              $("#attackerNumberDie").empty();
              $("#attackerNumberDie").show();
              $("#resetAttackMove").show();
              $("#skip").text("Done Attacking?");
              $("#skip").show();
              document.getElementById("attack").onclick = attack_territory;
              map.addListener("clickMapObject", select_territory);
            } else {
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn";
           	}
            break;

          case MOVE_TYPES.CHOOSE_DEFEND_DIE:

          	console.log("defend");
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            if (data.playerId == myId) {
              document.getElementById("phase").innerHTML = "Defend Yourself!";
              addBlink($("#phase"));
               setTimeout(function() {
                   removeBlink($("#phase")); 
                }, 4000);
              defending = data.defendTerritory;
              document.getElementById("bolsters").style.display = "inline";
              document.getElementById("attacking").innerHTML = "You Are Under Attack! Select Dice Number to Defend With!<br>";
              addBlink($("#attacking"));
               setTimeout(function() {
                   removeBlink($("#attacking")); 
                }, 4000);
              document.getElementById("attacking").style.display = "inline";  
              $("#defenderNumberDie").empty();
              for (let index = 1; index <= data.maxDieRoll; index++) {
            	 if (index == data.maxDieRoll) {
                   $("#defenderNumberDie").append("<option value=" + 
                      index.toString() + " selected='selected'>" + index.toString() + "</option>");
            	 } else {
                   $("#defenderNumberDie").append("<option value=" + 
                      index.toString() + ">" + index.toString() + "</option>");
            	   }
              }
              $("#defenderNumberDie").show();
              document.getElementById("defend").style.display = "inline";
              document.getElementById("defend").onclick = defend_territory;
            }
        	break;

          case MOVE_TYPES.CLAIM_TERRITORY:

          	console.log("claim");
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            if (data.playerId == myId) {
              document.getElementById("bolsters").innerHTML = "Select troops to move from " 
            	  + idToData[data.territoryClaimingFrom].name + " to " + idToData[data.territoryToClaim].name;
              let nav = $("#gameUpdates");
              let troops = "";
              claimed = data.territoryToClaim;
              claimedFrom = data.territoryClaimingFrom;
              for (let index = 1; index <= data.maxNumberTroops; index++) {
            	if (index == data.maxNumberTroops) {
                  troops += "<option value=" + index.toString() + " selected='selected'>" + index.toString() + "</option>";
            	} else {
            	  troops += "<option value=" + index.toString() + ">" + index.toString() + "</option>";
            	}
              }
              nav.append("<select id='troopChoice'>" + troops + "</select>");
              document.getElementById("claimTerritory").style.display = "inline";

              
            }
            break;

          case MOVE_TYPES.MOVE_TROOPS:

          	console.log("move");
        	 document.getElementById("phase").innerHTML = "Prepare for Battle!";
        	 if (data.playerId == myId) {
             phase = "move_troops";
             moveFrom = null;
             moveTo = null;
             moveables = [];
             document.getElementById("phase").innerHTML = "Move Your Troops!";
             addBlink($("#phase"));
               setTimeout(function() {
                   removeBlink($("#phase")); 
                }, 4000);
             document.getElementById("bolsters").innerHTML = "Select A Territory From Which to Move Troops<br>";     
             $("#moveTroops").disabled = true;
             $("#moveTroops").addClass('disabled');
             document.getElementById("moveTroops").style.display = "inline";
             $("#moveTroopsNumber").empty();
             $("#moveTroopsNumber").show();
             $("#resetMoveTroops").show();
             $("#skip").text("Skip Moving Troops?");
             $("#skip").show();
             terrToReachableTerrs = JSON.parse(data.canMove);
             terrToMaxTroopsMove = JSON.parse(data.maxTroopsMove);
             for (ter in terrToMaxTroopsMove) {
                availableForClaim.push(ter);
             }
           }
         }
       break;   
    }
  };
}

window.setInterval(function(){
  let mess = {"type": MESSAGE_TYPE.PING, "playerId": myId};
  conn.send(JSON.stringify(mess));
}, 60000);

function clickOnCard(element) {
  if (canClick) {
    if (element.style.borderStyle !== "solid") {
        element.style.borderStyle = "solid";
        element.style.borderColor = "black";
        cardsClicked++;
        ("#turnInCards").disabled = false;
        $("#turnInCards").removeClass('disabled');
      } else {
        element.style.borderStyle = "none";
        element.style.borderColor = "none";
        cardsClicked--;
        if (cardsClicked == 0) {
          $("#turnInCards").disabled = true;
          $("#turnInCards").addClass('disabled');
        }
      }
    }
}

function turnInCards() {
  canClick = false;
  $("#turnInCards").hide();
  $('#cards li').each(function() {
    if (this.style.borderStyle === "solid") {
      let arr = this.className.split(" ");
      if (arr[1] == "one") {
        myCards.push(1);
      } else {
        myCards.push(2);
      }
    }
  });
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.TURN_IN_CARD,
      "playerId": myId, "cards": myCards}; 
     conn.send(JSON.stringify(mess));
}

function claim_terr() {
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CLAIM_TERRITORY,
		  "playerId": myId, "troopsToMove": document.getElementById("troopChoice").value,
		  "claimTerritory": claimed, "attackTerritory": claimedFrom};
  document.getElementById("troopChoice").remove();
  document.getElementById("claimTerritory").style.display = "none";
  conn.send(JSON.stringify(mess));
}

function defend_territory() {
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CHOOSE_DEFEND_DIE,
		  "playerId": myId, "numberDieToRoll": document.getElementById("defenderNumberDie").value};
  $("#defenderNumberDie").hide();
  document.getElementById("defend").style.display = "none";
  document.getElementById("attacking").style.display = "none";
  conn.send(JSON.stringify(mess));
}

function attack_territory() {
  if (attackFrom != null && attackTo != null) {
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CHOOSE_ATTACK_DIE, 
    		"playerId": myId, "attackTerritory": attackFrom, "defendTerritory": attackTo, 
    		"numberDieToRoll": document.getElementById("attackerNumberDie").value};
    document.getElementById("attack").style.display = "none";
    document.getElementById("attacking").style.display = "none";
    document.getElementById("resetAttackMove").style.display = "none";
    $("#attackerNumberDie").hide();
    $("#skip").hide();
    availableForClaim = [];
    attackFrom = null;
    attackTo = null;
    conn.send(JSON.stringify(mess));
  }
}

function move_troops() {
  if (moveFrom != null && moveTo != null) {
    let mess = {"type":MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.MOVE_TROOPS,
      "playerId": myId, "moveFromTerritory": moveFrom, "moveToTerritory": moveTo, 
      "troopsToMove": document.getElementById("moveTroopsNumber").value};
    document.getElementById("moveTroops").style.display = "none";
    document.getElementById("resetMoveTroops").style.display = "none";
    $("#moveTroopsNumber").hide();
    $("#skip").hide();
    availableForClaim = [];
    moveables = [];
    moveFrom = null;
    moveTo = null;
    conn.send(JSON.stringify(mess));
  }
}

const skip_phase = event => {
  event.preventDefault();
  if (phase == "turnin" || phase == "move_troops" || phase == "attacking") {
	  availableForClaim = [];
    moveables = [];
    moveFrom = null;
    moveTo = null;
    attackFrom = null;
    attackTo = null;
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.SKIP, "playerId": myId};
    $("#skip").hide();
    $("#turnInCards").hide();
    $("#resetMoveTroops").hide();

    document.getElementById("attack").style.display = "none";
    document.getElementById("resetAttackMove").style.display = "none";
    document.getElementById("reinforcer").style.display = "none";
    document.getElementById("deinforcer").style.display = "none";
    document.getElementById("confirm").style.display = "none";  
    document.getElementById("attacking").style.display = "none";
    document.getElementById("resetMoveTroops").style.display = "none";
    document.getElementById("claimTerritory").style.display = "none";
    document.getElementById("moveTroops").style.display = "none";
    $("#skip").hide();
    $("#attackerNumberDie").hide();
    $("#defenderNumberDie").hide();
    $("#moveTroopsNumber").hide();
    $('#cards li').each(function() {
      if (this.style.borderStyle === "solid") {
        this.style.borderStyle = "none";
      }
    });
    conn.send(JSON.stringify(mess));
  }
}

const confirm_move = event => {
  event.preventDefault();
  if (phase == "reinforce" && placed == placeMax) {
	sparcify();
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.REINFORCE, "playerId": myId, "territories": Array.from(terToPlace)};
    document.getElementById("selecting").style.display = "none";
    document.getElementById("reinforcer").style.display = "none";
    document.getElementById("deinforcer").style.display = "none";
    document.getElementById("confirm").style.display = "none";
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
function addBlink(element) {
  element.addClass("blink");
}
function removeBlink(element) {
  console.log(element.attr("id"));
  element.removeClass("blink");

}
