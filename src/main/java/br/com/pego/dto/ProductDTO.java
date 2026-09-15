package br.com.pego.dto;

public record ProductDTO(
        Integer id,
        String name,
        Double price,
        Integer quantity
) {
    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
