package dev.dubrovsky.model.payment.method;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "method")
    private String method;

    public PaymentMethod() {
    }

    public PaymentMethod(String method) {
        this.method = method;
    }

    public void setId(Integer id) {
        this.id = id;
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
