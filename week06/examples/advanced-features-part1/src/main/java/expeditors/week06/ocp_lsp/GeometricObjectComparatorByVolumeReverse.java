package expeditors.week06.ocp_lsp;

import java.util.Comparator;

public class GeometricObjectComparatorByVolumeReverse implements Comparator<GeometricObject> {
    @Override
    public int compare(GeometricObject o1, GeometricObject o2) {
        return -1 * Double.compare(o1.calculateVolume(), o2.calculateVolume());
    }
}
