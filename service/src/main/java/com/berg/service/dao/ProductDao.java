package com.berg.service.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();



    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
