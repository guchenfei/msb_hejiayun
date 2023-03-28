package com.example.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private String name;
    private String avatar = "/avatar2.jpg";
    private Role role;
}
