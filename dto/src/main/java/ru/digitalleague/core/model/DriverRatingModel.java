package ru.digitalleague.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Модель оценки водителя")
public class DriverRatingModel {
    @ApiModelProperty("Идентификатор водителя")
    private Long driverId;

    @ApiModelProperty("Идентификатор заказа")
    private Long orderId;

    @ApiModelProperty("Оценка поездки")
    private Integer grade;
}
