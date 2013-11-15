package Events;
import Main.*;
import Classes.*;

public class GoodHarvest<Tribe> extends AbsEvent<Tribe> {
	
	public GoodHarvest() {
		super("Good Harvest", .500);
	}
	
	public void Effect(GameState gs){
		for(Main.Tribe t: gs.tribes){
			for(AbsMember m: t.members){
				if(m.symbol=='F'){
					//
				}
			}
		}
	}

}
