package com.lucasmoraist.balancea.service;

import com.lucasmoraist.balancea.domain.dto.DataCreateUser;
import com.lucasmoraist.balancea.domain.dto.DataDetailsUser;
import com.lucasmoraist.balancea.domain.dto.DataLoginUser;
import com.lucasmoraist.balancea.domain.dto.TokenDTO;

public interface UserService {
    DataDetailsUser register(DataCreateUser data);
    TokenDTO login(DataLoginUser data);
}
