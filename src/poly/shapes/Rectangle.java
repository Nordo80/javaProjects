package poly.shapes;

public class Rectangle implements Shape{
    private int height;
    private int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public double getArea() {
        return height * width;
    }
}
