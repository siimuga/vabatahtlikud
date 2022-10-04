package com.example.vabatahtlikud.domain.event.language;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LanguageMapper {
    LanguageInfo languageToLanguageInfo(Language language);

    List<LanguageInfo> languagesToLanguageInfos(List<Language> languages);
}