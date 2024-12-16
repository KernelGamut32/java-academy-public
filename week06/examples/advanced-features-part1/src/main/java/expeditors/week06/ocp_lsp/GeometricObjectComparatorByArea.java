package expeditors.week06.ocp_lsp;

import java.util.Comparator;

public class GeometricObjectComparatorByArea implements Comparator<GeometricObject> {
    @Override
    public int compare(GeometricObject o1, GeometricObject o2) {
        return Double.compare(o1.calculateArea(), o2.calculateArea());
    }
}
