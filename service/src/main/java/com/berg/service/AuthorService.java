package com.berg.service;

import com.berg.dto.AuthorCreateEditDto;
import com.berg.dto.AuthorReadDto;
import com.berg.mapper.AuthorCreateEditDtoToAuthorMapper;
import com.berg.mapper.AuthorToReadDtoMapper;
import com.berg.repositary.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorCreateEditDtoToAuthorMapper authorCreateEditDtoToAuthorMapper;
    private final AuthorToReadDtoMapper authorToReadDtoMapper;

    @Transactional
    public Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto authorDto){
        return authorRepository.findById(id)
                .map(entity -> authorCreateEditDtoToAuthorMapper.map(authorDto, entity))
                .map(authorRepository::saveAndFlush)
                .map(authorToReadDtoMapper::map);
    }

    @Transactional
    public AuthorReadDto create(AuthorCreateEditDto authorDto) {
        return Optional.of(authorDto)
                .map(authorCreateEditDtoToAuthorMapper::map)
                .map(authorRepository::save)
                .map(authorToReadDtoMapper::map)
                .orElseThrow();
    }

    public Optional<AuthorReadDto> findById(Long id) {
        return authorRepository.findById(id)
                .map(authorToReadDtoMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    authorRepository.delete(author);
                    authorRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public List<AuthorReadDto> findAll() {
        return authorRepository.findAll().stream()
                .map(authorToReadDtoMapper::map)
                .toList();
    }
}
