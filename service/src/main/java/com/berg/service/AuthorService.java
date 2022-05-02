package com.berg.service;

import com.berg.dao.AuthorRepository;
import com.berg.dto.AuthorCreateDto;
import com.berg.dto.AuthorReadDto;
import com.berg.entity.Author;
import com.berg.mapper.AuthorCreateMapper;
import com.berg.mapper.AuthorReadMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorCreateMapper authorCreateMapper;
    private final AuthorReadMapper authorReadMapper;

    public Long create (AuthorCreateDto authorDto){
        var authorEntity = authorCreateMapper.mapFrom(authorDto);
        return authorRepository.save(authorEntity).getId();
    }

    public Optional <AuthorReadDto> findById(Long id){
        return authorRepository.findById(id)
                .map(authorReadMapper::mapFrom);
    }

    public boolean delete(Long id){
        var maybeAuthor = authorRepository.findById(id);
        maybeAuthor.ifPresent(author -> authorRepository.delete(id));
        return maybeAuthor.isPresent();
    }

    public void update(Author author){
        authorRepository.update(author);
    }

    public List<AuthorReadDto> findAll(){
        return authorRepository.findAll().stream()
                .map(authorReadMapper::mapFrom)
                .collect(toList());
    }
}
