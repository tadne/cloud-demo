package cn.itcast.order;


import cn.itcast.feign.Clients.UserClient;
import cn.itcast.feign.config.defaultFeignConfiguration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication

@EnableFeignClients(clients = UserClient.class,defaultConfiguration= defaultFeignConfiguration.class)

public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    //创RestTemplete并注入IOC
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    /*@Bean
    public IRule randomRule(){
        return new RandomRule();
    }*/
}