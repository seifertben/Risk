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
  PING: 17,
  LEAVER: 18,
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
let seconds = 0;
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

  conn = new WebSocket("ws://107.170.49.223/matches");
  //conn = new WebSocket("ws://localhost:4567/matches");
  // SWAP THESE TWO TO GO FROM LOCAL VERSION TO SERVER VERSION

  conn.onerror = err => {
    console.log('Connection error:', err);
  };

  // Display leaver message
  conn.onclose = function(e) {
    playerLeftGameModal();
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

      // If someone leaves the game
      case MESSAGE_TYPE.LEAVER:
        playerLeftGameModal();
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
        $("#matches").append($("<button id="+ data.gameId +" name="+ data.matchName + " value=" + data.lobbySize + " class='regbtnlarge'>" 
          + data.matchName + ": " + data.playerNum + "/" + data.lobbySize + "</button>"));
        document.getElementById(data.gameId).onclick = join_match;
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
        let h1 = document.getElementById("inGame");
        h1.innerHTML += ", " + myName + "<br>";
        $('#background').css('background-image', 'none');
        createPlayer(data.playerNum);
    	setUp();

        // Set colors for chat.
        for(i=0; i<players.length; i++){
          colorMap.set(players[i], colors[players[i]]);
        }

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
        seconds += 5;
        break;

      // Update player info ingame
      case MESSAGE_TYPE.PLAYER_INFORMATION:
        let currPlayer = playerInfo[data.playerId];
        currPlayer.terrsTroops = data.terrsTroops;
        currPlayer.totalNumberTroops = data.totalNumberTroops;
        currPlayer.continents = data.continents;
        break;

      // When someone wins
      case MESSAGE_TYPE.WINNER:
    	winnerModal(data);
        break;

      // When someone loses
      case MESSAGE_TYPE.LOSER:
        document.getElementById(data.loser).style.backgroundColor = "grey";
        loserModal(data);
    	break;

      // Handle previous moves
      case MESSAGE_TYPE.PREVIOUS_ACTION:
        
        switch(data.moveType){

          // If someone selected a territory in setup
          case MOVE_TYPES.SETUP:
            color_reset();
          	$("#available").hide();
        	$("#clickList").hide();
        	$("#simSel").hide();
            updateLog("<b>" + idToName[data.movePlayer] + "</b> has Selected <b>" + idToData[data.territoryId].name + "</b>!");
            document.getElementById("bolsters").innerHTML = "Waiting to Select More Territories";
            make_selection(data.movePlayer, data.territoryId);
        	hideAll();
            break;

          // For a setup reinforcement
          case MOVE_TYPES.SETUP_REINFORCE:
          	$("#available").hide();
        	$("#clickList").hide();
        	$("#simSel").hide();
            color_reset();
            document.getElementById("bolsters").innerHTML = "Waiting to Place More Troops";
            updateLog("<b>" + idToName[data.movePlayer] + "</b> has Reinforced <b>" + idToData[data.territoryId].name + "</b>!");
            make_selection(data.movePlayer, data.territoryId);
        	hideAll();
            break;

          // For a normal reinforcement move
          case MOVE_TYPES.REINFORCE:
            color_reset();
        	$("#bolsters").hide();
        	$("#selecting").hide();
            let parsedTers = JSON.parse(data.territories);
            let bolts = "<b>" + idToName[data.movePlayer] + "</b> has Bolstered ";
            for (ter in parsedTers) {
              bolts += "<b>" + idToData[ter].name + "</b>, ";
            }
            bolts += "With More Troops!";
            updateLog(bolts);
        	make_bolster(data.movePlayer, JSON.parse(data.territories));
          	hideAll();
        	break;

          // When someone turns in a card
          case MOVE_TYPES.TURN_IN_CARD:
            color_reset();
            let cards = [];
            for (i in data.cards) {
              cards.push(i);
            }
              // checks which cards have been selected 
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

          // When someone attacks
          case MOVE_TYPES.CHOOSE_ATTACK_DIE:
            color_reset();
          	let result = " ";
          	let roll = JSON.parse(data.roll);
          	for (let index = 0; index < roll.length; index++) {
          	  result += roll[index] + " ";
          	}
          	updateLog("<b>" + idToName[data.movePlayer] + "</b> is Attacking <b>" + idToData[data.attackTo].name
          	  + "</b> from <b>" + idToData[data.attackFrom].name + "</b>!");
          	result = "<b>" + idToName[data.movePlayer] + "</b> Rolled " + result;
            let outer = terrToTerrToLine[data.attackFrom.toString()];
            let currLine = outer[data.attackTo.toString()];
            //line showing where player is attacking is changed to the attacking player's color
            changeLines(colors[data.movePlayer], currLine);
            map.dataProvider.zoomLevel = map.zoomLevel();
            map.dataProvider.zoomLatitude = map.zoomLatitude();
            map.dataProvider.zoomLongitude = map.zoomLongitude();
            map.validateData();
          	updateLog(result);
            break;

          // When someone defends
          case MOVE_TYPES.CHOOSE_DEFEND_DIE:
        	let def = " ";
        	let stall = JSON.parse(data.roll);
          	for (let index = 0; index < stall.length; index++) {
              def += stall[index] + " ";
            }

          	let defRoll = "<b>" + idToName[data.defender] + "</b> Rolled " + def;
          	updateLog(defRoll);

            let string = "<b>" + idToName[data.attacker] + "</b> Lost " + data.attackerTroopsLost
              + " Troops, and <b>" + idToName[data.defender] + "</b> Lost " + data.defenderTroopsLost + " Troops!";
            if (data.defenderLostTerritory) {
              if (data.attacker == myId) {
            	string = "<b>You</b> Have Conquered <b>" + idToData[data.defendTerritory].name + "</b>!";
                let outer = terrToTerrToLine[data.attackTerritory];
                if (outer != null) {
                  if (outer[data.defendTerritory] === attackLine) {
                    changeLines("black", attackLine);
                    map.dataProvider.zoomLevel = map.zoomLevel();
                    map.dataProvider.zoomLatitude = map.zoomLatitude();
                    map.dataProvider.zoomLongitude = map.zoomLongitude();
                    map.validateData();
                  }
                }
              } else {
              	string = "<b>" + idToName[data.attacker] + "</b> Has Conquered <b>" + idToData[data.defendTerritory].name + "</b>!";
                let outer = terrToTerrToLine[data.attackTerritory];
                if (outer != null) {
                  changeLines("black", outer[data.defendTerritory]);
                  map.dataProvider.zoomLevel = map.zoomLevel();
                  map.dataProvider.zoomLatitude = map.zoomLatitude();
                  map.dataProvider.zoomLongitude = map.zoomLongitude();
                  map.validateData();  
                }
              }
            } else if (data.attackerTroopsLost > data.defenderTroopsLost) {
              if (data.attacker == myId) {
                //resets line color to black
                let outer = terrToTerrToLine[data.attackTerritory];
                if (outer != null) {

                  if (outer[data.defendTerritory] === attackLine) {
                    changeLines("black", outer[data.defendTerritory]);                    
                    map.dataProvider.zoomLevel = map.zoomLevel();
                    map.dataProvider.zoomLatitude = map.zoomLatitude();
                    map.dataProvider.zoomLongitude = map.zoomLongitude();
                    map.validateData();
                  }
                }
              } else {
                //resets lines color to black
                let outer = terrToTerrToLine[data.attackTerritory];
                if (outer != null) {
                  changeLines("black", outer[data.defendTerritory]);
                  map.dataProvider.zoomLevel = map.zoomLevel();
                  map.dataProvider.zoomLatitude = map.zoomLatitude();
                  map.dataProvider.zoomLongitude = map.zoomLongitude();
                  map.validateData();
                }
              }
            } else {
              if (data.attacker == myId) {
                let outer = terrToTerrToLine[data.attackTerritory];
                if (outer != null) {
                  if (outer[data.defendTerritory] === attackLine) {
                    changeLines("black", outer[data.defendTerritory]);
                    map.dataProvider.zoomLevel = map.zoomLevel();
                    map.dataProvider.zoomLatitude = map.zoomLatitude();
                    map.dataProvider.zoomLongitude = map.zoomLongitude();
                    map.validateData();
                  }
                }
              } else {
                let outer = terrToTerrToLine[data.attackTerritory];
                if (outer != null) {
                  changeLines("black", outer[data.defendTerritory]);
                  map.dataProvider.zoomLevel = map.zoomLevel();
                  map.dataProvider.zoomLatitude = map.zoomLatitude();
                  map.dataProvider.zoomLongitude = map.zoomLongitude();
                  map.validateData();
                }
              }
            }

            updateLog(string);
            changeTerritoryStatus(idToName[data.attacker], -1 * data.attackerTroopsLost,
                    idToData[data.attackTerritory], colors[data.attacker]);
            changeTerritoryStatus(idToName[data.defender], -1 * data.defenderTroopsLost,
                    idToData[data.defendTerritory], colors[data.defender]);
        	hideAll();
            break;

          // When someone claims a territory
          case MOVE_TYPES.CLAIM_TERRITORY:
            color_reset();
        	updateLog("<b>" + idToName[data.movePlayer] + "</b> has Placed " + data.numberTroops 
        			+ " Troops at <b>" + idToData[data.claimedTerritory].name + "</b>!");
          	changeTerritoryStatus(idToName[data.movePlayer], data.numberTroops, 
        			idToData[data.claimedTerritory], colors[data.movePlayer]);
        	changeTerritoryStatus(idToName[data.movePlayer], -1 * data.numberTroops, 
        			idToData[data.claimedFrom], colors[data.movePlayer]);

        	hideAll();
        	break;

          // When someone moves troops
          case MOVE_TYPES.MOVE_TROOPS:
            color_reset();
          	updateLog("<b>" + idToName[data.movePlayer] + "</b> has Moved " + data.numberTroops 
        			+ " Troops from <b>" + idToData[data.moveFrom].name + "</b> to <b>" + idToData[data.moveTo].name + "</b>!");
            changeTerritoryStatus(idToName[data.movePlayer], -1 * data.numberTroops, 
              idToData[data.moveFrom], colors[data.movePlayer]);
            changeTerritoryStatus(idToName[data.movePlayer], data.numberTroops, 
              idToData[data.moveTo], colors[data.movePlayer]);
        	hideAll();
            break;

          // When someone skips a phase
          case MOVE_TYPES.SKIP:
            color_reset();
            let skip = data.skipType;
            if (skip === MOVE_TYPES.TURN_IN_CARD) {
              updateLog("<b>" + idToName[data.movePlayer] + "</b> has skipped turning in cards!");
            } else if (skip === MOVE_TYPES.CHOOSE_ATTACK_DIE) {
              updateLog("<b>" + idToName[data.movePlayer] + "</b> is done attacking!");
            } else {
              updateLog("<b>" + idToName[data.movePlayer] + "</b> has skipped moving troops!");
            }
            hideAll();
            break;
        }
        break;

      // Handle what actions are now available
      case MESSAGE_TYPE.VALID_ACTIONS:
        switch(data.moveType) {
          case MOVE_TYPES.SETUP:
          	document.getElementById("phase").innerHTML = "Select Territories";

            document.getElementById("bolsters").innerHTML = "Waiting to Select More Territories";
            $("#"+data.playerId).css("border-color", "gold");
            $("#"+data.playerId).css("border-width", "2px");
          	if (data.playerId == myId) {

              document.getElementById("bolsters").innerHTML = "Select a Territory";
              document.getElementById("turn").style.fontWeight = "bold";
          	  document.getElementById("turn").innerHTML = "Your Turn";
              addBlink($("#turn"));
              setTimeout(function() {
                removeBlink($("#turn")); 
              }, 2400);

              availableForClaim = JSON.parse(data.selectable);
              map.addListener("clickMapObject", select_territory);

              $("#available").show();
              document.getElementById("available").innerHTML = "You can Select:";
              $("#clickList").empty();
              selectTerritoriesInformation(availableForClaim);
              $("#clickList").show();
              $("#simSel").show();

          	} else {
              document.getElementById("turn").style.fontWeight = "normal";
          	  document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn to Select A Territory";
              addBlink($("#turn"));
              setTimeout(function() {
                removeBlink($("#turn")); 
              }, 2400);
          	}
            break;
            // when someone reinforces territory after all territories are claimed during setup
          case MOVE_TYPES.SETUP_REINFORCE:
            document.getElementById("phase").innerHTML = "Bolster Territories";

            $("#"+data.playerId).css("border-color", "gold");
            $("#"+data.playerId).css("border-width", "2px");
          	if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
          	  document.getElementById("turn").innerHTML = "Your Turn";
              addBlink($("#turn"));
              setTimeout(function() {
                removeBlink($("#turn")); 
              }, 2400);
              phase = "setup_reinforce";
              document.getElementById("bolsters").innerHTML = data.troopsToPlace + " Troops Left to Place";  
              availableForClaim = JSON.parse(data.territories);
              map.addListener("clickMapObject", select_territory);
              $("#available").show();
              document.getElementById("available").innerHTML = "You can Reinforce:";
              $("#clickList").empty();
              selectTerritoriesInformation(availableForClaim);
              $("#clickList").show();
              $("#simSel").show();
            } else {
              document.getElementById("turn").style.fontWeight = "normal";
          	  document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn To Reinforce 1 Territory";
              addBlink($("#turn"));
              setTimeout(function() {
                removeBlink($("#turn")); 
              }, 2400);
          	}
            break;
            //turn in card phase
          case MOVE_TYPES.TURN_IN_CARD:
            $("#"+data.playerId).css("border-color", "gold");
            $("#"+data.playerId).css("border-width", "2px");
            if (data.playerId == myId) {
              $('#cards li').each(function() {
                this.style.borderStyle = "none";
              });
              document.getElementById(myId).style.borderColor = 'gold';
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn"; 
              document.getElementById("phase").innerHTML = "Hand in Cards";             
              $("#skip").text("Skip Handing in Cards");
              $("#skip").show();
              document.getElementById("turnInCards").disabled = true;
              $("#turnInCards").addClass('disabled');
              $("#turnInCards").show();
              canClick = true;
              phase = "turnin";
            } else {
              document.getElementById("turn").style.fontWeight = "normal";
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn to Hand in Cards";
             
           }
           break;
           //reinforce phase
          case MOVE_TYPES.REINFORCE:
            document.getElementById("phase").innerHTML = "Prepare for Battle!";

            $("#"+data.playerId).css("border-color", "gold");
            $("#"+data.playerId).css("border-width", "2px");
        	if (data.playerId == myId) {
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn";
              addBlink($("#turn"));
              setTimeout(function() {
                removeBlink($("#turn")); 
              }, 2400);

              document.getElementById("bolsters").innerHTML = data.troopsToPlace + " Troops Left to Place";  
        	  document.getElementById("bolsters").style.display = "inline-block";  
              document.getElementById("phase").innerHTML = "Prepare for Battle!";
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
              terToPlace = new Map();
              placeMax = data.troopsToPlace;
              placed = 0;
        	  bolstering = null;
              phase = "reinforce";
              availableForClaim = JSON.parse(data.territories);
              map.addListener("clickMapObject", select_territory);
              $("#available").show();
              document.getElementById("available").innerHTML = "You can Reinforce:";
              $("#clickList").empty();
              selectTerritoriesInformation(availableForClaim);
              $("#clickList").show();
              $("#simSel").show();
        	} else {
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn to Place Reinforcements";
              addBlink($("#turn"));
              setTimeout(function() {
                removeBlink($("#turn")); 
              }, 2400);
              document.getElementById("bolsters").innerHTML = idToName[data.playerId] + " is Placing Reinforcements";
        	}
            break;

          //player chooses attack die
          case MOVE_TYPES.CHOOSE_ATTACK_DIE:

            $("#"+data.playerId).css("border-color", "gold");
            $("#"+data.playerId).css("border-width", "2px");
            document.getElementById("phase").innerHTML = "Prepare for Battle!";
            if (data.playerId == myId) {
              phase = "attacking";
              attackables = [];
              attackTo = null;
              attackFrom = null;
              document.getElementById("turn").style.fontWeight = "bold";
              document.getElementById("turn").innerHTML = "Your Turn";       
        	  $("#bolsters").show();
              document.getElementById("bolsters").innerHTML = "Which of your Territories is going to Attack?<br>";
              terToDie = JSON.parse(data.maxDieRoll);
              terToTar = JSON.parse(data.whoCanAttack);
              for (ter in terToDie) {
                availableForClaim.push(ter);
              }
              document.getElementById("available").innerHTML = "You Can Attack From:";
              $("#available").show();
              $("#clickList").empty();              
              selectTerritoriesInformation(availableForClaim);
              $("#clickList").show();
              $("#simSel").show();
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
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn to Attack";
           	}
            break;

          // Player choose defend die
          case MOVE_TYPES.CHOOSE_DEFEND_DIE:

            document.getElementById("phase").innerHTML = "Prepare for Battle!";

            // If I'm being attacked
            if (data.playerId == myId) {
              document.getElementById("phase").innerHTML = "Defend Yourself!";
              defending = data.defendTerritory;
              document.getElementById("attacking").innerHTML = "You Are Under Attack!<br> Select Dice Number to Defend With!<br>";

              $("#attacking").show();
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
              document.getElementById("defend").style.display = "inline-block";
              document.getElementById("defend").onclick = defend_territory;
            }
        	break;

          // Player claims territory
          case MOVE_TYPES.CLAIM_TERRITORY:

        	// Update player trun border and phase
            $("#"+data.playerId).css("border-color", "gold");
            $("#"+data.playerId).css("border-width", "2px");
            document.getElementById("phase").innerHTML = "Prepare for Battle!";

            // If it's my turn,
            if (data.playerId == myId) {

              // Setup controls for claiming a territory
              document.getElementById("bolsters").innerHTML = "Select troops to move from " 
            	  + idToData[data.territoryClaimingFrom].name + " to " + idToData[data.territoryToClaim].name;
              changeLines("black", attackLine);
              map.dataProvider.zoomLevel = map.zoomLevel();
              map.dataProvider.zoomLatitude = map.zoomLatitude();
              map.dataProvider.zoomLongitude = map.zoomLongitude();
              map.validateData();
              attackLine = null;

              $("#moveTroopsNumber").empty();
              claimed = data.territoryToClaim;
              claimedFrom = data.territoryClaimingFrom;
              for (let index = 1; index <= data.maxNumberTroops; index++) {
                if (index == data.maxNumberTroops) {
                  $("#moveTroopsNumber").append("<option value=" + index.toString()
                    + " selected='selected'>" + index.toString() + "</option>");
            	} else {
            	  $("#moveTroopsNumber").append("<option value=" + index.toString()
                    + ">" + index.toString() + "</option>");
            	}
              }
              $("#moveTroopsNumber").show()
              document.getElementById("claimTerritory").style.display = "inline-block";
            }
            break;

          //player is moving troops at end of turn
          case MOVE_TYPES.MOVE_TROOPS:

        	// Update player turn border and phase
            $("#"+data.playerId).css("border-color", "gold");
            $("#"+data.playerId).css("border-width", "2px");
            document.getElementById("phase").innerHTML = "Prepare for Battle!";

            // If it's my turn
            if (data.playerId == myId) {

              // Setup MoveTroops Controls
              phase = "move_troops";
              moveFrom = null;
              moveTo = null;
              moveables = [];
              document.getElementById("phase").innerHTML = "Move Your Troops!";
              document.getElementById("bolsters").innerHTML = "Select A Territory From Which to Move Troops<br>";     
              $("#moveTroops").disabled = true;
              $("#moveTroops").addClass('disabled');
              $("#moveTroopsNumber").empty();
              document.getElementById("moveTroops").style.display = "inline-block";
              $("#moveTroopsNumber").show();
              $("#resetMoveTroops").show();
              $("#skip").text("Skip Moving Troops");
              $("#skip").show();
              terrToReachableTerrs = JSON.parse(data.canMove);
              terrToMaxTroopsMove = JSON.parse(data.maxTroopsMove);
              for (ter in terrToMaxTroopsMove) {
                availableForClaim.push(ter);
              }
              document.getElementById("available").innerHTML = "You Can Move Troops From:";
              $("#available").show();
              $("#clickList").empty();              
              selectTerritoriesInformation(availableForClaim);
              $("#clickList").show();
              $("#simSel").show();
            } else {
              document.getElementById("turn").innerHTML = idToName[data.playerId] + "'s Turn to Transfer Troops";
            }
            break;
          }
        break;   
    }
  };
}
/**

**/
function color_reset() {
  for (index in players) {
    $("#"+players[index]).css("border-color", "white");
    $("#"+players[index]).css("border-width", "2px");
  }
}
/**
adds a new message to the update log showing the history of moves made
**/
function updateLog(string) {
	$li = $("<li class = 'chat'></li>");
	$li.html(string);

	$("#lastTurn").append($li);

	$li.css("border", "1px solid black");
    $li.css("border-left", "6px solid black");
    count = 0;
    blink($li);
    prevMove = $li;

    $("#lastTurn").scrollTop($("#lastTurn")[0].scrollHeight);
}
window.setInterval(function(){
  let mess = {"type": MESSAGE_TYPE.PING, "playerId": myId};
  conn.send(JSON.stringify(mess));
}, 5000);

/**
if card is clicked highlight/unhighlight border depending onwether it's already highlighted or not
**/

function clickOnCard(element) {
// if card can be clicked
  if (canClick) {
    // if card isn't hightlighted
    if (element.style.borderStyle !== "solid") {
        element.style.borderStyle = "solid";
        element.style.borderColor = "black";
        cardsClicked++;
        document.getElementById("turnInCards").disabled = false;
        $("#turnInCards").removeClass('disabled');
      } else {
        element.style.borderStyle = "none";
        element.style.borderColor = "none";
        cardsClicked--;
        if (cardsClicked == 0) {
          document.getElementById("turnInCards").disabled = true;
          $("#turnInCards").addClass('disabled');
        }
      }
    }
}
/**
turns in cards and makes cards disappears if the border is highlighted
**/
function turnInCards() {
  canClick = false;
  $("#turnInCards").hide();
  //checks each card if its highlighted 
  $('#cards li').each(function() {
    if (this.style.borderStyle === "solid") {
      let arr = this.className.split(" ");
      // if one star card
      if (arr[1] == "one") {
        myCards.push(1);
      } else {
        myCards.push(2);
      }
    }
  });
  //sends cards info back to backend
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.TURN_IN_CARD,
      "playerId": myId, "cards": myCards}; 
     conn.send(JSON.stringify(mess));
}

function claim_terr() {
  //sends message to backend
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CLAIM_TERRITORY,
		  "playerId": myId, "troopsToMove": document.getElementById("moveTroopsNumber").value,
		  "claimTerritory": claimed, "attackTerritory": claimedFrom};
  document.getElementById("claimTerritory").style.display = "none";
  //hides elements dealing with moving troops
  $("#moveTroopsNumber").hide();
  $("#moveTroopsNumber").empty();
  conn.send(JSON.stringify(mess));
}
/**
sends message to the backend once the attacker select a territory to attack
**/
function defend_territory() {
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CHOOSE_DEFEND_DIE,
		  "playerId": myId, "numberDieToRoll": document.getElementById("defenderNumberDie").value};
  $("#defenderNumberDie").hide();
  document.getElementById("defend").style.display = "none";
  document.getElementById("attacking").style.display = "none";
  conn.send(JSON.stringify(mess));
}
/**
sends information to backend that a player has attacked 
**/
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
    //resets attack from and attack to
    availableForClaim = [];
    attackFrom = null;
    attackTo = null;
    conn.send(JSON.stringify(mess));
  }
}
/**
when player moves troops from one territory to another 
message is sent to the backend
**/
function move_troops() {
  if (moveFrom != null && moveTo != null) {
    let mess = {"type":MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.MOVE_TROOPS,
      "playerId": myId, "moveFromTerritory": moveFrom, "moveToTerritory": moveTo, 
      "troopsToMove": document.getElementById("moveTroopsNumber").value};
    document.getElementById("moveTroops").style.display = "none";
    document.getElementById("resetMoveTroops").style.display = "none";
    //all elements dealing with moving troops are hidden
    $("#moveTroopsNumber").hide();
    $("#bolsters").hide();
    $("#skip").hide();
    availableForClaim = [];
    moveables = [];
    //variables are reset 
    moveFrom = null;
    moveTo = null;
    conn.send(JSON.stringify(mess));
  }
}

/**
when skip phase button is clicked depending on the phase variables are reset 
**/
const skip_phase = event => {
  event.preventDefault();
  // attacking phase
  if (phase == "attacking") {
      //resets line color to black
    for (let i = 0; i<attackableLines.length; i ++) {
          changeLines("black", attackableLines[i]);
      }
      //reset attackLine color to black
    if (attackLine != null) {
      changeLines("black", attackLine);
    }
    map.dataProvider.zoomLevel = map.zoomLevel();
    map.dataProvider.zoomLatitude = map.zoomLatitude();
    map.dataProvider.zoomLongitude = map.zoomLongitude();
    map.validateData();
  }
  //if its these three phases, reset everything 
  if (phase == "turnin" || phase == "move_troops" || phase == "attacking") {
	availableForClaim = [];
    moveables = [];
    moveFrom = null;
    moveTo = null;
    attackFrom = null;
    attackTo = null;
    attackLine = null;
    // tells backend player has reset 
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
    // resets card border to none
    $('#cards li').each(function() {
      if (this.style.borderStyle === "solid") {
        this.style.borderStyle = "none";
      }
    });
    conn.send(JSON.stringify(mess));
  }
}

/**
when confirm move has been clicked the information is sent to the backend
**/
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

/**
creates a unique game id when a game is created
**/
function guid() {
  function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
      .toString(16)
      .substring(1);
  }
  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
    s4() + '-' + s4() + s4() + s4();
}

/**
checks if enter has been fired when entering a name.
**/
window.onkeyup = function(e) {
  var key = e.keyCode ? e.keyCode : e.which;

  if (key == 13 && myName == null && document.getElementById("nameInput").value != "") {
    if (document.getElementById("nameInput").value.trim().length != 0) {
      myName = document.getElementById("nameInput").value;
      document.getElementById("nameField").style.display = "none";
      document.getElementById("menuField").style.display = "inline";
    }
  }
}

/**
Ask the back end to create a lobby.
**/
const create_match = event => {
  event.preventDefault();
  let name = document.getElementById("name").value;
  if (name.trim().length != 0) {
    let mess = {"type" : MESSAGE_TYPE.CREATE, "gameId" : guid(),
        "lobbySize" : document.getElementById("playerNum").value, "matchName" : document.getElementById("name").value}

    document.getElementById("name").value = "";
    conn.send(JSON.stringify(mess));
  }
}

/**
sends player information to the backend when a player joins the match
**/
const join_match = event => {
  event.preventDefault();
  let game = event.target;
  let mess = {"type" : MESSAGE_TYPE.JOIN, "playerId" : myId, "gameId" : game.id, "playerName" : myName};
  conn.send(JSON.stringify(mess));
}

$(document).ready(function() {
  //setups socket connection on load
  setup_matches();
});

/**
creates match when create match button is clicked
**/
$maker.click(create_match);
//adds blink class
function addBlink(element) {
  element.addClass("blink");
}
//removes blink class
function removeBlink(element) {
  element.removeClass("blink");

}
/**
mopdal that shows up if a player loses
**/
function loserModal(data) {
  if (data.loser == myId) {
    document.getElementById('loser').innerHTML = "YOU LOST!"; 
  } else {
    document.getElementById('loser').innerHTML = idToName[myId].toString() + " HAS BEEN DEFEATED";
  }
    document.getElementById('loserModal').style.display = "block";
    document.getElementById('loserMain').onclick = function () {
      window.location = "/risk";
    }
}
/**
modal that shows up when the game is over  
**/
function winnerModal(data) {
  if (data.winner == myId) {
    document.getElementById('winner').innerHTML = "YOU WON!"; 
  } else {
    document.getElementById('winner').innerHTML = idToName[myId].toString() + " WON!";
  }

  document.getElementById('winnerModal').style.display = "block";
  document.getElementById('winnerMain').onclick = function () {
    window.location = "/risk";
  }
}

/**
 * Function that sets up the information menu informing a player from which
 * territory and where he / she can attack.
 */
function attackTerritoryInformation(playerAttackInfo) {
    let s = "";
    for (terr in playerAttackInfo) {
      let x = playerAttackInfo[terr];
      s += terrToName[terr] + " can attack: ";
      for (let i = 0; i < x.length; i++) {
        if (i != x.length - 1) {
          s += terrToName[x[i]] + ", ";
        } else {
          s +=  "or " + terrToName[x[i]] + ".\n";
        }
      }
    }
}

/**
 * Function that sets up the information menu informing a player from which
 * territory and where he / she can transfer troops.
 */
function moveTroopsInformation(playerMoveTroopsInformation) {
  let s = "";
  for (terr in playerMoveTroopsInformation) {
    s += terrToName[terr] + " can transfer troops to: ";
    let x = playerMoveTroopsInformation[terr];
    for (let i = 0; i < x.length; i++) {
      if (i != x.length - 1) {
        s += terrToName[x[i]] + ", ";
      } else {
        s += "or " + terrToName[x[i]] + ".\n";
      }
    }
  }
}

/**
 * Function that sets up the information menu informing a player which territories
 * are available for selecting.
 */
function selectTerritoriesInformation(selectableTerrs) {
  for (let i = 0; i < selectableTerrs.length; i++) {
    $("#clickList").append("<option value=" + selectableTerrs[i]
      + ">" + terrToName[selectableTerrs[i]] + "</option>");
  }
}

function simClick() {
  let sel = document.getElementById("clickList");
  map.clickMapObject(idToData[sel.options[sel.selectedIndex].value]);
}
/**
if player leaves game after its over the player will be redirected 
**/
function playerLeftGameModal() {
  document.getElementById('gameOverModal').style.display = "block";
  document.getElementById('gameOverModal').onclick = function () {
    window.location = "/risk";
  }
}
