package com.plh.microservice.ali.order.controller;

import com.plh.microservice.ali.common.vo.OrderVO;
import com.plh.microservice.ali.common.vo.ProductVO;
import com.plh.microservice.ali.order.model.dataobject.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/11 18:36
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${isNewBiz}")
    private Boolean isNewBiz;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private OrderInfo getOrderInfo(String orderNo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setUserName("dd");
        orderInfo.setProductNo("1");
        orderInfo.setCreateDt(new Date());
        orderInfo.setProductCount(100);
        return orderInfo;
    }

    @RequestMapping("getOrderById/{orderNo}")
    public Object getOrderById(@PathVariable("orderNo") String orderNo) {
        System.out.println("isNewBiz="+isNewBiz);
//        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        OrderInfo orderInfo = getOrderInfo(orderNo);
        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }

        /**
         * 从nacos server获取 product-info的地址
         */
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("product-center");

        if (null == serviceInstanceList || serviceInstanceList.isEmpty()) {
            return "用户微服务没有对应的实例可用";
        }
        /**
         * 获取第0个元素
         */
        String targetUri = serviceInstanceList.get(0).getUri().toString();

        ResponseEntity<ProductVO> responseEntity = restTemplate.getForEntity(targetUri + "/getProductById/" + orderInfo.getProductNo(), ProductVO.class);

        ProductVO productVO = responseEntity.getBody();

        if (productVO == null) {
            return "没有对应的商品";
        }

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo(orderInfo.getOrderNo());
        orderVO.setUserName(orderInfo.getUserName());
        orderVO.setProductName(productVO.getProductName());
        orderVO.setProductNum(orderInfo.getProductCount());

        return orderVO;
    }

}
