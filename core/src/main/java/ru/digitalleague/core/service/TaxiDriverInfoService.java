package ru.digitalleague.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.mapper.TaxiInfoMapper;
import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.core.repository.TaxiDriverInfoRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxiDriverInfoService implements TaxiDriverInfoRepo {

    @Autowired
    private TaxiInfoMapper mapper;


    @Override
    public void createDriverInfo(TaxiDriverInfoModel taxiDriverInfoModel) {
        mapper.createDriver(taxiDriverInfoModel);
    }

    @Override
    public List<TaxiDriverInfoModel> getAllDrivers() {
        List<TaxiDriverInfoModel> allDrivers = new ArrayList<>();
        mapper.getAllDrivers().iterator().forEachRemaining(allDrivers::add);
        return allDrivers;
    }

    @Override
    public void updateDriverInfo(TaxiDriverInfoModel taxiDriverInfoModel) {
        mapper.updateDriver(taxiDriverInfoModel);
    }

    @Override
    public void deleteDriver(TaxiDriverInfoModel taxiDriverInfoModel) {
        mapper.deleteDriver(taxiDriverInfoModel);
    }
}
