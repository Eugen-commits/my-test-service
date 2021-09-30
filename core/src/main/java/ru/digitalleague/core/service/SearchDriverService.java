package ru.digitalleague.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.mapper.SearchDriverMapper;
import ru.digitalleague.core.model.SearchDriverModel;
import ru.digitalleague.core.repository.SearchDriverRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchDriverService implements SearchDriverRepo {
    @Autowired
    private SearchDriverMapper searchDriverMapper;

    @Override
    public void createSearch(SearchDriverModel searchDriverModel) {
        searchDriverMapper.createSearch(searchDriverModel);
    }

    @Override
    public List<SearchDriverModel> getAllSearch() {
        List <SearchDriverModel> allSearchDriver = new ArrayList<>();
        searchDriverMapper.getAll().iterator().forEachRemaining(allSearchDriver::add);
        return allSearchDriver;
    }

    @Override
    public void updateSearch(SearchDriverModel searchDriverModel) {
        searchDriverMapper.updateSearch(searchDriverModel);
    }

    @Override
    public void deleteSearch(SearchDriverModel searchDriverModel) {
        searchDriverMapper.deleteSearch(searchDriverModel);
    }
}
