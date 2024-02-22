import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws Exception {
        List<Book> books = List.of(
            new Book("Вино из одуванчиков", 100),
            new Book("Лето, прощай", 200),
            new Book("Марсианские хроники", 300),
            new Book("Рассказы", 250),
            new Book("Дюна", 125),
            new Book("Мастер и Маргарита", 400)
        );

        double minPrice = books.stream().mapToDouble(b -> b.getPrice()).min().getAsDouble();
        double maxPrice = books.stream().mapToDouble(b -> b.getPrice()).max().getAsDouble();
        double avgPrice = books.stream().mapToDouble(b -> b.getPrice()).average().getAsDouble();

        out.printf("Самая дешевая книга стоит: %.2f рублей\n", minPrice);
        out.printf("Самая дорогая книга стоит: %.2f рублей\n", maxPrice);
        out.printf("Средняя цена на книгу составляет: %.2f рублей\n", avgPrice);

        Stream<Book> stream = books.stream();
        stream.filter(b -> b.getName().length() > 10).forEach(out::println);
        
    }
}
