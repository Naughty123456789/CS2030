import java.util.Optional;


class Fraction extends AbstractNum<Frac>   {

    public Fraction(Optional<Frac> opt) {
        super(opt.filter(n -> (n.first().isValid()
            && n.second().isValid()) && !n.second().equals(Num.zero())));     
    }

    static Fraction of(int n, int d) {

        Num numerator = new Num(Optional.of(n));
        Num denominator = new Num(Optional.of(d));
        Num zero = new Num(Optional.of(d)).zero();

            
        if (valid.test(n) && valid.test(d) && !denominator.equals(zero)) {
            return new Fraction(Optional.of(Frac.of(numerator, denominator)));
        } else {
            return new Fraction(Optional.empty());
        }    
    }

    public Fraction add(Fraction other) {
        Optional<Frac> opt = this.opt.flatMap(f1 -> other.opt.map(f2 -> {
            Num numerator = f1.first().mul(f2.second()).add(f2.first().mul(f1.second()));
            Num denominator = f1.second().mul(f2.second());
            return Frac.of(numerator, denominator);
        }));
        return new Fraction(opt);
    }

    public Fraction sub(Fraction other) {
        Optional<Frac> opt = this.opt.flatMap(f1 -> other.opt.map(f2 -> {
            Num numerator = f1.first().mul(f2.second()).sub(f2.first().mul(f1.second()));
            Num denominator = f1.second().mul(f2.second());
            return Frac.of(numerator, denominator);
        }));
        
        return new Fraction(opt);
    }

    public Fraction mul(Fraction other) {
        Optional<Frac> opt = this.opt.flatMap(f1 -> other.opt.map(f2 -> {
            Num numerator = f1.first().mul(f2.first());
            Num denominator = f1.second().mul(f2.second());
            return Frac.of(numerator, denominator);
        }));
        return new Fraction(opt);
    }
}
 