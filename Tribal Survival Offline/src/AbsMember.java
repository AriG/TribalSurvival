import java.lang.Math;

public abstract class AbsMember {

	char symbol;
	int turnOrder;
	String type;
	String status;
	boolean succeeded;
	boolean acted;
	double chance;
	final static int FOOD = 0;
	final static int GOLD = 1;
	final static int BABIES = 2;
	final static int MAXTURNORDER = 999;
	
	public AbsMember(char symbol, int turnOrder, String type) {
		this.symbol = symbol;
		this.turnOrder = turnOrder;
		this.type = type;
		this.status = "Normal";
		this.acted = false;
	}

	void wound() {
		if (this.status == "Normal")
			this.status = "Wounded";
		else if (this.status == "Wounded")
			this.status = "Dying";
	}

	void eat(Resource food) {
		if (food.amount > 0)
			food.amount--;
		else
			wound();
	}

	abstract void act(GameState gs, int tribe, int turn);
	
	public double rand(){
		return Math.random();
	}
}
