package sample;

import java.util.List;

public interface Cipher {

    String encrypt(String initialText, String alphabet);

    List<String> decrypt(String encryptedText, String alphabet);

}
