package br.com.pego.presentationsTests;
import br.com.pego.Main;
import br.com.pego.dto.CreateProductDTO;
import br.com.pego.presentation.ProductController;
import junit.framework.TestCase;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductMockTests extends TestCase {

    ProductController productController = new ProductController();

    @Test
    public void testCreateProduct() throws SQLException {
        List<CreateProductDTO> products = List.of(
                new CreateProductDTO("makita", 122.90, 14),
                new CreateProductDTO("bola", 99.90, 14),
                new CreateProductDTO("lapis", 1.23, 14),
                new CreateProductDTO("mouse", 87.76, 14),
                new CreateProductDTO("banana", 7.40, 14)
        );

        products.forEach(product -> {
            try {
                productController.createProduct(product);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    @Test
    public void testUpdateProduct() throws SQLException {
        productController.updateProduct(15, new CreateProductDTO("Azeite", 17.80, 2));
        System.out.println(productController.getProduct(15));
    }

    @Test
    public void testDeleteProduct() throws SQLException {
        productController.deleteProduct(1);
    }

    @Test
    public void testGetProduct() throws SQLException {
        productController.getProduct(17);
    }

    @Test
    public void testGetAllProducts() throws SQLException {
        productController.getProducts();
    }
}
