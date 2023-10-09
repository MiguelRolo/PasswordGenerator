package org.example;

import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean IncludeUpp, boolean IncludeLow, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpp, IncludeLow, IncludeNum, IncludeSym);
    }

    public void mainLoop() {
        System.out.println("Welcome to password generator");
        menu();

        String option = "-1";

        while (!option.equals("0")){
            option = keyboard.next();
            switch (option) {
                case "1" -> {
                    requestPassword();
                    menu();
                }
                case "2" -> {
                    checkPassword();
                    menu();
                }
                case "0" -> quit();
                default -> {
                    System.out.println();
                    System.out.println("Select one available option");
                    menu();
                }
            }
        }
    }

    private Password GeneratePassword(int length) {
        final StringBuilder password = new StringBuilder("");
        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            password.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(password.toString());
    }

    private void requestPassword() {
        boolean IncludeUpp = false;
        boolean IncludeLow = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Answer by Yes or No \n");
        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                RequestPasswordError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isIncluded(input)) IncludeUpp = true;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                RequestPasswordError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isIncluded(input)) IncludeLow = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = keyboard.next();
                RequestPasswordError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isIncluded(input)) IncludeNum = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = keyboard.next();
                RequestPasswordError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isIncluded(input)) IncludeSym = true;

            //No Pool Selected
            if (!IncludeUpp && !IncludeLow && !IncludeNum && !IncludeSym) {
                System.out.println("At least one of your answers should be Yes\n");
                correctParams = true;
            }
        } while (correctParams);

        System.out.print("\nEnter the length: ");
        int length = keyboard.nextInt();
        final Generator generator = new Generator(IncludeUpp, IncludeLow, IncludeNum, IncludeSym);
        final Password password = generator.GeneratePassword(length);

        System.err.println("Password Generated [" + password + "]\n\n");
    }

    private boolean isIncluded(String Input) {
        return Input.equalsIgnoreCase("yes");
    }

    private void RequestPasswordError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("Invalid value \n");
        }
    }

    private void checkPassword() {
        String input;
        System.out.print("\nPassword: ");
        input = keyboard.next();
        final Password password = new Password(input);
        System.err.println("Strength [" + password.CalcScore() + "]\n\n");
    }

    private void menu() {
        System.out.println();
        System.out.println("1 - Password Generator");
        System.out.println("2 - Password Strength Check");
        System.out.println("0 - Quit");
        System.out.print("Choice: ");
    }

    private void quit() {
        System.out.println("Closing...");
    }
}
