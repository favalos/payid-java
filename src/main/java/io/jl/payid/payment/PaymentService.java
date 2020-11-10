package io.jl.payid.payment;

/**
 * @author @favalos
 */
public interface PaymentService<T> {

    T getPaymentInformation(String payid);
}
