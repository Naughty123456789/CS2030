import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {       
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    //causes your client to be aware    
    //of the state changes which is not what 
    //you want
    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.value));
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Maybe)) {
            return false;
        }
        Maybe<?> maybeOther = (Maybe<?>) other;
        if (this.value == null) {
            return maybeOther.value == null;
        }
        return this.value.equals(maybeOther.value);
    }

    public Maybe<T> filter(Predicate<? super T> predicate) {
        //predicate logic has been determined
        if (value != null && predicate.test(value)) {
            return this;
        } else {
            return empty();
        }
    }

    public void ifPresent(Consumer<? super T> action) {
        if (value != null) {
            action.accept(value);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (value != null) {
            action.accept(value);
        } else {
            emptyAction.run();
        }
    }

    public T orElse(T alternative) {
        return value != null ? value : alternative;
     }

    public T orElseGet(Supplier<? extends T> supplier) {
        return value != null ? value : supplier.get();
    }

    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        return value != null ? this : supplier.get().map(x -> x);
    }

    public <U> Maybe<U> flatMap(Function<? super T, ? extends Maybe<? extends U>> f) {
        if (value != null) {
            Maybe<? extends U> m = f.apply(this.value);
            return Maybe.<U>of(m.get());
        } else {
            return Maybe.empty();
        }
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.value + "]";
        }
    }
}
