package Pokemon;

import java.io.*;

public abstract class Pokemon implements Serializable {
	
	private String name;
	private int atack;
	private int defense;
	private String type;
	private String category;
	private String number;
	
	
	
	
	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}




	@Override
	public String toString() {
		return new StringBuffer().append("Name =>").append(this.name).append(" Atack =>").append(this.atack).append(" Defense =>").append(this.defense).append(" Type =>").append(this.type).append(" Category =>").append(this.category).append("]").toString();

	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAtack() {
		return atack;
	}



	public int setAtack(double d) {
		return this.atack = (int) d;
	}



	public int getDefense() {
		return defense;
	}



	public int setDefense(int defense) {
		return this.defense = defense;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}
	
	public abstract int atack(String type);
	
	public abstract int defense (String season);
	
	
	
	
	public Pokemon(String name, int atack, int defense, String type, String category) {
		super();
		this.name = name;
		this.atack = atack;
		this.defense = defense;
		this.type = type;
		this.category = category;

		
	}
	
	

}
