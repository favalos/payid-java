package io.jl.payid.entity;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author @favalos
 */
@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {

   @Query("select * from address " +
           "where account_id = :accountId")
   Flux<Address> findAddressFor(UUID accountId);

   @Query("select * from address " +
           "where account_id = :accountId " +
           "and payment_network = :paymentNetwork")
   Flux<Address> findAddressFor(UUID accountId, String paymentNetwork);

   @Query("select * from address " +
           "where account_id = :accountId " +
           "and payment_network = :paymentNetwork " +
           "and environment = :environment")
   Flux<Address> findAddressFor(UUID account, String paymentNetwork, String environment);

}
