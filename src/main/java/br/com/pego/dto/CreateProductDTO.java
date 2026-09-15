package br.com.pego.dto;

public record CreateProductDTO (
        String name,
        Double price,
        Integer quantity
) {
}
