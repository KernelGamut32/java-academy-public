package expeditors.week03.commissions;

import expeditors.week03.utility.InputHelper;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Prompts user for information about multiple sales amounts,
 * a commission rate, and displays the collection of resulting commissions.
 * @author Allen Sanders
 */
public class CommissionManager {
    public static void main(final String[] args) {
        System.out.println("Welcome to the Commission Manager!");
        String salespersonId;
        int numberOfSales;
        double commissionRate;
        double[] salesAmounts;
        double[] commissionAmounts;

        try (Scanner in = new Scanner(System.in)) {
            salespersonId = InputHelper.gatherInput(in, "Please enter the ID for the salesperson: ",
                    "Please provide a valid value for salesperson ID!");
            numberOfSales = InputHelper.gatherInput(in, String.format("How many sales did salesperson #%s record this quarter? ", salespersonId),
                    "Please provide a valid value for number of sales!", 0, 5);
            salesAmounts = new double[numberOfSales];
            commissionRate = InputHelper.gatherInput(in, "Please enter the salesperson's commission rate: ",
                    "Please provide a valid value for salesperson's commission rate!", 0.0, 0.45);
            for (var counter = 0; counter < numberOfSales; counter++) {
                salesAmounts[counter] = InputHelper.gatherInput(in, String.format("Please enter sales amount #%d: ", counter + 1),
                        "Please provide a valid value for sales amount!", 0.0, 100000.0);
            }
            commissionAmounts = calculateCommissions(salesAmounts, commissionRate);
            outputCommissionResults(salespersonId, salesAmounts, commissionAmounts, commissionRate);
        }
    }

    private static double[] calculateCommissions(final double[] salesAmounts, double commissionRate) {
        double[] commissions = new double[salesAmounts.length];
        for (var counter = 0; counter < salesAmounts.length; counter++) {
            commissions[counter] = salesAmounts[counter] * commissionRate;
        }
        return commissions;
    }

    private static void outputCommissionResults(String salespersonId, double[] salesAmounts,
                                                double[] commissionAmounts, double commissionRate) {
        Locale locale = Locale.US;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);

        System.out.println("Summary of commission results:");
        System.out.println();
        System.out.printf("Salesperson #%s earned a %s commission rate on %d sales", salespersonId,
                percentFormat.format(commissionRate), salesAmounts.length);
        System.out.println();
        int trackingValue = -1;
        for (var salesAmount : salesAmounts) {
            trackingValue++;
            System.out.printf("Sales Amount: %s\t\tCommission: %s\n",
                    currencyFormat.format(salesAmount), currencyFormat.format(commissionAmounts[trackingValue]));
        }
    }
}
