package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.OrderDetails;
import java.util.List;

@Mapper
@Repository
public interface OrderDetailsMapper {

    @Select("select * from orders")
    List <OrderDetails> getAllOrders();

    @Insert("insert into orders (clientNumber,level,carModel) values (#{clientNumber},#{level},#{carModel})")
    void createOrder(OrderDetails orderDetails);
}
