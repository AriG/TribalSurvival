package Classes;
import Main.*;

public class Medic extends AbsMember{

	int target;
	int heal;
	int healAmnt = 2;
	
	public Medic() {
		super('M', 210, "Defender");
		this.chance=.500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn){
			if(this.rand() <= this.chance && !gs.tribes.get(tribe).allHealed()){
				this.succeeded = true;
				heal=healAmnt;
				while(heal > 0){
					if(this.status==status.WOUNDED){
						this.status = this.status.NORMAL;
						heal=0;
					}
					else if(this.status==this.status.DYING){
						this.status = status.WOUNDED;
						heal=0;
					}
					else if (!gs.tribes.get(tribe).allHealed()){ //"Normal"
						System.out.println("Please input the first member of the tribe you would like to heal:");
						if(gs.s.hasNextInt()){
							target = gs.s.nextInt()-1;
							if(target >= gs.tribes.get(tribe).members.size() || target <= 0)
								System.out.println("I'm sorry, the number you have input is out of range.");
							else{
								switch(gs.tribes.get(tribe).members.get(target).status){
								case WOUNDED:
									gs.tribes.get(tribe).members.get(target).status=gs.tribes.get(tribe).members.get(target).status.NORMAL;
									heal--;
									break;
								case DYING:
									gs.tribes.get(tribe).members.get(target).status=gs.tribes.get(tribe).members.get(target).status.WOUNDED;
									heal--;
									break;
								case NORMAL:
									System.out.println("I'm sorry, your target is not Wounded or Dying.");
									break;
								default:
									System.out.println("Error.");
								}
							}
						}
					}
				}
			}
		this.acted = true;
		}
	}

	void reset() {
		healAmnt = 2;
	}
}
