package DAO;

import model_entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionDAOTest {

    BankTransaction bankTransaction;
    Customer customer;
    Account account ;
    Account account2;
    BankTransactionDAO bankTransactionDAO = new BankTransactionDAO();
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
    public void saveDepositTransaction(){

        bankTransactionDAO.saveWithdrawTransaction(bankTransaction);
    }

    @Test
    public void saveWithdrawTransactionTest(){
        bankTransaction.withdraw(account,200);

    }

    @Test
    public void saveTransferFundTransaction(){
        assertEquals(8100,bankTransaction.transferFund(account2,100));
    }

}