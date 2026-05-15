package learning.projects.java_ecommerce.product.repo;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import learning.projects.java_ecommerce.product.model.Product;
import learning.projects.java_ecommerce.product.model.Product_;

public class ProductSpecification {

    public static Specification<Product> hasProductId(UUID productId) {
        return (root, query, cb) ->
                productId == null ? null : cb.equal(root.get(Product_.productId), productId);
    }

    public static Specification<Product> hasBarCode(String barcode) {
        return (root, query, cb) ->
                barcode == null ? null : cb.equal(root.get(Product_.barcode), barcode);
    }

    public static Specification<Product> nameStartsWith(String nameStartsWith) {
        return (root, query, cb) -> nameStartsWith == null ? null : cb.like(cb.lower(root.get(Product_.name)), nameStartsWith.toLowerCase() + "%");
    }

    public static Specification<Product> nameContains(String nameContains) {
        return (root, query, cb) -> nameContains == null ? null : cb.like(cb.lower(root.get(Product_.name)), "%" + nameContains.toLowerCase() + "%");
    }

    public static Specification<Product> descriptionStartsWith(String descriptionStartsWith) {
        return (root, query, cb) -> descriptionStartsWith == null ? null : cb.like(cb.lower(root.get(Product_.description)), descriptionStartsWith.toLowerCase() + "%");
    }

    public static Specification<Product> hasStockQuantity(int stockQuantity) {
        return (root, query, cb) -> stockQuantity == 0 ? null : cb.equal(root.get(Product_.stockQuantity), stockQuantity);
    }

    public static Specification<Product> descriptionContains(String descriptionContains) {
        return (root, query, cb) -> descriptionContains == null ? null : cb.like(cb.lower(root.get(Product_.description)), "%" + descriptionContains.toLowerCase() + "%");
    }

    public static Specification<Product> stockQuantityAbove(int stockQuantityMin) {
        return (root, query, cb) -> stockQuantityMin == 0 ? null : cb.greaterThanOrEqualTo(root.get(Product_.stockQuantity), stockQuantityMin);
    }

    public static Specification<Product> stockQuantityBelow(int stockQuantityMax) {
        return (root, query, cb) -> stockQuantityMax == 0 ? null : cb.lessThanOrEqualTo(root.get(Product_.stockQuantity), stockQuantityMax);
    }

    public static Specification<Product> price(BigDecimal price) {
        return (root, query, cb) -> price.compareTo(BigDecimal.ZERO) == 0 ? null : cb.equal(root.get(Product_.price), price);
    }

    public static Specification<Product> priceAbove(BigDecimal priceMin) {
        return (root, query, cb) -> priceMin.compareTo(BigDecimal.ZERO) == 0 ? null : cb.greaterThanOrEqualTo(root.get(Product_.price), priceMin);
    }

    public static Specification<Product> priceBelow(BigDecimal priceMax) {
         return (root, query, cb) -> priceMax.compareTo(BigDecimal.ZERO) == 0 ? null : cb.lessThanOrEqualTo(root.get(Product_.price), priceMax);
    }

    public static Specification<Product> hasProductName(String productName) {
        return (root, query, cb) -> productName == null ? null : cb.equal(root.get(Product_.name), productName);
    }

}
