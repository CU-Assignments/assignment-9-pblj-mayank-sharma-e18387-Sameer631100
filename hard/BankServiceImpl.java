package com.example.banking;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Session session = sessionFactory.getCurrentSession();

        Account from = session.get(Account.class, fromAccountId);
        Account to = session.get(Account.class, toAccountId);

        if (from == null || to == null) {
            throw new RuntimeException("Account not found.");
        }

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance.");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        session.save(new Transaction(fromAccountId, toAccountId, amount));
        session.update(from);
        session.update(to);
    }
}
