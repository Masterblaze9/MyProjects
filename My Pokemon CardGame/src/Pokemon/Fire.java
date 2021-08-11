package Pokemon;

public class Fire extends Pokemon {


    public Fire(String name, int atack, int defense, String category) {
        super(name, atack, defense, "Fire", category);

    }


    @Override
    public int atack(String type) {

        switch (type) {

            case "Fire":
                return (int) (super.setAtack((int) (super.getAtack() * 0.5)));

            case "Water":
                return (int) (super.setAtack((int) (super.getAtack() * 0.5)));

            case "Eletric":
                return super.getAtack();

            case "Grass":
                return (super.setAtack((super.getAtack() * 2)));

        }

        return 0;
    }

    @Override
    public int defense(String season) {

        switch (season) {

            case "2":
                return super.setDefense((super.getDefense() + 1));

            case "4":
                return super.setDefense(getDefense() - 1);

        }
        return 0;
    }


}
