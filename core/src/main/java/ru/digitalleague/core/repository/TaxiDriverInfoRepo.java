package ru.digitalleague.core.repository;

import ru.digitalleague.core.model.TaxiDriverInfoModel;

import java.util.List;

public interface TaxiDriverInfoRepo {
    void createDriverInfo(TaxiDriverInfoModel taxiDriverInfoModel);

    List<TaxiDriverInfoModel> getAllDrivers();

    void updateDriverInfo(TaxiDriverInfoModel taxiDriverInfoModel);

    void deleteDriver(TaxiDriverInfoModel taxiDriverInfoModel);
}
