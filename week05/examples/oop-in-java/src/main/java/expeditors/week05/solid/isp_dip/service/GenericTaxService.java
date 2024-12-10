package expeditors.week05.solid.isp_dip.service;

import expeditors.week05.solid.isp_dip.service.interfaces.PropertyTaxProcessor;
import expeditors.week05.solid.isp_dip.service.interfaces.SalesTaxProcessor;
import expeditors.week05.solid.isp_dip.service.interfaces.TaxProcessor;

public class GenericTaxService {
    private final TaxProcessor taxProcessor;

    public GenericTaxService(TaxProcessor taxProcessor) {
        this.taxProcessor = taxProcessor;
    }

    public double calculateGenericTax(double amount, double rate) {
        if (taxProcessor instanceof PropertyTaxProcessor) {
            System.out.println("Calculating property tax generically...");
        } else if (taxProcessor instanceof SalesTaxProcessor) {
            System.out.println("Calculating sales tax generically...");
        } else {
            System.out.println("Calculating other tax generically...");
        }
        return taxProcessor.calculateTax(amount, rate);
    }

    // Other service methods here...
}
