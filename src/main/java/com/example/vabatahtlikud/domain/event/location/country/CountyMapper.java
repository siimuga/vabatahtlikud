package com.example.vabatahtlikud.domain.event.location.country;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CountyMapper {
    County countyInfoToCounty(CountyInfo countyInfo);

    @Mapping(source = "id", target = "countyId")
    CountyInfo countyToCountyInfo(County county);


    List<CountyInfo> countiesToCountyInfos(List<County> counties);
}
