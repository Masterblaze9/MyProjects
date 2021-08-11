package Pokemon;

public class Eletric extends Pokemon{
	
	public Eletric(String name, int atack, int defense, String category) {
		super(name, atack, defense,"Eletric", category);
		
	}

	@Override
	public int atack(String type) {
		switch(type) {
		
		case "Fire":
			return super.getAtack();
			
		case "Water":
			return super.setAtack(getAtack()*2);
		
		case "Eletric":
			return (int)(super.setAtack(getAtack())*0.5);
			
		case "Grass":
			return (int)(super.setAtack(getAtack())*0.5);
		}
		
		return 0;
	}

	@Override
	public int defense(String season) {
		
		switch (season) {
		
		case "1":
			return super.setDefense(getDefense()-1);
		
		case "3":
			return super.setDefense(getDefense()+1);
		}
		return 0;
	}

	

}
