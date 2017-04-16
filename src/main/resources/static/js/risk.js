$( document ).ready(function() {
	setUp();
	activateDropDown(5);
	addcard();
	addcard();
	addcard();
	addcard();
	addcard();


});
function setUp () {
	createPlayer(3);
	attackStatus();
	createDropdown();
	$sideNav = $('#n');
	$sideNav.append("<br>")
	$sideNav.append("<button type='button' class='btn btn-danger'>Attack</button>");
	$sideNav.append($("<p id = 'attackerRollText'>Attacker's roll</p>"));
	$sideNav.append($( "<ul id = 'blackRoll'>"));
	$sideNav.append($( "<p id = 'defenderRollText'>Defender's roll</p>"));
	 $sideNav.append($("<ul id = 'redRoll'>"));
	 $sideNav.append("<br>");
	  $sideNav.append($("<p id = 'attackLoss'>You lost 1 soldier</p>"));
	   $sideNav.append($(" <p id = 'defendLoss'>Player 2 lost 1 soldier</p>"));
	   $bottom = $('#bottom');


}
function addcard() {
	$('#cards').append($("<li class = 'card' ><div class='w3-card-4'><header class='w3-container-w3-blue'><h1>Card</h1></header><div class='w3-container'><p id = 'star'>**</p></div><footer class='w3-container-w3-blue'>  <h5>Turn in this card for reinforcements!</h5></footer></div></li>"));
}
function createPlayer(number) {
	const $sideNav = $("#n");
	for (let i = 0; i<number; i ++) {
		console.log('hi');
		let currDiv = $("<div></div>");
		let text =  $("<span></span>");
		currDiv.attr("class", "well well-sm");
		const player = i+1;
		let string = "player" + player;
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
	let $attack = $("<br><br><br><p></p>");
	$attack.attr("id", "attackingWho");
	$attack.html("You are attacking Player 2 in China!");
	$div.append($attack);
	let $attackerStatus = $("<p></p>");
	$attackerStatus.html("You have 5 soldiers in Russia");
	$div.append($attackerStatus);
	let $defenderStatus = $("<p></p>");
	$defenderStatus.html("Player 2 has 3 soldiers in China");
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
	$dropDown = $('#dieOptions');
	for (let i = 0; i<numbers; i++) {
		console.log("f");
		let a = $('<a></a>');
		let option = i+1;
		a.html(option);
		let li = $('<li class= "drop"></li>');
		li.append(a);
		$dropDown.append(li);
		if (i ==2) {
			break;
		}
	}
}