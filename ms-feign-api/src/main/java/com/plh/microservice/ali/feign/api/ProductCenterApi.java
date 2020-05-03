package com.plh.microservice.ali.feign.api;

import com.plh.microservice.ali.common.vo.ProductVO;
import com.plh.microservice.ali.feign.api.config.ProductCenterFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/25 11:19
 */
@FeignClient(name = "product-center",configuration = ProductCenterFeignConfig.class)
//@FeignClient(name = "product-center")
public interface ProductCenterApi {

    /**
     * 声明式接口,远程调用http://product-center/getProductById/{productNo}
     * @param productNo
     * @return
     */
    @RequestMapping("getProductById/{productNo}")
    ProductVO getProductById(@PathVariable("productNo") String productNo);

    @RequestMapping("getToken4Header")
    String getToken4Header(@RequestHeader("token") String token);
}
