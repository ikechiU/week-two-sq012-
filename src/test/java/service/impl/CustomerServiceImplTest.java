package service.impl;

import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    CustomerServiceImpl customerService;
    Customer customer;
    Customer customer2;

    @BeforeEach
    void init() {
        customerService = new CustomerServiceImpl();
        customer = new Customer("Henry", "Male", 43);
        customer2 = new Customer("Angela", "Female", 21);
    }

    @Test
    void buy() {
        String result = customerService.buy(customer, "BeAnS");
        String result1 = customerService.buy(customer2, "rIcE");

        assertEquals(result, "Hello cashier, I will like to buy " + customer.getProductName() + ".");
        assertEquals(result1, "Hello cashier, I will like to buy " + customer2.getProductName() + ".");
    }

    @Test
    void BuyOverLoaded() {
        String result = customerService.buy(customer, "BeAnS", 2022);
        String result1 = customerService.buy(customer2, "rIcE", 2033);

        assertEquals(result,
                "Hello cashier, I will like to buy " + customer.getProductName() + ". That was manufactured in " + customer.getProductYear() +".");
        assertEquals(result1,
                "Hello cashier, I will like to buy " + customer2.getProductName() + ". That was manufactured in " + customer2.getProductYear() +".");
    }

    @Test
    void confirmProductName() {
        String productName = customerService.confirmProductName("BeANs");
        String productName1 = customerService.confirmProductName("RicE");
        String productName2 = customerService.confirmProductName("CAsHeW");
        assertEquals("Beans", productName);
        assertEquals("Rice", productName1);
        assertEquals("Cashew", productName2);
    }
}