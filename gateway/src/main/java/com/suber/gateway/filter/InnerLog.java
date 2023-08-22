package com.suber.gateway.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author suber
 * 2023/8/19 12:57
 */
@Component
@Log4j2
public class InnerLog implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("有请求进入 (*^_^*)");
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        String hostString = request.getLocalAddress().getHostString();

//        将请求网关的前缀去掉，路由到服务的对应路径
        if (path.indexOf("/proxy/v1") == 0) {
            String newPath = request.getURI().getPath().replace("/proxy/v1","");
            ServerHttpRequest newRequest = request.mutate().path(newPath).build();
            log.info("请求路径转发--->" + newPath);
            return chain.filter(exchange.mutate().request(newRequest).build());
        }

        log.error("请求路径不存在");
        exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
