package com.Jordan.Example.DesignPattern.AbsFactoryExample;

public class Demo {
    public static void main(String[] args) {

        // get shape factory
        AbstractFactory shapeFactory1 = FactoryProducer.getFactory(false);
        AbstractFactory shapeFactory2 = FactoryProducer.getFactory(true);

        Shape shape1 = shapeFactory1.getShape("RECTANGLE");
        Shape shape2 = shapeFactory1.getShape("SQUARE");

        Shape shape3 = shapeFactory2.getShape("RECTANGLE");
        Shape shape4 = shapeFactory2.getShape("SQUARE");


        shape1.draw();
        shape2.draw();
        shape3.draw();
        shape4.draw();



    }
}
