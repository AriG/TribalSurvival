package Classes;
import Main.*;

public class Peasant extends AbsMember {

	public Peasant() {
		super('P',999,"Support");
	}
	
	static public boolean findMatch(char identifier){
		switch(identifier){
		case 'C': return true;
		case 'F': return true;
		//case 'G': return true;
		//case 'L': return true;
		case 'M': return true;
		case 'T': return true;
		case 'W': return true;
		default : return false;
		}
	}
	
	static public AbsMember change(char identifier){
		switch(identifier){
		case 'C': return new Civilian();
		case 'F': return new Farmer();
		case 'G': return new Guard();
		case 'L': return new Leader();
		case 'M': return new Medic();
		case 'T': return new Thief();
		case 'W': return new Warrior();
		default : return new Peasant();
		}
	}

	public void act(GameState gs, int tribe, int turn) {
		if(this.turnOrder <= turn ){
			this.acted = false;
		}
	}
	
	
}
