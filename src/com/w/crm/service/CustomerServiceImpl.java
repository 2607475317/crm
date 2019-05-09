package com.w.crm.service;

import com.w.common.utils.Page;
import com.w.crm.mapper.CustomerDao;
import com.w.crm.pojo.Customer;
import com.w.crm.pojo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    //通过四个条件  查询分页对象
    public Page<Customer> selectPageByQueryVo(QueryVo vo) {
        //新建分页对象
        Page<Customer> page = new Page<Customer>();
        //设置每页数
        page.setSize(10);
        vo.setSize(10);
        //判断当前页是否存在
        if (null != vo ){
            //并设置当前页
            if(null != vo.getPage()){
                page.setPage(vo.getPage());
                //设置开始行
                vo.setStartRow((vo.getPage()-1)*vo.getSize());
            }
            //判断客户名称等不能为空和不能为空字符串
            if(null!=vo.getCustName()&& !"".equals(vo.getCustName().trim())){
              vo.setCustName(vo.getCustName().trim());
            }
            if(null!=vo.getCustSource()&& !"".equals(vo.getCustSource().trim())){
                vo.setCustSource(vo.getCustSource().trim());
            }
            if(null!=vo.getCustIndustry()&& !"".equals(vo.getCustIndustry().trim())){
                vo.setCustIndustry(vo.getCustIndustry().trim());
            }
            if(null!=vo.getCustLevel()&& !"".equals(vo.getCustLevel().trim())){
                vo.setCustLevel(vo.getCustLevel().trim());
            }
           //设置信息条数
            page.setTotal(customerDao.customerCountByQueryVo(vo));
           //设置结果集
            page.setRows(customerDao.selectCustomerListByQueryVo(vo));
        }
           return page;
    }
    //根据id查询用户
    public Customer selectCustomerById(Integer id){
        return customerDao.selectCustomerById(id);
    }
    //修改客户通过id
    public void updateCustomerById(Customer customer){
        customerDao.updateCustomerById(customer);
    }
    //根据id删除
    public void deleteCustomerById(Integer id){
        customerDao.deleteCustomerById(id);
    }

}
