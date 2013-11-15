package Main;
import java.util.ArrayList;
import java.util.List;
import Classes.*;

public class BigBang {
	List<Tribe> tribesInit = new ArrayList<>();
	GameState initialState = new GameState(tribesInit);

	public BigBang() {
	}

	public void actState(GameState gs) {
		gs.printUpdate();
		gs.act();
		endState(gs);
	}

	public void endState(GameState gs) {
		gs.printUpdate();
		gs.bringOutYourDead();
		gs.printUpdate();
		gs.dinnerTime();
		gs.bringOutYourDead();
		gs.printUpdate();
		gs.babyBoom();
		this.reorder(gs);
		gs.tradeState();
		this.reorder(gs);
		gs.reset();
		gs.currentTurn++;
		actState(gs);
	}

	public GameState setUp() {
		System.out.println("Please Enter Number of Tribes");
		int max = this.initialState.s.nextInt();
		for (int i = 0; i < max; i++) {
			this.initialState.tribes.add(i, new Tribe());
		}
		System.out.println("Please Enter Number of Members");
		max = this.initialState.s.nextInt();
		for (Tribe t : this.initialState.tribes) {
			for (int j = 0; j < max; j++) {
				t.members.add(j, new Peasant());
			}
		}
		System.out.println("Please Enter Starting Food");
		int amount;
		amount = this.initialState.s.nextInt();
		for (Tribe t: this.initialState.tribes) {
			t.resources.add(new Resource("Food", amount));
			t.resources.add(new Resource("Gold", 0));
			t.resources.add(new Resource("Babies", 0));
		}
		return initialState;
	}

	public GameState pickMembers(GameState startState) {
		char c;
		if (startState.tribes.size() > 0
			&& startState.tribes.get(0).members.size() > 0) {
			for (int i = 0; i < startState.tribes.get(0).members.size(); i++) {
				for (int j=0; j < startState.tribes.size();) {
					System.out.print("Please Enter Symbol that Player ");
					System.out.print(j + 1);
					System.out.print(" would like in position ");
					System.out.println(i + 1);
					c = this.initialState.s.next().charAt(0);
					if (Peasant.findMatch((c))) {
						startState.tribes.get(j).members.set(i,Peasant.change(c));
						j += 1;
					}
				}
			}
		}
		return startState;
	}

	public GameState reorder(GameState gs) {
		for (Tribe t: gs.tribes) {
			this.sort(t.members);
		}
		return gs;
	}

	public List<AbsMember> insert(AbsMember member, List<AbsMember> list) {
		AbsMember tempAbs;
		if (list.size() == 0)
			list.add(0, member);
		else if (member.turnOrder >= list.get(0).turnOrder) {
			tempAbs = list.remove(0);
			insert(member, list).add(0, tempAbs);
		} else
			list.add(0, member);
		return list;
	}

	public List<AbsMember> sort(List<AbsMember> list) {
		if (list.size() == 0)
			return list;
		return insert(list.remove(0), sort(list));
	}

	public static void main(String[] args) {
		BigBang world = new BigBang();
		List<Tribe> testTribes = new ArrayList<>();
		Tribe testTribe1 = new Tribe();
		Tribe testTribe2 = new Tribe();
		Tribe testTribe3 = new Tribe();

		testTribe1.members.add(new Farmer());
		testTribe1.members.add(new Farmer());
		testTribe1.members.add(new Farmer());
		testTribe1.members.add(new Farmer());
		testTribe1.members.add(new Farmer());

		testTribe2.members.add(new Guard());

		testTribe3.members.add(new Warrior());
		testTribe3.members.add(new Warrior());
		
		testTribe2.members.get(0).acted =true;
		System.out.println(testTribe2.members.get(0).acted);

		testTribes.add(testTribe1);
		testTribes.add(testTribe2);
		testTribes.add(testTribe3);
		GameState testWorld = new GameState(testTribes);
		world.actState(world.reorder(world.pickMembers(world.setUp())));
		//world.actState(world.reorder(testWorld));
		//testWorld.reset();
		/*for(Tribe t : testWorld.tribes){
			for(AbsMember m : t.members){
				System.out.println(m.acted);
			}
		}*/
	}
}
