import java.util.Scanner;

public class RationalNumber {

    // fields to store numerator and denominator of rational number
    private int numerator;
    private int denominator;

    // constructor to create a new rational number
    public RationalNumber(int numerator, int denominator) {
        // check if denominator is zero
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        // simplify the fraction using the greatest common divisor (gcd)
        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;

        // ensure that the sign is on the numerator and not the denominator
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }

    // method to calculate the greatest common divisor (gcd) of two numbers
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // method to add two rational numbers
    public RationalNumber add(RationalNumber other) {
        // calculate the least common multiple (lcm) of the two denominators
        int lcd = denominator * other.denominator / gcd(denominator, other.denominator);

        // add the numerators using the common denominator and return a new RationalNumber
        int newNumerator = numerator * lcd / denominator + other.numerator * lcd / other.denominator;
        return new RationalNumber(newNumerator, lcd);
    }

    // method to subtract two rational numbers
    public RationalNumber subtract(RationalNumber other) {
        // calculate the least common multiple (lcm) of the two denominators
        int lcd = denominator * other.denominator / gcd(denominator, other.denominator);

        // subtract the numerators using the common denominator and return a new RationalNumber
        int newNumerator = numerator * lcd / denominator - other.numerator * lcd / other.denominator;
        return new RationalNumber(newNumerator, lcd);
    }

    // method to multiply two rational numbers
    public RationalNumber multiply(RationalNumber other) {
        // multiply the numerators and denominators separately and return a new RationalNumber
        int newNumerator = numerator * other.numerator;
        int newDenominator = denominator * other.denominator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    // method to divide two rational numbers
    public RationalNumber divide(RationalNumber other) {
        // check if the numerator of the second rational number is zero
        if (other.numerator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        // multiply the first numerator by the second denominator and the first denominator by the second numerator
        // and return a new RationalNumber
        int newNumerator = numerator * other.denominator;
        int newDenominator = denominator * other.numerator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    // method to convert the rational number to a double
    public double toDouble() {
        return (double) numerator / denominator;
    }

// method to return the absolute value of the rational
public RationalNumber abs() {
    return new RationalNumber(Math.abs(numerator), denominator);
}

    public int compareTo(RationalNumber other) {
        int lcd = denominator * other.denominator / gcd(denominator, other.denominator);
        int thisNumerator = numerator * lcd / denominator;
        int otherNumerator = other.numerator * lcd / other.denominator;
        return Integer.compare(thisNumerator, otherNumerator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Take user input
        System.out.print("Enter first rational number (in the format a/b): ");
        String[] firstParts = scanner.nextLine().split("/");
        int firstNumerator, firstDenominator;
        try {
            firstNumerator = Integer.parseInt(firstParts[0]);
            firstDenominator = Integer.parseInt(firstParts[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid input format");
            return;
        }
        RationalNumber first = new RationalNumber(firstNumerator, firstDenominator);

        System.out.print("Enter second rational number (in the format a/b): ");
        String[] secondParts = scanner.nextLine().split("/");
        int secondNumerator, secondDenominator;
        try {
            secondNumerator = Integer.parseInt(secondParts[0]);
            secondDenominator = Integer.parseInt(secondParts[1]);
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid input format");
            return;
        }
        RationalNumber second = new RationalNumber(secondNumerator, secondDenominator);
        // Perform operations and display results
        System.out.println("First rational number is: " + first);
        System.out.println("Second rational number is: " + second);
        System.out.println("The Sum is: " + first.add(second));
        System.out.println("The Difference is: " + first.subtract(second));
        System.out.println("The Product is: " + first.multiply(second));
        try {
            System.out.println("The Quotient is: " + first.divide(second));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + " hello");
        }
        System.out.println("Absolute value of the first rational number is: " + first.abs());
        System.out.println("Absolute value of second rational number is: " + second.abs());
        System.out.println("First as a floating point number is: " + first.toDouble());
        System.out.println("Second as a floating point number is: " + second.toDouble());
        System.out.println("Comparison result is: " + first.compareTo(second));
    }
}