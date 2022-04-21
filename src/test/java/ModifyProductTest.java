import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;
import com.geekbrains.dto.Product;
import com.geekbrains.enums.CategoryType;
import com.geekbrains.service.ProductService;
import com.geekbrains.util.RetrofitUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.geekbrains.util.DbUtils.getProductsMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.geekbrains.enums.CategoryType.FOOD;

public class ModifyProductTest {
    private static ProductService productService;
    private static int productId;
    private static ProductsMapper productsMapper;
    //private static Product newProduct;


    @SneakyThrows
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
        productsMapper = getProductsMapper();

        retrofit2.Response<Product> response =
                productService.createProduct(new Product().withCategoryTitle(FOOD.getTitle()).withTitle("Сметанка").withPrice(500))
                        .execute();
        productId = response.body().getId();
    }


    @DisplayName("Изменение данных о продукте (позитивный тест)")
    @SneakyThrows
    @Test
    void modifyProductTest(){
        Product productForChange = new Product()
                .withId(productId)
                .withCategoryTitle(FOOD.getTitle())
                .withPrice(200)
                .withTitle("Сырничек");

        retrofit2.Response<Product> response =
                productService.modifyProduct(productForChange)
                        .execute();

        Products productInBase = productsMapper.selectByPrimaryKey((long) productId);

        assertThat(productInBase.getTitle()).isEqualTo(productForChange.getTitle());
        assertThat(productInBase.getPrice()).isEqualTo(productForChange.getPrice());

        // TODO Как сверить категорию продукта в базе: заменилась ли??


    }

    @AfterAll
    static void afterAll() {
        productsMapper.deleteByPrimaryKey((long) productId);
    }


}
