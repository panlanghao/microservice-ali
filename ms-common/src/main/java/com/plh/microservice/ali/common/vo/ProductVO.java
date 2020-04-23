package com.plh.microservice.ali.common.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/11 21:50
 */
@Data
public class ProductVO {
    private String productNo;

    private String productName;

    private String productStore;

    private BigDecimal productPrice;
}
