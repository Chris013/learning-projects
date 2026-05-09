package learning.projects.java_ecommerce.location.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = City.Mapping.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    public static final class Mapping {
        public static final String TABLE = "city";
        public static final String COL_ID = "city_id";
        public static final String COL_NAME = "city_name";
        public static final String COL_POSTAL = "zip_code";
        public static final String COL_COUNTRY = "country_iso";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = Mapping.COL_ID))
    private CityId id;

    @Column(name = Mapping.COL_NAME, nullable = false)
    private String name;

    @Column(name = Mapping.COL_POSTAL, nullable = false)
    private String zipCode;

    @Column(name = Mapping.COL_COUNTRY, nullable = false, length = 3)
    private String countryCode;
}
