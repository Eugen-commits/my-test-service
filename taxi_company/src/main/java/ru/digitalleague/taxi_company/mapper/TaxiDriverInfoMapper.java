package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.entity.TaxiDriverInfoModel;

@Mapper
@Repository
public interface TaxiDriverInfoMapper {
    @Select("select driver_id from taxi_drive_info where " +
            "is_busy = false order by rating desc limit 1")
    Long getDriverByGlobalRatingAndBusyStatus();

    @Update("update taxi_drive_info set is_busy = true where driver_id = #{driverId}")
    void setDriverStatusTrue(Long driverId);

    @Update("update taxi_drive_info set is_busy = false where driver_id = #{driverId}")
    void setDriverStatusFalse(Long driverId);
}

//"rating = (select max(rating) from taxi_drive_info)