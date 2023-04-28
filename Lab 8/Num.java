import java.util.Optional;


class Num extends AbstractNum<Integer> {
    
    
    private Num(AbstractNum<Integer> i) {
        super(i.opt); 
    }

    public Num(Optional<Integer> opt) {
        super(opt);     
    }

    public static Num of(int i) {
        if (AbstractNum.valid.test(i)) {
            return new Num(Optional.of(i));
        } else {
            return new Num(Optional.empty());
        }
    }

    public static Num of(Optional<Integer> i) {

        return new Num(i.filter(AbstractNum.valid));
    }

    public static Num zero() {
        Optional<Integer> zeroOptional = AbstractNum.zero()
            .opt.map(value -> (Integer) value);
        return new Num(zeroOptional);
    }

    public Num succ() {
        return new Num(opt.map(AbstractNum.s));
    }

    public Num reverseSign() {
        return new Num(opt.map(AbstractNum.n));
    }


    public Num add(Num other) {

        if (!this.isValid() || !other.isValid()) {
            return new Num(Optional.empty());
        }

        Num temp = new Num(opt).zero();
        Num result = new Num(opt);

        while (!temp.equals(other)) {

            temp = temp.succ();
            result = result.succ();
        }

        return result;

    }

    static Num one() {
        return new Num(Optional.empty()).zero().succ();
    }

    public Num sub(Num other) {

        if (!this.isValid() || !other.isValid()) {
            return new Num(Optional.empty());   
        }

        return Num.of(new Num(other.opt.map(AbstractNum.n)).add(this).opt);
    }

    public Num mul(Num other) {

        if (!this.isValid() || !other.isValid()) {
            return new Num(Optional.empty());
        }

        Num temp = new Num(opt).zero();
        Num temp1 = new Num(opt).zero();
        Num result = new Num(opt);

        if (result.equals(temp) || other.equals(temp)) {
            return temp;
        }


        while (!temp.equals(other)) {

            temp = temp.succ();
            temp1 = temp1.add(result);
        }

       
        return temp1;
    }
}




