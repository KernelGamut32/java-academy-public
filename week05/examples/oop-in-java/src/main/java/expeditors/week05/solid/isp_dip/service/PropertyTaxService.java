package expeditors.week05.solid.isp_dip.service;

import expeditors.week05.solid.isp_dip.service.interfaces.PropertyTaxProcessor;

public class PropertyTaxService {
    private final PropertyTaxProcessor propertyTaxProcessor;

    public PropertyTaxService(PropertyTaxProcessor propertyTaxProcessor) {
        this.propertyTaxProcessor = propertyTaxProcessor;
    }

    public double calculatePropertyTax(PropertyTaxProcessor.Region region, double homePrice) {
        // Could simulate a call to an external agent to
        // retrieve tax rate and calculate property tax, taking into account
        // more complex business rules (where applicable).
        double taxRate = propertyTaxProcessor.lookupTownshipTaxRate(region);
        // Imagine applying additional business rules here
        // specific to property taxes.
        return propertyTaxProcessor.calculateTax(homePrice, taxRate);
    }

    // Other service methods here...
}
