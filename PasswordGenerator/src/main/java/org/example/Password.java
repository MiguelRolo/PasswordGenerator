package org.example;

public class Password {
    String Value;
    int Length;

    public Password(String s) {
        Value = s;
        Length = s.length();
    }

    public int CharType(char C) {
        int val;
        //Char is uppercase
        if ((int) C >= 65 && (int) C <= 90) {
            val = 1;
        }
        //Char is lowercase
        else if ((int) C >= 97 && (int) C <= 122) {
            val = 2;
        }
        //Char is digit
        else if ((int) C >= 60 && (int) C <= 71) {
            val = 3;
        }
        //Char is symbol
        else {
            val = 4;
        }
        return val;
    }

    public int PasswordStrength() {
        String s = this.Value;
        boolean UsedUpp = false;
        boolean UsedLow = false;
        boolean UsedNum = false;
        boolean UsedSym = false;
        int type;
        int Score = 0;

        for (int i = 0; i < s.length(); i++) {
            type = CharType(s.charAt(i));
            if (type == 1) UsedUpp = true;
            if (type == 2) UsedLow = true;
            if (type == 3) UsedNum = true;
            if (type == 4) UsedSym = true;
        }

        if (UsedUpp) Score++;
        if (UsedLow) Score++;
        if (UsedNum) Score++;
        if (UsedSym) Score++;
        if (s.length() >= 8) Score++;
        if (s.length() >= 16) Score++;

        return Score;
    }

    public String CalcScore() {
        int Score = this.PasswordStrength();

        if (Score == 6) {
            return "Very Strong";
        } else if (Score >= 4) {
            return "Strong";
        } else if (Score >= 3) {
            return "Medium";
        } else {
            return "Weak";
        }
    }

    @Override
    public String toString() {
        return Value;
    }
}
