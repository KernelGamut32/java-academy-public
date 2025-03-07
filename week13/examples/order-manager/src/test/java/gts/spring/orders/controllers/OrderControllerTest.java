package gts.spring.orders.controllers;

import gts.spring.orders.domain.Order;
import gts.spring.orders.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    void testCreateOrder() throws Exception {
        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content("{\"customerName\": \"Jane Doe\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setCustomerName("Jane Doe");
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(new Order()));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateOrder() throws Exception {
        mockMvc.perform(put("/orders/1")
                        .contentType("application/json")
                        .content("{\"customerName\": \"Updated Name\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isOk());
    }
}