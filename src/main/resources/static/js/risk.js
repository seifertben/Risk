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
let start = false;
let prevMessage = undefined;
let body = $('#background');

 let now = -1;

let imageList = ["url('https://s-media-cache-ak0.pinimg.com/originals/f6/ee/d2/f6eed2fd34fd0d5d8e17fe417c288dba.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/originals/53/2c/22/532c224459ada029dfb2db7be6165cde.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/originals/15/d0/41/15d041870d416ac9647203e96b4ab78b.jpg')","url('https://www.dal.ca/content/dam/dalhousie/images/fass/classics/Rome%20battle.jpg')", "url('http://i.imgur.com/yG3BO.jpg')"
,"url('https://fthmb.tqn.com/toprD0AfngOnFREScDpSg-5bl-g=/1172x796/filters:no_upscale():fill(FFCC00,1)/about/battle-of-ascalon-large-56a61c243df78cf7728b63a7.jpg')", 
"url('https://s-media-cache-ak0.pinimg.com/originals/cc/f0/11/ccf011df7a8f59a31efc28e35ec0a655.jpg')",
, "url('http://www.museumofthecity.org/wp-content/uploads/2013/07/history_timeline_photo_09_1.jpg')", "url('http://www.imgbase.info/images/safe-wallpapers/miscellaneous/historical/42834_historical_medieval_battle.jpg')",
"url('https://s-media-cache-ak0.pinimg.com/736x/4f/3f/49/4f3f49b4b8945fe107d68163df14be7a.jpg')", "url('https://s-media-cache-ak0.pinimg.com/originals/7e/5d/a8/7e5da8f7f966ec2e946e205fcdfe5d02.jpg')", "url('https://s-media-cache-ak0.pinimg.com/originals/91/c2/ad/91c2adff774b415f4cd3f7204efff1a9.jpg')", 
"url('http://www.rmg.co.uk/sites/default/files/styles/slider/public/Loutherbourg-Spanish_Armada.jpg?itok=_-MaaV38')", "url('https://i.ytimg.com/vi/GvPXCnrAtMo/maxresdefault.jpg')", 
"url('http://s3.amazonaws.com/mtv-main-assets/files/resources/surrender_of_lord_cornwallis-capitol-web.jpg')",  "url('http://www.hms-victory.com/sites/default/files/images/D.jpg')",
"url('http://www.britishbattles.com/wp-content/uploads/2017/02/Charge_of_the_French_Cuirassiers_at_Waterloo-Henri-F%C3%A9lix-Emmanuel-Philippoteaux.jpg')", 
"url('https://userscontent2.emaze.com/images/7518df8e-7872-4fa4-87ce-0264c2418005/9293405ab76210dc084d21b92bac733a.jpg')", "url('http://francoprussianwar.com/prus-cav-charge.jpg')", "url('http://www.historyonthenet.com/wp-content/uploads/2016/12/135151-004-0D4D550E.jpg')", 
"url('http://i.imgur.com/raE3EQw.jpg')", "url('http://i2.cdn.cnn.com/cnnnext/dam/assets/140828132531-01-world-war-ii-0828-horizontal-large-gallery.jpg')"
, "url('https://d11in36igezwwb.cloudfront.net/texts/images/000/000/930/original/Approaching_Omaha.jpg?1489685651')"];
slideshow();
const sendMessage = event => {
	console.log(event);
	if (event !==undefined) {
	event.preventDefault();
}
    let  message = $('#messageField').val();
    console.log("f");
    console.log(message);
    $('#messageField').val("");
    let mess = {"type" : MESSAGE_TYPE.MESSAGE, "message": message, "playerId": myId};
    conn.send(JSON.stringify(mess));
}
document.getElementById("chatButton").onclick = sendMessage;
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
	changeMusicStatus();
	$("#homeMute").on("click", changeMusicStatus);
	$("#diceconfirm").on("click", confirmDice);
	$("#turnInCards").on( "click", turnInCards);
	// $("#messageForm").on('submit', function(e){
 //    // validation code here
 //      e.preventDefault();
 //    sendMessage();
 //  });
 function changeMusicStatus() {
 	console.log($("#homeMute").text());
 	if ($("#homeMute").text() == "Mute") {
 		$("#homeMute").text("Unmute");
 		document.getElementById('mainMenuMusic').pause();
 		// document.getElementById('mainMenuMusic').currentTime = 0;
 	}
 	else {
 		$("#homeMute").text("Mute");
 		document.getElementById('mainMenuMusic').play();	
 	}
 }
	$('.card').on("click", clickOnCard);
	
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

function clickOnCard() {
	if (canClick) {
		if (this.style.borderStyle !== "solid") {
  		this.style.borderStyle = "solid";
  		this.style.borderColor = "black";
  	} else {
  		this.style.borderStyle = "none";
  		this.style.borderColor = "none";
  	}
  }
}


function changePlayerImage(id, backgroundColor, color) { 
	id.style.color = color;
	id.style.backgroundColor = backgroundColor;
}
function slideshow(){
	console.log('in!');
	console.log(imageList.length);
	if (!start) {
	 now = (now+1) % (imageList.length) ;
	 console.log(imageList[now]);
        body.css('background-image', imageList[now]);
        body.fadeIn(1000);
        setTimeout(function(){body.fadeOut(1000)}, 5000);
        //$("#nameField").fadeIn(6000);
    }
}   

function setUp () {
	$sideNav = $('#n');
	$sideNav.append("<br><br><br>");
	$sideNav.append($("<p id = 'phase' class = 'blink'></p>"));
	$sideNav.append($("<p id = 'turn' class = 'blink'></p>"));
	$sideNav.append($("<p id = 'prevMove' class = 'blink'></p>"));
	$sideNav.append($("<p id = 'bolsters' class = 'blink'></p>"));
	$sideNav.append($("<p id = 'selecting' class = 'blink'></p>"));
	$sideNav.append($("<p id = 'attacking' class = 'blink'></p>"));
	$sideNav.append($("<button type='button' id = 'resetAttackMove'class='btn btn-danger'>Reset Attack Move</button>"));
	$sideNav.append($("<button type='button' id = 'attack'class='btn btn-danger'>Attack!</button>"));
	$sideNav.append($("<button type='button' id = 'defend'class='btn btn-danger'>Defend!</button>"));
	$sideNav.append($("<button type='button' id = 'skip'class='btn btn-danger'>End Turn</button>"));
	$sideNav.append($("<button type='button' id = 'resetMoveTroops'class='btn btn-danger'>Reset Move Troops</button>"));
	document.getElementById("resetAttackMove").onclick = reset_attack;
	document.getElementById("skip").onclick = skip_phase;
	document.getElementById("resetMoveTroops").onclick = reset_move_troops;
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
 	$("#resetMoveTroops").hide();
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
    document.getElementById(string).style.font = "bold 12px/30px Georgia, serif";
    document.getElementById(string).style.color = "white";
    document.getElementById(string).style.cursor = "pointer";

    document.getElementById(string).onmouseover = function() {
      document.getElementById(string).style.backgroundColor = "black";
      document.getElementById(string).style.transition = "all 1s";
    }
    document.getElementById(string).onmouseout = function() {
      document.getElementById(string).style.backgroundColor = colors[players[i]];
      document.getElementById(string).style.transition = "all 1s";
	 }

    document.getElementById(string).onclick = function() {
      document.getElementById('datadump').innerHTML = "PLAYER PROFILE FOR: " + idToName[string];
      document.getElementById('territories').innerHTML = "Occupies these territories:";
      document.getElementById('continents').innerHTML = "Possesses these continents:";
      document.getElementById('totaltroops').innerHTML = "Has this many troop in total:";
      document.getElementById('myModal').style.display = "block";
    }

    document.getElementById("closer").onclick = function() {
      document.getElementById('myModal').style.display = "none";
    }
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

    $("#chatting").scrollTop($("#chatting")[0].scrollHeight);
}

//listen for enter on messaging.
document.querySelector("#messageField").addEventListener("keyup", function (e) {
    let key = e.keyCode;
    console.log(key);
    if (key === 13) { 
      sendMessage();
    }
});
