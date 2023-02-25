package nl.hakktastic.order.api.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Product Entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Validated
@Table(name = "product")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotBlank(message = "Product name should be provided")
    @Size(min=2, max = 256, message = "Product name should be min. 2 and max. 256 characters")
    private String name;

    @Positive(message = "Product price should be positive")
    BigDecimal price;
}
