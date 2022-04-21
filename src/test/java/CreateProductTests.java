import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Products;
import com.geekbrains.dto.Product;
import com.geekbrains.service.ProductService;
import com.geekbrains.util.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.geekbrains.enums.CategoryType.FOOD;
import static com.geekbrains.util.DbUtils.getProductsMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateProductTests {
    private static ProductService productService;
    private static int productId;
    private static ProductsMapper productsMapper;
    private Product newProduct;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
        productsMapper = getProductsMapper();
    }



    @BeforeEach
    void setUp() {
        newProduct = new Product()
                .withCategoryTitle(FOOD.getTitle())
                .withPrice(200)
                .withTitle("Маслице");
    }

    @DisplayName("Создать новый продукт(Позитивный тест)")
    @SneakyThrows
    @Test
    void createNewProductTest() {
        retrofit2.Response<Product> response =
                productService.createProduct(newProduct)
                        .execute();
        assertThat(response.isSuccessful()).isTrue();
        productId = response.body().getId();


        Products productInBase = productsMapper.selectByPrimaryKey((long) productId);

        assertThat(productInBase.getTitle()).isEqualTo(newProduct.getTitle());
        assertThat(productInBase.getPrice()).isEqualTo(newProduct.getPrice());
      //TODO как написать проверку на правильность id категории созданноно объекта в базе? Запуталась с енамами.
        // assertThat(productInBase.getCategoryId().intValue()).isEqualTo(newProduct.getCategoryTitle());


    }

    @DisplayName("Создать новый продукт(Негативный тест)")
    @SneakyThrows
    @Test
    void createNewProductNegativeTest() {
       int id = 1245;
        retrofit2.Response<Product> response =
                productService.createProduct(newProduct.withId(id))
                        .execute();
        assertThat(response.code()).isEqualTo(400);
        response.toString().contains("Id must be null for new entity");

        Products productInBase = productsMapper.selectByPrimaryKey((long)id);
        assertThat(productInBase).isNull();

    }


    @AfterAll
    static void afterAll() {
        productsMapper.deleteByPrimaryKey((long) productId);
    }

}
