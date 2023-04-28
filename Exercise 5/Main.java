import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;



class Main {

    public static IntStream twinPrimes(int n) {

        if (n <= 2) {

            return IntStream.empty();
        }
    
        return IntStream.rangeClosed(2, n)
                .filter(x -> isPrime(x))
                .filter(x -> isPrime(x + 2) || isPrime(x - 2));        
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        
        return IntStream.rangeClosed(2, (int) Math.sqrt(n))
            .noneMatch(i -> n % i == 0);
    }

    public static String reverse(String str) {

        return Stream.of(str.split(""))
        .reduce((s1, s2) -> s2 + s1)
        .orElse("");

    }

    public static long countRepeats(List<Integer> list) {
        return IntStream.range(1, list.size())
            .filter(x -> x - 2 < 0 || list.get(x - 2) != list.get(x))
            .filter(x -> list.get(x) == list.get(x - 1))
            .count();
    }
    
    //i cant do level 4, i have been stuck at this for about a month or two. 
    //This is from a senior who helped and guided me in understanding this 
    //monstrosity 

    public static boolean alive(int i) {
        return i == 1;
    }

    public static String toCross(List<Integer> list) {
        return IntStream.range(0,list.size())
            .map(x -> list.get(x)).mapToObj(y -> String.valueOf(y))
            .map(i -> i.equals("1") ? "x" : ".")
            .reduce("", (x,y) -> x + y);
    }

    public static int adjacent(List<Integer> list, Integer index) {
        if (alive(list.get(index))) {
            return -1;
        } else if (index == 0) {
            if (list.get(index + 1) == 1) {
                return 1;
            } else {
                return 0;
            }
        } else if (index == list.size() - 1) {
            if (list.get(index - 1) == 1) {
                return 1;
            } else {
                return 0;
            }
        } else if ((list.get(index + 1) == 0 && list.get(index - 1) == 1)) {
            return 1;
        } else if ((list.get(index + 1) == 1 && list.get(index - 1) == 0)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static UnaryOperator<List<Integer>> generateRule() {
        return x -> IntStream.range(0, x.size())
            .boxed()
            .map(i -> x.get(i) + adjacent(x,i))
            .collect(Collectors.toList());
    }

    public static Stream<String> gameOfLife(List<Integer> list,
        UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list,rule)
            .limit(n)
            .map(x -> toCross(x));    
    }

     
}

    

    
    

    


   

