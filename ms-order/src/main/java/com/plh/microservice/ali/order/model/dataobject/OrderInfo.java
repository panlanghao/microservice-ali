package com.plh.microservice.ali.order.model.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/11 21:23
 */
@Data
public class OrderInfo {
    private String orderNo;

    private String userName;

    private Date createDt;

    private String productNo;

    private Integer productCount;
}
