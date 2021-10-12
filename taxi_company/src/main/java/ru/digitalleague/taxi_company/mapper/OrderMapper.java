package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import ru.digitalleague.taxi_company.entity.OrderDetails;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     *
     * @param order информация о заказе.
     */
    @Insert(" insert into orders (client_number,startpointaddress, endpointaddress, driver_id, city)" +
            " values( #{clientNumber},#{startPointAddress}, #{endPointAddress}, #{driverId}, #{city})")
    void saveOrder(OrderDetails order);

    @Update("update orders set start_trip = #{startTrip} where order_id = #{orderId}")
    void setOrderStartTripById(OrderDetails orderDetails);

    @Update("update orders set end_trip = #{endTrip} where order_id = #{orderId}")
    void updateOrderEndById(OrderDetails orderDetails);

    @Select("select driver_id from orders where order_id = #{orderId}")
    Long getDriverId(OrderDetails orderDetails);
}