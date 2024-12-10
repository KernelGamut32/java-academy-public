package expeditors.week05.solid.ocp_lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on an article at <a href="https://www.geeksforgeeks.org/open-closed-principle-in-java-with-examples/">...</a>
 */
public class Shapes {
    public static void main(String[] args) {
        List<GeometricObject> shapes = getShapes();
        calculateAndDisplayVolumes(shapes);
        System.out.println();
        calculateAndDisplayAreas(shapes);
    }

    private static List<GeometricObject> getShapes() {
        List<GeometricObject> shapes = new ArrayList<>();
        shapes.add(new Cuboid(3.4, 9.2, 1.45));
        shapes.add(new Cylinder(14.56, 1.998));
        shapes.add(new Sphere(22.34));
        return shapes;
    }

    private static void calculateAndDisplayVolumes(List<GeometricObject> shapes) {
        System.out.println("Shape Volumes:");
        System.out.println("-".repeat(80));
        for (GeometricObject shape : shapes) {
            System.out.printf("%s Volume =\t%.4f\n", shape.getClass().getSimpleName(), shape.calculateVolume());
        }
    }

    private static void calculateAndDisplayAreas(List<GeometricObject> shapes) {
        System.out.println("Shape Areas:");
        System.out.println("-".repeat(80));
        for (GeometricObject shape : shapes) {
            System.out.printf("%s Area =\t%.4f\n", shape.getClass().getSimpleName(), shape.calculateVolume());
        }
    }
}
