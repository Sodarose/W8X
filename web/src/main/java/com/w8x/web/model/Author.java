package com.w8x.web.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Author {
    private String name;
    private String email;
    private String username;
}