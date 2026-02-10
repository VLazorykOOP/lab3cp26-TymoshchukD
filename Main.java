import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
// =====================
// Prototype
// =====================
interface Product extends Cloneable {
    Product clone();
    String getName();
    double getPrice();
}

class Phone implements Product {
    private String name;
    private double price;

    public Phone(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Product clone() {
        return new Phone(name, price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// =====================
// Decorator
// =====================
abstract class ProductDecorator implements Product {
    protected Product product;

    public ProductDecorator(Product product) {
        this.product = product;
    }

    @Override
    public Product clone() {
        return product.clone();
    }
}

class GiftDecorator extends ProductDecorator {

    public GiftDecorator(Product product) {
        super(product);
    }

    @Override
    public String getName() {
        return product.getName() + " + gift wrap";
    }

    @Override
    public double getPrice() {
        return product.getPrice() + 20;
    }
}

class WarrantyDecorator extends ProductDecorator {

    public WarrantyDecorator(Product product) {
        super(product);
    }

    @Override
    public String getName() {
        return product.getName() + " + extended warranty";
    }

    @Override
    public double getPrice() {
        return product.getPrice() + 50;
    }
}

// =====================
// Iterator
// =====================
class ShoppingCart implements Iterable<Product> {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}

// =====================
// Main
// =====================

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your budget: ");
        double budget = scanner.nextDouble();

        Product phone = new Phone("Samsung Galaxy", 800);
        Product phoneCopy = phone.clone();

        Product decoratedPhone =
                new GiftDecorator(new WarrantyDecorator(phoneCopy));

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(phone);
        cart.addProduct(decoratedPhone);

        for (Product p : cart) {
            System.out.println("Product: " + p.getName());
            System.out.println("Price: " + p.getPrice());

            if (p.getPrice() <= budget) {
                System.out.println("Status: You can buy this product");
            } else {
                System.out.println("Status: Not enough budget");
            }

            System.out.println("-------------------");
        }

        scanner.close();
    }
}
