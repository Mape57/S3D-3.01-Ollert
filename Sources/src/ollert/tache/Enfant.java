package ollert.tache;

import ollert.Parent;

public abstract class Enfant<T extends Parent> {
	protected Parent parent;

	public abstract T getParent();

}
