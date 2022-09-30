package service.impl;

import model.Product;
import model.Store;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreDBImplTest {

    @Test
    void getStoreProducts() {
        TestProductImplDB productImplDB = new TestProductImplDB();

        StoreDBImpl storeDB = new StoreDBImpl();
        Store store = storeDB.getStoreProducts(productImplDB.getProductDB());

        List<Product> products = store.getProducts();
        assertEquals(products.size(), 10);
    }
}