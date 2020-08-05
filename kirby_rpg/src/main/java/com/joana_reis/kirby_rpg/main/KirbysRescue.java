package com.joana_reis.kirby_rpg.main;

import com.joana_reis.kirby_rpg.entities.Map;
import com.joana_reis.kirby_rpg.entities.Mission;
import com.joana_reis.kirby_rpg.entities.Monster;
import com.joana_reis.kirby_rpg.entities.Player;
import com.joana_reis.kirby_rpg.enums.Attack;
import com.joana_reis.kirby_rpg.enums.Weakness;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KirbysRescue implements Serializable {

    /** Information about the game **/
    private static KirbysRescue game;
    private boolean finishedGame = false;
    private boolean exitedGame = false;
    private static boolean loadedGame = false;
    private static boolean startedGame = false;

    /** Object references used in the game **/
    private List<Mission> gameMissions;
    private Player player;
    private Monster browser;
    private Monster kingBoo;
    private Monster donkeyKong;
    private Monster calamityGannon;
    private Map map;
    private transient static Scanner scanner = new Scanner(System.in);

    /** Map dimensions (vertical x horizontal) **/
    private final static int N = 4;
    private final static int M = 10;

    /** Keys pressed **/
    static final String KEY_1 = "1";
    static final String KEY_2 = "2";
    static final String KEY_3 = "3";
    static final String KEY_4 = "4";
    static final String KEY_Y = "y";
    static final String KEY_N = "n";

    /** Colors used **/
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {

        startGame();

        if (isStartedGame()) {
            game.startNewGame();
        }

        game.loadGame();

    }

    /** Method that starts the game **/
    private static void startGame() {

        while(!isStartedGame() && !isLoadedGame()){

            System.out.println(ANSI_CYAN + "Hello to Adventure of Kirby! Do you want to start a new game(1) or load from a file(2)?" + ANSI_RESET);

            String startGaming = scanner.nextLine();

            while (!startGaming.equals(KEY_1) && !startGaming.equals(KEY_2)){
                System.out.println("Please insert a valid option.");
                startGaming = scanner.nextLine();
            }

            if (startGaming.equalsIgnoreCase(KEY_1)) {
                game = new KirbysRescue();
                setStartedGame(true);

            } else {
                game = resumeGame();
                if (game != null) {
                    setLoadedGame(true);
                } else {
                    System.out.println("We are sorry, something came up. Make sure you have a save file \"Save.ser\" on the folder, or that you haven't changed the source code.");
                }
            }
        }
    }

    /** Method that starts a new game (initializes map, player, monsters and missions) **/
    private void startNewGame(){

        map = new Map(N, M);

        createPlayer();

        createMonsters();

        createMissions();

    }

    /** Method that creates a new player **/
    private void createPlayer(){

        System.out.println(ANSI_CYAN + "From the deep echoes of an abandoned castle.. You hear the screams of your friend Kirby. " +
                "Help! Help! Nintendo family, please come to my rescue.. I cannot fight Calamity Gannon much longer...");
        System.out.println(ANSI_BLUE + "Kirby: Is there anyone to help me?" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Press (y) to help Kirby, (n) to let it die."+ ANSI_RESET) ;

        String startGame= scanner.nextLine();

        while (!startGame.equalsIgnoreCase(KEY_Y) && !startGame.equalsIgnoreCase(KEY_N)) {
            System.out.println("Please insert a valid answer.");
            startGame= scanner.nextLine();
        }

        if (startGame.equalsIgnoreCase(KEY_Y)) {
            System.out.println(ANSI_CYAN + "Oh, hello you. Who are you? Mario(1), Luigi(2) or Princess Peach(3)?" + ANSI_RESET);

            String characterChosen = scanner.nextLine();

            while (!characterChosen.equals(KEY_1) && !characterChosen.equals(KEY_2) && !characterChosen.equals(KEY_3) && !characterChosen.equals(KEY_4)) {
                System.out.println("Please insert a valid answer.");
                characterChosen = scanner.nextLine();
            }

            if (characterChosen.equals(KEY_1)) {
                player = new Player("Mario", 20);
            } else if (characterChosen.equals(KEY_2)) {
                player = new Player("Luigi", 20);
            } else {
                player = new Player("Princess Peach", 20);
            }
            System.out.println(ANSI_BLUE +  "Kirby: Hello " + player.getName() + ". Thanks for helping me. Please get started.." + ANSI_RESET );
        } else {
            System.out.println(ANSI_RED + "Kirby: Thank you, I've always known you were not my friend.. Bye." + ANSI_RESET);
            setExitedGame(true);
        }
    }

    /** Method that creates the monsters **/
    private void createMonsters() {
        browser = new Monster(Weakness.FIRE, "Browser", "Oh hello.. I'm hungry", 20, 2);
        kingBoo = new Monster(Weakness.ICE, "King Boo", "Do you think I am scared?", 60, 10);
        donkeyKong = new Monster(Weakness.PHYSICAL_ATTACKS, "Donkey Kong", "Pff.. Get lost", 100, 30);
        calamityGannon = new Monster(Weakness.THUNDER, "Calamity Gannon", "Kirby is mine.. ", 200, 39);
    }

    /** Method that creates the missions **/
    private void createMissions() {

        Mission mission1 = new Mission("Fight Browser", "Kirby: Please.. go up north and fight Browser.. You need to get stronger to fight this dragon! \nHelp me... ",
                "Kirby: You are my hero, please hurry up!", browser, player, 1);
        Mission mission2 = new Mission("Destroy King Boo", "Kirby: I've heard King Boo is on the West side of the map. He was the one who kidnapped me!",
                "Kirby: You are half way. Thank you!", kingBoo, player,1);
        Mission mission3 = new Mission("Kill Donkey Kong", "Kirby: Somewhere South.. is Donkey Kong. He will tell you where I am.",
                "Kirby: The Castle is on the SouthEast! Please, gather all your strength to fight Calamity Gannon...", donkeyKong, player, 1);
        Mission mission4 = new Mission("Save Kirby",  "Kirby: Get ready.. this will not be an easy fight.",
                "Kirby: Thank you. I am now free thanks to you!", calamityGannon, player, 1);

        gameMissions = new ArrayList<Mission>();
        gameMissions.add(mission1);
        gameMissions.add(mission2);
        gameMissions.add(mission3);
        gameMissions.add(mission4);

    }

    /** Method that loads the game **/
    private void loadGame(){
        while(!finishedGame && !exitedGame) {
            playGame(gameMissions);
        }
        System.out.println(ANSI_PURPLE + "Thank you for experiencing the game. Credits: Joana Reis." + ANSI_RESET);
    }

    /** Method that has the core structure of the game **/
    private void playGame(List<Mission> missions) {

        for (Mission mission : missions) {

            while (!mission.isFinished()) {

                // Clean and print position of the player in the map
                map.resetPlayerPosition(player.getPosition());
                map.printPosition();

                // Show start mission dialogs
                mission.startMission();

                explore(mission);

                attack(mission);

                if (mission.getPlayer().getLife() > 0) {
                    mission.endMission();
                    saveOrContinue();
                    if (isExitedGame()) {
                        return;
                    }
                } else {
                    System.out.println(ANSI_RED + "Game over.." + ANSI_RESET);
                    restartMission(mission);
                    if (isFinishedGame()) {
                        return;
                    }
                }
            }
        }
        setFinishedGame(true);
    }

    /** Method that prompts user to navigate on the map **/
    private void explore(Mission mission){
        while (!mission.hasReachedLocation()) {

            System.out.println(ANSI_CYAN + "Use your keys to navigate through the map: left(1), up(2), right(3) or down(4)." + ANSI_RESET);

            String direction = scanner.nextLine();

            while (!direction.equals(KEY_1) && !direction.equals(KEY_2) && !direction.equals(KEY_3) && !direction.equals(KEY_4)) {
                System.out.println("Please insert a valid direction.");
                direction = scanner.nextLine();
            }

            map.updatePlayerPosition(player, Integer.parseInt(direction));
            map.printPosition();

        }

    }

    /** Method that plays the attack **/
    private void attack(Mission mission){
        System.out.println(ANSI_RED + mission.getMonster().getName() + ": " + mission.getMonster().getGreeting() + ANSI_RESET);

        mission.startFight();

        while (!mission.getFight().isOver()) {
            Attack attack;

            System.out.println(ANSI_CYAN + "Your turn! Which attack do you want to use? Sword Charge(1), Eruption(2), Lightening Attack(3) or Blizzard(4)?" + ANSI_RESET);

            String attackChosen = scanner.nextLine();

            while (!attackChosen.equals(KEY_1) && !attackChosen.equals(KEY_2) && !attackChosen.equals(KEY_3) && !attackChosen.equals(KEY_4)){
                System.out.println("Please insert a valid attack.");
                attackChosen = scanner.nextLine();
            }

            if (attackChosen.equals(KEY_1)) {
                attack = Attack.SWORD_CHARGE;
            } else if (attackChosen.equals(KEY_2)) {
                attack = Attack.ERUPTION;
            } else if (attackChosen.equals(KEY_3)) {
                attack = Attack.LIGHTENING_ATTACK;
            } else {
                attack = Attack.BLIZZARD;
            }

            mission.getFight().playerAttacks(attack);

            if (mission.getMonster().getLife() > 0) {
                System.out.println("Monster life: " + mission.getMonster().getLife());
                System.out.println(ANSI_RED + "Monster attacks back!" + ANSI_RESET);
                mission.getFight().monsterAttacks();
                System.out.println(player.getName() + " life: " + mission.getPlayer().getLife());
            } else {
                System.out.println(ANSI_GREEN + "You killed it!" + ANSI_RESET);
            }

        }

    }

    /** Method that prompts the user to save or continue **/
    private void saveOrContinue() {
        System.out.println("Do you wish to save your progress and exit the game(1) or continue(2)?");

        String save = scanner.nextLine();
        while (!save.equals(KEY_1) && !save.equals(KEY_2)){
            System.out.println("Please insert a valid option.");
            save = scanner.nextLine();
        }

        if (save.equals(KEY_1)) {
            saveGame();
            exitedGame = true;
        }

    }

    /** Method that restarts a mission **/
    private void restartMission(Mission mission) {
        System.out.println("Do you want to restart the mission? Yes (y) or no(n).");

        String restart = scanner.nextLine();
        while (!restart.equalsIgnoreCase(KEY_Y) && !restart.equalsIgnoreCase(KEY_N)){
            System.out.println("Please insert a valid option.");
            restart = scanner.nextLine();
        }

        if (restart.equalsIgnoreCase(KEY_Y)) {
            mission.restartMission();
            map.resetPlayerPosition(player.getPosition());
            return;
        } else {
            setFinishedGame(true);
            return;
        }

    }

    /** Method to save a game **/
    private void saveGame() {

        try {
            FileOutputStream saveFile = new FileOutputStream("Saving.ser");
            ObjectOutputStream os = new ObjectOutputStream(saveFile);

            os.writeObject(game);
            os.close();

        } catch(Exception ex){
            ex.printStackTrace();;
        }
    }

    /** Method to resume a game **/
    private static KirbysRescue resumeGame() {
        try {
            FileInputStream saveFile = new FileInputStream("Saving.ser");
            ObjectInputStream is = new ObjectInputStream(saveFile);
            game = (KirbysRescue) is.readObject();
            return game;

        } catch(Exception ex){
            return null;
        }

    }

    /** Getters and setters **/
    public static boolean isStartedGame() {
        return startedGame;
    }

    public static void setStartedGame(boolean startedGame) {
        KirbysRescue.startedGame = startedGame;
    }

    public static boolean isLoadedGame() {
        return loadedGame;
    }

    public static void setLoadedGame(boolean loadedGame) {
        KirbysRescue.loadedGame = loadedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }
    public boolean isFinishedGame() {
        return finishedGame;
    }

    public boolean isExitedGame() {
        return exitedGame;
    }

    public void setExitedGame(boolean exitedGame) {
        this.exitedGame = exitedGame;
    }


}
