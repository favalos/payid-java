package io.jl.payid.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author @favalos
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InfrastructureConfiguration.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findAccountById() {

        Mono<Account> monoAccount = this.accountRepository.findById(UUID.fromString("232370e9-045e-4269-96ec-5a79091d65ff"));
        System.out.println(monoAccount.block());

    }

    @Test
    public void getAccountByPayid() {

        Mono<Account> monoAccount = this.accountRepository.getAccountByPayid("alice$xpring.money");
        System.out.println(monoAccount.block());

    }

}