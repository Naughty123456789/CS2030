import java.util.Optional;
import java.util.function.Function;

class Log<T> {
    private final T value;
    private final Optional<String> log;
 
    public Log(T value, Optional<String> log) {    
        this.value = value;
        this.log = log;
    }

    public T getValue() {
        return this.value;
    }

    public Optional<String> log() {
        return this.log;
    }

    public static <T> Log<T> of(T value) {  
        return new Log<T>(value, Optional.empty()).of(value, "");
    }

    public static <T> Log<T> of(T value, String log) {
        return Optional.ofNullable(value)
            .flatMap(v -> Optional.ofNullable(log)
                    .filter(x -> !(v instanceof Log))
                    .map(x -> new Log<T>(v, Optional.of(x)))
            )
            .orElseThrow(() -> 
            new IllegalArgumentException("Invalid arguments"));
    }

    public <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        R newValue = mapper.apply(this.value);
        return new Log<R>(newValue, this.log);
    }

    public <R> Log<R> flatMap(Function<? super T, ? extends Log<R>> mapper) {   
        Log<R> result = mapper.apply(value);
        Optional<String> mappedLog = log
            .flatMap(log1 -> result.log.map(log2 -> log1 + "\n" + log2));
        String finalLog = mappedLog.orElse(log.orElse("") 
            + "\n" + result.log.orElse("")).trim();
        return new Log<R>(result.value, Optional.of(finalLog));
    }
 
    public boolean equals(Object  other) {

        if (other instanceof Log<?>) { 
            Log<?> temp = (Log<?>) other;
            return this.value.equals(temp.value) &&
            this.log.equals(temp.log);
        } 
        return false;
    }
      
    public String toString() {
        return log.map(l -> "Log[" + value + "]\n" + l).orElse("Log[" + value + "]");
    }

} 