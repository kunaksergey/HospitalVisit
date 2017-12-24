package ua.shield.del;

import java.io.*;
import java.util.*;

/**
 * Created by sa on 12.12.17.
 */
public class Main {
    public static void main(String[] args) {
//        List<Shape> list = new ArrayList<>();
//        list.add(new Circle(5));
//        list.add(new Rectangle(2, 45));
//        list.add(new Circle(8));
//        list.add(new Rectangle(3, 12));
//        try {
//            Shape shape = Shape.maxBroadLength(list);
//            Shape shape1 = Shape.maxSquire(list);
//            System.out.println(shape.broadLength());
//            System.out.println(shape.squire());
//            Shape.write(list, "test.txt");
//            Collection<Shape> readedCollection = Shape.read("test.txt");
//            System.out.println(Arrays.toString(readedCollection.toArray()));
//        } catch (IOException | ClassNotFoundException | CollectionIsEmptyException e) {
//            e.printStackTrace();
//        }
    }
}

abstract class Shape {
    abstract double broadLength();
    abstract double squire();

    static void write(Collection<Shape> shapeList, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new Ser(shapeList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Collection<Shape> read(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fos = new FileInputStream(fileName);
        ObjectInputStream oos = new ObjectInputStream(fos);
        Ser ser = (Ser) oos.readObject();
        return ser.getCollection();
    }

    static Shape maxBroadLength(Collection<Shape> collection) throws CollectionIsEmptyException {
        return maxFunction(collection, (s, s1) -> s.broadLength() > s1.broadLength());
    }


    static Shape maxSquire(Collection<Shape> collection) throws CollectionIsEmptyException {
        return maxFunction(collection, (s, s1) -> s.squire() > s1.squire());
    }

    private static Shape maxFunction(Collection<Shape> collection, ToBooleanBiFunction<Shape, Shape> function) throws CollectionIsEmptyException {
        if (collection.isEmpty()) throw new CollectionIsEmptyException();
        Iterator<Shape> iterator = collection.iterator();
        Shape shape = iterator.next();
        while (iterator.hasNext()) {
            Shape next = iterator.next();
            if (function.applyAsBoolean(next, shape)) {
                shape = next;
            }
        }
        return shape;
    }

    private static class Ser implements Serializable {
        Collection<Shape> collection;
        Ser(Collection<Shape> collection) {
            this.collection = collection;
        }
        Collection<Shape> getCollection() {
            return collection;
        }
    }

}

class Circle extends Shape implements Serializable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double broadLength() {
        return 2 * Math.PI * radius;
    }

    @Override
    double squire() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}

class Rectangle extends Shape implements Serializable {
    private double wight;
    private double height;

    public Rectangle(double wight, double height) {
        this.wight = wight;
        this.height = height;
    }

    @Override
    double broadLength() {
        return 2 * wight * height;
    }

    @Override
    double squire() {
        return wight * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "wight=" + wight +
                ", height=" + height +
                '}';
    }
}

