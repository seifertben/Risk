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
}

const player = "Player 2"
	
	//setUp();
	//activateDropDown(2);
	//replaceField();
	//changePlayerImage(player2, "white", "blue");

	// addcard(2);
	// addcard(1);
	// addcard();
	// addcard();
	// addcard();


function changePlayerImage(id, backgroundColor, color) { 
	console.log(id);
	id.style.color = color;
	id.style.backgroundColor = backgroundColor;
}

function setUp () {
	createPlayer(3);
	attackStatus();
	createDropdown();
	$sideNav = $('#n');
	$sideNav.append("<br>")
	$sideNav.append("<button type='button' id = 'attack'class='btn btn-danger'>Attack</button>");
	$sideNav.append($("<p id = 'attackerRollText'>Attacker's roll</p>"));
	$sideNav.append($( "<ul id = 'blackRoll'>"));
	$sideNav.append($( "<p id = 'defenderRollText'>Defender's roll</p>"));
	 $sideNav.append($("<ul id = 'redRoll'>"));
	 $sideNav.append("<br>");
	  $sideNav.append($("<p id = 'attackLoss'>You lost 1 soldier</p>"));
	   $sideNav.append($(" <p id = 'defendLoss'>Player 2 lost 1 soldier</p>"));
	   $bottom = $('#bottom');
	   hideAll();
	   changeAttackStatus("Player 1", "Player 2", "Russia");
	   changeAttackersTerritoryInfo("Player 1", "Ontario", 10);
	   changeDefendersTerritoryInfo("Player 2", "Western United States", 20);
	   attackerLoss("Player 3", 2);
	   defenderLoss("Player 3", 3);
}

function changeAttackStatus(attackingPlayer, defendingPlayer, territory) {
	let message  = attackingPlayer + " is attacking " + defendingPlayer  + " in " + territory;
	if (attackingPlayer ===player) {
		 message  = "You are attacking " + defendingPlayer + " in " + territory;
	}
	 else if (defendingPlayer === player) {
		message = attackingPlayer + " is attacking you in " + territory;
	}
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
}

function addcard(number) {
	if (number ===1) {
	  $('#cards').append($("<li class = 'card' ><div class='w3-card-4'><header class='w3-container-w3-blue'><h1>Card</h1></header><div class='w3-container'><p id = 'star'>*</p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>"));
    }
    if (number ===2) {
	  $('#cards').append($("<li class = 'card' ><div class='w3-card-4'><header class='w3-container-w3-blue'><h1>Card</h1></header><div class='w3-container'><p id = 'star'>**</p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>"));
    }
}

function createPlayer(number, names) {
	const $sideNav = $("#n");
	for (let i = 0; i<number; i ++) {
		let currDiv = $("<div></div>");
		let text =  $("<span></span>");
		currDiv.attr("class", "well well-sm");
		const player = i+1;
		let string = names[i];
		text.html(string);
		currDiv.attr("id", string);
		currDiv.append(text);
		console.log(currDiv.attr("class"));
		console.log(currDiv.attr("Id"));
		console.log($sideNav);
		$("#n").append(currDiv);  
	}
}
function attackStatus() {
	const $div = $("#n");
	let $br = $("<br><br><br>");
	$div.append($br);
	let $attack = $("<p></p>");
	$attack.attr("id", "attackingWho");
	$attack.html("You are attacking Player 2 in China!");
	$div.append($attack);
	let $attackerStatus = $("<p></p>");
	$attackerStatus.html("You have 5 soldiers in Russia");
		$attackerStatus.attr("id", "attackerStatus");
	$div.append($attackerStatus);
	let $defenderStatus = $("<p></p>");
	$defenderStatus.html("Player 2 has 3 soldiers in China");
	$defenderStatus.attr("id", "defenderStatus");
	$div.append($defenderStatus);
}
function createDropdown() {
	const $parent = $("#n");
	const $outer = $("<div class='btn-group' id = 'dropdowngroup'></div>");
	$outer.append($("<button type='button' class='btn btn-primary' id = 'dropdownbutton'><span id = dropdown>Select the amount of dice to roll</span></button>"));
	$outer.append("<button type='button' class='btn btn-primary dropdown-toggle' id ='soldierOptions' data-toggle='dropdown'><span class='caret'></span></button>");
	$outer.append($("<ul class='dropdown-menu' id = 'dieOptions' role='menu'></ul>"));
	$parent.append($outer);
}
function activateDropDown(numbers) {
	$("#dropdown").html("Select the amount of dice to roll");
	$dropDown = $('#dieOptions');
	for (let i = 0; i<numbers; i++) {
		console.log("f");
		// let a = $('<a></a>');
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
	console.log("Hi");
	console.log(this);
	let id = "#" + "dropdown";
   $(id).html(this.text);
   console.log(this.text);
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
