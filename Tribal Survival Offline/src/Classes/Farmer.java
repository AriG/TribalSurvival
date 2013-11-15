package Classes;
import Main.*;


public class Farmer extends AbsMember{

	public int foodMin =1;
	public int foodMax =3;
	
	public Farmer() {
		super('F', 100, "Producer");
		this.chance=.500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn && this.status=="Normal"){
			gs.tribes.get(tribe).resources.get(AbsMember.FOOD).amount+=foodMin;
			if(this.rand()<=this.chance){
				gs.tribes.get(tribe).resources.get(AbsMember.FOOD).amount+=(foodMax-foodMin);	
				this.succeeded = true;
			}
		this.acted = true;
		}
	}

	void reset() {
		foodMin =1;
		foodMax =3;
	}
}
