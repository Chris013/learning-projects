package learning.projects.java_ecommerce.location.model;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import learning.projects.java_ecommerce.customer.model.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Address.DbSchema.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Street {

    public static final class DbSchema {
        public static final String TABLE = "street";
        public static final String COL_ID = "street_id";
        public static final String COL_STREET = "street_name";
        public static final String COL_HOUSE_NUMBER = "house_number";
        public static final String FK_CITY = "city_id";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.COL_ID))
    private StreetId id;

    @Column(name = DbSchema.COL_STREET, nullable = false)
    private String streetName;

    @Column(name = DbSchema.COL_HOUSE_NUMBER, nullable = false)
    private String houseNumber;

     @OneToMany(mappedBy = "address") //refers to the attribute name in CustomerAddress
    private List<CustomerAddress> customerAdresses;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = DbSchema.FK_CITY, nullable = false, foreignKey = @ForeignKey(name = City.DbSchema.COL_ID))
    private City city;
}
