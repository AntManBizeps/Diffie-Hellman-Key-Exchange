import java.math.BigInteger;
class FiniteField1 implements FiniteFieldInterface<FiniteField1>{
    final private int N = 1234567891; // finite field number
    private BigInteger a;

    public int nGetter() {
        return N;
    }

    public FiniteField1() {
        this.a = new BigInteger("0");
    }

    public FiniteField1(BigInteger x) {
        this.a = myMod(x);
    }

    @Override
    public String toString() {
        return ": " + a;
    }

    public BigInteger aGetter() {
        return this.a;
    }

    public void aSetter(BigInteger x) {
        this.a = x;
    }

    public String FiniteField1Characteristic() {
        String characteristic = "Finite field based on prime number " + N + ".\n";
        return characteristic;
    }

    public boolean equals(FiniteField1 other) {
        return myMod(a).equals(myMod(other.aGetter()));
    }

    public boolean notEquals(FiniteField1 other) {
        return !myMod(a).equals(myMod(other.aGetter()));
    }

    public boolean lessThan(FiniteField1 other) {
        return myMod(a).compareTo(myMod(other.aGetter())) < 0;
    }

    public boolean greaterThan(FiniteField1 other) {
        return myMod(a).compareTo(myMod(other.aGetter())) > 0;
    }

    public boolean lessThanOrEqual(FiniteField1 other) {
        return myMod(a).compareTo(myMod(other.aGetter())) <= 0;
    }

    public boolean greaterThanOrEqual(FiniteField1 other) {
        return myMod(a).compareTo(myMod(other.aGetter())) >= 0;
    }

    public FiniteField1 add(FiniteField1 other) {
        return new FiniteField1(myMod(a.add(other.aGetter())));
    }

    public FiniteField1 subtract(FiniteField1 other) {
        BigInteger result = a.subtract(other.aGetter());
        return new FiniteField1(myMod(result));
    }

    public FiniteField1 multiply(FiniteField1 other) {
        BigInteger a1 = a;
        BigInteger b = other.aGetter();
        BigInteger c = BigInteger.ZERO;
        while (!b.equals(BigInteger.ZERO)) {
            if (b.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                c = myMod(c.add(a1));
            }
            a1 = myMod(a1.multiply(BigInteger.TWO));
            b = b.divide(BigInteger.TWO);
        }
        return new FiniteField1(c);
    }

    public FiniteField1 divide(FiniteField1 other) {
        try {
            if (!other.aGetter().equals(BigInteger.ZERO)) {
                BigInteger inverse = this.modInverse(other.aGetter());
                BigInteger a1 = a;
                BigInteger b = inverse;
                BigInteger c = BigInteger.ZERO;
                while (!b.equals(BigInteger.ZERO)) {
                    if (b.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                        c = myMod(c.add(a1));
                    }
                    a1 = myMod(a1.multiply(BigInteger.TWO));
                    b = b.divide(BigInteger.TWO);
                }
                return new FiniteField1(c);
            } else {
                throw new ArithmeticException("Division by zero");
            }
        } catch (ArithmeticException e) {
            System.err.println("Dividing by zero not allowed!");
            return new FiniteField1(new BigInteger("0"));
        }
    }

    public FiniteField1 assign(FiniteField1 other) {
        a = other.aGetter();
        return this;
    }

    public FiniteField1 addAssign(FiniteField1 other) {
        a = myMod(a.add(other.aGetter()));
        return this;
    }

    public FiniteField1 subtractAssign(FiniteField1 other) {
        a = myMod(a.add(modOpposite(other.aGetter())));
        return this;
    }

    public FiniteField1 multiplyAssign(FiniteField1 other) {
        a = multiply(other).aGetter();
        return this;
    }

    public FiniteField1 divideAssign(FiniteField1 other) {
        try {
            if (!other.aGetter().equals(BigInteger.ZERO)) {
                a = divide(other).aGetter();
            } else {
                throw new ArithmeticException("Division by zero");
            }
        } catch (ArithmeticException e) {
            System.err.println("Dividing by zero not allowed!");
        }
        return this;
    }

    private BigInteger extendedEuclidean(BigInteger a, BigInteger b, BigInteger[] x, BigInteger[] y) {
        if (b.equals(BigInteger.ZERO)) {
            x[0] = BigInteger.ONE;
            y[0] = BigInteger.ZERO;
            return a;
        }

        BigInteger[] x1 = new BigInteger[1], y1 = new BigInteger[1];
        BigInteger gcd = extendedEuclidean(b, a.mod(b), x1, y1);

        x[0] = y1[0];
        y[0] = x1[0].subtract(a.divide(b).multiply(y1[0]));

        return gcd;
    }

    private BigInteger modInverse(BigInteger a) {
        BigInteger[] x = new BigInteger[1], y = new BigInteger[1];
        BigInteger gcd = extendedEuclidean(a, BigInteger.valueOf(N), x, y);

        if (gcd.compareTo(BigInteger.ONE) != 0) {
            // Element a nie ma odwrotności modulo m
            return BigInteger.ZERO;
        } else {
            // Upewniamy się, że wynik jest dodatni
            return x[0].mod(BigInteger.valueOf(N)).add(BigInteger.valueOf(N)).mod(BigInteger.valueOf(N));
        }
    }

    private BigInteger modOpposite(BigInteger x) {
        return BigInteger.valueOf(N).subtract(x);
    }

    private BigInteger myMod(BigInteger x) {
        while (x.compareTo(BigInteger.ZERO) < 0) {
            x = x.add(BigInteger.valueOf(N));
        }
        while (x.compareTo(BigInteger.valueOf(N)) >= 0) {
            x = x.subtract(BigInteger.valueOf(N));
        }
        return x;
    }
}
