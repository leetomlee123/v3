package com.lx.backstagemanagement.controller;

import com.lx.backstagemanagement.entity.OrderItem;
import com.lx.backstagemanagement.entity.OrderItemDetailResponse;
import com.lx.backstagemanagement.entity.Orders;
import com.lx.backstagemanagement.entity.Product;
import com.lx.backstagemanagement.service.IProductService;
import com.lx.backstagemanagement.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;
    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/{limit}")
    public String getUsers(@PathVariable("limit") Integer limit, Model model) {
        if (StringUtils.isEmpty(limit)) {
            limit = 0;
        }
        Page<Orders> orders = orderServiceImpl.orders(limit, 10);
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
        model.addAttribute("pageBean", orders);
        return "order/list";
    }

    @RequestMapping(value = "/orderitem", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> toAdd(String oid) {
        List<Product> products = new ArrayList<>();
        List<OrderItem> orderItems = orderServiceImpl.orderitem(oid);
        orderItems.stream().forEach(e -> products.add(productService.getProductById(e.getPid())));
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("orderItems", orderItems);
        stringObjectHashMap.put("products", products);

        return stringObjectHashMap;
    }


}
