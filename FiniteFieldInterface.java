import java.math.BigInteger;
interface FiniteFieldInterface <T>{
    
    public BigInteger aGetter();

    public int nGetter();

    public void aSetter(BigInteger x);

    String FiniteField1Characteristic();

    boolean notEquals(T other);

    boolean lessThan(T other);

    boolean greaterThan(T other);

    boolean lessThanOrEqual(T other);

    boolean greaterThanOrEqual(T other);

    T add(T other);

    T subtract(T other);

    T multiply(T other);

    T divide(T other);

    T assign(T other);

    T addAssign(T other);

    T subtractAssign(T other);

    T multiplyAssign(T other);

    T divideAssign(T other);

    // private BigInteger extendedEuclidean(BigInteger a, BigInteger b, BigInteger[] x, BigInteger[] y) {
    //     if (b == 0) {
    //         x[0] = 1;
    //         y[0] = 0;
    //         return a;
    //     }

    //     BigInteger[] x1 = new BigInteger[1], y1 = new BigInteger[1];
    //     BigInteger gcd = extendedEuclidean(b, a % b, x1, y1);

    //     x[0] = y1[0];
    //     y[0] = x1[0] - (a / b) * y1[0];

    //     return gcd;
    // }

    // private BigInteger modInverse(BigInteger a) {
    //     BigInteger[] x = new BigInteger[1], y = new BigInteger[1];
    //     BigInteger gcd = extendedEuclidean(a, N, x, y);

    //     if (gcd != 1) {
    //         // Element a nie ma odwrotności modulo m
    //         return 0;
    //     } else {
    //         // Upewniamy się, że wynik jest dodatni
    //         return (x[0] % N + N) % N;
    //     }
    // }

    // private BigInteger modOpposite(BigInteger x) {
    //     return N - x;
    // }

    // private BigInteger myMod(BigInteger x) {
    //     while (x < 0) {
    //         x += N;
    //     }
    //     while (x >= N) {
    //         x -= N;
    //     }
    //     return x;
    // }
}
