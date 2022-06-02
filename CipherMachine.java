package encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CipherMachine {
    private CipherMethod cipherMethod;
    private String message;
    private int key;
    private String mode;
    private String outputPath;

    public CipherMachine() {
        this.message = "";
        this.mode = "enc";
        this.key = 0;
        this.outputPath = "";
        this.cipherMethod = new ShiftCipherMethod();
    }
    public void setCipherMethod(String cipherMethod) {
        if ("shift".equals(cipherMethod)) {
            this.cipherMethod = new ShiftCipherMethod();
        } else if ("unicode".equals(cipherMethod)) {
            this.cipherMethod = new UnicodeCipherMethod();
        } else {
            throw new IllegalArgumentException("Invalid -alg parameter provided");
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void readMessageFile(String path) {
        if ("".equals(message)) {
            try {
                message = new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String encrypt(String message, int key) {
        return this.cipherMethod.encrypt(message, key);
    }

    public String decrypt(String message, int key) {
        return this.cipherMethod.decrypt(message, key);
    }

    public void run() {
        if ("enc".equals(this.mode)) {
            this.message = this.encrypt(this.message, this.key);
        } else if ("dec".equals(this.mode)) {
            this.message = this.decrypt(this.message, this.key);
        }
    }

    public void outputResult() {
        if ("".equals(this.outputPath)) {
            System.out.println(message);
        } else {
            try (FileWriter fileWriter = new FileWriter(this.outputPath)) {
                fileWriter.write(this.message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
