package com.redeemerlives.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_order_line_items")
public class OrderLineItems {
    @Id
    @SequenceGenerator(name = "order_list_sequence", sequenceName = "order_list_sequence", allocationSize = 1)
    @GeneratedValue(generator = "order_list_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
