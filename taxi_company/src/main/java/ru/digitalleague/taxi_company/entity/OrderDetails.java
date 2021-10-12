package ru.digitalleague.taxi_company.entity;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OrderDetails {

    private Long orderId;

    /**
     * Идентификатор клиента.
     */

    private Long clientNumber;

    private Long driverId;

    private String startPointAddress;

    private String endPointAddress;

    private OffsetDateTime startTrip;

    private OffsetDateTime endTrip;

    private String city;
}
