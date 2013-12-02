package Main;
import java.util.ArrayList;
import Classes.*;
import java.util.List;

public class Tribe {

	public List<Resource> resources = new ArrayList<>();
	public List<AbsMember> members = new ArrayList<>();

	// public String name = ""; TODO

	public Tribe() {
	}

	public boolean allHealed() {
		for (AbsMember m : members) {
			if (m.status != m.status.NORMAL)
				return false;
		}
		return true;
	}

	public boolean allWounded() {
		for (AbsMember m : members) {
			if (m.status != m.status.WOUNDED)
				return false;
		}
		return true;
	}

	public boolean allDying() {
		for (AbsMember m : members) {
			if (m.status != m.status.DYING)
				return false;
		}
		return true;
	}

	public boolean hasResources() {
		for (Resource r : resources) {
			if (r.amount != 0)
				return true;
		}
		return false;
	}

	public boolean hasPeasant(){
		for (AbsMember m : members) {
			if (m.symbol == 'P')
				return true;
		}
		return false;
	}
	
	public int getPeasant(){
		for (AbsMember m : members) {
			if (m.symbol == 'P')
				return this.members.indexOf(m);
		}
		return -1;
	}
	
	public void starve(GameState gs) {
		int input;
		System.out.print("Tribe ");
		System.out.print(gs.tribes.indexOf(this)+1);
		System.out.print(" Has ");
		System.out.print(this.resources.get(AbsMember.FOOD).amount);
		System.out.print(" Food. But ");
		System.out.print(this.members.size());
		System.out.print(" members.");
		System.out.println();

		System.out.println("Who would you like to starve?");
		for (int i = this.resources.get(AbsMember.FOOD).amount; i < this.members.size();) {
			if (gs.s.hasNextInt()) {
				input = gs.s.nextInt();
				if (input > 0 && input <= this.members.size()) {
					switch (this.members.get(input-1).status) {
					case NORMAL:
						this.members.get(input-1).status = this.members.get(input-1).status.WOUNDED;
						System.out.println("Who else would you like to starve?");
						i++;
						break;
					case WOUNDED:
						this.members.get(input-1).status = this.members.get(input-1).status.DYING;
						System.out.println("Who else would you like to starve?");
						i++;
						break;
					default:
						System.out.println("I'm afraid he's already dying./'I/'m not dead yet!/'");
					}
				}
			}
		}
		this.resources.get(AbsMember.FOOD).amount = 0;
	}
}
