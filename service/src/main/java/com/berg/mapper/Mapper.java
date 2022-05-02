package com.berg.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
