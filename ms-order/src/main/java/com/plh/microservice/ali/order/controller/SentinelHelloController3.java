package com.plh.microservice.ali.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.plh.microservice.ali.order.BlockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author 潘朗豪
 * @date 2020/4/30 0:24
 */
@Slf4j
@RestController
public class SentinelHelloController3 {


    /**
     * 初始化流控规则
     */
    @PostConstruct
    public void init() {
        List<FlowRule> flowRules = new ArrayList<>();
        /**
         * 定义 helloSentinelV2 受保护的资源的规则
         */
//创建流控规则对象
        FlowRule flowRule2 = new FlowRule();
//设置流控规则 QPS
        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
//设置受保护的资源
        flowRule2.setResource("helloSentinelV3");
//设置受保护的资源的阈值
        flowRule2.setCount(1);
        flowRules.add(flowRule2);
//加载配置好的规则
        FlowRuleManager.loadRules(flowRules);
    }

    /**
     * 我们看到了v2中的缺点,我们通过blockHandlerClass 来指定处理被流控的类
     * 通过testHelloSentinelV3BlockMethod 来指定blockHandlerClass 中的方法名称
     * ***这种方式 处理异常流控的方法必须要是static的
     * 频繁请求接口 http://localhost:8080/helloSentinelV3
     * @return
     */
    @RequestMapping("/helloSentinelV3")
    @SentinelResource(value = "helloSentinelV3",blockHandler = "testHelloSentinelV3BlockMethod",blockHandlerClass = BlockUtils.class)
    public String testHelloSentinelV3() {
        System.out.println("开始执行 自己的业务方法!!!");
        return "OK!!!!";
    }
}
