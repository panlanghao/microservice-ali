package com.plh.microservice.ali.product.controler;

import com.plh.microservice.ali.common.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/11 17:06
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("getServiceList")
    public List<ServiceInstance> getServiceList() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("product-center");
        return serviceInstanceList;
    }

    @RequestMapping("getProductById/{productNo}")
    public ProductVO getProductById(@PathVariable("productNo") String productNo) throws InterruptedException {
        System.out.println("!!!");
        Thread.sleep(3000);
        return getProductVO(productNo);
    }

    private ProductVO getProductVO(String productNo) {
        ProductVO productVO = new ProductVO();
        productVO.setProductName("测试");
        productVO.setProductNo(productNo);
        productVO.setProductPrice(new BigDecimal(10));
        productVO.setProductStore("1000");
        return productVO;
    }

    @RequestMapping("getToken4Header")
    public String getToken4Header(@RequestHeader("token") String token) {
        log.info("token:{}",token);
        return token;
    }
}
