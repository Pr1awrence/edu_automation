package other.patterndecorator;

public class BlueShapeDecorator extends ColorDecorator {
    public BlueShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        setBlueColor(decoratedShape);
    }

    private void setBlueColor(Shape decoratedShape) {
        System.out.println("decorate: set blue color");
    }
}
