package com.beifeng.hadoop.spring.cloud.zuul;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class MyZuulFilter extends ZuulFilter {
    
    Logger logger=LoggerFactory.getLogger(MyZuulFilter.class);

    //过滤器的具体逻辑
    public Object run() {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request=context.getRequest();
        logger.info("url:"+request.getRequestURL().toString());
        Object token=request.getParameter("token");
        if (token==null||StringUtils.isEmpty(token.toString())) {
            logger.info("token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                context.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        logger.info("ok");
        return null;
    }

    //是否要过滤,可以写逻辑判断
    public boolean shouldFilter() {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request=context.getRequest();
        String url=request.getRequestURL().toString();
        return url.contains("/api-a/");//只对api-a的请求进行验证
    }

    //过滤的顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    //返回过滤器的类型，pre:路由前，routing:路由时，post:路由之后，error:发生错误时调用
    @Override
    public String filterType() {
        return "pre";
    }
}
