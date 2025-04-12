package com.fengye.gateway.filter;

import cn.hutool.core.util.ObjectUtil;
import com.fengye.gateway.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MemberLoginFilter implements GlobalFilter, Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(MemberLoginFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String Path = exchange.getRequest().getURI().getPath();
        if(Path.contains("/member/member/login")
        || Path.contains("/member/member/logout")
        || Path.contains("/member/member/register")
        || Path.contains("/member/member/send-code")
        || Path.contains("/member/admin")) {
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("token");

        if(token == null || token.isEmpty()) {
            LOG.info("token为空，已经被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        Boolean validate = JwtUtil.validate(token);


        if(validate) {
            LOG.info("token有效，请求通过");
            return chain.filter(exchange);
        }else {
            LOG.info("token无效，请求失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
