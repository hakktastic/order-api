package nl.hakktastic.order.api.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Validated
@Entity
@Table(name="item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int productId;

    @Positive(message = "Quantity should be positive")
    @Min(value = 1, message = "Cart item should be min. 1")
    private int quantity;
}
