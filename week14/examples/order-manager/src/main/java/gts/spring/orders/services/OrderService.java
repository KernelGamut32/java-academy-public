package gts.spring.orders.services;

import gts.spring.orders.repositories.jpa.OrderRepository;
import gts.spring.orders.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setCustomerName(updatedOrder.getCustomerName());
            order.setOrderNumber(updatedOrder.getOrderNumber());
            order.setOrderLineItems(updatedOrder.getOrderLineItems());
//            order.getOrderLineItems().clear();
//            order.getOrderLineItems().addAll(updatedOrder.getOrderLineItems());
            return orderRepository.save(order);
        }).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
