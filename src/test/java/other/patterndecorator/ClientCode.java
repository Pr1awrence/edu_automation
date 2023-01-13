package other.patterndecorator;

import java.util.Arrays;

/*
* Output:
* Drawing a circle (first regular circle)
* decorate: add contour (modified blue circle)
* Drawing a circle (modified blue circle)
* decorate: set blue color (modified blue circle)
*/
public class ClientCode {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape blueCircle = new BlueShapeDecorator(circle);

        Arrays.asList(circle, blueCircle)
                .forEach(shape -> shape.draw());
    }
}
