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
@Table(name = Housenumber.DbSchema.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Housenumber {

    public static final class DbSchema {
        public static final String TABLE = "house_number";
        public static final String COL_ID = "house_number_id";
        public static final String COL_HOUSE_NUMBER = "number";
    }


    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.COL_ID))
    private HousenumberId id;

    @Column(name = DbSchema.COL_HOUSE_NUMBER, nullable = false)
    private String houseNumber;

}
