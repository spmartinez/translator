/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.AccountAux;
import com.sales.market.repository.AccountAuxWithDelayRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountAuxService {
    @Value("${AccountAuxService.secret}")
    private String secret;

    private final AccountAuxWithDelayRepositoryImpl accountAuxWithDelayRepository;

    public AccountAuxService(AccountAuxWithDelayRepositoryImpl accountAuxWithDelayRepository) {
        this.accountAuxWithDelayRepository = accountAuxWithDelayRepository;
    }


    public AccountAux debit(Long accountAuxId, BigDecimal debitAmount) {
        AccountAux accountAux = accountAuxWithDelayRepository.getByIdWithDelay(accountAuxId, 10L);//10 sec delay
        if (accountAux.getBalance().compareTo(debitAmount) >= 0) {
            accountAux.setTotalDebit(accountAux.getTotalDebit().add(debitAmount));
            accountAux.setBalance(accountAux.getBalance().subtract(debitAmount));
        }
        return accountAuxWithDelayRepository.save(accountAux);
    }

    public AccountAux save(AccountAux accountAux) {
        return accountAuxWithDelayRepository.save(accountAux);
    }


    public AccountAux getById(Long id) {
        return accountAuxWithDelayRepository.getById(id);
    }
}
