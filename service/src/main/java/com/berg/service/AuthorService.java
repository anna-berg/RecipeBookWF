package com.berg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
//
//    private final AuthorRepository authorRepository;
//    private final AuthorCreateMapper authorCreateMapper;
//    private final AuthorReadMapper authorReadMapper;
//
//    public Long create(AuthorCreateDto authorDto) {
//        var authorEntity = authorCreateMapper.mapFrom(authorDto);
//        return authorRepository.save(authorEntity).getId();
//    }
//
//    public Optional<AuthorReadDto> findById(Long id) {
//        return authorRepository.findById(id)
//                .map(authorReadMapper::mapFrom);
//    }
//
//    public boolean delete(Long id) {
//        var maybeAuthor = authorRepository.findById(id);
//        maybeAuthor.ifPresent(authorRepository::delete);
//        return maybeAuthor.isPresent();
//    }
//
//    public List<AuthorReadDto> findAll() {
//        return authorRepository.findAll().stream()
//                .map(authorReadMapper::mapFrom)
//                .collect(toList());
//    }
}
