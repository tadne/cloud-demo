package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class AuthorizeFilter implements GlobalFilter
{


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        //1获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        //2获取参数中的authorization参数
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //3判断参数值是否等于admin
        String authorization = queryParams.getFirst("authorization");
        //4是,放行
        if("admin".equals(authorization)){
           return chain.filter(exchange);
        }
        //5否，拦截

        //5.1设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //5.2放行
        return exchange.getResponse().setComplete();


    }
}
