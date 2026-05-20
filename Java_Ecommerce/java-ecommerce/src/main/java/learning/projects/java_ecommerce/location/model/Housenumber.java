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
        public static final String PK_COL_ID = "house_number_id";
        public static final String COL_HOUSE_NUMBER = "number";
        public static final String FK_COL_STREET = "fk_street";
    }

    public static final class Constraints {
        public static final String FK_HOUSE_NUMBER_TO_STREET = "fk_house_number_to_street";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.PK_COL_ID))
    private HouseNumberId id;

    @Column(name = DbSchema.COL_HOUSE_NUMBER, nullable = false)
    private String houseNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = DbSchema.FK_COL_STREET, nullable = false, foreignKey = @ForeignKey(name = Constraints.FK_HOUSE_NUMBER_TO_STREET))
    private Street street;

}
