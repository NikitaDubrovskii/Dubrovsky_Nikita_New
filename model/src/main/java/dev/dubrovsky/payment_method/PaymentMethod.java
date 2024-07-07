package dev.dubrovsky.payment_method;

import java.util.Objects;

public class PaymentMethod {

    private final Integer id;
    private final String method;

    public PaymentMethod(Integer id, String method) {
        this.id = id;
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(id, that.id) && Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, method);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", method='" + method + '\'' +
                '}';
    }

}
