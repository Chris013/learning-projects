package learning.projects.java_ecommerce.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learning.projects.java_ecommerce.product.model.Product;
import learning.projects.java_ecommerce.product.model.ProductId;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId>{

}
