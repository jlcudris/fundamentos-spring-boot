package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepositiry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepositiry userRepositiry;

    public UserService(UserRepositiry userRepositiry) {
        this.userRepositiry = userRepositiry;
    }
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
             .peek(user->LOG.info("Usuario insertado "+user ))
                .forEach(userRepositiry::save);
    }

    public List<User>  getAllUsers(){

       return userRepositiry.findAll();
    }

    public User save(User newUser) {
        return userRepositiry.save(newUser);
    }

    public void delete(Long id) {
        userRepositiry.deleteById(id);
    }

    public User update(Long id, User userUpdate) {
       return userRepositiry.findById(id)
                .map(
                        user->{
                            user.setEmail(userUpdate.getEmail());
                            user.setBirthDate(userUpdate.getBirthDate());
                            user.setName(userUpdate.getName());
                            return userRepositiry.save(user);
                        }
                ).orElse(null);
    }

    public User getFindByIDd(Long id) {
        return userRepositiry.findById(id).get();
    }

    public List<User> getAllUsersPaginate(int page, int size) {
        return userRepositiry.findAll(PageRequest.of(page,size)).getContent();
    }
}
