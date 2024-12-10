package expeditors.week05.solid.isp_dip.service;

import expeditors.week05.solid.isp_dip.service.interfaces.SalesTaxProcessor;

public class SalesTaxCalculator implements SalesTaxProcessor {
    @Override
    public double lookupStateTaxRate(String stateAbbreviation) {
        int ascii = stateAbbreviation.toLowerCase().chars().sum();
        return ascii / 1000.0;
    }

    @Override
    public double calculateTax(double amount, double rate) {
        return amount * rate;
    }
}
