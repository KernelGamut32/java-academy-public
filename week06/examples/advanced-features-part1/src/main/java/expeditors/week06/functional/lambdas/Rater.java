package expeditors.week06.functional.lambdas;

@FunctionalInterface
public interface Rater<T,R> {
    R rate(T input);
}
