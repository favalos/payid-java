package io.jl.payid.entity;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author @favalos
 */
@Repository
public interface AccountRepository extends ReactiveCrudRepository<Account, UUID> {

    @Query("select * from account " +
            "where pay_id = :payid")
    Mono<Account> getAccountByPayid(String payid);

}
