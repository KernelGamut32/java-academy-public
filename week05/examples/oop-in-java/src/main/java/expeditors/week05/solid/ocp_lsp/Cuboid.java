package expeditors.week05.solid.ocp_lsp;

/**
 * Based on an article at <a href="https://www.geeksforgeeks.org/open-closed-principle-in-java-with-examples/">...</a>
 */
public class Cuboid extends GeometricObject {
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
}
