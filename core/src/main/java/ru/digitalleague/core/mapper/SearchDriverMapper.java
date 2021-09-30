package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.SearchDriverModel;

import java.util.List;

@Mapper
@Repository
public interface SearchDriverMapper {
    List <SearchDriverModel> getAll();

    void createSearch(SearchDriverModel searchDriverModel);

    void deleteSearch (SearchDriverModel searchDriverModel);

    void updateSearch (SearchDriverModel searchDriverModel);
}
