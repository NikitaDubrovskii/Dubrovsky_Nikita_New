package dev.dubrovsky.model.payment.method;

import dev.dubrovsky.model.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "payment_method")
@Getter
@Setter
@NoArgsConstructor
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "method")
    private String method;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Order> orders;

    public PaymentMethod(String method) {
        this.method = method;
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
