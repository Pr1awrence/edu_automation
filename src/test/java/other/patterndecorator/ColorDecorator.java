package other.patterndecorator;

/*
* Абстрактный класс ColorDecorator, реализующий интерфейс Shape
* и содержащий в себе экземпляр Shape
* Абстрактный класс декоратора не обязателен, но нужен если важно
* добавить повторяющиеся действия в работу всех декораторов,
* например добавить у всех декораторов отрисовку фигурам черной рамки
*/
public abstract class ColorDecorator implements Shape {
    protected Shape decoratedShape;

    public ColorDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        System.out.println("decorate: add contour");
        decoratedShape.draw();
    }
}
