package nl.hakktastic.order.api.cart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Positive(message = "Product ID should be positive")
    @Min(value = 1, message = "Product ID should be min. 1")
    private int productId;

    @Positive(message = "Quantity should be positive")
    @Min(value = 1, message = "Cart item should be min. 1")
    private int quantity;
}
