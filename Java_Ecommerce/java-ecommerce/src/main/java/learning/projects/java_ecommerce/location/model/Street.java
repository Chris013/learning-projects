package learning.projects.java_ecommerce.location.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Street.DbSchema.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Street {

    public static final class DbSchema {
        public static final String TABLE = "street";
        public static final String PK_COL_ID = "street_id";
        public static final String COL_STREET = "street_name";
        public static final String FK_COL_CITY = "fk_city";
    }

    public static final class Constraints {
        public static final String FK_STREET_TO_CITY = "fk_street_to_city";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.PK_COL_ID))
    private StreetId id;

    @Column(name = DbSchema.COL_STREET, nullable = false)
    private String streetName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = DbSchema.FK_COL_CITY, nullable = false, foreignKey = @ForeignKey(name = Constraints.FK_STREET_TO_CITY))
    private City city;
}
