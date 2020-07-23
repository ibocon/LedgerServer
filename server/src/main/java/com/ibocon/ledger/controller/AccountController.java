package com.ibocon.ledger.controller;

import java.util.List;
import java.util.Optional;

import com.ibocon.ledger.annotation.CurrentUser;
import com.ibocon.ledger.domain.account.UserDefinedAccount;
import com.ibocon.ledger.domain.account.UserDefinedAccountRepository;
import com.ibocon.ledger.domain.user.LedgerUser;
import com.ibocon.ledger.model.payload.AccountRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path="/account", produces="application/json")
public class AccountController {
    @Autowired
    private UserDefinedAccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<?> read(@CurrentUser LedgerUser user) {
        List<UserDefinedAccount> accounts = accountRepository.findByBelongTo(user);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@CurrentUser LedgerUser user, @RequestBody AccountRequest accountRequest ) {
        UserDefinedAccount account = new UserDefinedAccount(accountRequest.getOfficialAccount(), accountRequest.getAccountName());
        account = accountRepository.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@CurrentUser LedgerUser user, @RequestBody AccountRequest accountRequest) {
        List<UserDefinedAccount> optionalAccount = accountRepository.findByBelongToAndAccountName(user, accountRequest.getAccountName());
        if(!optionalAccount.isEmpty()){
            UserDefinedAccount account = optionalAccount.get(0);
            // TODO 계정 정보를 업데이트하자.
            account = accountRepository.save(account);

            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@CurrentUser LedgerUser user, @RequestBody AccountRequest accountRequest) {
        Optional<UserDefinedAccount> optionalAccount = accountRepository.findById(accountRequest.getId());
        if(optionalAccount.isPresent()){
            UserDefinedAccount account = optionalAccount.get();
            accountRepository.deleteById(account.getId());
            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}