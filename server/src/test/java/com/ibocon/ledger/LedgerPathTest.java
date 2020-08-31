package com.ibocon.ledger;

import com.ibocon.ledger.repository.account.LedgerPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LedgerPathTest {

    @Test
    public void ConvertStringPathToLedgerPath() throws Exception {
        var stringPath = "/1#/2/3/4#/5/6";
        var ledgerPath = new LedgerPath(stringPath);

        assertEquals(1, ledgerPath.getRootAccountCategoryId());

        var accountCategoryIds = ledgerPath.getAccountCategoryIds();
        assertEquals(3, accountCategoryIds.size());

        assertEquals(2, accountCategoryIds.get(0));
        assertEquals(3, accountCategoryIds.get(1));
        assertEquals(4, accountCategoryIds.get(2));

        var accountIds = ledgerPath.getAccountIds();
        assertEquals(2, accountIds.size());

        assertEquals(5, accountIds.get(0));
        assertEquals(6, accountIds.get(1));
    }

    @Test
    public void ConvertLedgerPathToStringPath() throws Exception {
        var rootCategoryId = 1L;

        var accountCategoryIds = new ArrayList<Long>();
        accountCategoryIds.add(2L);
        accountCategoryIds.add(3L);
        accountCategoryIds.add(4L);

        var accountIds = new ArrayList<Long>();
        accountIds.add(5L);
        accountIds.add(6L);

        var ledgerPath = LedgerPath.builder()
                .rootAccountCategoryId(rootCategoryId)
                .accountCategoryIds(accountCategoryIds)
                .accountIds(accountIds)
                .build();

        assertEquals("/1#/2/3/4#/5/6", ledgerPath.toString());
    }
}
