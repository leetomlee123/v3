package com.lx.dao;


import com.core.dao.BaseMapper;
import com.lx.entity.Orders;
import com.lx.entity.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {
    @Select("select * from product where is_hot=1 and pflag=0 order by pdate desc")
    List<Product> getHotProduct();

    @Select("SELECT *from product where pflag=0 ORDER BY pdate desc LIMIT 0,12")
    List<Product> getNewHotProduct();
}
