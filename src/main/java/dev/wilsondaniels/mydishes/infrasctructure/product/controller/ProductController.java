package dev.wilsondaniels.mydishes.infrasctructure.product.controller;

import dev.wilsondaniels.mydishes.infrasctructure.product.ProductAPI;
import dev.wilsondaniels.mydishes.infrasctructure.product.dto.ProductDTO;

import java.util.List;

public class ProductController implements ProductAPI {

    @Override
    public List<ProductDTO> findAll() {
        return List.of();
    }
}
