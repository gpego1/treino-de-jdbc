package br.com.pego.presentation;
import br.com.pego.dto.CreateProductDTO;
import br.com.pego.dto.ProductDTO;
import br.com.pego.service.ProductService;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    public List<ProductDTO>   getProducts() throws SQLException {
        return productService.getProducts();
    }

    public ProductDTO getProduct(Integer id) throws SQLException {
        return productService.getProductById(id);
    }

    public void createProduct(CreateProductDTO dto) throws SQLException {
         productService.createProduct(dto);
    }

    public void updateProduct(Integer id, CreateProductDTO dto) throws SQLException {
        productService.updateProduct(id, dto);
    }

    public void deleteProduct(Integer id) throws SQLException {
        productService.deleteProduct(id);
    }
}
