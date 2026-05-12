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
@Table(name = City.DbSchema.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    public static final class DbSchema {
        public static final String TABLE = "city";
        public static final String COL_ID = "city_id";
        public static final String COL_NAME = "city_name";
        public static final String COL_POSTAL = "zip_code";
        public static final String FK_COUNTRY = "country";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.COL_ID))
    private CityId id;

    @Column(name = DbSchema.COL_NAME, nullable = false)
    private String name;

    @Column(name = DbSchema.COL_POSTAL, nullable = false)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = DbSchema.FK_COUNTRY, nullable = false, foreignKey = @ForeignKey(name = Country.DbSchema.COL_ID))
    private Country country;
}
