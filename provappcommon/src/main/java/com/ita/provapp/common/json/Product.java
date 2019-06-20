package com.ita.provapp.common.json;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer productId;
    private String name;
    private Integer price;
    private String description;

    public Product(Integer productId, String name, Integer price, String description) {
        this(name,price,description);
        this.productId = productId;
    }

    public Product(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
