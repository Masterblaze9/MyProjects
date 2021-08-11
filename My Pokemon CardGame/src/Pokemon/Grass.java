package Pokemon;

public class Grass extends Pokemon{

	public Grass(String name, int atack, int defense,String category) {
		super(name, atack, defense, "Grass", category);
		
	}

	@Override
	public int atack(String type) {
		
		switch (type) {
		
		case "Fire":
			return  (int) (super.setAtack((int)(super.getAtack()*0.5)));
			
		case "Water":
			return super.setAtack(getAtack()*2);
			
		case "Eletric":
			return super.getAtack();
			
		case"Grass":
			return  (int) (super.setAtack((int)(super.getAtack()*0.5)));
		}		
		
		return 0;
	}

	@Override
	public int defense(String season) {
		
		switch (season) {
		
		case "1":
			return super.setDefense(getDefense()+1);
			
		case "3":
			return super.setDefense(getDefense()-1);
		}
		return 0;
	}

}
