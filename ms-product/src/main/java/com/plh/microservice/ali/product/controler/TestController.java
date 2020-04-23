package com.plh.microservice.ali.product.controler;

import com.plh.microservice.ali.common.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/11 17:06
 */
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
    public ProductVO getProductById(@PathVariable("productNo") String productNo) {
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
}
