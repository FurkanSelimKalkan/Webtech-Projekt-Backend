package htw.berlin.webtechprojekt.demo.service;

import htw.berlin.webtechprojekt.demo.persistence.UserEntity;
import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.web.api.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserTransformer {

    public User transformEntity(UserEntity userEntity){
        var votingsIds = userEntity.getVotings().stream().map(VotingEntity::getId).collect(Collectors.toList());
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                votingsIds);
    }
}
