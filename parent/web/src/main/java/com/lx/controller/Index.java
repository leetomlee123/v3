package com.lx.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.core.action.BaseController;
import com.lx.entity.Category;
import com.lx.entity.Product;
import com.lx.service.ICategoryService;
import com.lx.service.IProductService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ValidateCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
public class Index {
//    @Value("${code}")
//    private String code;

    //    @RequestMapping(value = "/home")
//    public String home(Model model) {
//        model.addAttribute("code", code);
//        return "admin/index";
//    }
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Index.class);

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "/index/initCategory")
    public @ResponseBody
    String initCategory() {
        Object goodsCateGory = null;

        try {
            goodsCateGory = redisTemplate.opsForValue().get("goodsCateGory");
        } catch (Exception e) {
            LOGGER.info("redis 连接异常");
            return JSON.toJSONString(categoryService.select());
        }

        if (goodsCateGory == null) {
            try {
                List<Category> categories = categoryService.select();
                redisTemplate.opsForValue().set("goodsCateGory", new ObjectMapper().writeValueAsString(categories));

                goodsCateGory = redisTemplate.opsForValue().get("goodsCateGory").toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return goodsCateGory.toString();
    }

    @RequestMapping(value = "/index/validateCode")
    public void validateCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //指定生成的响应图片,一定不能缺少这句话,否则错误.
        response.setContentType("image/jpeg");
        BufferedImage image = ValidateCodeUtils.getImage(request);
        ServletOutputStream out = response.getOutputStream();

        try {
            ImageIO.write(image, "JPEG", out);
            out.flush();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        } finally {
            out.close();
        }

    }

    @RequestMapping(value = "/")
    public String home(Model model) {
        List<Product> hotProduct = null;


        try {
            hotProduct = JSONArray.parseArray(redisTemplate.opsForValue().get("hotProduct").toString(), Product.class);
        } catch (Exception e) {
            LOGGER.info("redis 连接异常");
            hotProduct = iProductService.getHotProduct();
        }

        if (hotProduct == null) {
            hotProduct = iProductService.getHotProduct();
            redisTemplate.opsForValue().set("hotProduct", JSON.toJSON(hotProduct));
        }
        model.addAttribute("hotProductList", hotProduct);

        List<Product> newProductList = null;
        try {
            newProductList = JSONArray.parseArray(redisTemplate.opsForValue().get("newProductList").toString(), Product.class);
        } catch (Exception e) {
            LOGGER.info("redis 连接异常");
            newProductList = iProductService.getNewHotProduct();
        }

        if (newProductList == null) {

            newProductList = iProductService.getNewHotProduct();
            redisTemplate.opsForValue().set("newProductList", JSON.toJSON(newProductList));
        }
        model.addAttribute("newProductList", newProductList);

        return "index";
    }

}