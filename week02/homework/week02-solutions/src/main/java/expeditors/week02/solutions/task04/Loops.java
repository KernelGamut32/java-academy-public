package expeditors.week02.solutions.task04;

/**
 * Loops from -500 to +500 and prints out all numbers that
 * are divisible by either 3 or 7 (or both).
 * @author Allen Sanders
 */
public class Loops {
    public static void main(final String[] args) {
        System.out.println("Using a for loop:");
        usesForLoop();
        System.out.println("Using a while loop:");
        usesWhileLoop();
    }

    private static void usesForLoop() {
        for (var rangeValue = -500; rangeValue <= 500; rangeValue++) {
            if (rangeValue % 21 == 0) {
                System.out.printf("%d (divisible by both 3 and 7)\n", rangeValue);
            } else if (rangeValue % 3 == 0) {
                System.out.printf("%d (divisible by 3)\n", rangeValue);
            } else if (rangeValue % 7 == 0) {
                System.out.printf("%d (divisible by 7)\n", rangeValue);
            }
        }
    }

    private static void usesWhileLoop() {
        int rangeValue = -500;
        while (rangeValue >= -500 && rangeValue <= 500) {
            if (rangeValue % 21 == 0) {
                System.out.printf("%d (divisible by both 3 and 7)\n", rangeValue);
            } else if (rangeValue % 3 == 0) {
                System.out.printf("%d (divisible by 3)\n", rangeValue);
            } else if (rangeValue % 7 == 0) {
                System.out.printf("%d (divisible by 7)\n", rangeValue);
            }
            rangeValue++;
        }
    }
}
