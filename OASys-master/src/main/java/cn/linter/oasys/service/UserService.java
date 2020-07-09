package cn.linter.oasys.service;

import cn.linter.oasys.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUserById(int id);

    PageInfo<?> getUsers(int pageNumber, int pageSize,String username,String email);

    int updateUser(User user);

    int addUser(User user);

    void deleteUser(Integer[] ids);

}
