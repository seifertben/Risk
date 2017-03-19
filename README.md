# cs0320 Term Project

**Team Members:**
Justin Huang
Benjamin Seifert
Benjamin Gabinet
Sarah Gilmore

**Project Idea:** 
Risk, the board game, but as an online game.

**Mentor TA:**
Ansel Vahle
ansel_vahle@brown.edu

## Project Requirements
Below are the basic roles that are essential to risk. Players must be able to:
Play with 3-6 Players.

Set up phase (initial setup of game depending on number of players)
  Claim territories
  Roll die to choose the order in which the players choose territories. If there is a tie, the players reroll.
Turn phase
  Attacking territories
    You can choose any any enemy adjacent territory to attack
    Can bring up to N-1 soldiers
    Defender
      Roll a die up to two times. (Once if you only have one army at the territory)
    Attacker
      Roll a die up to three times. (Once if you only have one army at the territory)
    Remove one army from the territory that got a lower number. If there is a tie, it is treated as a loss for the attacker.
    If the attacker still has an army, the attacker can choose to attack any adjacent territory.
  Conquering territories
    If you attack a player and they have no more armies in that territory, choose how many armies from the territory you used to attack to place at the newly conquered territory.
    A defeated player with no more territories, should be able to continue viewing the game.
  Draw from a set of shuffled cards shared by all players. 
    Trade in sets of 3 cards on end of round for more armies.
End of turn phase
  Move up to N-1 armies from a territory with N armies to an adjacent territory that you have captured.
End of Round
  Based on number of territories and continental bonuses, place more soldiers on the board.


GUI Requirements:
On page load: 
  Enter name, then choose/create server
  When you enter room, check off the ready box if you’re ready/don’t want to wait for more players when the minimum amount of required players is met.
During setup phase:
  Everyone is given a random turn order.
  Select territories at which to place armies by clicking on them.
An interactive map, big enough for everything to be visible.
  Players should be able to drag from one territory they own to an adjacent enemy territory to attack.
  How many armies are at a territory should be visible on the map, these should be color coded to the player that owns them.
Players should be able to see the cards they own.
Players should be able to input what they want to do on each turn, such as trading in cards at the end of a round.

What Users can see
Users should be able to see territorial changes when other players make their turn 
Also see other players attacking (seeing the results of each die roll) 


_Fill in your project requirements here!_

## Project Specs and Mockup
_A link to your specifications document and your mockup will go here!_
https://docs.google.com/a/brown.edu/document/d/1YPDIsYZ_XN2TWaXAFnIfJTLsNHdkNYXWZeOt4CMnJ1o/edit?usp=sharing

Notes on how we’ve Accessed Grading:

-A: Fancy GUI and Improvements

-B: Functional and Acceptable

-C: Only One Laptop to Play

-N/C: No Multiplayer

---Main Menu:
This is the home page for Risk
A user will type their name into the textarea and click the begin button to go to the next  page, which is creating a new game or joining a game that currently does not have enough players

---Create Game or Join Game:
A user has two options: either to create a new game or to join a pre-existing game that has not started yet because not enough players have joined the game
To create a new game, the user must input a name for the game, select the number of players in the game and then click the begin game button to actually create it. The total number players can range from 2-6. If either the lobby name field or the number of players field is not filled in, the create game button will not do anything and an alert will pop up and inform the user to fill in the missing fields.
If the user wishes to join a pre-existing game, he or she must select their desired color and click the join game button associated with the game. The color select-box only displays colors not currently selected by players in the game. If the user does not select a color before joining the game, a random color is given to the player.
Next to the game’s name is the total number of players currently in the game and the total number of players needed to start it.
Once a game has enough players, the game should disappear from the Join Game menu.

---Waiting for Players to Join the Game:
Once the user has joined a game, the page will switch to show the game map. If not enough players have joined the game, a message underneath the player icons will say that the game is waiting for enough players to join.
By this point, those players already in the game will be able to message each other.

---Begin Game:
Once enough players have joined the game, an alert will pop up on each player’s screen saying the game can begin. Each player much click the yes button on the alert for the game to begin.
If a player clicks no, the game will revert back to the Waiting for Players to Join the Game phase.
If all of the players click no, the game ceases to exist :(
If all of the players click yes, the game begins :)
Turn order is determined after this randomly.

---Pick Territories:
On their turn, each player selects a territory to place one army on. 
They can only select territories not yet claimed and cannot add more than one army onto any given territory.
This phase continues until no territories are left to claim.

---Player on the offensive:
A player can choose to attack on their turn by clicking and dragging from one of their territories to another. A button will appear for them to choose how many armies they want to send to attack, and then roll their dice. The attack is visible to all players and is indicated on the map with an arrow connecting the two competing territories.
The attacker will see the results of the die roll below and will be updated on whether they won and now many people they have left and the enemy has left. The map will also be updated to reflect the changes.
This will go on till the attacker chooses to stop attacking.
If the attacker defeats all armies at a territory, the attacker will be asked to play armies at the territory. They shall receive a card for having taken a territory.

---Player on the defensive:
If player is attacked, the player will get an alert saying they are being attacked, which territory is being attacked, and a button will appear for them to click on to roll the dice. The player will be alerted how many people are attacking their territory and how many people are at the current territory. The map will have the arrow of the attacking player pointing from their territory to the defender’s territory. 
The defender  will see the results of the die roll below and will be updated on whether they won and how many people they have left. The map will also be updated to reflect the changes. 
This will go on till the attackers chooses to stop attacking.
If a defenders’ territory gets taken over, the defender will be alerted that they have lost the territory

---Card Exchange:
At the end of one rotation, players can trade in cards that they have gained through taking territories in exchange for armies. They can select which cards they want to turn in, or abstain from turning in any cards.

---If player Loses:
An X will go over the player’s icon. If player loses, then they will be alerted that they lost and will be given an option to go back to the home page.They can also stay and watch the game.

---Winner Phase:
Once a player wins the game, an alert message will pop up informing them they won the game.
At the bottom of the alert, there is a button that will bring the player back to the Main Menu page
All the player icons except the winner’s will have a red x indicating they lost.

## Project Design Presentation
_A link to your design presentation/document will go here!_

## How to Build and Run
_A necessary part of any README!_
