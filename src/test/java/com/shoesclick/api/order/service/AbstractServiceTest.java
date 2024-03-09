package com.shoesclick.api.order.service;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import com.shoesclick.api.order.aspect.ReturnRepositoryElement;
public abstract class AbstractServiceTest {


    protected AspectJProxyFactory factory;

    public <T> T setupServiceTest(T service) {
        this.factory = new AspectJProxyFactory(service);
        this.factory.addAspect(new ReturnRepositoryElement());
        return service = this.factory.getProxy();
    }

}
