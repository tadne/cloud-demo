package cn.itcast.order.service;


import cn.itcast.feign.Clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;


    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        //Feign远程调用
        User user = userClient.findById(order.getUserId());
        order.setUser(user);

        // 4.返回
        return order;
    }






    /**
     * 用RestTemplate远程调用
     * @param orderId
     * @return
     */
    /*public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        String url="http://userservice/user/"+order.getUserId();
         //String url="http://localhost:8081/user/"+order.getUserId();
         User user = restTemplate.getForObject(url, User.class);
         order.setUser(user);

        // 4.返回
        return order;
    }*/
}
