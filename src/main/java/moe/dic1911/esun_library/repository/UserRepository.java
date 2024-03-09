package moe.dic1911.esun_library.repository;

import moe.dic1911.esun_library.data.UserDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<UserDto, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into users (name, password, phone_num, username) values (?1,?2,?3,?4)", nativeQuery = true)
    int addUser(String name, String password, String phone_num, String username);

    @Modifying
    @Transactional
    @Query(value = "insert into users (username, password) values (?1,?2)", nativeQuery = true)
    int addUser(String username, String password);

    UserDto findByUsername(String username);
    UserDto findByPhoneNum(String phone);
}
