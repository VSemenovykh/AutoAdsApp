package ru.ncedu.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpiredTokenKey {

    private String userName;

    private long exp;
}
