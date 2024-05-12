import java.math.BigInteger;
public class Main {
    public static void main(String[] args) {
        BigInteger sampleNum = new BigInteger("6723");
        FiniteField1 sample = new FiniteField1(sampleNum);
        DHSetup<FiniteField1> dhsetup = new DHSetup<FiniteField1>(sample);
       
        User<FiniteField1> user1 = new User<>(dhsetup, 696969);
        User<FiniteField1> user2 = new User<>(dhsetup, 420420);

        user1.setKey(user2.getPublicKey());
        user2.setKey(user1.getPublicKey());

        BigInteger message = new BigInteger("2137");
        FiniteField1 m = new FiniteField1(message);
        FiniteField1 encrypted_message = user1.encrypt(m);

        System.out.println("wiadomość: " + m);
        System.out.println("klucz user1: " + user1.getPublicKey());
        System.out.println("klucz user2: " + user2.getPublicKey());
        System.out.println("zaszyfrowane: " + encrypted_message);

        FiniteField1 decrypted_message = user2.decrypt(encrypted_message);

        System.out.println("odszyfrowane: " + decrypted_message);
    }
}
