package com.roncoo.eshop.datalink.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.datalink.service.EshopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
public class DataLinkController {


    @Autowired
    private EshopProductService eshopProductService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/product")
    @ResponseBody
    public String product(Long productId){
        //先读取本读的ehcache

        //读redis主集群
        Jedis jedis = jedisPool.getResource();
        String dimProductJSON = jedis.get("dim_product:" + productId+":");

        if(dimProductJSON == null || "".equals(dimProductJSON)){

            String productDataJSON = eshopProductService.findProductById(productId);


            if(productDataJSON != null && !"".equals(productDataJSON)){
                JSONObject productDataJSONObject = JSONObject.parseObject(productDataJSON);

                String productPropertyDataJSON = eshopProductService.findProductPropertyProductById(productId);
                if(productPropertyDataJSON != null && !"".equals(productPropertyDataJSON)){
                    productDataJSONObject.put("product_property",productPropertyDataJSON);
                }

                String productSpecificationDataJSON = eshopProductService.findProductSpecificationByProductId(productId);
                if(productSpecificationDataJSON != null && !"".equals(productSpecificationDataJSON)){
                    productDataJSONObject.put("product_specification",productSpecificationDataJSON);
                }


                jedis.set("dim_product:" + productId +":",productDataJSONObject.toJSONString());

                return productDataJSONObject.toJSONString();
            }
        }
        return dimProductJSON;

    }
}
