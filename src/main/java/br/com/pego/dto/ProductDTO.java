package br.com.pego.dto;

public record ProductDTO(
        Integer id,
        String name,
        Double price,
        Integer quantity
) {
}
