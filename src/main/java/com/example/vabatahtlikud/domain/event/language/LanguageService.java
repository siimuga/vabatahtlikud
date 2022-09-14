package com.example.vabatahtlikud.domain.event.language;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LanguageService {

    @Resource
    private LanguageMapper languageMapper;

    @Resource
    private LanguageRepository languageRepository;

    public List<LanguageInfo> findAllLanguages() {
        List<Language> languages = languageRepository.findAll();
        return languageMapper.languagesToLanguageInfos(languages);
    }
}
