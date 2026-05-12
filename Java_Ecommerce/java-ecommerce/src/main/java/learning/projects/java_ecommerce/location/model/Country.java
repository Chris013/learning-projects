package learning.projects.java_ecommerce.location.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Country.DbSchema.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    public static class DbSchema{
        public static final String TABLE_NAME = "country";
        public static final String COL_ID = "country_id";
        public static final String COL_COUNTRY_CODE = "country_iso";
        public static final String COL_COUNTRY_NAME = "country_name";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.COL_ID, length = 3))
    private Long id;

    @Column(name = DbSchema.COL_COUNTRY_CODE, nullable = false)
    private String countryCode;
    
    @Column(name = DbSchema.COL_COUNTRY_NAME, nullable = false)
    private String countryName;

}
