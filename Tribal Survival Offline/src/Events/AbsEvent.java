package Events;

public abstract class AbsEvent<T> {

	String name;
	double chance;
	
	public AbsEvent(String name, double chance) {
		this.name=name;
		this.chance=chance;
	}
	
}
