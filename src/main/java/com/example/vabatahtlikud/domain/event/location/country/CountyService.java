package com.example.vabatahtlikud.domain.event.location.country;

import com.example.vabatahtlikud.domain.event.category.CategoryInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CountyService {

    @Resource
    private CountyRepository countyRepository;

    @Resource
    private CountyMapper countyMapper;
    public List<CountyInfo> findAllCounties() {
        List<County> counties = countyRepository.findAll();
        return countyMapper.countiesToCountyInfos(counties);
    }
}
