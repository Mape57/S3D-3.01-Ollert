package ollert;

public abstract class Enfant<T extends Parent> {
    protected Parent parent;

    public abstract T getParent();

}
