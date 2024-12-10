package expeditors.week05.solid.isp_dip.service;

import expeditors.week05.solid.isp_dip.service.interfaces.PropertyTaxProcessor;

public class PropertyTaxCalculator implements PropertyTaxProcessor {
    private static final double OVERHEAD = 0.005;

    @Override
    public double lookupTownshipTaxRate(Region region) {
        return switch (region) {
            case EAST -> 0.05;
            case WEST -> 0.075;
            case NORTH -> 0.08;
            case SOUTH -> 0.12;
        };
    }

    @Override
    public double calculateTax(double amount, double rate) {
        return (rate + OVERHEAD) * amount;
    }
}
