package learning.projects.java_ecommerce.product.exception;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;
import learning.projects.java_ecommerce.product.dto.ProductDto;

public class ProductDataValidationException extends DomainException{

    public ProductDataValidationException(ProductDto productDto) {
        super(
            "PRODUCT_DATA_NOT_PROCESSABLE",
            "Could not save product due to data validation errors for SKU/Name: " + productDto.barcode() + "/" + productDto.name(),
            HttpStatus.UNPROCESSABLE_CONTENT
        );
    }

}
