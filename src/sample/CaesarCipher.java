package sample;

import java.util.ArrayList;
import java.util.List;

public class CaesarCipher implements Cipher {

    private int step;

    public CaesarCipher(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String encrypt(String initialText, String alphabet) {
        StringBuilder encryptedText = new StringBuilder();
        initialText.chars()
                .mapToObj(c -> (char) c)
                .map(character -> {
                    int oldPosition = alphabet.indexOf(character);
                    if (oldPosition >= 0) {
                        int newPosition = this.step % alphabet.length();
                        if (newPosition < 0 && oldPosition + newPosition < 0) {
                            newPosition = alphabet.length() + newPosition + oldPosition;
                        } else {
                            newPosition = oldPosition + newPosition;
                        }
                        return alphabet.charAt(newPosition);
                    }
                    return character;
                })
                .forEach(encryptedText::append);
        return encryptedText.toString();
    }

    @Override
    public List<String> decrypt(String encryptedText, String alphabet) {
        List<String> decryptedStrings = new ArrayList<>();
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < alphabet.length(); i++) {
            final int step = i;
            encryptedText.chars()
                    .mapToObj(c -> (char) c)
                    .map(character -> {
                        int newPosition = alphabet.indexOf(character) + step;
                        return alphabet.charAt(newPosition);
                    })
                    .forEach(decryptedString::append);
            decryptedStrings.add(decryptedString.toString());
            decryptedString.setLength(0);
        }
        return decryptedStrings;
    }

}
