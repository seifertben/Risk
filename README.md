# cs0320 Term Project

**Team Members:** _Fill this in!_

**Project Idea:** _Fill this in!_

**Mentor TA:** _Put your mentor TA's name and email here once you're assigned one!_

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

## Project Design Presentation
_A link to your design presentation/document will go here!_

## How to Build and Run
_A necessary part of any README!_
