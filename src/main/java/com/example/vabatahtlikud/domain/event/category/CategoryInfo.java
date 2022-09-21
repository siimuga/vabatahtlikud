package com.example.vabatahtlikud.domain.event.category;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryInfo implements Serializable {
    private String name;
    private Integer categoryId;
}
