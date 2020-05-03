package com.plh.microservice.ali.order;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/30 1:00
 */
@Slf4j
public class BlockUtils {
    public static String testHelloSentinelV3BlockMethod(BlockException e){
        log.info("testHelloSentinelV3方法被流控了");
        return "testHelloSentinelV3方法被流控了";
    }
}
