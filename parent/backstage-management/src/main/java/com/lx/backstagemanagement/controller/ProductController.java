package com.lx.backstagemanagement.controller;

import com.lx.backstagemanagement.entity.Category;
import com.lx.backstagemanagement.entity.Product;
import com.lx.backstagemanagement.service.ICategoryService;
import com.lx.backstagemanagement.service.IProductService;
import com.lx.backstagemanagement.util.UUIDUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/{limit}")
    public String getUsers(@PathVariable("limit") Integer limit, Model model) {
        if (StringUtils.isEmpty(limit)) {
            limit = 0;
        }
        Page<Product> products = productService.products(limit, 10);
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
        model.addAttribute("pageBean", products);
        return "product/list";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, Product product, @Param("upload") MultipartFile upload) {
        if (upload.getSize() > 0) {
            String contentType = upload.getContentType();
            String originalFilename = "products/1/" + upload.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("/" + originalFilename);
            try {
                FileCopyUtils.copy(upload.getBytes(), new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setPimage(originalFilename);
            product.setPid(UUIDUtils.getUUID());
            product.setPflag(0);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                product.setPdate(simpleDateFormat.parse(simpleDateFormat.format(date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            productService.save(product);
        } else {
            return "redirect:/product/0";
        }
        return "redirect:/product/0";
    }

    @RequestMapping(value = "/edit/{pid}/{num}")

    public String editUser(@PathVariable("pid") String pid, Model model, @PathVariable("num") String num) {
        if (StringUtils.isEmpty(pid)) {
            return null;
        }
        Product product = productService.getProductById(pid);
        List<Category> categorys = categoryService.categorys();

        model.addAttribute("categorys", categorys);
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
        model.addAttribute("model", product);
        model.addAttribute("num", num);
        return "product/edit";
    }

    @RequestMapping(value = "/delete/{uid}")
    public String deleteUser(@PathVariable("uid") String uid) {

        productService.delete(uid);

        return "redirect:/product/0";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request, @Param("upload") MultipartFile upload, Product product, String num) {
        if (upload.getSize() > 0) {
            String contentType = upload.getContentType();
            String originalFilename = upload.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("" + product.getPimage());
            try {
                FileCopyUtils.copy(upload.getBytes(), new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productService.update(product);
        return "redirect:/product/" + num;
    }

    @RequestMapping(value = "/to_add")
    public String toAdd(Model model) {
        List<Category> categorys = categoryService.categorys();

        model.addAttribute("categorys", categorys);

        return "product/add";
    }

    @Test
    public void s() throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        System.out.println(simpleDateFormat.parse(format));
    }
}
