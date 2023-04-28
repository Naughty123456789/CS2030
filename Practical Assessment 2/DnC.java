import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Optional;

class DnC <T , R> {
    private final Supplier<T> problem;
    private final Predicate<T> checkAtomic;
    private final Function<T, R> mapper;
    private final Optional<Function<T,Pair<Supplier<T>,Supplier<T>>>> transform;

    private DnC(Supplier<T> problem, Predicate<T> checkAtomic, Function<T, R> mapper) {

        this.problem = problem;
        this.checkAtomic = checkAtomic;
        this.mapper = mapper;
        this.transform = Optional.empty();
    
    }

    public DnC(Supplier<T> problem, Predicate<T> checkAtomic, Function<T, R> mapper,
       Optional<Function<T,Pair<Supplier<T>,Supplier<T>>>> transform) {

        this.problem = problem;
        this.checkAtomic = checkAtomic;
        this.mapper = mapper;
        this.transform = transform;
    }
          
    public static  <T , R> DnC<T, R> of(T prob, Predicate<T> checkAtomic, Function<T, R> mapper) {  

        return new DnC<T, R>(() -> prob, checkAtomic, mapper);
    }

    public static  <T , R> DnC<T,R> of(T prob,  Predicate<T> checkAtomic, Function<T, R> mapper,
        Function<T,Pair<T,T>> transform) {

            Function<Pair<T,T>, Pair<Supplier<T>, Supplier<T>>> f  = 
            pair -> Pair.of(() -> pair.first(), () -> pair.second());
            
            return new DnC<T, R>(() -> prob, checkAtomic, mapper, Optional.of(transform.andThen(f)));
    }

    public static <T , R> DnC<T, R> of(Supplier<T> problem, Predicate<T> checkAtomic, 
        Function<T, R> mapper, Function<T, Pair<Supplier<T>, Supplier<T>>> transform) {
        
        return new DnC<T, R>(problem, checkAtomic, mapper, Optional.ofNullable(transform));
    }


    public void peek(Consumer<T> action) {

        action.accept(this.problem.get());
    }
   
    public DnC<T, R> left() {

        return this.left(this.problem.get());
    }
    
    public DnC<T, R> left(T problemGet) {

        return Optional.of(problemGet)
            .filter(x -> !this.checkAtomic.test(x))
            .flatMap(prob -> this.transform.map(div -> {
                Pair<Supplier<T>, Supplier<T>> pair = div.apply(prob);
                return new DnC<T, R>(pair.first(), this.checkAtomic,
                this.mapper, this.transform);
            }))
            .orElse(this);
    }

    public DnC<T, R> right() {

        return this.right(this.problem.get());
    }
    
    
    public DnC<T, R> right(T problemGet) {

        return Optional.of(problemGet)
            .filter(x -> !this.checkAtomic.test(x))
            .flatMap(prob -> this.transform.map(div -> {
                Pair<Supplier<T>, Supplier<T>> pair = div.apply(prob);
                return new DnC<T, R>(pair.second(), this.checkAtomic,
                this.mapper, this.transform);
                
            }))
            .orElse(this);
    }

    public Optional<R> solve() {

        return this.solve(this.problem.get());
    }
    
    public Optional<R> solve(T problemGet) {

        boolean checking = checkAtomic.test(problemGet);
        return Optional.of(problemGet)
        .filter(x -> checking)
        .map(x -> mapper.apply(x));
    }

    public Optional<R> solve(BinaryOperator<R> combiner) {

        return this.solve(this.problem.get(), combiner);
    }
    
    public Optional<R> solve(T problemGet, BinaryOperator<R> combiner) {
        
        return this.solve(problemGet).or(
        () -> left(problemGet).solve(combiner)
        .flatMap(leftS -> right(problemGet).solve(combiner)
        .map(rightS -> combiner.apply(leftS, rightS)
        )));  
    }       
}








    


    