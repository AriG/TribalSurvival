
public abstract class AbsEvent {

	String name;
	double chance;
	
	public AbsEvent() {
	}

	public AbsEvent change(){
		return this;
	}
	
}
