
public class ClassCivilian extends AbsMember{

	public ClassCivilian() {
		super('C', 110, "Producer");
		this.chance = .500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn && this.status=="Normal"){
			gs.tribes.get(tribe).resources.get(AbsMember.GOLD).amount+=1;
			if(this.rand()<=this.chance)
				gs.tribes.get(tribe).resources.get(AbsMember.BABIES).amount+=1;
			this.acted = true;
		}
	}

}
