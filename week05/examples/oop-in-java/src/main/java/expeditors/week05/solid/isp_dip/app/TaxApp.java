package expeditors.week05.solid.isp_dip.app;

import expeditors.week05.solid.isp_dip.service.*;
import expeditors.week05.solid.isp_dip.service.interfaces.PropertyTaxProcessor;
import expeditors.week05.solid.isp_dip.service.interfaces.SalesTaxProcessor;
import expeditors.week05.solid.isp_dip.service.interfaces.TaxProcessor;

import java.text.NumberFormat;
import java.util.Locale;

public class TaxApp {
    private static final Locale locale = Locale.US;
    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    private static final NumberFormat percentFormatter = NumberFormat.getPercentInstance(locale);

    public static void main(String[] args) {
        System.out.println("Property tax demonstration...");
        demonstratePropertyTax(PropertyTaxProcessor.Region.NORTH, 200500.00);
        System.out.println();
        System.out.println("Sales tax demonstration...");
        demonstrateSalesTax("OH", 175.99);
        System.out.println();
        System.out.println("Generic tax demonstration...");
        demonstrateGenericTax(1999.98, 0.25);
    }

    private static void demonstratePropertyTax(PropertyTaxProcessor.Region region,
                                               double homePrice) {
        TaxProcessor processor = new PropertyTaxCalculator();
        // This won't work...
        // var service = new PropertyTaxService(processor);
        var service = new PropertyTaxService((PropertyTaxProcessor)processor);
        System.out.printf("For the %s region on a home price of %s, property taxes are %s\n",
                region, currencyFormatter.format(homePrice), currencyFormatter.format(service.calculatePropertyTax(region, homePrice)));
    }

    private static void demonstrateSalesTax(String stateAbbreviation, double purchasePrice) {
        TaxProcessor processor = new SalesTaxCalculator();
        // This won't work...
        // var service = new SalesTaxService(processor);
        var service = new SalesTaxService((SalesTaxProcessor)processor);
        System.out.printf("For goods purchased at a sales price of %s, sales tax will be %s\n",
                currencyFormatter.format(purchasePrice), currencyFormatter.format(service.calculatePropertyTax(stateAbbreviation, purchasePrice)));
    }

    private static void demonstrateGenericTax(double amount, double taxRate) {
        TaxProcessor processor1 = new PropertyTaxCalculator();
        GenericTaxService service1 = new GenericTaxService(processor1);
        double taxAmount1 = service1.calculateGenericTax(amount, taxRate);
        System.out.printf("For an amount of %s at a rate of %s, total tax will be %s\n",
                currencyFormatter.format(amount), percentFormatter.format(taxRate), currencyFormatter.format(taxAmount1));

        TaxProcessor processor2 = new SalesTaxCalculator();
        GenericTaxService service2 = new GenericTaxService(processor2);
        double taxAmount2 = service2.calculateGenericTax(amount, taxRate);
        System.out.printf("For an amount of %s at a rate of %s, total tax will be %s\n",
                currencyFormatter.format(amount), percentFormatter.format(taxRate), currencyFormatter.format(taxAmount2));
    }
}
