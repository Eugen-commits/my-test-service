package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import ru.digitalleague.core.model.OrderResponse;
import ru.digitalleague.taxi_company.entity.OrderDetails;
import ru.digitalleague.taxi_company.entity.TaxiDriverInfoModel;

import java.time.OffsetDateTime;

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

    @Results(id = "drivers", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "carModel", column = "car_model"),
            @Result(property = "createDttm", column = "create_dttm")
    })
    @Select("select td.last_name, td.first_name, td.car_model from orders left join taxi_drive_info td on orders.driver_id = td.driver_id" +
            " where order_id = #{orderId}")
    TaxiDriverInfoModel getDriverByOrderId(OrderDetails orderDetails);

    @Select("select start_trip from orders where order_id = #{orderId}")
    OffsetDateTime getStartTrip(OrderDetails orderDetails);

    @Select("select end_trip from orders where order_id = #{orderId}")
    OffsetDateTime getEndTrip(OrderDetails orderDetails);

    @Update("update orders set price = #{result} where order_id = #{id}")
    void saveCost(long result, Long id);

    @Results(id = "orders", value = {
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "clientNumber", column = "client_number"),
            @Result(property = "startPointAddress", column = "startpointaddress"),
            @Result(property = "endPointAddress", column = "endpointaddress"),
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "startTrip", column = "start_trip"),
            @Result(property = "endTrip", column = "end_trip")
    })
    @Select("select order_id, client_number,startpointaddress, endpointaddress, driver_id, start_trip, end_trip " +
            "        from orders " +
            "        where order_id = #{orderId}")
    OrderDetails getOrderById(OrderDetails orderDetails);

}