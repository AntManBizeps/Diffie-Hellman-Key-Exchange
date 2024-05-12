import java.util.Objects;
import java.math.BigInteger;


class User<T extends FiniteFieldInterface> {
    private final DHSetup<T> setup;
    private final long secret;

    private T cypherKey;

    public User(DHSetup<T> dhSetup, long userSecret) {
        this.setup = dhSetup;
        this.secret = userSecret;
    }

    public T getPublicKey() {
        return setup.power(setup.getGenerator(), secret);
    }

    public void setKey(T a) {
        cypherKey = setup.power(a, secret);
        System.out.println("klucz szyfrujący: " + cypherKey);
    }

    public T encrypt(T m) {
        Objects.requireNonNull(cypherKey, "Cypher key is not set");
        if (cypherKey.aGetter() == new BigInteger(String.valueOf(0))) {
            throw new RuntimeException("Cypher key is equal 0");
        }
        return (T)m.multiply(cypherKey);
    }

    public T decrypt(T c) {
        Objects.requireNonNull(cypherKey, "Cypher key is not set");
        if (cypherKey.aGetter() == new BigInteger(String.valueOf(0))) {
            throw new RuntimeException("Cypher key is equal 0");
        }
        return (T)c.divide(cypherKey);
    }
}

