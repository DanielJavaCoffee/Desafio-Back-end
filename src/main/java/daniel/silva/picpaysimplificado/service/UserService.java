package daniel.silva.picpaysimplificado.service;

import daniel.silva.picpaysimplificado.dtos.user.UserCreateRecord;
import daniel.silva.picpaysimplificado.entity.User;
import daniel.silva.picpaysimplificado.exceptionUser.UserUniqueViolationException;
import daniel.silva.picpaysimplificado.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserCreateRecord createUser(UserCreateRecord userCreateRecord){

        try {
            var user =  new User();
            BeanUtils.copyProperties(userCreateRecord, user);
            userRepository.save(user);
            return userCreateRecord;
        } catch (DataIntegrityViolationException exception){
            throw  new UserUniqueViolationException("User já cadastrado com CPF ou Email já informado.");
        }
    }

}
