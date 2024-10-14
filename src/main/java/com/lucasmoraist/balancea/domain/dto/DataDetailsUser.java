package com.lucasmoraist.balancea.domain.dto;

import com.lucasmoraist.balancea.domain.entity.User;

public record DataDetailsUser(
        String name,
        String email
) {
    public DataDetailsUser(User user) {
        this(user.getName(), user.getEmail());
    }
}
