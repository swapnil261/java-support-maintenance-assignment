package com.assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task1 {

    public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

        // FIX: Return an empty list when the input list is null.
        if (accounts == null) {
            return new ArrayList<>();
        }

        // FIX: Initialize the result list to avoid NullPointerException.
        List<LoanAccount> result = new ArrayList<>();

        for (LoanAccount account : accounts) {

            // FIX: Skip null account objects to avoid NullPointerException.
            if (account == null) {
                continue;
            }

            // FIX: Check dueDate for null before calling before().
            if (account.getDueDate() != null
                    && account.getDueDate().before(new Date())) {

                // FIX: Include only overdue accounts with a positive outstanding balance.
                if (account.getOutstandingBalance() > 0) {
                    result.add(account);
                }
            }
        }

        return result;
    }
}