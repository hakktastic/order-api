package nl.hakktastic.order.api.order.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order.
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Customer name should be provided")
    @Size(min=2, max = 256, message = "Product name should be min. 2 and max. 256 characters")
    private String customerName;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should contain ten digits")
    private String mobileNr;

    @PastOrPresent(message = "Order date time should be today or in the past")
    private LocalDateTime orderDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private List<OrderItemEntity> orderItemList;
}
