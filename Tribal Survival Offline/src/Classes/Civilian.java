package Classes;
import Main.*;


public class Civilian extends AbsMember{

	public int goldAmnt = 1;
	public int babyAmnt = 1;
	
	public Civilian() {
		super('C', 110, "Producer");
		this.chance = .500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn && this.status==status.NORMAL){
			gs.tribes.get(tribe).resources.get(AbsMember.GOLD).amount+=goldAmnt;
			if(this.rand()<=this.chance)
				gs.tribes.get(tribe).resources.get(AbsMember.BABIES).amount+=babyAmnt;
			this.acted = true;
		}
	}

	void reset() {
		goldAmnt = 1;
		babyAmnt = 1;
	}

}
