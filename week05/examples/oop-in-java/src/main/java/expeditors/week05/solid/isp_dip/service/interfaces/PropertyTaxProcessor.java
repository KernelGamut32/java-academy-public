package expeditors.week05.solid.isp_dip.service.interfaces;

public interface PropertyTaxProcessor extends TaxProcessor {
    enum Region {
        EAST,
        WEST,
        NORTH,
        SOUTH,
    }

    double lookupTownshipTaxRate(Region region);
}
