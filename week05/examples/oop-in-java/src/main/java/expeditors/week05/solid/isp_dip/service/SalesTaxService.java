package expeditors.week05.solid.isp_dip.service;

import expeditors.week05.solid.isp_dip.service.interfaces.SalesTaxProcessor;

public class SalesTaxService {
    private final SalesTaxProcessor salesTaxProcessor;

    public SalesTaxService(SalesTaxProcessor salesTaxProcessor) {
        this.salesTaxProcessor = salesTaxProcessor;
    }

    public double calculatePropertyTax(String stateAbbreviation, double purchasePrice) {
        // Could simulate a call to an external agent to
        // retrieve tax rate and calculate sales tax for a specific
        // state.
        double taxRate = salesTaxProcessor.lookupStateTaxRate(stateAbbreviation);
        // Imagine applying additional business rules here
        // specific to sales tax for a given state.
        return salesTaxProcessor.calculateTax(purchasePrice, taxRate);
    }

    // Other service methods here...
}
