package learning.projects.java_ecommerce.location.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = HouseNumber.DbSchema.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseNumber {

    public static final class DbSchema {
        public static final String TABLE = "house_number";
        public static final String COL_ID = "house_number_id";
        public static final String COL_HOUSE_NUMBER = "number";
        public static final String FK_STREET = "fk_street";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.COL_ID))
    private HouseNumberId id;

    @Column(name = DbSchema.COL_HOUSE_NUMBER, nullable = false)
    private String houseNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = DbSchema.FK_STREET, nullable = false, foreignKey = @ForeignKey(name = Street.DbSchema.COL_ID))
    private Street street;

}
