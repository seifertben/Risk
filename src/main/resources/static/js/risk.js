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
let cardID = 0;
let prevMessage = undefined;
 // "url('')"
 let now = 0;
let imageList = ["url('https://s-media-cache-ak0.pinimg.com/originals/f6/ee/d2/f6eed2fd34fd0d5d8e17fe417c288dba.jpg')", "url('http://www.imgbase.info/images/safe-wallpapers/miscellaneous/historical/42834_historical_medieval_battle.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/originals/15/d0/41/15d041870d416ac9647203e96b4ab78b.jpg')","url('https://www.dal.ca/content/dam/dalhousie/images/fass/classics/Rome%20battle.jpg')", "url('http://i.imgur.com/yG3BO.jpg')"];
slideshow();
setInterval(slideshow, 6000);
//	activateDropDown(2);
//	replaceField();
//	replaceTransferListField();
//	//changePlayerImage(player2, "white", "blue");
//
//	addcard(2);
//	addcard(1);
//	  // populateTransferList(10);
//	// addcard();
//	// addcard();
//	// addcard();
//	$("#transferconfirm").on("click", confirmTransfer);
	$("#resetTransfer").on("click", resetTransfer);
	$("#diceconfirm").on("click", confirmDice);
	$("#turnInCards").on( "click", turnInCards);
	$('.card').click(function() {
	if (this.style.borderStyle !== "solid") {
   this.style.borderStyle = "solid";
  	this.style.borderColor = "black";
  	   }
  else {
  		 this.style.borderStyle = "none";
  		this.style.borderColor = "none";
  }
});
	// $("#messageForm").on('submit', function(e){
 //    // validation code here
 //      e.preventDefault();
 //    sendMessage();
 //  });
	function resetTransfer() {
		
	}
function confirmTransfer() {
	console.log($("#transferDropDownText").text());
	if ($("#transferDropDownText").text() !== "Select troops to move to conquered territory") {
		$("#transfergroup").hide();

	}
}
function confirmDice() {
	console.log($("#dropdown").text());
	if ($("#dropdown").text() !== "Select the amount of dice to roll") {
		$("#dropdowngroup").hide();

	}
}
function turnInCards() {
	$('#cards li').each(function() {
		if (this.style.borderStyle === "solid") {
			this.remove();
		}
    //this now refers to each li
    //do stuff to each
});

}
function changePlayerImage(id, backgroundColor, color) { 
	id.style.color = color;
	id.style.backgroundColor = backgroundColor;
}
function slideshow(){
	console.log('in!');
	let body = $('#background');
	console.log(imageList.length);
	 now = (now+1) % (imageList.length) ;
        body.css('background-image', imageList[now]);
}   

function setUp () {
	$sideNav = $('#n');
	$sideNav.append("<br><br><br>");
	$sideNav.append($("<p id = 'phase' class = 'alert'></p>"));
	$sideNav.append($("<p id = 'turn' class = 'alert'></p>"));
	$sideNav.append($("<p id = 'prevMove' class = 'alert'></p>"));
	$sideNav.append($("<p id = 'bolsters' class = 'alert'></p>"));
	$sideNav.append($("<p id = 'selecting' class = 'alert'></p>"));
	$sideNav.append($("<p id = 'attacking' class = 'alert'></p>"));
	$sideNav.append($("<button type='button' id = 'resetAttackMove'class='btn btn-danger'>Reset Attack Move</button>"));
	$sideNav.append($("<button type='button' id = 'attack'class='btn btn-danger'>Attack!</button>"));
	$sideNav.append($("<button type='button' id = 'defend'class='btn btn-danger'>Defend!</button>"));
	$sideNav.append($("<button type='button' id = 'skip'class='btn btn-danger'>End Turn</button>"));
	document.getElementById("resetAttackMove").onclick = reset_attack;
//	$sideNav.append($("<p id = 'attackerRollText'>Attacker's roll</p>"));
//	$sideNav.append($( "<ul id = 'blackRoll'>"));
//	$sideNav.append($( "<p id = 'defenderRollText'>Defender's roll</p>"));
//	 $sideNav.append($("<ul id = 'redRoll'>"));
//	 $sideNav.append("<br>");
//	  $sideNav.append($("<p id = 'attackLoss'>You lost 1 soldier</p>"));
//	   $sideNav.append($(" <p id = 'defendLoss'>Player 2 lost 1 soldier</p>"));
	   $('#bottom').append($("<button type='button' id = 'turnInCards' class='btn btn-success'>Turn In Cards</button>"));
       
 	   hideAll();
//	   changeAttackStatus("Player 1", "Player 2", "Russia");
//	   changeAttackersTerritoryInfo("Player 1", "Ontario", 10);
//	   changeDefendersTerritoryInfo("Player 2", "Western United States", 20);
//	   attackerLoss("Player 3", 2);
//	   defenderLoss("Player 3", 3);
//	   updateReinforcementMessage(10);
}

function createConquestTransferTroopsList() {
	const $parent = $("#n");
	const $outer = $("<div class='btn-group' id = 'transfergroup'></div>");
	$outer.append($("<button type='button' class='btn btn-primary' id = 'transferbutton'><span id = transferDropDownText>Select troops to move to conquered territory</span></button>"));
	$outer.append("<button type='button' class='btn btn-primary dropdown-toggle' id ='transferDropDown' data-toggle='dropdown'><span class='caret'></span></button>");
	$outer.append($("<ul class='dropdown-menu' id = 'transferOptions' role='menu'></ul>"));
	$outer.append($("<button type='button' class='btn btn-primary' id = 'transferconfirm'>Confirm Selection</button>"));
	$parent.append($outer);
}

function replaceTransferListField() {
	$(".transferOption").click(function(){
	    let id = "#" + "transferDropDownText";
        $(id).html(this.text);
    });
}
function populateTransferList(number) {
	$("#transferDropDownText").html("Select troops to move to conquered territory");
	for (let i = 1; i <= number; i++) {
		console.log("loop");
		let a = $("<a class = 'transferOption'></a>");
		a.html(i.toString());
		let li = $('<li class= "transferDrop"></li>');
		li.append(a);
		$("#transferOptions").append(li); 
	}
	console.log($("transferOptions"));
}

function updateTransferMessage(stage) {
	let $message = $("#end_of_turn_transfer");
	if (stage ===1) {
		$message.html("Select one territory if to transfer army from if you wish");
	}
	else if (stage == 2) {
		$message.html("Select territory to transfer your army to");
	}
	else if (stage ===3) {
		$message.hide();
	}
}

function changeAttackStatus(attackingPlayer, defendingPlayer, territory) {
	let message  = attackingPlayer + " is attacking " + defendingPlayer  + " in " + territory;
	if (attackingPlayer ===player) {
		 message  = "You are attacking " + defendingPlayer + " in " + territory;
	}
	 else if (defendingPlayer === player) {
		message = attackingPlayer + " is attacking you in " + territory;
	}
	$("#attackingWho").style.fontWeight = "bold";
	$("#attackingWho").html(message);
}

function changeAttackersTerritoryInfo(attackingPlayer,  territory, numSoldiers ) {
	let message  = attackingPlayer + " has " + numSoldiers  + " soldiers in " + territory;
	if (attackingPlayer ===player) {
		 message  = " You have " + numSoldiers  + " soldiers in " + territory;
	}	
	$("#attackerStatus").html(message);
	 console.log(message);
}

function changeDefendersTerritoryInfo(defendingPlayer,  territory, numSoldiers ) {
	let message  = defendingPlayer + " has " + numSoldiers  + " soldiers in " + territory;
	if (defendingPlayer ===player) {
		 message  = " You have " + numSoldiers  + " soldiers in " + territory;
	$("#defenderStatus").html(message);
	}	
}

function hideAll() {
	$("#attack").hide();
	$("#defend").hide();
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

    document.getElementById("attacking").style.display = "none";
}

function addcard(number) {
	let card;
	if (number ===1) {
		card = $("<li class = 'card'><div class='w3-card-4'><header class='w3-container-w3-blue'><h1>Card</h1></header><div class='w3-container'><p id = 'star'>*</p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>");
		card.attr("id", cardID.toString());
		cardID++;		
	  $('#cards').append(card);
	  console.log(card.attr('id'));
    }
    if (number ===2) {
    	card = $("<li class = 'card' ><div class='w3-card-4'><header class='w3-container-w3-blue'><h1>Card</h1></header><div class='w3-container'><p id = 'star'>**</p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>");
			card.attr("id", cardID.toString());
			  console.log(card.attr('id'));
		cardID++;  
	  $('#cards').append(card);
    }
    console.log(cardID);
}

function createPlayer(number) {
	const $sideNav = $("#n");
	for (let i = 0; i < number; i++) {
		let currDiv = $("<div></div>");
		let text =  $("<span></span>");
		currDiv.attr("class", "well well-sm");
		const player = i+1;
		let string = players[i];
		text.html(idToName[players[i]]);
		currDiv.attr("id", string);
		currDiv.append(text);
		$("#n").append(currDiv);
		document.getElementById(string).style.backgroundColor = colors[players[i]];
	}
}

function attackStatus() {
	const $div = $("#n");
	let $br = $("<br><br><br>");
	$div.append($br);
	let $attack = $("<p  id = 'attackingWho' class = 'alert'></p>");
	
	// $attack.html("You are attacking Player 2 in China!");
	$div.append($attack);
	let $attackerStatus = $("<p id = 'attackerStatus' class = 'alert'></p>");
	// $attackerStatus.html("You have 5 soldiers in Russia")
	$div.append($attackerStatus);
	let $defenderStatus = $("<p></p>");
	// $defenderStatus.html("Player 2 has 3 soldiers in China");
	// $defenderStatus.attr("id", "defenderStatus");
	$div.append($defenderStatus);
}

function createDropdown() {
	const $parent = $("#n");
	const $outer = $("<div class='btn-group' id = 'dropdowngroup'></div>");
	$outer.append($("<button type='button' class='btn btn-primary' id = 'dropdownbutton'><span id = dropdown>Select the amount of dice to roll</span></button>"));
	$outer.append("<button type='button' class='btn btn-primary dropdown-toggle' id ='soldierOptions' data-toggle='dropdown'><span class='caret'></span></button>");
	$outer.append($("<ul class='dropdown-menu' id = 'dieOptions' role='menu'></ul>"));
	$outer.append($("<button type='button' class='btn btn-primary' id = 'diceconfirm'>Confirm Selection</button>"));
	$parent.append($outer);
}

function activateDropDown(numbers) {
	$("#dropdown").html("Select the amount of dice to roll");
	$dropDown = $('#dieOptions');
	for (let i = 0; i<numbers; i++) {
		console.log("f");
		let option = i+1;
		let a = $("<a class = 'option'></a>");
		a.html(option);
		let li = $('<li class= "drop"></li>');
		li.append(a);
		$dropDown.append(li);
		if (i ==2) {
			break;
		}
	}
}

function replaceField() {
	$(".option").click(function(){
	    let id = "#" + "dropdown";
        $(id).html(this.text);
    });
}

function attackerLoss(attackingPlayer, losses) {
	let message = attackingPlayer + " lost " + losses + " soldiers."
	if (attackingPlayer === player) {
		message = "You lost " + losses + " soldiers."
 
	}
	$("#attackLoss").html(message);
}

function defenderLoss(defendingPlayer, losses) {
	let message = defendingPlayer + " lost " + losses + " soldiers."
	if (defendingPlayer === player) {
			message = "You lost " + losses + " soldiers."
 
	}
	$("#defendLoss").html(message);
}

function sendMessage() {
	


    let  message = $('#messageField').val();
    console.log("f");
    console.log(message);
    $('#messageField').val("");
    let mess = {"type" : MESSAGE_TYPE.MESSAGE, "message": message, "playerId": myId};
    conn.send(JSON.stringify(mess));


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


	console.log(colorMap.get(myId).toString());

	$("li:last-child").css("border", "1px solid " + colorMap.get(player).toString());
    $("li:last-child").css("border-left", "6px solid " + colorMap.get(player).toString());
    count = 0;
    blink("li:last-child");
    prevMessage = $li;
}

//listen for enter on messaging.
document.querySelector("#messageField").addEventListener("keyup", function (e) {
	console.log("Hi");
    let key = e.keyCode;
    console.log(key);
    if (key === 13) { 
      sendMessage();
    }
});
