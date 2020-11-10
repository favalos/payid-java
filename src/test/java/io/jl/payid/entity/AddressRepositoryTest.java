package io.jl.payid.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author @favalos
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InfrastructureConfiguration.class)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void findAddressForAccountId() {

        Flux<Address> addressFlux = this.addressRepository.findAddressFor(
                UUID.fromString("232370e9-045e-4269-96ec-5a79091d65ff"));
        System.out.println(addressFlux.collectList().block());
    }

    @Test
    public void findAddressForAccountIdAndPaymentNetwork() {

        Flux<Address> addressFlux = this.addressRepository.findAddressFor(
                UUID.fromString("232370e9-045e-4269-96ec-5a79091d65ff"),
                "XRPL");
        System.out.println(addressFlux.collectList().block());
    }

    @Test
    public void findAddressForAccountIdAndPaymentNetworkAndEnvironment() {

        Flux<Address> addressFlux = this.addressRepository.findAddressFor(
                UUID.fromString("232370e9-045e-4269-96ec-5a79091d65ff"),
                "XRPL",
                "MAINNET");
        System.out.println(addressFlux.collectList().block());
    }

}