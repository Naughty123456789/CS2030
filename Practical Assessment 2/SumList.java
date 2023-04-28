import java.util.List;
import java.util.Optional;

class SumList extends DnC<List<Integer>,Integer> {
    
    public SumList(List<Integer> i) {
        super(

        () -> i,
        x -> x.size() == 1,

        x -> x.get(0), Optional.of(list -> {
                int mid = (list.size() + 1) / 2;

                return Pair.of(
                () -> list.subList(0,mid),
                () -> list.subList(mid,list.size())
                );
        }));     
    }
}
