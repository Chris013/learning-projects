package learning.projects.java_ecommerce.location.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = Country.DbSchema.TABLE_NAME,
    uniqueConstraints = {
        @UniqueConstraint(name = Country.Constraints.UQ_COUNTRY_CODE, columnNames = Country.DbSchema.COL_COUNTRY_CODE)
    },
    indexes = {}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    public static class DbSchema{
        public static final String TABLE_NAME = "country";
        public static final String PK_COL_ID = "country_id";
        public static final String COL_COUNTRY_CODE = "country_iso";
        public static final String COL_COUNTRY_NAME = "country_name";
    }

    public static final class Constraints {
        public static final String UQ_COUNTRY_CODE = "uq_country_country_iso";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbSchema.PK_COL_ID)
    private Long id;

    @Column(name = DbSchema.COL_COUNTRY_CODE, nullable = false, length = 3)
    private String countryCode;
    
    @Column(name = DbSchema.COL_COUNTRY_NAME, nullable = false)
    private String countryName;

}
