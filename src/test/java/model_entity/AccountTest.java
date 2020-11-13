package model_entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    public void accountCheckTest(){
        Account account = new SavingAccount();
        assertTrue(account instanceof CheckingAccount);
    }
    @Test
    public void accountSavingTest(){
        Account account = new SavingAccount();
        assertTrue(account instanceof CheckingAccount);
    }
}