package Pokemon;


import java.io.*;
import java.util.*;

public class Services {

    File fileName = new File("PokemonDB2");
    File battleFile = new File("Battle");
    File playerFile = new File("PlayerDB");
    Scanner sc = new Scanner(System.in);


    ArrayList<Pokemon> pokemonList = new ArrayList<>();
    HashMap<String, Pokemon> pokemonHashMap = new HashMap<String, Pokemon>();
    HashMap<Integer, Player> playerList = new HashMap<Integer, Player>();
    ObjectInputStream inputObject;
    ObjectInputStream playerInputObject;
    ObjectOutputStream fos;
    //FileWriter
    FileWriter fw;
    //BufferWriter
    BufferedWriter bR;
    private Object Pokemon;


    public Services() {

        ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
        HashMap<String, Pokemon> pokemonHashMap = new HashMap<String, Pokemon>();
    }


    public void insertElements() {
        String pokemonName;
        int pokemonType;
        int atack;
        int defense;
        boolean done = false;
        String category;
        String number;
        String stringRegex = "^[a-zA-Z]+$";
        String stringNumberRegex = "[0-9]+";


        System.out.println("You want to add a new Pokemon");
        System.out.println("Insert the name:");
        pokemonName = sc.next();
        if (pokemonName.matches(stringRegex)) {
            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                if (elements.getValue().getName().contains(pokemonName)) {
                    System.out.println("The name of that pokemon is already in the database please enter a new one");
                    System.out.println("Insert the name:");
                    pokemonName = sc.next();

                }
            }
        } else {
            System.out.println("The input needs to be only letters");
            while (!pokemonName.matches(stringRegex)) {
                System.out.println("You want to add a new Pokemon");
                System.out.println("Insert the name:");
                pokemonName = sc.next();
            }
        }
        while (!done) {
            try {
                System.out.println("Insert the atack");
                atack = sc.nextInt();
                done = true;
                if (atack < 1) {
                    System.out.println("You need to insert only positive numbers");
                    while (atack < 1) {
                        System.out.println("Insert the atack");
                        atack = sc.nextInt();
                    }
                }

                System.out.println("Insert the defense");
                done = false;
                defense = sc.nextInt();
                done = true;

                if (defense < 1) {
                    System.out.println("You need to insert only positive numbers");
                    while (defense < 1) {
                        System.out.println("Insert the defense");
                        defense = sc.nextInt();
                    }
                }

                System.out.println("Insert the category");
                category = sc.next();
                if (!category.matches(stringRegex)) {
                    System.out.println("The input needs to be only letters");
                    while (!category.matches(stringRegex)) {
                        System.out.println("Insert the category");
                        category = sc.next();
                    }
                }
                System.out.println("Insert the number in the pokedex");
                number = sc.next();
                if (number.matches(stringNumberRegex)) {
                    for (int i = 0; i < pokemonList.size(); i++) {
                        if (pokemonHashMap.containsKey("#" + number)) {
                            System.out.println("The number is already in the database plase entrer a new one");
                            System.out.println("Insert the number in the pokedex");
                            number = sc.next();
                        } else {
                            break;
                        }
                    }
                } else {
                    System.out.println("You have to insert only numbers in this input");
                    while (!number.matches(stringNumberRegex)) {
                        number = sc.next();
                    }
                }
                System.out.println("Insert the type\n(1)Fire\n(2)Water\n(3)Eletric\n(4)Grass\n(5)Return to main menu");
                pokemonType = sc.nextInt();

                if (pokemonType > 0 && pokemonType <= 5) {
                    switch (pokemonType) {

                        case 1:
                            Pokemon f = new Fire(pokemonName, atack, defense, category);
                            pokemonHashMap.put("#" + number, f);
                            writeObjectToFile(f);
                            System.out.println("Pokemon of Fire type inserted ");
                            break;

                        case 2:

                            Pokemon w = new Water(pokemonName, atack, defense, category);
                            pokemonHashMap.put("#" + number, w);
                            System.out.println("Pokemon of Water type inserted ");
                            writeObjectToFile(w);

                            break;
                        case 3:
                            Pokemon e = new Eletric(pokemonName, atack, defense, category);
                            pokemonHashMap.put("#" + number, e);
                            System.out.println("Pokemon of Eletric type inserted ");

                            break;
                        case 4:
                            Pokemon g = new Grass(pokemonName, atack, defense, category);
                            pokemonHashMap.put("#" + number, g);
                            System.out.println("Pokemon of Grass type inserted ");

                            break;
                        case 5:
                            break;

                    }

                } else {
                    System.out.println("Wrong option please insert a valid option ");

                    insertElements();
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input please verify your input");
                sc.nextLine();
            }
        }
        this.writeObjectToFile(this.pokemonHashMap);
    }


    public void showByType() throws IOException {

        if (pokemonHashMap.isEmpty()) {

            System.out.println("The list is empty add some Pokemon first");
            insertElements();


        } else {

            int type;
            boolean done = false;
            while (!done) {
                try {
                    do {
                        System.out.println("Select the type you want to search");

                        System.out.println("Insert the type\n(1)Fire\n(2)Water\n(3)Eletric\n(4)Grass");
                        type = sc.nextInt();
                        done = true;
                    } while (type > 4 || type < 1);

                    switch (type) {

                        case 1:
                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Fire")) {
                                    System.out.println("The Pokemon of Fire type " + elements.getValue().getName() + " has been found");
                                }
                            }

                            break;

                        case 2:

                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Water")) {
                                    System.out.println("The Pokemon of Water type " + elements.getValue().getName() + " has been found");
                                }
                            }
                            break;

                        case 3:
                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Eletric")) {
                                    System.out.println("The Pokemon of Eletric type " + elements.getValue().getName() + " has been found");
                                }
                            }
                            break;
                        case 4:
                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Grass")) {
                                    System.out.println("The Pokemon of Grass type " + elements.getValue().getName() + " has been found");
                                }
                            }
                            break;

                    }
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    System.out.println("Invalid input please check your input\n");


                }
            }
        }
    }


    public void showByName() {

        if (pokemonHashMap.isEmpty()) {
            System.out.println("The list is empty insert some Pokemon first");
            insertElements();


        } else {
            String name;
            String stringRegex = "^[a-zA-Z]+$";
            System.out.println("Insert the name of the pokemon you what to search");
            name = sc.next();
            if (name.matches(stringRegex)) {
                for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {

                    if (elements.getValue().getName().equals(name)) {
                        System.out.println("The Pokemon with name " + elements.getValue().getName() + " has been found");
                    }

                }
            } else {
                System.out.println("The input needs to be only letters");
                while (!name.matches(stringRegex)) {
                    System.out.println("Insert the name of the pokemon you what to search :");
                    name = sc.next();
                }

            }
        }

    }

    public void showByTypeAndByNameNoInput() {

        if (pokemonHashMap.isEmpty()) {
            System.out.println("The list is empty add some Pokemon first");
            insertElements();


        } else {
            boolean done = false;
            while (!done) {
                try {


                    int type;
                    do {
                        System.out.println("Select the type you want to search");

                        System.out.println("Insert the type\n(1)Fire\n(2)Water\n(3)Eletric\n(4)Grass");
                        type = sc.nextInt();
                        done = true;
                        if (type > 4 || type < 1) {
                            System.out.println("No negative numbers or the option you choose is not between 1 and 4");
                        }
                    } while (type > 4 || type < 1);


                    switch (type) {

                        case 1:
                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Fire")) {
                                    System.out.println("The Pokemon of Fire type " + elements.getValue().getName() + " has been found");
                                }
                            }


                            break;

                        case 2:
                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Water")) {
                                    System.out.println("The Pokemon of Water type " + elements.getValue().getName() + " has been found");
                                }
                            }


                            break;

                        case 3:
                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Eletric")) {
                                    System.out.println("The Pokemon of Electric type " + elements.getValue().getName() + " has been found");
                                }
                            }


                            break;
                        case 4:
                            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                                if (elements.getValue().getType().equals("Grass")) {
                                    System.out.println("The Pokemon of Grass type " + elements.getValue().getName() + " has been found");
                                }
                            }
                            break;

                    }
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    System.out.println("Invalid input this input is only for numbers");

                }
            }
        }
    }

    public void showByNameAndByTypeInput() throws InputMismatchException {
        if (pokemonList.isEmpty()) {

            System.out.println("THe list is empty please enter some Pokemon first");
            insertElements();

        } else {
            String stringRegex = "^[a-zA-Z]+$";
            String name;
            boolean done = false;

            System.out.println("Insert the name of the pokemon you what to search:");
            name = sc.next();
            if (name.matches(stringRegex)) {
                ArrayList<Pokemon> aux = new ArrayList<Pokemon>();
                int type;
                try {

                    do {
                        System.out.println("Select the type you want to search");

                        System.out.println("Insert the type\n(1)Fire\n(2)Water\n(3)Eletric\n(4)Grass");

                        type = sc.nextInt();
                        done = true;
                        if (type > 4 || type < 1) {
                            System.out.println("No negative numbers or the option you choose is not between 1 and 4");
                        }

                    } while (type > 4 || type < 1);

                    switch (type) {

                        case 1:
                            for (int i = 0; i < pokemonList.size(); i++) {

                                if (pokemonList.get(i) instanceof Fire && pokemonList.get(i).getName().equals(name)) {

                                    aux.add(pokemonList.get(i));
                                }
                            }

                            for (int j = 0; j < aux.size(); j++) {

                                System.out.println("The Pokemon of Fire type " + aux.get(j).getName());
                            }

                            aux.clear();
                            break;

                        case 2:

                            for (int i = 0; i < pokemonList.size(); i++) {

                                if (pokemonList.get(i) instanceof Water && pokemonList.get(i).getName().equals(name)) {

                                    aux.add(pokemonList.get(i));
                                }
                            }

                            for (int j = 0; j < aux.size(); j++) {

                                System.out.println("The Pokemon of Water type " + aux.get(j).getName());
                            }
                            aux.clear();
                            break;

                        case 3:
                            for (int i = 0; i < pokemonList.size(); i++) {

                                if (pokemonList.get(i) instanceof Eletric && pokemonList.get(i).getName().equals(name)) {

                                    aux.add(pokemonList.get(i));
                                }
                            }
                            for (int j = 0; j < aux.size(); j++) {

                                System.out.println("The Pokemon of Eletric type " + aux.get(j).getName());
                            }
                            aux.clear();
                            break;
                        case 4:
                            for (int i = 0; i < pokemonList.size(); i++) {

                                if (pokemonList.get(i) instanceof Grass && pokemonList.get(i).getName().equals(name)) {

                                    aux.add(pokemonList.get(i));
                                }
                            }
                            for (int j = 0; j < aux.size(); j++) {

                                System.out.println("The Pokemon of Grass type " + aux.get(j).getName());
                            }
                            aux.clear();
                            break;

                    }
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    System.out.println("Invalid input please enter a number\n");
                }


            } else {
                System.out.println("The input needs to be only letters");
                while (!name.matches(stringRegex)) {
                    System.out.println("Insert the name of the pokemon you what to search:");
                    System.out.println("Insert the name:");
                    name = sc.next();

                    ArrayList<Pokemon> aux = new ArrayList<Pokemon>();
                    int type;
                    try {

                        do {
                            System.out.println("Select the type you want to search");

                            System.out.println("Insert the type\n(1)Fire\n(2)Water\n(3)Eletric\n(4)Grass");

                            type = sc.nextInt();
                            done = true;
                            if (type > 4 || type < 1) {
                                System.out.println("No negative numbers or the option you choose is not between 1 and 4");
                            }

                        } while (type > 4 || type < 1);

                        switch (type) {

                            case 1:
                                for (int i = 0; i < pokemonList.size(); i++) {

                                    if (pokemonList.get(i) instanceof Fire && pokemonList.get(i).getName().equals(name)) {

                                        aux.add(pokemonList.get(i));
                                    }
                                }

                                for (int j = 0; j < aux.size(); j++) {

                                    System.out.println("The Pokemon of Fire type " + aux.get(j).getName());
                                }

                                aux.clear();
                                break;

                            case 2:

                                for (int i = 0; i < pokemonList.size(); i++) {

                                    if (pokemonList.get(i) instanceof Water && pokemonList.get(i).getName().equals(name)) {

                                        aux.add(pokemonList.get(i));
                                    }
                                }

                                for (int j = 0; j < aux.size(); j++) {

                                    System.out.println("The Pokemon of Water type " + aux.get(j).getName());
                                }
                                aux.clear();
                                break;

                            case 3:
                                for (int i = 0; i < pokemonList.size(); i++) {

                                    if (pokemonList.get(i) instanceof Eletric && pokemonList.get(i).getName().equals(name)) {

                                        aux.add(pokemonList.get(i));
                                    }
                                }
                                for (int j = 0; j < aux.size(); j++) {

                                    System.out.println("The Pokemon of Eletric type " + aux.get(j).getName());
                                }
                                aux.clear();
                                break;
                            case 4:
                                for (int i = 0; i < pokemonList.size(); i++) {

                                    if (pokemonList.get(i) instanceof Grass && pokemonList.get(i).getName().equals(name)) {

                                        aux.add(pokemonList.get(i));
                                    }
                                }
                                for (int j = 0; j < aux.size(); j++) {

                                    System.out.println("The Pokemon of Grass type " + aux.get(j).getName());
                                }
                                aux.clear();
                                break;

                        }
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("Invalid input please enter a number\n");
                    }
                }
            }
        }
    }

    //File part
    public void openFileToWrite(File f) {
        try {
            FileOutputStream fs = new FileOutputStream(fileName);
            fos = new ObjectOutputStream(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObjectToFile(Object pokemon) {
        try {
            this.openFileToWrite(fileName);
            fos.writeObject(pokemon);
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openFileToWritePlayerList(File f) {
        try {
            FileOutputStream fs = new FileOutputStream(playerFile);
            fos = new ObjectOutputStream(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObjectToFilePlayerList(Object player) {
        try {
            this.openFileToWritePlayerList(playerFile);
            fos.writeObject(player);
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openFile(File f) {
        try {
            FileInputStream input = new FileInputStream(f);
            inputObject = new ObjectInputStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closePokemonFile() throws IOException {
        inputObject.close();
    }

    public Object readFile() {
        try {
            return inputObject.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("NAO CONSEGUIU LER");
            e.printStackTrace();
        }
        return null;
    }

    public int openPlayerFile() {
        try {
            FileInputStream input = new FileInputStream("PlayerDB");
            playerInputObject = new ObjectInputStream(input);
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

    public Object readFilePLayers() {
        try {
            return playerInputObject.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void openBattleFile(File battleFile) throws IOException {
        bR = new BufferedWriter(new FileWriter(battleFile, true));
    }


    public void writeResultInFile(String result) throws IOException {
        bR.write(result);
        bR.newLine();
    }

    public void closeBattleFile() throws IOException {
        bR.close();
    }

    public HashMap<String, Pokemon> addFileToHashMap() {
        HashMap<String, Pokemon> aux = (HashMap<String, Pokemon>) this.readFile();
        for (Map.Entry<String, Pokemon> elements : aux.entrySet()) {
            elements.getValue().toString();
        }
        pokemonHashMap = aux;

        return pokemonHashMap;
    }

    public HashMap<Integer, Player> addPlayerToFile() {
        int check = openPlayerFile();
        if (check != 0) {
            HashMap<Integer, Player> aux = (HashMap<Integer, Player>) this.readFilePLayers();
            if (aux != null) {
                for (Integer key : aux.keySet()) {
                    if (!playerList.containsKey(key))
                        playerList.put(key, aux.get(key));
                }

                return playerList;
            }
        }
        return null;
    }

    public ArrayList<Pokemon> hashMapToArrayList(HashMap<String, Pokemon> pokemonHashMap) {
        for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
            pokemonList.add(elements.getValue());
        }
        return pokemonList;
    }


    public void showHashMap(HashMap<String, Pokemon> pokemonHashMap) {
        for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
            System.out.println("Key " + elements.getKey() + " Values " + elements.getValue());
        }
    }


    public ArrayList<Pokemon> shuffleCards(ArrayList<Pokemon> pokemonList, int numberOfMoves) {

        ArrayList<Pokemon> cardsShuffled = new ArrayList<>(numberOfMoves);
        Random r = new Random();

        Collections.shuffle(pokemonList);

        for (int i = 0; i < numberOfMoves; i++) {
            int cardToAdd = r.nextInt(pokemonList.size());
            cardsShuffled.add(pokemonList.get(cardToAdd));

        }

        return cardsShuffled;
    }

    public void printConsoleToFile(File f) throws FileNotFoundException {
        final boolean append = true;
        final boolean autoFlush = true;

        PrintStream printStream = new PrintStream(new FileOutputStream(f, append), autoFlush);

        System.setOut(printStream);
    }

    public void editCards(ArrayList<Pokemon> pokemonList) throws IOException {
        String opc;
        String name;
        int atack;
        int defense;
        String type;
        String category;
        String number;
        int opc2;
        boolean done = false;
        String stringNumberRegex = "[0-9]+";
        String stringRegex = "^[a-zA-Z]+$";

        if (pokemonHashMap.isEmpty()) {
            System.out.println("The list is empty please insert some Pokemon first");
            this.insertElements();
        } else {
            System.out.println("This are the Pokemon you have in your list");

            for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                System.out.println("Key " + elements.getKey() + " Values " + elements.getValue());
            }
            System.out.println("What is the key of the Pokemon you want to change?");
            while (!done) {
                try {
                    opc = sc.next();
                    if (opc.matches(stringNumberRegex)) {
                        for (Map.Entry<String, Pokemon> elements : pokemonHashMap.entrySet()) {
                            if (elements.getKey().equals("#" + opc)) {

                                System.out.println("You want to edit " + pokemonHashMap.get("#" + opc));
                                System.out.println("What is the feature you want to change?");
                                System.out.println("(1) Name");
                                System.out.println("(2) Attack");
                                System.out.println("(3) Defense");
                                System.out.println("(4) Type");
                                System.out.println("(5) Category");
                                System.out.println("(6) Number");
                                opc2 = sc.nextInt();
                                done = true;

                                switch (opc2) {
                                    case 1:
                                        System.out.println("You want to change " + pokemonHashMap.get("#" + opc).getName() + "name\n What is the new name?");
                                        name = sc.next();

                                        if (name.matches(stringRegex)) {
                                            pokemonHashMap.get("#" + opc);
                                            pokemonHashMap.get("#" + opc).setName(name);
                                            this.writeObjectToFile(pokemonHashMap);
                                            break;
                                        } else {
                                            System.out.println("That input needs to be only letters,no special characters or numbers allowed");
                                            break;
                                        }

                                    case 2:
                                        System.out.println("You want to change " + pokemonHashMap.get("#" + opc).getName() + " attack\n What is the new attack?");
                                        atack = sc.nextInt();
                                        if (atack >= 1) {
                                            pokemonHashMap.get("#" + opc).setAtack(atack);
                                            this.writeObjectToFile(pokemonHashMap);
                                            break;
                                        } else {
                                            System.out.println("You cant have Pokemon with 0 or less attack");
                                            break;
                                        }

                                    case 3:
                                        System.out.println("You want to change " + pokemonHashMap.get("#" + opc).getName() + " defense\n What is the new defense?");
                                        defense = sc.nextInt();
                                        if (defense >= 1) {
                                            pokemonHashMap.get("#" + opc).setDefense(defense);
                                            this.writeObjectToFile(pokemonHashMap);
                                            break;
                                        } else {
                                            System.out.println("You cant have Pokemon with 0 or less defense");
                                            break;
                                        }


                                    case 4:
                                        System.out.println("You want to change " + pokemonHashMap.get("#" + opc).getName() + " type\n What is the new type?");
                                        System.out.println("(1) Fire");
                                        System.out.println("(2) Water");
                                        System.out.println("(3) Eletric");
                                        System.out.println("(4) Grass");
                                        int opc3 = sc.nextInt();

                                        switch (opc3) {
                                            case 1:
                                                Pokemon f = new Fire(pokemonHashMap.get("#" + opc).getName(), pokemonHashMap.get("#" + opc).getAtack(), pokemonHashMap.get("#" + opc).getDefense(), pokemonHashMap.get("#" + opc).getCategory());
                                                pokemonHashMap.put("#" + opc, f);
                                                this.writeObjectToFile(pokemonHashMap);
                                                break;
                                            case 2:
                                                Pokemon w = new Water(pokemonHashMap.get("#" + opc).getName(), pokemonHashMap.get("#" + opc).getAtack(), pokemonHashMap.get("#" + opc).getDefense(), pokemonHashMap.get("#" + opc).getCategory());
                                                pokemonHashMap.put("#" + opc, w);
                                                this.writeObjectToFile(pokemonHashMap);
                                                break;
                                            case 3:
                                                Pokemon e = new Eletric(pokemonHashMap.get("#" + opc).getName(), pokemonHashMap.get("#" + opc).getAtack(), pokemonHashMap.get("#" + opc).getDefense(), pokemonHashMap.get("#" + opc).getCategory());
                                                pokemonHashMap.put("#" + opc, e);
                                                this.writeObjectToFile(pokemonHashMap);
                                                break;
                                            case 4:
                                                Pokemon g = new Grass(pokemonHashMap.get("#" + opc).getName(), pokemonHashMap.get("#" + opc).getAtack(), pokemonHashMap.get("#" + opc).getDefense(), pokemonHashMap.get("#" + opc).getCategory());
                                                pokemonHashMap.put("#" + opc, g);
                                                this.writeObjectToFile(pokemonHashMap);
                                                break;

                                        }
                                        System.out.println("Not a valid option");
                                        break;

                                    case 5:
                                        System.out.println("You want to change " + pokemonHashMap.get("#" + opc).getName() + " category\n What is the new category?");
                                        category = sc.next();
                                        if (category.matches(stringRegex)) {
                                            pokemonHashMap.get("#" + opc).setCategory(category);
                                            this.writeObjectToFile(pokemonHashMap);
                                            break;
                                        } else {
                                            System.out.println("That input can only have letters no numbers or special characters allowed");
                                            break;
                                        }

                                    case 6:
                                        System.out.println("You want to change " + pokemonHashMap.get("#" + opc).getName() + " number\n What is the new number?");
                                        number = sc.next();
                                        if (number.matches(stringNumberRegex)) {
                                            Pokemon aux = pokemonHashMap.get("#" + opc);
                                            pokemonHashMap.remove("#" + opc);
                                            pokemonHashMap.put("#" + number, aux);
                                            this.writeObjectToFile(pokemonHashMap);
                                            break;
                                        } else {
                                            System.out.println("You can only put numbers here");
                                        }

                                }

                            }
                        }
                    } else {
                        System.out.println("The input doesn't mack the criteria\n Please insert the Key you want to search for");
                    }

                } catch (InputMismatchException e) {
                    sc.nextLine();
                    System.out.println("The input is wrong please verify it");
                }
            }
        }
    }


    public void cleanPlayField(ArrayList<Pokemon> playField) {
        playField.removeAll(playField);

    }


    public void battle(Pokemon playerPokemon, Pokemon computerPokemon, String season, Player player, Player computer, ArrayList<Pokemon> playField) {

        playField.get(1).atack(playField.get(0).getType());
        playField.get(0).defense(season);


        System.out.println(player.getName() + "'s " + playField.get(1).getName() + " atacks with " + playField.get(1).getAtack() + " and " + computer.getName() + "'s " + playField.get(0).getName() + " defends with " + playField.get(0).getDefense());
        if (playField.get(1).getAtack() > playField.get(0).getDefense()) {
            if (player.getScore() >= 1) {
                player.setScore(player.getScore() + 2);

                System.out.println(player.getName() + " scores another point he now has " + player.getScore());
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }
            if (player.getScore() == 0) {
                System.out.println(player.getName() + " scores his first point");
                player.setScore(player.getScore() + 2);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }


        }

        if (playField.get(1).getAtack() < playField.get(0).getDefense()) {
            if (computer.getScore() >= 1) {
                computer.setScore(computer.getScore() + 2);
                System.out.println(computer.getName() + " scores another point he now has " + computer.getScore());
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }

            if (computer.getScore() == 0) {
                System.out.println("Computer scores is first point");
                computer.setScore(computer.getScore() + 2);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }


        }
        if (playField.get(1).getAtack() == playField.get(0).getDefense()) {
            System.out.println("The attack and defenses are the same so its a tie both players get a point");
            if (player.getScore() >= 1) {
                player.setScore(player.getScore() + 1);
                System.out.println(player.getName() + " scores another point he now has " + player.getScore());
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }
            if (player.getScore() == 0) {
                System.out.println(player.getName() + " scores his first point");
                player.setScore(player.getScore() + 1);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }

            if (computer.getScore() >= 1) {

                computer.setScore(computer.getScore() + 1);
                System.out.println(computer.getName() + " scores another point he now has " + computer.getScore());
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }
            if (computer.getScore() == 0) {
                System.out.println("Computer scores is first point");
                computer.setScore(computer.getScore() + 1);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }


        }

        playField.get(0).atack(playField.get(1).getType());
        playField.get(1).defense(season);

        System.out.println(computer.getName() + " 's " + playField.get(0).getName() + " atacks with " + playField.get(0).getAtack() + " and " + player.getName() + "'s " + playField.get(1).getName() + " defends with " + playField.get(1).getDefense());

        if (playField.get(0).getAtack() > playField.get(1).getDefense()) {
            if (computer.getScore() >= 1) {

                computer.setScore(computer.getScore() + 2);
                System.out.println(computer.getName() + " scores another point he now has " + computer.getScore());
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }

            if (computer.getScore() == 0) {
                System.out.println("Computer scores is first point");
                computer.setScore(computer.getScore() + 2);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }

        }

        if (playField.get(0).getAtack() < playField.get(1).getDefense()) {
            if (player.getScore() >= 1) {

                player.setScore(player.getScore() + 2);
                System.out.println(player.getName() + " scores another point he now has " + player.getScore());
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }

            if (player.getScore() == 0) {
                System.out.println(player.getName() + " scores his first point");
                player.setScore(player.getScore() + 2);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }


        }
        if (playField.get(0).getAtack() == playField.get(1).getDefense()) {
            System.out.println("its a tie");
            if (player.getScore() >= 1) {
                player.setScore(player.getScore() + 1);

                System.out.println(player.getName() + " scores another point he now has " + player.getScore());
                System.out.println("and " + computer.getName() + " also scores a point");
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }
            if (player.getScore() == 0) {
                System.out.println(player.getName() + " scores his first point");
                player.setScore(player.getScore() + 1);

                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }

            if (computer.getScore() >= 1) {
                computer.setScore(computer.getScore() + 1);
                System.out.println(computer.getName() + " scores another point he now has " + computer.getScore());
                System.out.println(player.getName() + " also gets a point");
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }
            if (computer.getScore() == 0) {
                System.out.println(computer.getName() + " scores is first point");
                System.out.println(player.getName() + " also gets a point");
                computer.setScore(computer.getScore() + 1);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }

            if (player.getScore() == 0 && computer.getScore() == 0) {
                System.out.println("Both " + player.getName() + " and" + computer.getName() + " scored their first points");
                player.setScore(player.getScore() + 1);
                computer.setScore(computer.getScore() + 1);
                System.out.println("The scoreboard is now");
                System.out.println(player.getName() + "=>" + player.getScore());
                System.out.println(computer.getName() + "=>" + computer.getScore());
            }


        }


    }

}



