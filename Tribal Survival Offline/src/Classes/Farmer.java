package Classes;
import Main.*;


public class Farmer extends AbsMember{

	public Farmer() {
		super('F', 100, "Producer");
		this.chance=.500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn && this.status=="Normal"){
			gs.tribes.get(tribe).resources.get(AbsMember.FOOD).amount+=1;
			if(this.rand()<=this.chance){
				gs.tribes.get(tribe).resources.get(AbsMember.FOOD).amount+=2;	
				this.succeeded = true;
			}
		this.acted = true;
		}
	}
}
