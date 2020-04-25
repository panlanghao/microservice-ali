package com.plh.microservice.ali.order.controller;

import com.plh.microservice.ali.common.vo.OrderVO;
import com.plh.microservice.ali.common.vo.ProductVO;
import com.plh.microservice.ali.order.model.dataobject.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * 利用ribbon
 *
 * @author 潘朗豪
 * @date 2020/4/24 23:40
 */
@RestController
public class TestController2 {

    @Autowired
    private RestTemplate restTemplate;

    private OrderInfo getOrderInfo(String orderNo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setUserName("dd");
        orderInfo.setProductNo("1");
        orderInfo.setCreateDt(new Date());
        orderInfo.setProductCount(100);
        return orderInfo;
    }


    @RequestMapping("getOrderById2/{orderNo}")
    public Object getOrderById(@PathVariable("orderNo") String orderNo) {
//        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        OrderInfo orderInfo = getOrderInfo(orderNo);
        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }

        ResponseEntity<ProductVO> responseEntity = restTemplate.getForEntity("http://product-center/getProductById/" + orderInfo.getProductNo(), ProductVO.class);

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
