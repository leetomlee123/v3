package com.lx.controller;

import com.alibaba.fastjson.JSON;
import com.core.constant.SysKeyWord;
import com.lx.entity.*;
import com.lx.service.IOrderItemService;
import com.lx.service.IOrderService;
import com.lx.service.IProductService;
import com.lx.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderItemService orderItemService;

    @RequestMapping(value = "/payOrder")

    public String payOrder(Orders orders) {
        orderService.payOrder(orders);
        return "common/paySuccess";
    }

    @RequestMapping(value = "/findProductInfoByOid")
    public @ResponseBody
    String findOrderInfoByOid(String oid) {
        List<OrderItemDetailResponse> products = orderItemService.findProductInfoByOid(oid);
        return JSON.toJSONString(products);
    }

    @RequestMapping(value = "/orderInfo")
    public String orderInfo(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(SysKeyWord.getUserName());

        ProductService service = new ProductService();
        //查询该用户的所有的订单信息(单表查询orders表)
        //集合中的每一个Order对象的数据是不完整的 缺少List<OrderItem> orderItems数据
        List<Orders> orderList = orderService.findAllOrders(user.getUid());
        //循环所有的订单 为每个订单填充订单项集合信息
        if (orderList != null) {
            for (Orders order : orderList) {
                //获得每一个订单的oid
                String oid = order.getOid();
                //查询该订单的所有的订单项---mapList封装的是多个订单项和该订单项中的商品的信息
                List<Map<String, Object>> mapList = orderItemService.findAllOrderItemByOid(oid);
                //将mapList转换成List<OrderItem> orderItems
                for (Map<String, Object> map : mapList) {
                    try {
                        //从map中取出count subtotal 封装到OrderItem中
                        OrderItem item = new OrderItem();
                        //item.setCount(Integer.parseInt(map.get("count").toString()));
                        BeanUtils.populate(item, map);
                        //从map中取出pimage pname shop_price 封装到Product中
                        Product product = new Product();
                        BeanUtils.populate(product, map);
                        //将product封装到OrderItem
                        item.setProduct(product);
                        //将orderitem封装到order中的orderItemList中
                        order.getOrderItems().add(item);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }


                }

            }
        }
        //orderList封装完整了
        request.setAttribute("orderList", orderList);
        return "order/order_list";
    }

    @RequestMapping(value = "/myOrders")
    public String myOrders() {
        return "order/order_info";
    }

    @RequestMapping(value = "/submitOrder")
    public String submitOrder(HttpServletRequest request) {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(SysKeyWord.getUserName());

        //目的：封装好一个Order对象 传递给service层
        Orders order = new Orders();

        //1、private String oid;//该订单的订单号
        String oid = UUIDUtils.getUUID();
        order.setOid(oid);

        //2、private Date ordertime;//下单时间
        order.setOrdertime(new Date());

        //3、private double total;//该订单的总金额
        //获得session中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        double total = cart.getTotal();
        order.setTotal(total);

        //4、private int state;//订单支付状态 1代表已付款 0代表未付款
        order.setState(0);

        //5、private String address;//收货地址
        order.setAddress(null);

        //6、private String name;//收货人
        order.setName(null);

        //7、private String telephone;//收货人电话
        order.setTelephone(null);

        //8、private User user;//该订单属于哪个用户
        order.setUser(user);

        //9、该订单中有多少订单项List<OrderItem> orderItems = new ArrayList<OrderItem>();
        //获得购物车中的购物项的集合map
        Map<String, CartItem> cartItems = cart.getCartItems();
        for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
            //取出每一个购物项
            CartItem cartItem = entry.getValue();
            //创建新的订单项
            OrderItem orderItem = new OrderItem();
            //1)private String itemid;//订单项的id
            orderItem.setItemid(UUIDUtils.getUUID());
            //2)private int count;//订单项内商品的购买数量
            orderItem.setCount(cartItem.getBuyNum());
            //3)private double subtotal;//订单项小计
            orderItem.setSubtotal(cartItem.getSubtotal());
            //4)private Product product;//订单项内部的商品
            orderItem.setProduct(cartItem.getProduct());
            //5)private Order order;//该订单项属于哪个订单
            orderItem.setOrder(order);
            //将该订单项添加到订单的订单项集合中
            order.getOrderItems().add(orderItem);
        }
        //order对象封装完毕
        //传递数据到service层
        orderService.submitOrder(order);
        session.setAttribute("order", order);
        return "redirect:/order/myOrders";
    }

}
