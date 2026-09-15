package br.com.pego.service;
import br.com.pego.dao.ProductDAO;
import br.com.pego.dto.CreateProductDTO;
import br.com.pego.dto.ProductDTO;
import br.com.pego.model.ProductEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public List<ProductDTO> getProducts() throws SQLException {
        List<ProductEntity> products = productDAO.listAllProducts();

        return products.stream()
            .map(this::convertToDTO)
            .toList();
    }

    public ProductDTO getProductById(Integer id) throws SQLException {
        ProductEntity product = productDAO.getProductById(id);

        if (product != null) {
            return this.convertToDTO(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public void createProduct(CreateProductDTO dto) throws SQLException {
            List<ProductEntity> allProducts = productDAO.listAllProducts();

            Integer newId = allProducts
                    .stream()
                    .map(ProductEntity::getId)
                    .max(Integer::compareTo)
                    .orElse(0) + 1;

            ProductEntity productEntity = this.convertToEntity(dto);

            if (productEntity.getId() == null) {
                productEntity.setId(newId);
                productDAO.createPoduct(productEntity);
            } else {
                throw new  RuntimeException("Product already exists");
            }
    }

    public void updateProduct(Integer id, CreateProductDTO dto) throws SQLException {
        ProductEntity prod = productDAO.getProductById(id);

        if (prod == null) {
            throw new SQLException("Product not found");
        }

        prod.setName(dto.name());
        prod.setPrice(dto.price());
        prod.setQuantity(dto.quantity());

        productDAO.updatePoduct(prod);
    }

    public void deleteProduct(Integer id) throws SQLException {
        ProductEntity product = productDAO.getProductById(id);

        if (product == null) {
            throw new SQLException("Product not found");
        }
        productDAO.deletePoduct(id);
    }

    private ProductEntity convertToEntity(CreateProductDTO dto) {
        return new ProductEntity(
                dto.name(),
                dto.price(),
                dto.quantity()
        );
    }

    private ProductDTO convertToDTO(ProductEntity prod) {
        return new ProductDTO(
                prod.getId(),
                prod.getName(),
                prod.getPrice(),
                prod.getQuantity()
        );
    }
}
