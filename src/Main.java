import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product(101, "Смартфон", 50000, "Электроника");
        Product product2 = new Product(101, "Другой смартфон", 60000, "Электроника");
        Product product3 = new Product(102, "Ноутбук", 90000, "Электроника");
        Product product4 = new Product(103, null, 1000, null);
        Product product5 = new Product(103, "Без категории", 2000, null);
        Product product6 = new Product(101, "Смартфон", 50000, "Телефоны");

        System.out.println("Товары:");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println(product4);
        System.out.println(product5);
        System.out.println(product6);

        System.out.println("\nСравнение товаров:");

        System.out.println("product1.equals(product2): "
                + product1.equals(product2)); // true

        System.out.println("product1.equals(product3): "
                + product1.equals(product3)); // false: разные id

        System.out.println("product4.equals(product5): "
                + product4.equals(product5)); // true: категории null

        System.out.println("product1.equals(product6): "
                + product1.equals(product6)); // false: разные категории

        System.out.println("product1.equals(product1): "
                + product1.equals(product1)); // true

        System.out.println("product1.equals(null): "
                + product1.equals(null)); // false

        System.out.println("product1.equals(\"Смартфон\"): "
                + product1.equals("Смартфон")); // false

        Order order1 = new Order("Анна", new Product[]{product1, product3});
        Order order2 = new Order("Анна", new Product[]{product2, product3});
        Order order3 = new Order("Анна", new Product[]{product3, product1});
        Order order4 = new Order("Иван", new Product[]{product1, product3});
        Order order5 = new Order(null, new Product[]{product4, null});
        Order order6 = new Order(null, new Product[]{product5, null});
        Order order7 = new Order(null, null);
        Order order8 = new Order(null, null);

        Order[] orders = {
                order1, order2, order3, order4,
                order5, order6, order7, order8
        };

        System.out.println("\nЗаказы:");
        for (Order order : orders) {
            System.out.println(order);
        }

        System.out.println("\nПопарное сравнение заказов:");
        for (int i = 0; i < orders.length; i++) {
            for (int j = i + 1; j < orders.length; j++) {
                System.out.println(
                        "Заказ " + (i + 1) + " и заказ " + (j + 1)
                                + ": " + orders[i].equals(orders[j])
                );
            }
        }

        System.out.println("Заказ равен самому себе: "
                + order1.equals(order1));
        System.out.println("Заказ равен null: "
                + order1.equals(null));
        System.out.println("Заказ равен товару: "
                + order1.equals(product1));
    }
}

class Product {
    private final int id;
    private final String name;
    private final int price;
    private final String category;

    public Product(int id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Товар[артикул=" + id
                + ", название=" + name
                + ", цена=" + price
                + ", категория=" + category + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Product product = (Product) object;

        return id == product.id
                && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }
}

class Order {
    private final String customer;
    private final Product[] basket;

    public Order(String customer, Product[] basket) {
        this.customer = customer;
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Заказ[customer=" + customer
                + ", basket=" + Arrays.toString(basket) + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Order order = (Order) object;

        if (!Objects.equals(customer, order.customer)) {
            return false;
        }

        if (basket == order.basket) {
            return true;
        }

        if (basket == null || order.basket == null) {
            return false;
        }

        if (basket.length != order.basket.length) {
            return false;
        }

        boolean equal = true;

        for (int i = 0; i < basket.length; i++) {
            Product product1 = basket[i];
            Product product2 = order.basket[i];

            if (product1 == null) {
                if (product2 != null) {
                    equal = false;
                    break;
                }
            } else if (!product1.equals(product2)) {
                equal = false;
                break;
            }
        }

        return equal;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(customer)
                + Arrays.hashCode(basket);
    }
}