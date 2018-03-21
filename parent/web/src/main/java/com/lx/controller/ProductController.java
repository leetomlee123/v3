package com.lx.controller;


import com.core.action.BaseController;
import com.core.constant.SysKeyWord;
import com.lx.entity.*;
import com.lx.service.ICategoryService;
import com.lx.service.IProductService;
import com.lx.service.ProductService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.UUIDUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/productList")
    public String
    productList(HttpServletRequest request, HttpServletResponse response, String key, String flage, String cid, Integer currentPage, Model model) {
        if (cid == null && !"1".equals(flage)) {
            model.addAttribute("exception", "参数异常");
            return "exception/404";
        }
        if (currentPage == null) {
            currentPage = 1;
        }
        Page<Product> productPage = new Page<Product>();
        productPage.setCurrentPage(currentPage);
        productPage.setPageSize(SysKeyWord.getPageSize());
        if (StringUtils.isNotBlank(key)) {
            productPage.setKeyWord(key);
            productPage.setParamEntity(new Product());
        }
        if (StringUtils.isNotBlank(cid)) {
            Product p = new Product();
            p.setCid(cid);
            productPage.setParamEntity(p);
        }
        Page<Product> pageBean = productService.selectPage(productPage);
        if (pageBean.getList() == null) {
            model.addAttribute("exception", "数据异常");
            return null;
        }
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("cid", cid);
        //定义一个记录历史商品信息的集合
        List<Product> historyProductList = new ArrayList<Product>();

        //获得客户端携带名字叫pids的cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("pids".equals(cookie.getName())) {
                    String pids = cookie.getValue();//3-2-1
                    String[] split = pids.split("-");
                    for (String pid : split) {
                        Product product = new Product();
                        product.setPid(pid);

                        List<Product> select = productService.select(product);
                        if (select.size() > 0) {
                            historyProductList.add(select.get(0));
                        }
                    }
                }
            }
        }

        //将历史记录的集合放到域中
        request.setAttribute("historyProductList", historyProductList);
        return "product/product_list";

    }

    @RequestMapping(value = "/delProFromCart")
    public String delProFromCart(HttpServletRequest request, String pid) {
        //删除session中的购物车中的购物项集合中的item
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            Map<String, CartItem> cartItems = cart.getCartItems();
            //需要修改总价
            cart.setTotal(cart.getTotal() - cartItems.get(pid).getSubtotal());
            //删除
            cartItems.remove(pid);
            cart.setCartItems(cartItems);
        }
        session.setAttribute("cart", cart);
        return "redirect:/product/CartUI";

    }

    @RequestMapping(value = "/productInfo")
    public String productInfo(HttpServletResponse response, HttpServletRequest request, String pid, String cid, Integer currentPage, Model model) {

        if (cid == null || pid == null) {
            model.addAttribute("exception", "参数异常");
            return "exception/404";
        }
        Product product = new Product();
        product.setPid(pid);
        List<Product> products = productService.select(product);
        if (products.size() < 1) {
            model.addAttribute("exception", "数据异常");
            return "exception/500";
        }
        Category category = new Category();
        category.setCid(cid);
        product = products.get(0);
        List<Category> categories = categoryService.select(category);
        model.addAttribute("product", product);
        model.addAttribute("cid", cid);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("category", categories.get(0));
        //此地做最近浏览

        //获得客户端携带cookie---获得名字是pids的cookie
        String pids = pid;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("pids".equals(cookie.getName())) {
                    pids = cookie.getValue();
                    //1-3-2 本次访问商品pid是8----->8-1-3-2
                    //1-3-2 本次访问商品pid是3----->3-1-2
                    //1-3-2 本次访问商品pid是2----->2-1-3
                    //将pids拆成一个数组
                    String[] split = pids.split("-");//{3,1,2}
                    List<String> asList = Arrays.asList(split);//[3,1,2]
                    LinkedList<String> list = new LinkedList<String>(asList);//[3,1,2]
                    //判断集合中是否存在当前pid
                    if (list.contains(pid)) {
                        //包含当前查看商品的pid
                        list.remove(pid);
                        list.addFirst(pid);
                    } else {
                        //不包含当前查看商品的pid 直接将该pid放到头上
                        list.addFirst(pid);
                    }
                    //将[3,1,2]转成3-1-2字符串
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < list.size() && i < 7; i++) {
                        sb.append(list.get(i));
                        sb.append("-");//3-1-2-
                    }
                    //去掉3-1-2-后的-
                    pids = sb.substring(0, sb.length() - 1);
                }
            }
        }


        Cookie cookie_pids = new Cookie("pids", pids);
        response.addCookie(cookie_pids);
        return "product/product_info";
    }

    @RequestMapping(value = "/addProductToCart")
    public String addProductToCart(HttpServletRequest request, HttpServletResponse response, Product product, String buyNum, Model model) {

        HttpSession session = request.getSession();


        //获得product对象
        product = productService.select(product).get(0);
        //计算小计
        double subtotal = product.getShop_price() * Integer.parseInt(buyNum);
        //封装CartItem
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setBuyNum(Integer.parseInt(buyNum));
        item.setSubtotal(subtotal);

        //获得购物车---判断是否在session中已经存在购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        //将购物项放到车中---key是pid
        //先判断购物车中是否已将包含此购物项了 ----- 判断key是否已经存在
        //如果购物车中已经存在该商品----将现在买的数量与原有的数量进行相加操作
        Map<String, CartItem> cartItems = cart.getCartItems();

        double newsubtotal = 0.0;

        if (cartItems.containsKey(product.getPid())) {
            //取出原有商品的数量
            CartItem cartItem = cartItems.get(product.getPid());
            int oldBuyNum = cartItem.getBuyNum();
            oldBuyNum += Integer.parseInt(buyNum);
            cartItem.setBuyNum(oldBuyNum);
            cart.setCartItems(cartItems);
            //修改小计
            //原来该商品的小计
            double oldsubtotal = cartItem.getSubtotal();
            //新买的商品的小计
            newsubtotal = Integer.parseInt(buyNum) * product.getShop_price();
            cartItem.setSubtotal(oldsubtotal + newsubtotal);

        } else {
            //如果车中没有该商品
            cart.getCartItems().put(product.getPid(), item);
            newsubtotal = Integer.parseInt(buyNum) * product.getShop_price();
        }

        //计算总计
        double total = cart.getTotal() + newsubtotal;
        cart.setTotal(total);


        //将车再次访问session
        session.setAttribute("cart", cart);


        return "redirect:/product/CartUI";
    }

    @RequestMapping(value = "/clearCart")
    public String clearCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("cart");
        return "redirect:/product/CartUI";
    }


    @RequestMapping(value = "/CartUI")
    public String CartUi() {
        return "common/cart";
    }

}
