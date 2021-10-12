package ru.digitalleague.core.model;

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

    private List<Integer> rating;

    private boolean isBusy;
}
