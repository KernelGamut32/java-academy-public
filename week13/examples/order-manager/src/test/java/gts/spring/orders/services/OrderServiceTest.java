package gts.spring.orders.services;

import gts.spring.orders.dao.jpa.OrderRepository;
import gts.spring.orders.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setCustomerName("Jane Doe");
    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        Order createdOrder = orderService.createOrder(order);
        assertNotNull(createdOrder);
    }

    @Test
    void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Optional<Order> foundOrder = orderService.getOrderById(1L);
        assertTrue(foundOrder.isPresent());
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order));
        List<Order> orders = orderService.getAllOrders();
        assertFalse(orders.isEmpty());
    }

    @Test
    void testUpdateOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenReturn(order);
        Order updatedOrder = orderService.updateOrder(1L, order);
        assertNotNull(updatedOrder);
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);
        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }
}