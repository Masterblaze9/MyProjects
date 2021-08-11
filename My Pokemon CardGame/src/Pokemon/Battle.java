package Pokemon;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Battle {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        String season;
        int opc = 0;

        // Player and computer instance

        Player computer = new Player(0, "Computer");
        Player player = new Player();

        // Service intance
        Services services = new Services();

        //FIle instance
        File pokemonFile = new File("PokemonDB2");

        File battleFile = new File("Battle");
        //playerFile instance
        File playerFile = new File("PlayerDB");

        File pokemonTxt = new File("PokemonTXT");


        //Field Instance
        ArrayList<Pokemon> playField = new ArrayList<>(2);

        //Hashmap Instance
        HashMap<String, Pokemon> pokemonHashMap = new HashMap<>();

        ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

        HashMap<Integer, Player> playersList = new HashMap<>();

        //open file
        services.openFile(pokemonFile);
        //convert file to hashmap
        pokemonHashMap = services.addFileToHashMap();
        //convert hashmap to arraylist
        pokemonList = services.hashMapToArrayList(pokemonHashMap);

        services.closePokemonFile();

        while (opc != -1) {


            System.out.println("Select your option:");
            System.out.println(
                    "(1)Insert a new Card\n(2)Show the cards\n(3)Show the card by type\n(4)Show cards by name\n(5)Show the Name of the Pokemon of the type you choose\n(6)Show by Type the name of the Pokemon you introduce\n(7)Battle\n(8)Edit a Pokemon\n(9)Statistics\n(-1)Exit the program");
            opc = sc.nextInt();

            switch (opc) {

                case 1:
                    services.insertElements();


                    break;

                case 2: {
                    services.openFile(pokemonFile);
                    pokemonHashMap = services.addFileToHashMap();
                    pokemonList = services.hashMapToArrayList(pokemonHashMap);
                    services.showHashMap(pokemonHashMap);


                }
                break;

                case 3:
                    services.showByType();
                    break;
                case 4:
                    services.showByName();
                    break;
                case 5:
                    services.showByTypeAndByNameNoInput();
                    break;
                case 6:
                    services.showByNameAndByTypeInput();
                    break;

                case 7:
                    HashMap<Integer, Player> aux = services.addPlayerToFile();
                    if (aux != null) {
                        for (Integer key : aux.keySet()) {
                            if (!playersList.containsKey(key))
                                playersList.put(key, aux.get(key));
                        }
                    }
                    int playCounter = 0;

                    String result;

                    System.out.println(playersList);

                    System.out.println("What is your name?");
                    String playerName = sc.next();
                    if (!playersList.isEmpty()) {
                        for (Integer key : playersList.keySet()) {
                            if (playersList.get(key).getName().equals(playerName))
                                player = playersList.get(key);
                        }
                    } else player = new Player(0, playerName);

                    if (playersList.isEmpty()) {
                        int playerId = 1;
                        playersList.put(playerId, player);
                    } else {
                        int playerId = 0;
                        for (Map.Entry<Integer, Player> elements : playersList.entrySet()) {
                            if (!elements.getValue().getName().equals(playerName)) {

                                playerId = playersList.size() + 1;
                            }
                        }
                        if (playerId != 0 && playerId != playersList.size() + 1)
                            playersList.put(playerId, player);
                    }
                    services.writeObjectToFilePlayerList(playersList);


                    int numberOfPlays;
                    System.out.println("How many plays do you want to make? Needs to be at least 3 moves");
                    numberOfPlays = sc.nextInt();
                    player.setNumberOfPlays(player.getNumberOfPlays() + numberOfPlays);
                    player.setNumberOfGames(player.getNumberOfGames() + 1);
                    System.out.println(player.getNumberOfPlays());
                    System.out.println(player.getNumberOfGames());

                    System.out.println(playersList);

                    while (numberOfPlays < 3) {

                        System.out.println("You need to make at least 3 moves");
                        System.out.println("How many plays do you want to make? Needs to be at least 3 moves");
                        numberOfPlays = sc.nextInt();
                        for (Map.Entry<Integer, Player> elements : playersList.entrySet()) {
                            if (!elements.getValue().getName().equals(playerName)) {
                                elements.getValue().setNumberOfPlays(elements.getValue().getNumberOfPlays() + numberOfPlays);
                                elements.getValue().setNumberOfGames(elements.getValue().getNumberOfGames() + 1);
                            }
                        }
                    }
                    int numberOfCards = numberOfPlays;

                    ArrayList<Pokemon> cardsInHandPlayer = new ArrayList<Pokemon>();
                    ArrayList<Pokemon> cardsInHandComputer = new ArrayList<Pokemon>();

                    Collections.shuffle(services.pokemonList);


                    for (int i = 0; i < numberOfCards; i++) {
                        cardsInHandPlayer.add(services.pokemonList.get(i));

                    }


                    Collections.shuffle(services.pokemonList);

                    for (int i = 0; i < numberOfCards; i++) {
                        cardsInHandComputer.add(services.pokemonList.get(i));
                    }

                    System.out.println("Select the season you´r playing in");
                    System.out.println("(1)Spring \n (2)Summer \n (3)Autumn \n (4)Winter");
                    season = sc.next();
                    switch (season) {
                        case "1":
                            System.out.println("You selected Spring Grass type Pokemon gain 1 Defense and Eletric Pokemon lose 1 Defense");
                            while (playCounter < numberOfPlays) {

                                services.battle(computer.computerPlayCards(cardsInHandPlayer, playField, numberOfCards), player.playCard(cardsInHandComputer, playField, numberOfCards), season, player, computer, playField);

                                playCounter++;



                                if (playCounter == numberOfPlays) {

                                    System.out.println("Final score board");
                                    System.out.println(player.getName() + " has " + player.getScore() + " points");
                                    System.out.println(computer.getName() + " has " + computer.getScore() + " points");

                                    if (player.getScore() > computer.getScore()) {

                                        System.out.println(player.getName() + " wins!!!");
                                        player.setNumberOfWins(player.getNumberOfWins() + 1);
                                        result = player.getName() + " wins\n Number of wins=>" + player.getNumberOfWins();

                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println(player.getName() + " now has " + player.getNumberOfWins() + " wins");
                                    }
                                    if (computer.getScore() > player.getScore()) {
                                        System.out.println("Computer wins!!!");
                                        computer.setNumberOfWins(computer.getNumberOfWins() + 1);
                                        result = computer.getName() + " wins\n Number of wins=>" + computer.getNumberOfWins();

                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println("Computer gets a win now has " + computer.getNumberOfWins() + "wins");
                                    }
                                    if (computer.getScore() == player.getScore()) {
                                        result = "Nobody wins its a tie";
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println("Its a tie nobody gets a win");
                                    }
                                    player.setScore(0);
                                    computer.setScore(0);
                                }
                            }
                            break;
                        case "2":
                            System.out.println("You selected Summer Fire type Pokemon gain 1 Defense and Water type Pokemon lose 1 Defense");
                            while (playCounter < numberOfPlays) {

                                services.battle(computer.computerPlayCards(cardsInHandPlayer, playField, numberOfCards), player.playCard(cardsInHandComputer, playField, numberOfCards), season, player, computer, playField);

                                playCounter++;

                                if (playCounter == numberOfPlays) {
                                    System.out.println("Final score board");
                                    System.out.println(player.getName() + " has " + player.getScore() + " points");
                                    System.out.println(computer.getName() + " has " + computer.getScore() + " points");


                                    if (player.getScore() > computer.getScore()) {
                                        System.out.println(player.getName() + " wins!!!");
                                        player.setNumberOfWins(player.getNumberOfWins() + 1);

                                        result = player.getName() + " wins\n Number of wins=>" + player.getNumberOfWins();
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println(player.getName() + " now has " + player.getNumberOfWins() + " wins");
                                    }
                                    if (computer.getScore() > player.getScore()) {
                                        System.out.println("Computer wins!!!");
                                        player.setNumberOfWins(computer.getNumberOfWins() + 1);

                                        result = computer.getName() + " wins\n Number of wins=>" + computer.getNumberOfWins();
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println("Computer gets a win w now has" + computer.getNumberOfWins());
                                    }
                                    if (computer.getScore() == player.getScore()) {
                                        System.out.println("Its a tie nobody gets a win");
                                        result = "Nobody wins its a tie";
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                    }
                                    player.setScore(0);
                                    computer.setScore(0);
                                }
                            }
                            break;

                        case "3":
                            System.out.println("You selected Autumn Eletric type Pokemon gain 1 Defense and Grass type Pokemon loss 1 Defense");
                            while (playCounter < numberOfPlays) {

                                services.battle(computer.computerPlayCards(cardsInHandPlayer, playField, numberOfCards), player.playCard(cardsInHandComputer, playField, numberOfCards), season, player, computer, playField);

                                playCounter++;

                                if (playCounter == numberOfPlays) {
                                    System.out.println("Final score board");
                                    System.out.println(player.getName() + " has " + player.getScore() + " points");
                                    System.out.println(computer.getName() + " has " + computer.getScore() + " points");


                                    if (player.getScore() > computer.getScore()) {
                                        System.out.println(player.getName() + " wins!!!");
                                        player.setNumberOfWins(player.getNumberOfWins() + 1);

                                        result = player.getName() + " wins\n Number of wins=>" + player.getNumberOfWins();
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println(player.getName() + " now has " + player.getNumberOfWins() + " wins");
                                    }
                                    if (computer.getScore() > player.getScore()) {
                                        System.out.println("Computer wins!!!");
                                        player.setNumberOfWins(computer.getNumberOfWins() + 1);

                                        result = computer.getName() + " wins\n Number of wins=>" + computer.getNumberOfWins();
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println("Computer gets a win w now has" + computer.getNumberOfWins());
                                    }
                                    if (computer.getScore() == player.getScore()) {
                                        System.out.println("Its a tie nobody gets a win");

                                        result = "Nobody wins its a tie";
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                    }
                                    player.setScore(0);
                                    computer.setScore(0);
                                }
                            }
                            break;


                        case "4":
                            System.out.println("You selected Winter Water type Pokemon gain 1 Defense and Fire type Pokemon loss 1 Defense");
                            while (playCounter < numberOfPlays) {

                                services.battle(computer.computerPlayCards(cardsInHandPlayer, playField, numberOfCards), player.playCard(cardsInHandComputer, playField, numberOfCards), season, player, computer, playField);

                                playCounter++;

                                if (playCounter == numberOfPlays) {
                                    System.out.println("Final score board");
                                    System.out.println(player.getName() + " has " + player.getScore() + " points");
                                    System.out.println(computer.getName() + " has " + computer.getScore() + " points");


                                    if (player.getScore() > computer.getScore()) {

                                        System.out.println(player.getName() + " wins!!!");
                                        player.setNumberOfWins(player.getNumberOfWins() + 1);

                                        result = player.getName() + " wins\n Number of wins=>" + player.getNumberOfWins();
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println(player.getName() + " now has " + player.getNumberOfWins() + " wins");
                                    }
                                    if (computer.getScore() > player.getScore()) {

                                        System.out.println("Computer wins!!!");
                                        player.setNumberOfWins(computer.getNumberOfWins() + 1);

                                        result = computer.getName() + " wins\n Number of wins=>" + computer.getNumberOfWins();
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();
                                        System.out.println("Computer gets a win w now has" + computer.getNumberOfWins());
                                    }
                                    if (computer.getScore() == player.getScore()) {
                                        result = "Nobody wins its a tie";
                                        services.openBattleFile(battleFile);
                                        services.writeResultInFile(result);
                                        services.closeBattleFile();

                                        System.out.println("Its a tie nobody gets a win");
                                    }
                                    player.setScore(0);
                                    computer.setScore(0);
                                }
                            }
                            break;
                    }
                    break;
                case 8:
                    services.editCards(pokemonList);

                    break;
                case 9:
                    HashMap<Integer, Player> aux2 = services.addPlayerToFile();
                    if (aux2 != null) {
                        for (Integer key : aux2.keySet()) {
                            if (!playersList.containsKey(key))
                                playersList.put(key, aux2.get(key));
                        }
                    }
                    System.out.println(playersList);


                    System.out.println("What is the name you want to know the stats");
                    String name = sc.next();

                    for (Map.Entry<Integer, Player> elements : playersList.entrySet()) {
                        if (elements.getValue().getName().equals(name)) {
                            System.out.println("Hello " + elements.getValue().getName());
                            System.out.println("What is the stats you want to look to?");
                            System.out.println("(1) Average of wins per play");
                            System.out.println("(2) Average number of wins");
                            System.out.println("(3) How many wins you have");


                            int opc2 = sc.nextInt();

                            switch (opc2) {
                                case 1:

                                    elements.getValue().calculateMeanWinsPerPlayer(elements.getValue().getNumberOfPlays(), elements.getValue().getNumberOfGames());
                                    break;
                                case 2:

                                    double divisionResult =(double) elements.getValue().getNumberOfWins() / elements.getValue().getNumberOfGames();

                                    System.out.printf("Resultado: %.2f",divisionResult);
                                    break;
                                case 3:
                                    System.out.println("Number of wins "+elements.getValue().getNumberOfWins());


                            }
                        }

                    }

                    break;
                case -1:
                    break;
            }

        }
    }
}



