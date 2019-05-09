package com.w.crm.service;

import com.w.common.utils.Page;
import com.w.crm.pojo.Customer;
import com.w.crm.pojo.QueryVo;

public interface CustomerService {
    //通过四个条件  查询分页对象
    public Page<Customer> selectPageByQueryVo(QueryVo VO);
    //通过id查询客户
    public Customer selectCustomerById(Integer id);
    //修改客户通过id
    public void updateCustomerById(Customer customer);
    //删除
    public void deleteCustomerById(Integer id);
}
