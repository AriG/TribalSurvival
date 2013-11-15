
public class ClassPeasant extends AbsMember {

	public ClassPeasant() {
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
		case 'C': return new ClassCivilian();
		case 'F': return new ClassFarmer();
		case 'G': return new ClassGuard();
		case 'L': return new ClassLeader();
		case 'M': return new ClassMedic();
		case 'T': return new ClassThief();
		case 'W': return new ClassWarrior();
		default : return new ClassPeasant();
		}
	}

	void act(GameState gs, int tribe, int turn) {
		if(this.turnOrder <= turn ){
			this.acted = false;
		}
	}
	
	
}
