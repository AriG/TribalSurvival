package Classes;
import Main.*;

public class Thief extends AbsMember{
	
	int targetTribe;
	char action;
	int foodAmnt =3;
	int goldAmnt =2;
	int babyAmnt =1;
	
	public Thief() {
		super('T', 310, "Attacker");
		this.chance=.500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn && this.status==this.status.NORMAL){
			if(this.rand()<=this.chance && gs.enemyHasStealable(tribe)){
				this.succeeded = true;
				do{
					System.out.print("Please input the enemy tribe tribe");
					System.out.print(tribe+1);
					System.out.print(" would like to steal from:");
					System.out.println();
					if(gs.s.hasNextInt()){
						targetTribe = gs.s.nextInt()-1;
						if(targetTribe>gs.tribes.size()||targetTribe<0)
							System.out.println("I'm sorry, the number you have input is out of range.");
						else if(targetTribe==tribe)
							System.out.println("I'm afraid I can't let you do that.");
					}
				}
				while(targetTribe>gs.tribes.size()||targetTribe<0||targetTribe==tribe && gs.tribes.get(targetTribe).hasResources());
				do{
					System.out.println("Please input what you would like to steal F, G, B:");
					if(gs.s.hasNext()){
						action = gs.s.next().charAt(0);
							switch(action){
							case 'F': 
								if(gs.tribes.get(targetTribe).resources.get(AbsMember.FOOD).amount>=foodAmnt){
									gs.tribes.get(targetTribe).resources.get(AbsMember.FOOD).amount-=foodAmnt;
									gs.tribes.get(tribe).resources.get(AbsMember.FOOD).amount+=foodAmnt;
									this.acted=true;
								}
								else if (gs.tribes.get(targetTribe).resources.get(AbsMember.FOOD).amount!=0){
									gs.tribes.get(tribe).resources.get(AbsMember.FOOD).amount+=gs.tribes.get(targetTribe).resources.get(AbsMember.FOOD).amount;
									gs.tribes.get(targetTribe).resources.get(AbsMember.FOOD).amount=0;
									this.acted=true;
								}
								break;
							case 'G':
								if(gs.tribes.get(targetTribe).resources.get(AbsMember.GOLD).amount>=goldAmnt){
									gs.tribes.get(targetTribe).resources.get(AbsMember.GOLD).amount-=goldAmnt;
									gs.tribes.get(tribe).resources.get(AbsMember.GOLD).amount+=goldAmnt;
									this.acted=true;
								}
								else if (gs.tribes.get(targetTribe).resources.get(AbsMember.GOLD).amount!=0){
									gs.tribes.get(tribe).resources.get(AbsMember.GOLD).amount+=gs.tribes.get(targetTribe).resources.get(AbsMember.GOLD).amount;
									gs.tribes.get(targetTribe).resources.get(AbsMember.GOLD).amount=0;
									this.acted=true;
								}
								break;
							case 'B':
								if(gs.tribes.get(targetTribe).resources.get(AbsMember.BABIES).amount>=babyAmnt){
									gs.tribes.get(targetTribe).resources.get(AbsMember.BABIES).amount-=babyAmnt;
									gs.tribes.get(tribe).resources.get(AbsMember.BABIES).amount+=babyAmnt;
									this.acted=true;
								}
								else if (gs.tribes.get(targetTribe).resources.get(AbsMember.BABIES).amount!=0){
									gs.tribes.get(tribe).resources.get(AbsMember.BABIES).amount+=gs.tribes.get(targetTribe).resources.get(AbsMember.BABIES).amount;
									gs.tribes.get(targetTribe).resources.get(AbsMember.BABIES).amount=0;
									this.acted=true;
								}
								break;
							default:
								System.out.println("I'm sorry, the character you have entered cannot be understood.");
							}
					}
				}
				while(this.acted=false);	
				this.acted=true;
			}
		}
	}

	void reset() {
		foodAmnt =3;
		goldAmnt =2;
		babyAmnt =1;
	}
}