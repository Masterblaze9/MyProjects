package Pokemon;

public class Water extends Pokemon{
	
	public Water(String name, int atack, int defense, String category) {
		super(name, atack, defense,"Water", category);
		
	}

	@Override
	public int atack(String type) {
		
		switch (type) {
		
		case "Fire":
			return super.setAtack(getAtack()*2);
		
		case "Water":
			return  (int) (super.setAtack((int)(super.getAtack()*0.5)));
		
		case "Eletric":
			return super.getAtack();
			
		case "Grass":
			return   (int) (super.setAtack((int)(super.getAtack()*0.5)));
		}
		
		return 0;
	}

	@Override
	public int defense(String season) {
		
		switch (season) {
		
		case "2":
			return super.setDefense(getDefense()-1);
			
		case "4":
			return super.setDefense(getDefense()+1);
		}
		
		return 0;
	}

}
