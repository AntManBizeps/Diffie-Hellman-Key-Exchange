import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.math.BigInteger;

class DHSetup<T extends FiniteFieldInterface> {
    private T generator;

    DHSetup(T sampleObject) {
        System.out.println("DHSetup constructor");
        // constructor
        Random rd = new Random();
        int randomNumber;
        do {
            randomNumber = rd.nextInt(1234577) + 1;
            System.out.println("Random number: " + randomNumber);
            BigInteger randomNumberBigInteger = new BigInteger(String.valueOf(randomNumber));
            try {
                T potentialGenerator = (T) sampleObject.getClass().getConstructor(BigInteger.class).newInstance(randomNumberBigInteger);
                if (isGenerator(potentialGenerator)) {
                    generator = potentialGenerator;
                    System.out.println("Generator: " + generator);
                    break;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    T getGenerator() {
        return generator;
    }

    T power(T a, long b) {
        // Class<?> sampleObjectClass = a.getClass();
        // Constructor<?> constructor;
        try {
            T result = (T) a.getClass().getConstructor(BigInteger.class).newInstance(BigInteger.ONE);
            while (b > 0) {
                if (b % 2 == 0) {
                    result = (T) result.multiply(a);
                }
                a = (T) a.multiply(a);
                b = b/2;
            }
            return (T) result;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    private List<Integer> getPrimeDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (n%i == 0 && isPrime(i)) {
                divisors.add(i);
            }
        }
        return divisors;
    }

    // Function to check if a number is a generator
    private boolean isGenerator(T num) {
        int n = num.nGetter();

        List<Integer> divisors = getPrimeDivisors(n - 1);
        for (Integer divisor : divisors) {
            if (power(num, (n-1)/divisor).equals(new FiniteField1(BigInteger.ONE))) {
                return false;
            }
        }
        return true;
    }
}
