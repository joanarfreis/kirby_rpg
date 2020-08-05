Read Me (Kirby_RPG)

Name
Kirby's Rescue

Description
Kirby's Rescue is a simple RPG game in which Kirby is kidnapped and kept in the castle with Calamity Gannon. Someone from the Nintendo family (Mario, Luigi or Princess Peach has to save it!).

Commands
The game interacts with the user, so he or she can navigate through the map, perform attacks, save the game and other actions. 
When navigating through the map, the player's location is represented by "~", the sites the player has visited are represented by "%", and the remaining ones are represented by ".".

How to build? 
Extract the .zip folder provided and save it anywhere.
Check if you have Java and Maven installed (java -version, mvn -version). If so, through the command line navigate to the extracted folder and run "mvn install". This will compile, build, install all the dependencies and run the tests. 

How to run? 
From within the extracted folder, go to /target. Then, run: "java -jar kirby_rpg.1.0-SNAPSHOT.jar".

How to extend?
The project is based on missions, so in order to increase the game's length, the number of missions may increase. Additionally, new monsters should be created in order to be incorporated into those missions.
The fight mechanism could also be improved by adding new attacks and weaknesses. 

Test coverage
Test coverage is at the moment roughly around 80%, given that all methods outside the main class (which are mostly interactions with the user) and other than getters and setters are covered.

Authors
Joana Reis