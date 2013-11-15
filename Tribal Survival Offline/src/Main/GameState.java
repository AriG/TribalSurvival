package Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Classes.*;


public class GameState {

	public List<Tribe> tribes = new ArrayList<>();
	public Scanner s = new Scanner(System.in);
	public int currentTurn = 0;
	
	public GameState(List<Tribe> tribes) {
		this.tribes = tribes;
	}
	
	public void reset(){
		for(Tribe t : tribes){
			for(AbsMember m : t.members){
				m.acted = false;
			}
		}
	}
	
	public int tribeMax(){
		int tempMax=0;
		for(Tribe t : tribes){
			if(t.members.size()>tempMax)
				tempMax=t.members.size();
		}
		return tempMax;
	}
	
	public void printUpdate() {
		int i;
		int j;
		for (i = 0; i < this.tribes.size(); i++) {
			System.out.print("Tribe ");
			System.out.print(i + 1);
			System.out.print("\t\t");
		}
		System.out.print(currentTurn);
		System.out.println();

		for (i = 0; i < this.tribes.get(0).resources.size(); i++) {
			for (j = 0; j < this.tribes.size(); j++) {
				System.out.print(this.tribes.get(j).resources.get(i).name);
				System.out.print(": ");
				if (this.tribes.get(j).resources.get(i).name.length() < 6)
					System.out.print("\t");
				System.out.print(this.tribes.get(j).resources.get(i).amount);
				System.out.print("\t");
			}
			System.out.println();
		}

		System.out.println();
		int max = this.tribeMax();
		for (i = 0; i < max; i++) {
			for (Tribe t: this.tribes) {
				System.out.print("\t");
				if (t.members.size() > i){
					switch(t.members.get(i).status){
					case "Wounded":
						System.out.print("x");
						break;
					case "Dying":
						System.out.print("xx");
						break;
					default:
						break;
					}
					System.out.print(t.members.get(i).symbol);
					if(t.members.get(i).acted)
						System.out.print("o");					
				}
				System.out.print("\t");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public void bringOutYourDead(){
		for(Tribe t: this.tribes){
			for(int i = t.members.size()-1; i>=0; i--){
				if(t.members.get(i).status=="Dying"){
					t.members.remove(i);
				}
			}
		}
	}
	
	public void dinnerTime(){
		for(Tribe t: this.tribes){
			if(t.resources.get(AbsMember.FOOD).amount<t.members.size()){
				t.starve(this);
				t.resources.get(AbsMember.FOOD).amount = 0;
			}
			else
				t.resources.get(AbsMember.FOOD).amount-=t.members.size();
		}
	}
	
	public void babyBoom(){
		for(Tribe t: this.tribes){
			for(int i = t.resources.get(AbsMember.BABIES).amount; i>0; i--){
				t.members.add(new ClassPeasant());
			}
			t.resources.get(AbsMember.BABIES).amount=0;
		}
	}
	
	//make a temporary list ordered based on turnorder
	public void act(){
		for(int i = 0; i <= AbsMember.MAXTURNORDER; i++){
			for(Tribe t: tribes){
				for(AbsMember m: t.members){
					m.act(this, this.tribes.indexOf(t), i);
				}
			}
		}
	}
	
	public boolean enemyHasStealable(int tribe){
		for(Tribe t : tribes)
			if(this.tribes.indexOf(t)!=tribe)
					for(Resource r: t.resources)
						if(r.amount > 0)
							return true;
		return false;
	}
	
	public boolean enemyHasAttackable(int tribe){
		for(Tribe t : tribes)
			if(this.tribes.indexOf(t)!=tribe)
				if(!t.allDying())
					return true;
		return false;
	}	
	
	public void tradeState(){
		char input;
		for(Tribe t: tribes){
			input= ' '; //neither N nor Y
			if(t.resources.get(AbsMember.GOLD).amount>=2 && 
					t.hasPeasant()){
				do{
					do{
						System.out.print("Would Tribe ");
						System.out.print(tribes.indexOf(t)+1);
						System.out.print(" like to upgrade a peasant?");
						System.out.println();
						input = s.next().charAt(0);
					}
					while(!(input=='Y'||input=='N'));
					while(input=='Y'){
						System.out.println("What would you like your peasant to be?");
						if(s.hasNextLine()){
							input = s.next().charAt(0);
							if(ClassPeasant.findMatch(input)){
								t.members.set(t.getPeasant(), ClassPeasant.change(input));
								t.resources.get(AbsMember.GOLD).amount-=2;
							}
							else{
								System.out.println("I'm sorry but that's not a class");
								input=' ';
							}
						}
					}
					input = 'Y';
				}
				while(input=='Y'&& t.resources.get(AbsMember.GOLD).amount>=2 && t.hasPeasant());
				}
			}
		}
	}
