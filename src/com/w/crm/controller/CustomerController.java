package com.w.crm.controller;

import com.w.common.utils.Page;
import com.w.crm.pojo.BaseDict;
import com.w.crm.pojo.Customer;
import com.w.crm.pojo.QueryVo;
import com.w.crm.service.BaseDictService;
import com.w.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class CustomerController {
    //@value注解获取properties中的配置信息并注解在成员变量上
    @Value("${fromType.code1}")
    private String fromTypeCode1;
    @Value("${fromType.code2}")
    private String fromTypeCode2;
    @Value("${fromType.code3}")
    private String fromTypeCode3;
    @Autowired
    private BaseDictService baseDictService;
    @Autowired
    private CustomerService customerService;
    //显示
   @RequestMapping(value = "/customer/list")
    public String list(Model model ,QueryVo vo){
       List<BaseDict> fromType = baseDictService.selectBaseDictListByCode(fromTypeCode1);
       List<BaseDict> industryType = baseDictService.selectBaseDictListByCode(fromTypeCode2);
       List<BaseDict> levelType =baseDictService.selectBaseDictListByCode(fromTypeCode3);
       //使用model.add...向前端传送数据
       model.addAttribute("fromType",fromType);
       model.addAttribute("industryType",industryType);
       model.addAttribute("levelType",levelType);
       //通过四个条件，查询分页对象
       Page<Customer> page = customerService.selectPageByQueryVo(vo);
       model.addAttribute("page",page);
       return "customer";
   }
   //去修改页面
    @RequestMapping(value = "/customer/edit.action")
    public @ResponseBody Customer edit(Integer id){
        return  customerService.selectCustomerById(id);
    }
    //修改保存
    @RequestMapping(value = "customer/update.action")
    public @ResponseBody
    String update(Customer customer){
       //修改
     customerService.updateCustomerById(customer);
     return "ok";
    }
    //删除
    @RequestMapping(value = "customer/delete.action")
    public @ResponseBody
    String delete(Integer id){
        //删除
  customerService.deleteCustomerById(id);
        return "ok";
    }

}
