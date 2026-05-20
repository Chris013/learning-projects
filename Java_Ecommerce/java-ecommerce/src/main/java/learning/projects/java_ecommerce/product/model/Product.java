package learning.projects.java_ecommerce.product.model;

import java.math.BigDecimal;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import learning.projects.java_ecommerce.location.model.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = Product.DbSchema.TABLE,
        uniqueConstraints = {
        @UniqueConstraint(name = Product.Constraints.UQ_BARCODE, columnNames = Product.DbSchema.COL_BARCODE)
    },
    indexes = {}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    public static final class DbSchema{
        public static final String TABLE = "product";
        public static final String PK_COL_ID = "product_id";
        public static final String COL_BARCODE = "barcode";
        public static final String COL_NAME = "name";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_STOCK_QUANTITY = "stock_quantity";
        public static final String COL_PRICE = "price";
    }

    public static final class Constraints {
        public static final String UQ_BARCODE = "uq_product_barcode";
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = DbSchema.PK_COL_ID))
    private ProductId productId;

    @Column(name = DbSchema.COL_BARCODE, nullable = false)
    private String barcode;

    @Column(name = DbSchema.COL_NAME, nullable = false)
    private String name;

    @Column(name = DbSchema.COL_DESCRIPTION, nullable = true)
    private String description;

    @Column(name = DbSchema.COL_STOCK_QUANTITY, nullable = false)
    private int stockQuantity;

    @Column(name = DbSchema.COL_PRICE, nullable = false)
    private BigDecimal price;

}
