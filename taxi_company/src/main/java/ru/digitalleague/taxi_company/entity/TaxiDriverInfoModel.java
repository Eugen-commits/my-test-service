package ru.digitalleague.taxi_company.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@ApiModel
public class TaxiDriverInfoModel {
    @ApiModelProperty("ИД водителя")
    private Long driverId;

    /**
     * Фамилия.
     */
    @ApiModelProperty("Фамилия")
    private String lastName;

    /**
     * Имя.
     */
    @ApiModelProperty("Имя")
    private String firstName;

    /**
     * Отчество.
     */
    @ApiModelProperty("Отчество")
    private String middleName;

    /**
     * Уровень.
     */
    @ApiModelProperty("Уровень авто")
    private int level;

    /**
     * Модель авто (должна быть ENUM).
     */
    @ApiModelProperty("Модель машины")
    private String carModel;

    /**
     * Дата создания.
     */
    @ApiModelProperty("Время найма")
    private OffsetDateTime createDttm;

    //Оценки водителя для расчета среднего арифметического для globalRating
    @ApiModelProperty("Оценки водителя для расчета средней арифметической")
    private List <Integer> rating;

    @ApiModelProperty("Средняя оценка")
    private int globalRating;

    @ApiModelProperty("Статус водителя")
    private boolean isBusy;
}
