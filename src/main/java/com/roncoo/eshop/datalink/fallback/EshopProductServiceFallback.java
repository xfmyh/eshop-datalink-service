package com.roncoo.eshop.datalink.fallback;

import com.roncoo.eshop.datalink.service.EshopProductService;
import org.springframework.stereotype.Component;

@Component
public class EshopProductServiceFallback implements EshopProductService {
    @Override
    public String findBrandById(Long id) {
        return null;
    }

    @Override
    public String findBrandByIds(String ids) {
        return null;
    }

    @Override
    public String findCategoryById(Long id) {
        return null;
    }

    @Override
    public String findProductById(Long id) {
        return null;
    }

    @Override
    public String findProductIntroById(Long id) {
        return null;
    }

    @Override
    public String findProductPropertyById(Long id) {
        return null;
    }

    @Override
    public String findProductPropertyProductById(Long productId) {
        return null;
    }

    @Override
    public String findProductSpecificationById(Long id) {
        return null;
    }

    @Override
    public String findProductSpecificationByProductId(Long productId) {
        return null;
    }
}
