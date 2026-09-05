package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @Schema(description = "Product ID", example = "101")
    private Long productId;

    @Schema(description = "Name of the product", example = "iPhone 16")
    private String productName;

    @Schema(description = "Product image URL", example = "iphone16.jpg")
    private String image;

    @Schema(description = "Description of the product", example = "Latest Apple iPhone with advanced features")
    private String description;

    @Schema(description = "Available quantity", example = "50")
    private Integer quantity;

    @Schema(description = "Product price", example = "79999.99")
    private double price;

    @Schema(description = "Discount percentage", example = "10.0")
    private double discount;

    @Schema(description = "Price after applying discount", example = "71999.99")
    private Double specialPrice;
}