package expeditors.week05.solid.isp_dip.service.interfaces;

public interface SalesTaxProcessor extends TaxProcessor {
    double lookupStateTaxRate(String stateAbbreviation);
}
