package com.example.vabatahtlikud.domain.event.language;

import lombok.Data;

import java.io.Serializable;

@Data
public class LanguageInfo implements Serializable {
    private final String name;
}
