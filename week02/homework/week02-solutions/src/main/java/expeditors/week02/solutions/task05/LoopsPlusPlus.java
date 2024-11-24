package expeditors.week02.solutions.task05;

/**
 * Loops from -500 to +500 and prints out the count of numbers that
 * are divisible by either 3 or 7 (or both).
 * @author Allen Sanders
 */
public class LoopsPlusPlus {
    public static void main(final String[] args) {
        var countOfDivisibleBy3And7 = 0;
        var countOfDivisibleBy3 = 0;
        var countOfDivisibleBy7 = 0;

        for (var rangeValue = -500; rangeValue <= 500; rangeValue++) {
            if (rangeValue % 21 == 0) {
                countOfDivisibleBy3And7++;
            } else if (rangeValue % 3 == 0) {
                countOfDivisibleBy3++;
            } else if (rangeValue % 7 == 0) {
                countOfDivisibleBy7++;
            }
        }
        System.out.printf("Number of values in range divisible by both 3 and 7: %d\n", countOfDivisibleBy3And7);
        System.out.printf("Number of values in range divisible by 3 only: %d\n", countOfDivisibleBy3);
        System.out.printf("Number of values in range divisible by 7 only: %d\n", countOfDivisibleBy7);
    }
}
