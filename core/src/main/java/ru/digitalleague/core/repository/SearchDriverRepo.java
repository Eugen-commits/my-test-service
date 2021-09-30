package ru.digitalleague.core.repository;

import ru.digitalleague.core.model.SearchDriverModel;

import java.util.List;

public interface SearchDriverRepo {
    void createSearch(SearchDriverModel searchDriverModel);

    List<SearchDriverModel> getAllSearch();

    void updateSearch(SearchDriverModel searchDriverModel);

    void deleteSearch(SearchDriverModel searchDriverModel);
}
