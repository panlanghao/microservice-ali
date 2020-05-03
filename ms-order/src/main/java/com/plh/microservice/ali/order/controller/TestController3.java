package com.plh.microservice.ali.order.controller;

import com.plh.microservice.ali.common.vo.OrderVO;
import com.plh.microservice.ali.common.vo.ProductVO;
import com.plh.microservice.ali.feign.api.ProductCenterApi;
import com.plh.microservice.ali.order.model.dataobject.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Feign的使用
 *
 * @author 潘朗豪
 * @date 2020/4/25 21:38
 */
@RestController
public class TestController3 {

    @Autowired
    private ProductCenterApi productCenterApi;


    private OrderInfo getOrderInfo(String orderNo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setUserName("dd");
        orderInfo.setProductNo("1");
        orderInfo.setCreateDt(new Date());
        orderInfo.setProductCount(100);
        return orderInfo;
    }


    @RequestMapping("getOrderById3/{orderNo}")
    public Object getOrderById(@PathVariable("orderNo") String orderNo) {
//        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        OrderInfo orderInfo = getOrderInfo(orderNo);
        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }
        ProductVO productVO = productCenterApi.getProductById(orderInfo.getProductNo());

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

    @RequestMapping("/getToken")
    public String getToken() {
        return productCenterApi.getToken4Header("123");
    }
}
