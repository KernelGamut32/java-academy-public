package expeditors.week06.ocp_lsp;

/**
 * Based on an article at <a href="https://www.geeksforgeeks.org/open-closed-principle-in-java-with-examples/">...</a>
 */
public class Cylinder implements GeometricObject {
    private final double radius;
    private final double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 2 * Math.PI * radius * (radius + height);
    }

    @Override
    public double calculateVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "radius=" + radius +
                ", height=" + height +
                ", area=" + calculateArea() +
                ", volume=" + calculateVolume() +
                "}";
    }
}
