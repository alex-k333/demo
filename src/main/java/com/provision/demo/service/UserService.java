package com.provision.demo.service;

import com.provision.demo.mapper.UserMapper;
import com.provision.demo.model.dto.UserDto;

import com.provision.demo.model.dto.UserResponseDto;
import com.provision.demo.model.entity.Token;
import com.provision.demo.model.entity.User;
import com.provision.demo.repository.TokenRepository;
import com.provision.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
public class UserService extends CrudService <UserDto, User, UserResponseDto, Long>{

    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Override
    public UserResponseDto create(UserDto dto) {
        User user = mapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toResponse(repository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserDto dto) {
        User entity = getRepository().findById(id)
                .orElseThrow(EntityNotFoundException::new);
        User updatedEntity = getMapper().toEntity(dto);
        updatedEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        updatedEntity.setId(entity.getId());
        getRepository().save(updatedEntity);
        return getMapper().toResponse(updatedEntity);
    }

    public UserResponseDto findUserByToken(String token) {
        return tokenRepository.findByToken(token)
                .map(Token::getUser)
                .map(mapper::toResponse)
                .orElse(null);
    }
}
