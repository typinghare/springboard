import java.io.*;

public class PrimeNumbers {
    public static void main(String[] args) {
        new PrimeNumbers().run();
    }

    /**
     * Name of the binary data file.
     */
    private final static String BINARY_DATA_FILE_NAME = "data.dat";

    /**
     * Size of the prime number array.
     */
    private final static int PRIME_NUMBER_ARRAY_SIZE = 10000;

    /**
     * Max number.
     */
//    private final static long MAX_NUMBER = 10_000_000_000L;
    private final static long MAX_NUMBER = 100L;

    /**
     * Stores prime numbers.
     */
    private final long[] primeNumberArray = new long[PRIME_NUMBER_ARRAY_SIZE];

    /**
     * Current number.
     */
    private long current_number = 2;

    public PrimeNumbers() {
        final File file = new File(BINARY_DATA_FILE_NAME);
        System.out.println("The absolute path of the binary data file: " + file.getAbsolutePath());

        if (!file.exists()) {
            // Create a binary data file if it does not exist
            this.appendPrimeNumberToBinaryFile(current_number);
        } else {
            // Read the binary data file, and update the current number
            try (
                final FileInputStream fileInputStream = new FileInputStream(BINARY_DATA_FILE_NAME);
                final DataInputStream dataInputStream = new DataInputStream(fileInputStream)
            ) {
                // Read long numbers from the binary file
                while (dataInputStream.available() >= Long.BYTES) {
                    current_number = dataInputStream.readLong();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        current_number++;
    }

    /**
     * Runs the program. Continue to check numbers one by one; if a number is a prime number, append it to the binary
     * file.
     */
    public void run() {
        while (current_number <= MAX_NUMBER) {
            if (isPrimeNumber(current_number)) appendPrimeNumberToBinaryFile(current_number);

            current_number++;
        }
    }

    /**
     * Checks if a number is a prime number.
     * @param number Number to check.
     */
    public boolean isPrimeNumber(long number) {
        try (
            final FileInputStream fileInputStream = new FileInputStream(BINARY_DATA_FILE_NAME);
            final DataInputStream dataInputStream = new DataInputStream(fileInputStream)
        ) {
            while (dataInputStream.available() >= Long.BYTES) {
                // Read (at most) 10000 long numbers
                int numberRead = -1;
                for (int i = 0; i < PRIME_NUMBER_ARRAY_SIZE; i++) {
                    if (dataInputStream.available() < Long.BYTES) {
                        numberRead = i;
                        break;
                    }
                    primeNumberArray[i] = dataInputStream.readLong();
                }
                if (numberRead == -1) numberRead = PRIME_NUMBER_ARRAY_SIZE;

                // Check if one of these 10000 numbers can divide the number
                for (int i = 0; i < numberRead; i++) {
                    if (number % primeNumberArray[i] == 0) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * Appends a prime number to a binary file.
     * @param primeNumber Prime number to append.
     */
    public void appendPrimeNumberToBinaryFile(long primeNumber) {
        try (
            final FileOutputStream fileOutputStream = new FileOutputStream(BINARY_DATA_FILE_NAME, true);
            final DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)
        ) {
            dataOutputStream.writeLong(primeNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
