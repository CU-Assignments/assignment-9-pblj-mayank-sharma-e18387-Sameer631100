package com.example.banking;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTransaction {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BankService service = context.getBean("bankServiceImpl", BankService.class);

        try {
            System.out.println("💸 Starting successful transfer...");
            service.transferMoney(1, 2, 100);
            System.out.println("✅ Transfer successful!");
        } catch (Exception e) {
            System.out.println("❌ Transaction failed: " + e.getMessage());
        }

        try {
            System.out.println("💥 Starting failing transfer (Insufficient funds)...");
            service.transferMoney(1, 2, 10000);
        } catch (Exception e) {
            System.out.println("❌ Transaction failed (rolled back): " + e.getMessage());
        }
    }
}
