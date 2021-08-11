package Pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {
    private int score;
    private String name;
    private int numberOfWins;
    private int numberOfGames;
    private int numberOfPlays;

    public Player() {

    }

    public Player(int score, String name) {
        this.score = 0;
        this.name = name;
    }

    public Player(int score, String name, int numberOfWins, int numberOfGames, int numberOfPlays) {
        this.score = score;
        this.name = name;
        this.numberOfWins = numberOfWins;
        this.numberOfGames = numberOfGames;
        this.numberOfPlays = numberOfPlays;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public void setNumberOfPlays(int numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Pokemon playCard(ArrayList<Pokemon> cardsInHand, ArrayList<Pokemon> playField, int numberOfCards) {
        Scanner sc = new Scanner(System.in);
        int opc;
        boolean done = false;

        System.out.println("What card you whant to play? \n");
        System.out.println("Cards in your hand please play one");
        for (int i = 0; i < cardsInHand.size(); i++) {

            System.out.println("(" + i + ")=> " + "Name: " + cardsInHand.get(i).getName() + " Attack: " + cardsInHand.get(i).getAtack() + " Defense: " + cardsInHand.get(i).getDefense() + " Type: " + cardsInHand.get(i).getType());
        }

        opc = sc.nextInt();

        if (opc < 0 || opc >= cardsInHand.size()) {
            System.out.println("That index is impossible to access please choose another");
            do {
                for (int i = 0; i < cardsInHand.size(); i++) {

                    System.out.println("(" + i + ")=> " + "Name: " + cardsInHand.get(i).getName() + " Attack: " + cardsInHand.get(i).getAtack() + " Defense: " + cardsInHand.get(i).getDefense() + " Type: " + cardsInHand.get(i).getType());

                }
                opc = sc.nextInt();

            } while (opc < 0 || opc >= cardsInHand.size());

        }


        System.out.println("You played " + cardsInHand.get(opc));
        playField.add(cardsInHand.get(opc));


        Pokemon auxPokemon = cardsInHand.get(opc);
        cardsInHand.remove(opc);
        return auxPokemon;


    }


    public Pokemon computerPlayCards(ArrayList<Pokemon> cardsInHand, ArrayList<Pokemon> playField, int numberOfPLays) {
        Services services = new Services();
        Random r = new Random();
        Pokemon opc = cardsInHand.get(r.nextInt(cardsInHand.size()));
        services.cleanPlayField(playField);

        System.out.println("Cartas computador");
        for (int i = 0; i < cardsInHand.size(); i++) {
            System.out.println("(" + i + ")=> " + "Name: " + cardsInHand.get(i).getName() + " Atack: " + cardsInHand.get(i).getAtack() + " Defense: " + cardsInHand.get(i).getDefense() + " Type: " + cardsInHand.get(i).getType());
        }


        playField.add(opc);
        Pokemon pokemonAux = cardsInHand.get(cardsInHand.indexOf(opc));


        System.out.println("The computer played");
        System.out.println("Name: " + opc.getName() + " Atack: " + opc.getAtack() + " Defense: " + opc.getDefense() + " Type " + opc.getType());
        cardsInHand.remove(opc);
        return pokemonAux;

    }

    public void calculateMeanWinsPerPlayer(int numberOfPlays, int numberOfGames) {
        System.out.println("This is the mean Win of plays you have\n");
        double result = numberOfPlays / numberOfGames;

        System.out.print(result);

    }

    public void caculateAverageNumberOfWins(int numberOfWins, int numberOfGames) {
        System.out.println("This is the Mean number of wins you have\n");

        double result = numberOfWins / numberOfGames;

        System.out.print(result);
    }


    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", numberOfWins=" + numberOfWins +
                ", numberOfGames=" + numberOfGames +
                ", numberOfPlays=" + numberOfPlays +
                '}';
    }
}