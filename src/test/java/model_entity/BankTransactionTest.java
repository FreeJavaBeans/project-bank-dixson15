package model_entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionTest {

    BankTransaction bankTransaction;
    Customer customer;
    Account account ;
    Account account2;
    @BeforeEach
    void setUp() {
        customer = new Customer();
        account = new CheckingAccount(1,customer,63757,600);
        account2 = new SavingAccount(1,customer,6300757,8000);
        bankTransaction = new BankTransaction(account);
    }

    @AfterEach
    void tearDown() {
        customer = null;
        account = null;
        account2 = null;
        bankTransaction = null;
    }

    @Test
    public void withdrawTest(){

        //asserEquals(450, BankTransaction.withdraw(account,150));
        assertEquals(5000, bankTransaction.withdraw(account2,3000));
    }

    @Test
    public void depositTest(){

        assertEquals(bankTransaction.deposit(account,800), true);

    }
    @Test
    void transferFundTest(){

        assertTrue(bankTransaction.transferFund(account2,400) == 8400);

    }
}