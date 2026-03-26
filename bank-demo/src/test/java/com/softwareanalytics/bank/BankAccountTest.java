package com.softwareanalytics.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for BankAccount (~70% coverage).
 * Intentionally leaves some paths uncovered for Software Analytics course exercise.
 */
@DisplayName("BankAccount Tests")
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Alice", 100.0);
    }

    @Nested
    @DisplayName("Constructor")
    class ConstructorTests {

        @Test
        @DisplayName("valid arguments: asserts owner, balance, and isFrozen")
        void constructorWithValidArguments() {
            BankAccount acc = new BankAccount("Bob", 250.50);

            assertEquals("Bob", acc.getOwner());
            assertEquals(250.50, acc.getBalance());
            assertFalse(acc.isFrozen());
        }

        // @Test
        // @DisplayName("initial balance zero is allowed")
        // void constructorWithZeroBalance() {
        //     BankAccount acc = new BankAccount("Charlie", 0.0);
        
        //     assertEquals(0.0, acc.getBalance());
        // }

        // @Test
        // @DisplayName("null owner throws exception")
        // void constructorNullOwner() {
        //     IllegalArgumentException ex = assertThrows(
        //             IllegalArgumentException.class,
        //             () -> new BankAccount(null, 100.0)
        //     );
        //     assertEquals("owner cannot be null", ex.getMessage());
        // }

        // @Test
        // @DisplayName("blank owner throws exception")
        // void constructorBlankOwner() {
        //     IllegalArgumentException ex = assertThrows(
        //             IllegalArgumentException.class,
        //             () -> new BankAccount("   ", 100.0)
        //     );
        //     assertEquals("owner cannot be blank", ex.getMessage());
        // }
        // @Test
        // @DisplayName("negative initial balance throws exception")
        // void constructorNegativeBalance() {
        //     IllegalArgumentException ex = assertThrows(
        //             IllegalArgumentException.class,
        //             () -> new BankAccount("Alice", -1.0)
        //     );
        //     assertEquals("initial balance cannot be negative", ex.getMessage());
        // }

    }

    @Nested
    @DisplayName("deposit")
    class DepositTests {

        @Test
        @DisplayName("happy path: exact balance after deposit")
        void depositHappyPath() {
            account.deposit(50.0);

            assertEquals(150.0, account.getBalance());
        }

        @Test
        @DisplayName("multiple times: exact cumulative balance")
        void depositMultipleTimes() {
            account.deposit(25.0);
            account.deposit(75.0);
            account.deposit(10.0);

            assertEquals(210.0, account.getBalance());
        }

        // @Test
        // @DisplayName("zero amount: throws IllegalArgumentException (kills mutant)")
        // void depositZeroAmount() {
        //     IllegalArgumentException ex = assertThrows(
        //             IllegalArgumentException.class,
        //             () -> account.deposit(0.0)
        //     );
        //     assertEquals("amount must be positive", ex.getMessage());
        // }

        // @Test
        // @DisplayName("negative amount: throws IllegalArgumentException")
        // void depositNegativeAmount() {
        //     IllegalArgumentException ex = assertThrows(
        //             IllegalArgumentException.class,
        //             () -> account.deposit(-5.0)
        //     );
        //     assertEquals("amount must be positive", ex.getMessage());
        // }

        // @Test
        // @DisplayName("deposit on frozen account throws IllegalStateException")
        // void depositWhenFrozen() {
        //     account.freeze();
        
        //     IllegalStateException ex = assertThrows(
        //             IllegalStateException.class,
        //             () -> account.deposit(10.0)
        //     );
        //     assertEquals("account is frozen", ex.getMessage());
        // }


    }

    @Nested
    @DisplayName("withdraw")
    class WithdrawTests {

        @Test
        @DisplayName("happy path: exact balance after withdrawal")
        void withdrawHappyPath() {
            account.withdraw(30.0);

            assertEquals(70.0, account.getBalance());
        }

        @Test
        @DisplayName("exact balance to zero")
        void withdrawExactBalanceToZero() {
            account.withdraw(100.0);

            assertEquals(0.0, account.getBalance());
        }

        @Test
        @DisplayName("insufficient funds: throws IllegalArgumentException")
        void withdrawInsufficientFunds() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> account.withdraw(150.0)
            );
            assertEquals("insufficient funds", ex.getMessage());
        }

        @Test
        @DisplayName("negative amount: throws IllegalArgumentException")
        void withdrawNegativeAmount() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> account.withdraw(-10.0)
            );
            assertEquals("amount must be positive", ex.getMessage());
        }

        // @Test
        // @DisplayName("zero amount: throws IllegalArgumentException (kills mutant)")
        // void withdrawZeroAmount() {
        //     IllegalArgumentException ex = assertThrows(
        //             IllegalArgumentException.class,
        //             () -> account.withdraw(0.0)
        //     );
        //     assertEquals("amount must be positive", ex.getMessage());
        // }

        // @Test
        // @DisplayName("withdraw on frozen account throws IllegalStateException")
        // void withdrawWhenFrozen() {
        //     account.freeze();
        
        //     IllegalStateException ex = assertThrows(
        //             IllegalStateException.class,
        //             () -> account.withdraw(10.0)
        //     );
        //     assertEquals("account is frozen", ex.getMessage());
        // }
    }
    // @Nested
    // @DisplayName("freeze / unfreeze")
    // class FreezeTests {

    //     @Test
    //     @DisplayName("freeze sets account to frozen")
    //     void freezeSetsFrozen() {
    //         account.freeze();
    //         assertTrue(account.isFrozen());
    //     }

    //     @Test
    //     @DisplayName("unfreeze restores account")
    //     void unfreezeRestores() {
    //         account.freeze();
    //         account.unfreeze();

    //         assertFalse(account.isFrozen());
    //     }

    //     @Test
    //     @DisplayName("operations work again after unfreeze")
    //     void operationsAfterUnfreeze() {
    //         account.freeze();
    //         account.unfreeze();

    //         account.deposit(50.0);
    //         assertEquals(150.0, account.getBalance());
    //     }
    // }

    // @Nested
    // @DisplayName("transfer")
    // class TransferTests {
    
    //     @Test
    //     @DisplayName("happy path transfer updates both accounts")
    //     void transferHappyPath() {
    //         BankAccount target = new BankAccount("Bob", 50.0);
        
    //         account.transfer(target, 30.0);
        
    //         assertEquals(70.0, account.getBalance());
    //         assertEquals(80.0, target.getBalance());
    //     }
    
    //     @Test
    //     @DisplayName("null target throws exception")
    //     void transferNullTarget() {
    //         IllegalArgumentException ex = assertThrows(
    //                 IllegalArgumentException.class,
    //                 () -> account.transfer(null, 10.0)
    //         );
    //         assertEquals("target cannot be null", ex.getMessage());
    //     }
    
    //     @Test
    //     @DisplayName("transfer to self throws exception")
    //     void transferToSelf() {
    //         IllegalArgumentException ex = assertThrows(
    //                 IllegalArgumentException.class,
    //                 () -> account.transfer(account, 10.0)
    //         );
    //         assertEquals("cannot transfer to self", ex.getMessage());
    //     }
    
    //     @Test
    //     @DisplayName("transfer respects withdraw validation")
    //     void transferInsufficientFunds() {
    //         BankAccount target = new BankAccount("Bob", 0.0);
        
    //         IllegalArgumentException ex = assertThrows(
    //                 IllegalArgumentException.class,
    //                 () -> account.transfer(target, 200.0)
    //         );
    //         assertEquals("insufficient funds", ex.getMessage());
    //     }
    // }

    // @Nested
    // @DisplayName("applyMonthlyInterest")
    // class InterestTests {
    
    //     @Test
    //     @DisplayName("applies interest correctly for positive balance")
    //     void applyInterestPositiveBalance() {
    //         account.applyMonthlyInterest(12.0); // 12% annually → 1% monthly
        
    //         assertEquals(101.0, account.getBalance(), 0.0001);
    //     }
    
    //     @Test
    //     @DisplayName("zero balance remains unchanged")
    //     void applyInterestZeroBalance() {
    //         BankAccount acc = new BankAccount("Bob", 0.0);
        
    //         acc.applyMonthlyInterest(10.0);
        
    //         assertEquals(0.0, acc.getBalance());
    //     }
    
    //     @Test
    //     @DisplayName("negative rate throws exception")
    //     void applyInterestNegativeRate() {
    //         IllegalArgumentException ex = assertThrows(
    //                 IllegalArgumentException.class,
    //                 () -> account.applyMonthlyInterest(-5.0)
    //         );
    //         assertEquals("rate cannot be negative", ex.getMessage());
    //     }
    // }



}
