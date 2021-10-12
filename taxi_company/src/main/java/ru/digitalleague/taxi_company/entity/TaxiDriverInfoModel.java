package ru.digitalleague.taxi_company.entity;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class TaxiDriverInfoModel {

    private Long driverId;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Отчество.
     */
    private String middleName;

    /**
     * Уровень.
     */
    private int level;

    /**
     * Модель авто (должна быть ENUM).
     */
    private String carModel;

    /**
     * Дата создания.
     */
    private OffsetDateTime createDttm;

    //Оценки водителя для расчета среднего арифметического для globalRating
    private List <Integer> rating;

    private int globalRating;

    private boolean isBusy;
}
