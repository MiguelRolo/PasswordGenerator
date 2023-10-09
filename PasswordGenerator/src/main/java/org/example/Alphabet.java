package org.example;

public class Alphabet {
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "0123456789";
    public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";

    private final StringBuilder pool;

    public Alphabet(boolean uppercaseIncluded, boolean lowercaseIncluded, boolean numbersIncluded, boolean symbolsIncluded) {
        pool = new StringBuilder();

        if (uppercaseIncluded) pool.append(UPPERCASE_LETTERS);
        if (lowercaseIncluded) pool.append(LOWERCASE_LETTERS);
        if (numbersIncluded) pool.append(NUMBERS);
        if (symbolsIncluded) pool.append(SYMBOLS);
    }

    public String getAlphabet() {
        return pool.toString();
    }
}
