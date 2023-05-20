import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    ShopRepository repository = new ShopRepository();
    Product product1 = new Product(35, "Книга", 350);
    Product product2 = new Product(48, "Парфюмерная вода", 8_000);
    Product product3 = new Product(157, "Молоко", 50);

    @BeforeEach
    public void setup() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
    }

    @Test
    public void shouldRemoveByIdIfExists() {
        repository.remove(48);
        Product[] expected = {product1, product3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.remove(7);
        });
    }

    @Test
    public void shouldAddNewProduct() {
        Product product4 = new Product(74, "Крем для рук", 410);
        repository.add(product4);
        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsException() {
        Product product5 = new Product(35, "хлеб", 35);
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product5);
        });
    }
}
