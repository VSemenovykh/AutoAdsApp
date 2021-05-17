package ru.ncedu.services;

import ru.ncedu.entity.User;

public interface VerifyUserService {

    boolean verify(String code);
}
