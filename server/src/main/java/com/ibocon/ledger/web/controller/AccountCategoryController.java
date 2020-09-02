package com.ibocon.ledger.web.controller;

import com.ibocon.ledger.repository.TranslatedString;
import com.ibocon.ledger.repository.TranslatedStringRepository;
import com.ibocon.ledger.repository.account.AccountCategoryRepository;
import com.ibocon.ledger.repository.account.LedgerPath;
import com.ibocon.ledger.repository.exception.LedgerPathException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/account-category", produces = "application/hal+json")
public class AccountCategoryController {

    private final TranslatedStringRepository translatedStringRepository;
    private final AccountCategoryRepository accountCategoryRepository;

    @GetMapping
    public ResponseEntity<?> GetAccountCategoriesUnderPath(@RequestParam("path") String path) {
        if(!LedgerPath.isValidPath(path)) return new ResponseEntity<>(path, HttpStatus.BAD_REQUEST);

        try {
            var accountCategories = accountCategoryRepository.findByPathContains(new LedgerPath(path));

            return new ResponseEntity<>(accountCategories, HttpStatus.OK);
        } catch(LedgerPathException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
