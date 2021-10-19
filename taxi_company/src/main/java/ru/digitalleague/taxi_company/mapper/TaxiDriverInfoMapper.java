package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.DriverRatingModel;
import ru.digitalleague.taxi_company.entity.TaxiDriverInfoModel;

import java.util.List;

@Mapper
@Repository
public interface TaxiDriverInfoMapper {
    @Select("select driver_id from taxi_drive_info where " +
            "is_busy = false and city = #{city} order by rating desc limit 1")
    Long getDriverByGlobalRatingAndBusyStatus(String city);

    @Update("update taxi_drive_info set is_busy = true where driver_id = #{driverId}")
    void setDriverStatusTrue(Long driverId);

    @Update("update taxi_drive_info set is_busy = false where driver_id = #{driverId}")
    void setDriverStatusFalse(Long driverId);

    @Insert("insert into driver_rating (driver_id, order_id, grade) values (#{driverId}, #{orderId}, #{grade})")
    void saveGrade(DriverRatingModel ratingModel);

    @Select("select grade from driver_rating where driver_id = #{driverId}")
    List<Integer> getAllDriverGradeByDriverId(Long driverId);

    @Update("update taxi_drive_info set rating = #{rating} where driver_id = #{driverId}")
    void saveDriverRating (int rating, Long driverId);

    @Select("select minute_cost from taxi_drive_info where driver_id = #{driverId}")
    int getMinuteCost(Long driverId);
}

//"rating = (select max(rating) from taxi_drive_info)