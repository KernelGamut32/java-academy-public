package gts.spring.orders.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {
    private String customerName;
    private String orderNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference // Prevents infinite recursion - better to use DTOs
    private List<OrderLineItem> orderLineItems;

    @PrePersist
    @PreUpdate
    private void assignLineItemsToOrder() {
        if (orderLineItems != null) {
            orderLineItems.forEach(orderLineItem -> orderLineItem.setOrder(this));
        }
    }
}