package learning.projects.java_ecommerce.product.model;

import java.math.BigDecimal;

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
@Table(name = Product.DbSchema.TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    public static final class DbSchema{
        public static final String TABLE = "product";
        public static final String COL_ID = "product_id";
        public static final String COL_BARCODE = "barcode";
        public static final String COL_NAME = "name";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_PRICE = "price";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.COL_ID))
    private ProductId productId;

    @Column(name = DbSchema.COL_BARCODE, nullable = false)
    private String barcode;

    @Column(name = DbSchema.COL_NAME, nullable = false)
    private String name;

    @Column(name = DbSchema.COL_DESCRIPTION, nullable = true)
    private String description;

    @Column(name = DbSchema.COL_PRICE, nullable = false)
    private BigDecimal price;

}
