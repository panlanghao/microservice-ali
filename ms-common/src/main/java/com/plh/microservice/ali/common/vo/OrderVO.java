package com.plh.microservice.ali.common.vo;

import lombok.Data;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/11 19:44
 */
@Data
public class OrderVO {

    private String orderNo;

    private String userName;

    private String productName;

    private Integer productNum;
}
