import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Product.main(args);
        Order.main(args);
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

    public static void main(String[] args) {
        Product product1 = new Product(
                101, "Смартфон", 50000, "Электроника");
        Product product2 = new Product(
                101, "Другой смартфон", 60000, "Электроника");
        Product product3 = new Product(
                102, "Ноутбук", 90000, "Электроника");
        Product product4 = new Product(
                101, "Смартфон", 50000, "Телефоны");
        Product product5 = new Product(
                103, null, 1000, null);
        Product product6 = new Product(
                103, "Без категории", 2000, null);

        System.out.println("Тестирование класса Product:");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println(product4);
        System.out.println(product5);
        System.out.println(product6);

        // Название и цена различаются, но id и category совпадают.
        System.out.println("Товары 1 и 2 (ожидается true): "
                + product1.equals(product2));

        // Разные артикулы.
        System.out.println("Товары 1 и 3 (ожидается false): "
                + product1.equals(product3));

        // Разные категории при одинаковом артикуле.
        System.out.println("Товары 1 и 4 (ожидается false): "
                + product1.equals(product4));

        // Одинаковые артикулы и обе категории null.
        System.out.println("Товары 5 и 6 (ожидается true): "
                + product5.equals(product6));

        // У одного товара категория null, у другого — строка.
        Product product7 = new Product(
                103, "Аксессуар", 1000, "Аксессуары");
        System.out.println(product7);
        System.out.println("Товары 5 и 7 (ожидается false): "
                + product5.equals(product7));

        System.out.println("Сравнение с собой (ожидается true): "
                + product1.equals(product1));

        System.out.println("Сравнение с null (ожидается false): "
                + product1.equals(null));

        System.out.println("Сравнение со строкой (ожидается false): "
                + product1.equals("Смартфон"));
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

    public static void main(String[] args) {
        Product product1 = new Product(
                101, "Смартфон", 50000, "Электроника");
        Product product2 = new Product(
                101, "Другой смартфон", 60000, "Электроника");
        Product product3 = new Product(
                102, "Ноутбук", 90000, "Электроника");

        Order order1 = new Order(
                "Анна", new Product[]{product1, product3});
        Order order2 = new Order(
                "Анна", new Product[]{product2, product3});
        Order order3 = new Order(
                "Анна", new Product[]{product3, product1});
        Order order4 = new Order(
                "Иван", new Product[]{product1, product3});
        Order order5 = new Order(
                null, new Product[]{product1, null});
        Order order6 = new Order(
                null, new Product[]{product2, null});
        Order order7 = new Order(null, null);
        Order order8 = new Order(null, null);
        Order order9 = new Order(
                "Анна", new Product[]{product1});

        System.out.println("\nТестирование класса Order:");
        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        System.out.println(order4);
        System.out.println(order5);
        System.out.println(order6);
        System.out.println(order7);
        System.out.println(order8);
        System.out.println(order9);

        System.out.println("Заказы 1 и 2 (ожидается true): "
                + order1.equals(order2));

        System.out.println("Заказы 1 и 3 (ожидается false): "
                + order1.equals(order3));

        System.out.println("Заказы 1 и 4 (ожидается false): "
                + order1.equals(order4));

        System.out.println("Заказы 5 и 6 (ожидается true): "
                + order5.equals(order6));

        System.out.println("Заказы 7 и 8 (ожидается true): "
                + order7.equals(order8));

        System.out.println("Заказы 5 и 7 (ожидается false): "
                + order5.equals(order7));

        System.out.println("Заказы 1 и 9 (ожидается false): "
                + order1.equals(order9));

        System.out.println("Сравнение с собой (ожидается true): "
                + order1.equals(order1));

        System.out.println("Сравнение с null (ожидается false): "
                + order1.equals(null));

        System.out.println("Сравнение с товаром (ожидается false): "
                + order1.equals(product1));
    }
}