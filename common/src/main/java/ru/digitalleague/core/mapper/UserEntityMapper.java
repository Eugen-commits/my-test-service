package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.UserEntity;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    Optional<UserEntity> findByLogin(String login);

    List<UserEntity> getAllUsers();
   /* @SelectProvider(value = SqlProvider.class, method = "test")
    UserEntity findTestSqlInjection(String sql);*/
}
