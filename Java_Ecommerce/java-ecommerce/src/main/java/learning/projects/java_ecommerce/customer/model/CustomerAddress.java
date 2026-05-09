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
import learning.projects.java_ecommerce.location.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = CustomerAddress.Mapping.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress {

    public static final class Mapping {
        public static final String TABLE = "customer_address";
        public static final String COL_ID = "customer_address_id";
        public static final String COL_ADR_TYPE = "address_type";
        public static final String FK_CUSTOMER = "fk_customer";
        public static final String FK_ADRESS = "fk_adress";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Customer.Mapping.COL_ID, foreignKey = @ForeignKey(name = Mapping.FK_CUSTOMER))
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Address.Mapping.COL_ID, foreignKey = @ForeignKey(name = Mapping.FK_ADRESS))
    private Address address;

    @Enumerated(EnumType.STRING) // Saves "RECHNUNG" instead of 0 in the DB
    @Column(name = Mapping.COL_ADR_TYPE)
    private AddressType addressType;
}
