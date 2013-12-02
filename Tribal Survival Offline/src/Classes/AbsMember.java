package Classes;
import Main.*;

public abstract class AbsMember {

	public char symbol;
	public int turnOrder;
	public String type;
	public Status status;
	public boolean succeeded;
	public boolean acted;
	public double chance;
	public final static int FOOD = 0;
	public final static int GOLD = 1;
	public final static int BABIES = 2;
	public final static int MAXTURNORDER = 999;
	
	public AbsMember(char symbol, int turnOrder, String type) {
		this.symbol = symbol;
		this.turnOrder = turnOrder;
		this.type = type;
		this.status = status.NORMAL;
		this.acted = false;
	}

	public void wound() {
		if (this.status == status.NORMAL)
			this.status = status.WOUNDED;
		else if (this.status == status.WOUNDED)
			this.status = status.DYING;
	}

	public void eat(Resource food) {
		if (food.amount > 0)
			food.amount--;
		else
			wound();
	}
	
	abstract void reset();

	public abstract void act(GameState gs, int tribe, int turn);
	
	public double rand(){
		return Math.random();
	}
}
