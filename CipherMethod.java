package encryptdecrypt;

public interface CipherMethod {

    String encrypt(String message, int key);

    String decrypt(String message, int key);
}
