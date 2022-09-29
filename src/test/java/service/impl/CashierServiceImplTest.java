package service.impl;

import model.Cashier;
import model.Customer;
import model.Product;
import model.Store;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceImplTest {
    private final Store storeProducts = new StoreDBImpl().getStoreProducts();
    Product product = storeProducts.getProducts().get(0);
    Product product1 = storeProducts.getProducts().get(1);
    Product product2 = storeProducts.getProducts().get(2);

    CashierServiceImpl cashierService;
    Customer customer;
    Cashier cashier;
    CustomerServiceImpl customerService;

    @BeforeEach
    public void init() {
        customerService = new CustomerServiceImpl();
        cashierService = new CashierServiceImpl();
        customer = new Customer("Kendrick", "Female", 19);
        cashier = new Cashier();
    }

    @Test
    void sell() {
        customerService.buy(customer, "RiCe", 2022);
        cashier.setCustomer(customer);
        String result = cashierService.sell(cashier, storeProducts, customer.getProductName());
        String receipt = cashier.getReceipt();
        Assertions.assertEquals(
                result,
                "Thank you " + cashier.getCustomer().getName() + " for buying " + cashier.getCustomer().getProductName() + ". Here is your receipt " + receipt + "."
        );

        customerService.buy(customer, "Chicken", 2022);
        cashier.setCustomer(customer);
        String result2 = cashierService.sell(cashier, storeProducts, customer.getProductName());
        Assertions.assertEquals(result2, cashier.getCustomer().getName() + ", your product request is not available in our storeProducts.");


        customerService.buy(customer, "Beans", 2030);
        cashier.setCustomer(customer);
        String result3 = cashierService.sell(cashier, storeProducts, customer.getProductName());
        Assertions.assertEquals(
                result3,
                cashier.getCustomer().getName() + ", we only have " + cashier.getCustomer().getProductName() + " manufactured in " + cashierService.getManufactureYear() + "."
        );
    }

    @Test
    void getProduct() {
        for (Product aProduct : storeProducts.getProducts()) {
            if (aProduct.getName().equals("Rice")) {
                product2 = aProduct;
            }
        }

        Product product3 = null;
        for (Product aProduct : storeProducts.getProducts()) {
            if (aProduct.getName().equals("Cashew")) {
                product3 = aProduct;
            }
        }

        assertEquals(product, product2);
        assertNotEquals(product1, product2);
        assertNull(product3);
    }

    @Test
    void getProductToSell() {
        for (Product aProduct : storeProducts.getProducts()) {
            if (aProduct.getName().equals("Carrot")) {
                product = aProduct;
            }
        }
        assertEquals("Carrot", cashierService.getProductToSell(product, "Carrot"));
        assertNull(cashierService.getProductToSell(product, ""));
        assertNotEquals("Coke", cashierService.getProductToSell(product, "Carrot"));
        assertNull(cashierService.getProductToSell(product, null));
    }

    @Test
    void extractYear() {
        String year = "02-02-2020";
        String year1 = "02-02-2023";
        String year2 = "02-02-2024";

        assertEquals("2020", String.valueOf(cashierService.extractYear(year)));
        assertEquals("2023", String.valueOf(cashierService.extractYear(year1)));
        assertEquals("2024", String.valueOf(cashierService.extractYear(year2)));

        String year3 = null;
        for (Product aProduct: storeProducts.getProducts()) {
            if (aProduct.getName().equals("Carrot")) {
                year3 = aProduct.getManufactureDate();
            }
        }
        assertEquals("2030", String.valueOf(cashierService.extractYear(year3)));
    }

    @Test
    void getProductYear() {
        assertEquals("02-02-2022", cashierService.getProductYear(product));
        assertEquals("02-02-2023", cashierService.getProductYear(product1));
        assertEquals("02-02-2024", cashierService.getProductYear(product2));
    }
}