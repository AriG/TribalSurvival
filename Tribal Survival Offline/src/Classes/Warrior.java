package Classes;
import Main.*;

public class Warrior extends AbsMember{

	int targetMember;
	int targetTribe;
	
	public Warrior() {
		super('W', 300, "Attacker");
		this.chance=.500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn && this.status!="Dying"){
			if(this.rand()<=this.chance && gs.enemyHasAttackable(tribe)){
				this.succeeded = true;
				do{
					System.out.print("Please input the enemy tribe tribe");
					System.out.print(tribe+1);
					System.out.print(" would like to attack:");
					System.out.println();
					if(gs.s.hasNextInt()){
						targetTribe = gs.s.nextInt()-1;
						if(targetTribe>gs.tribes.size() || targetTribe<0)
							System.out.println("I'm sorry, the number you have input is out of range.");
						else if(targetTribe==tribe)
							System.out.println("I'm afraid I can't let you do that.");
					}
				}
				while(targetTribe>gs.tribes.size()||targetTribe<0||targetTribe==tribe && !gs.tribes.get(targetTribe).allDying());
				do{
					System.out.println("Please input the member you would like to attack:");
					if(gs.s.hasNextInt()){
						targetMember = gs.s.nextInt()-1;
						if(targetMember>gs.tribes.get(targetTribe).members.size()||targetMember<0)
							System.out.println("I'm sorry, the number you have input is out of range.");
						else
							switch(gs.tribes.get(targetTribe).members.get(targetMember).status){
								case "Normal": 
									gs.tribes.get(targetTribe).members.get(targetMember).status="Wounded";
									this.acted=true;
									break;
								case "Wounded":
									gs.tribes.get(targetTribe).members.get(targetMember).status="Dying";
									this.acted=true;
									break;
								case "Dying":
									System.out.println("The member you are targetting is already dying.");
									break;
								default:
									System.out.println("Error");
						}
					}
				}
				while(this.acted=false);
			}
			this.acted = true;
			gs.printUpdate();
		}
	}
}
