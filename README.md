# TankGame

Documentation: Tank Game Project

Contributors: 
Carolyn Chen,
Jason Chow

GitHub Repository:  

Build Instructions for NetBeans for Windows
1.	Be sure to have the latest JDK 8u162 or higher
2.	Clone the tank game project from the repository link above that was provided
3.	Start NetBeans
4.	File -> Open Project -> Go to cloned project from GitHub -> Open Project
5.	On the left Tab, Press on TankGame -> Source Packages -> tankgame
6.	Right click “TankWorld” and press on Run
7.	An Applet should appear; you will need to resize the game by expanding the game or pressing on the maximize button on the top right

* You should not have to set a working directory, but if you must set it, set it as “TankGame”
csc413-03-tankgame-Team31-master/TankGame
** The program will not have a main method due to the nature of JApplets.

Game Objectives and Rules
In this tank game, the two players are teammates. The sole objective is to eliminate all enemies. You are given a health, and are only allowed to take four bullets before losing a life. Each player is given 3 lives. After both player losing all three lives, you lose the game. There is no friendly fire and also you can go through teammates, but not enemies. When a player comes in contact with an enemy, both the user and the enemy will explode and be eliminated. There are two types of power ups in the game. The red power up will give the player full health and lives. The blue power up will allow the player to shoot 3 bullets at once, until the player loses a life.  There are also two types of walls. The darker walls are unbreakable and the lighter colored walls are breakable by the tank’s bullet. Once all enemies are eliminated, you have won the game. 

Controls for Tank Game
Player 1:
	Up arrow:    Moves tank upwards
	Down arrow:  Moves tank downwards
	Left arrow:  Rotates tank left
	Right arrow: Rotates tank right
	Space key:   Fires a bullet

Player 2:
	W key: Moves tank upwards
	S key: Moves tank downwards
	A key: Rotates tank left
	D key: Rotates tank right
	Q key: Fires a bullet
