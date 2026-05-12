package learning.projects.java_ecommerce.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import learning.projects.java_ecommerce.location.model.Street;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = CustomerAddress.DbSchema.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress {

    public static final class DbSchema {
        public static final String TABLE = "customer_address";
        public static final String COL_ID = "customer_address_id";
        public static final String COL_ADR_TYPE = "address_type";
        public static final String FK_CUSTOMER = "fk_customer";
        public static final String FK_STREET = "fk_street";
        public static final String FK_HOUSENUMBER = "fk_housenumber";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Customer.DbSchema.COL_ID, foreignKey = @ForeignKey(name = DbSchema.FK_CUSTOMER))
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Street.DbSchema.COL_ID, foreignKey = @ForeignKey(name = DbSchema.FK_STREET))
    private Street street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Housenumber.DbSchema.COL_ID, foreignKey = @ForeignKey(name = DbSchema.FK_HOUSENUMBER))
    private Housenumber housenumber;

    @Enumerated(EnumType.STRING) // Saves "RECHNUNG" instead of 0 in the DB
    @Column(name = DbSchema.COL_ADR_TYPE)
    private AddressType addressType;
}
