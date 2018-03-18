package com.lx.backstagemanagement.controller;

import com.lx.backstagemanagement.entity.Category;
import com.lx.backstagemanagement.service.ICategoryService;
import com.lx.backstagemanagement.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryServiceImpl;
    @RequestMapping(value = "/list")
    public String list(Model model){
        List<Category> categorys = categoryServiceImpl.categorys();
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
        model.addAttribute("pageBean", categorys);
        return "category/list";
    }
    @RequestMapping(value = "/update/{cid}")
    public String update(@PathVariable(value = "cid")String cid,Model model){
      Category category=  categoryServiceImpl.getByCid(cid);
      model.addAttribute("model",category);
      return "category/edit";
    }
    @RequestMapping(value = "/edit")
    public String edit(Category category){
categoryServiceImpl.edit(category);
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
//        model.addAttribute("pageBean", categorys);
        return "redirect:/category/list";
    }
    @RequestMapping(value = "/delete/{cid}")
    public String delete(@PathVariable(value = "cid")String cid){

        categoryServiceImpl.deleteCategory(cid);
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
//        model.addAttribute("pageBean", categorys);
        return "redirect:/category/list";
    }
    @RequestMapping(value = "/add")
    public String add(Category category){
        category.setCid(UUIDUtils.getUUID());
        categoryServiceImpl.add(category);
        return "redirect:/category/list";
    }
    @RequestMapping(value = "/save")
    public String SAVE(){
        return "category/add";
    }
}
