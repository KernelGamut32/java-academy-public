package expeditors.week06.functional.interfaces;

@FunctionalInterface
public interface Picker<T> {
    boolean pick(T option);
}
