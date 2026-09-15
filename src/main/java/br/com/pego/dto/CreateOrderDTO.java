package br.com.pego.dto;

import br.com.pego.model.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateOrderDTO (
        Integer userId,
        BigDecimal total
) {

}
