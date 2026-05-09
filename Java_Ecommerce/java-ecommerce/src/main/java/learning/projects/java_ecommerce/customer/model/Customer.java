package learning.projects.java_ecommerce.customer.model;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Customer.Mapping.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    public static final class Mapping {
        public static final String TABLE = "customer";
        public static final String COL_ID = "customer_id";
        public static final String COL_FIRSTNAME = "firstname";
        public static final String COL_LASTNAME = "lastname";
        public static final String COL_EMAIL = "email_address";
        public static final String COL_USERNAME = "username";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = Mapping.COL_ID))
    private CustomerId customerId;

    @Column(name = Mapping.COL_USERNAME, unique = true, nullable = false)
    private String userName;

    @Column(name = Mapping.COL_FIRSTNAME, nullable = false)
    private String firstName;

    @Column(name = Mapping.COL_LASTNAME, nullable = false)
    private String lastName;

    @Column(name = Mapping.COL_EMAIL, unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer") //refers to the attribute name in CustomerAddress
    private List<CustomerAddress> customerAdresses;

}
