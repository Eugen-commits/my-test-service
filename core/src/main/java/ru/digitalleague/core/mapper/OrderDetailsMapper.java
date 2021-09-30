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

    List <OrderDetails> getAllOrders();

    void createOrder(OrderDetails orderDetails);

    void updateByClientNumber (OrderDetails orderDetails);

    void delete(OrderDetails orderDetails);
}
