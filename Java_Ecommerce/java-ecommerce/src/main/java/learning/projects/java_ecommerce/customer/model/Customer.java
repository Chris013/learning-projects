package learning.projects.java_ecommerce.customer.model;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = Customer.DbSchema.TABLE,
    uniqueConstraints = {
        @UniqueConstraint(name = Customer.Constraints.UQ_USERNAME, columnNames = Customer.DbSchema.COL_USERNAME),
        @UniqueConstraint(name = Customer.Constraints.UQ_EMAIL, columnNames = Customer.DbSchema.COL_EMAIL)
    },
    indexes = {}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    public static final class DbSchema {
        public static final String TABLE = "customer";
        public static final String PK_COL_ID = "customer_id";
        public static final String COL_FIRSTNAME = "firstname";
        public static final String COL_LASTNAME = "lastname";
        public static final String COL_EMAIL = "email_address";
        public static final String COL_USERNAME = "username";
    }

    public static final class Constraints {
        public static final String UQ_USERNAME = "uq_customer_username";
        public static final String UQ_EMAIL = "uq_customer_email_address";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.PK_COL_ID))
    private CustomerId customerId;

    @Column(name = DbSchema.COL_USERNAME, unique = true, nullable = false)
    private String userName;

    @Column(name = DbSchema.COL_FIRSTNAME, nullable = false)
    private String firstName;

    @Column(name = DbSchema.COL_LASTNAME, nullable = false)
    private String lastName;

    @Column(name = DbSchema.COL_EMAIL, unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer") //refers to the attribute name in CustomerAddress
    private List<CustomerAddress> customerAdresses;

}
