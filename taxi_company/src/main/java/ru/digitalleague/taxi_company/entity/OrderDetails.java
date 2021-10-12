package ru.digitalleague.taxi_company.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@ApiModel
public class OrderDetails {
    @ApiModelProperty("Id заказа")
    private Long orderId;

    /**
     * Идентификатор клиента.
     */
    @ApiModelProperty("Номер телефона клиента")
    private Long clientNumber;

    @ApiModelProperty("ИД водителя")
    private Long driverId;

    @ApiModelProperty("Точка отправления")
    private String startPointAddress;

    @ApiModelProperty("Пункт назначения")
    private String endPointAddress;

    @ApiModelProperty("Время начала поездки")
    private OffsetDateTime startTrip;

    @ApiModelProperty("Время окончания поездки")
    private OffsetDateTime endTrip;

    @ApiModelProperty("Город")
    private String city;
}
