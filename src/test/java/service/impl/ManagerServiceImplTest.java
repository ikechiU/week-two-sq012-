package service.impl;

import model.Cashier;
import model.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceImplTest {
    Staff cashier;
    Cashier cashier2;
    ManagerServiceImpl managerService;

    @BeforeEach
    void init() {
        cashier = new Cashier("Marcus", "Male", 41, 90); //Polymorphism
        cashier2 = new Cashier("Frederica", "Female", 39, 95);
        managerService = new ManagerServiceImpl();
    }

    @Test
    void hireCashier() {
        assertEquals("Frederica", cashier2.getName());
        assertEquals("Marcus", cashier.getName());
        assertNotEquals("Marcus", null);
        assertEquals(95, cashier2.getId());
        assertEquals(39, cashier2.getAge());

        managerService.hireCashier((Cashier) cashier);
        managerService.hireCashier(cashier2);

    }
}