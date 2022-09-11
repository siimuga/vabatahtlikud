package com.example.vabatahtlikud.domain.event.additional_info;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(source = "eventRegisterId", target = "eventRegister.id")
    AdditionalInfo additionalInfoRequestToAdditionalInfo(AdditionalInfoRequest additionalInfoRequest);

    @Mapping(source = "eventRegister.id", target = "eventRegisterId")
    AdditionalInfoRequest additionalInfoToAdditionalInfoRequest(AdditionalInfo additionalInfo);



    AdditionalInfoResponse additionalInfoToAdditionalInfoResponse(AdditionalInfo additionalInfo);


    List<AdditionalInfoResponse> additionalInfosToAdditionalInfoResponses(List<AdditionalInfo> additionalInfos);

    @Mapping(source = "eventRegisterId", target = "eventRegister.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdditionalInfo updateAdditionalInfoFromAdditionalInfoRequest(AdditionalInfoRequest additionalInfoRequest, @MappingTarget AdditionalInfo additionalInfo);
}
