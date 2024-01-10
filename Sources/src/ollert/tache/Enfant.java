package ollert.tache;

import ollert.Parent;

/**
 * Classe abstraite représentant un enfant d'un parent
 *
 * @param <T> type du parent (ListeTaches, Page et Tache)
 */
public abstract class Enfant<T extends Parent> {
	/**
	 * Parent de l'enfant
	 */
	protected Parent parent;

	/**
	 * @return le parent de l'enfant
	 */
	public abstract T getParent();
	public abstract void setParent(T parent);

}
