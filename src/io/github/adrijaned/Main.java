package io.github.adrijaned;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by adrijaned on 9.7.17.
 */
public class Main {
    public static void main(String[] args) {
        BigInteger n, p, q, lambda;
        p = getRandomPrime();
        q = getRandomPrime();
        n = p.multiply(q);
        lambda = lcm(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
        BigInteger d = findCoprime(lambda);
        BigInteger e = d.modInverse(lambda);
        BigInteger message = new BigInteger("5654");
        System.out.println(message.toString());
        BigInteger encrypted = encrypt(message, e, n);
        System.out.println(encrypted.toString());
        BigInteger decrypted = decrypt(encrypted, d, n);
        System.out.println(decrypted.toString());
        String stringMessage = "Hello World!";
        System.out.println(stringMessage);
        String stringEncrypted = encryptString(stringMessage, e, n);
        System.out.println(stringEncrypted);
        String stringDecrypted = decryptString(stringEncrypted, d, n);
        System.out.println(stringDecrypted);
    }

    private static BigInteger findCoprime(BigInteger lambda) {
        BigInteger d = new BigInteger("2");
        while (!lambda.gcd(d).equals(BigInteger.ONE)) {
            d = d.add(BigInteger.ONE);
        }
        return d;
    }

    static BigInteger getRandomPrime() {
        return new BigInteger(1024, 200, new Random());
    }

    static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }

    static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    static String encryptString(String message, BigInteger e, BigInteger n) {
        return new BigInteger(message.getBytes()).modPow(e, n).toString();
    }

    static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {
        return message.modPow(d, n);
    }

    static String decryptString(String message, BigInteger d, BigInteger n) {
        return new String(decrypt(new BigInteger(message), d, n).toByteArray());
    }
}
