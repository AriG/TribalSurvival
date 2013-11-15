
public class ClassMedic extends AbsMember{

	int target;
	int heal;
	
	public ClassMedic() {
		super('M', 210, "Defender");
		this.chance=.500;
	}
	
	public void act(GameState gs, int tribe, int turn){
		if(this.acted==false && this.turnOrder <= turn){
			if(this.rand() <= this.chance && !gs.tribes.get(tribe).allHealed()){
				this.succeeded = true;
				heal=2;
				while(heal > 0){
					if(this.status=="Wounded"){
						this.status = "Normal";
						heal=0;
					}
					else if(this.status=="Dying"){
						this.status = "Wounded";
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
								case "Wounded":
									gs.tribes.get(tribe).members.get(target).status="Normal";
									heal--;
									break;
								case "Dying":
									gs.tribes.get(tribe).members.get(target).status="Wounded";
									heal--;
									break;
								case "Normal":
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
}
