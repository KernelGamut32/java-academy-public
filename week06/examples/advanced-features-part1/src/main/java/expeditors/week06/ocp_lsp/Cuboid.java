package expeditors.week06.ocp_lsp;

/**
 * Based on an article at <a href="https://www.geeksforgeeks.org/open-closed-principle-in-java-with-examples/">...</a>
 */
public class Cuboid implements GeometricObject {
    private final double length;
    private final double width;
    private final double height;

    public Cuboid(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 2 * ((length * width) + (length * height) + (width * height));
    }

    @Override
    public double calculateVolume() {
        return length * width * height;
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", area=" + calculateArea() +
                ", volume=" + calculateVolume() +
                "}";
    }
}
