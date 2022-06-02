package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        // Initialize CipherMachine with default options
        CipherMachine cipherMachine = new CipherMachine();

        // Parse args
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-mode":
                    cipherMachine.setMode(args[i + 1]);
                    break;
                case "-data":
                    cipherMachine.setMessage(args[i + 1]);
                    break;
                case "-key":
                    cipherMachine.setKey(Integer.parseInt(args[i + 1]));
                    break;
                case "-in":
                    cipherMachine.readMessageFile(args[i + 1]);
                    break;
                case "-out":
                    cipherMachine.setOutputPath(args[i + 1]);
                    break;
                case "-alg":
                    cipherMachine.setCipherMethod(args[i + 1]);
                    break;
            }
        }

        // Start the cipher process
        cipherMachine.run();
        cipherMachine.outputResult();
    }
}
