package nl.hakktastic.order.api.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO implements Serializable {

    private int id;
    private String customerName;
    private String mobileNr;
    private LocalDateTime orderDateTime;
    private ArrayList<OrderItemDTO> orderItemList;
}
