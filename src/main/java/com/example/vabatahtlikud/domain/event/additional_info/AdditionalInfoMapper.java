package com.example.vabatahtlikud.domain.event.additional_info;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(constant = "true", target = "status")
    AdditionalInfo additionalInfoRequestToAdditionalInfo(AdditionalInfoRequest additionalInfoRequest);

    AdditionalInfoResponse additionalInfoToAdditionalInfoResponse(AdditionalInfo additionalInfo);

    List<AdditionalInfoResponse> additionalInfosToAdditionalInfoResponses(List<AdditionalInfo> additionalInfos);

@Mapping(source = "id", target = "addInfoId")
@Mapping(source = "event.id", target = "eventId")
    AdditionalInfoRequest additionalInfoToAdditionalInfoRequest(AdditionalInfo additionalInfo);
    List<AdditionalInfoRequest> additionalInfosToAdditionalInfoRequests(List<AdditionalInfo> additionalInfos);

    @Mapping(constant = "true", target = "status")
    @Mapping(source = "eventId", target = "event.id")
    AdditionalInfo additionalInfoInfoToAdditionalInfo(AdditionalInfoInfo request);


//    @Mapping(source = "eventRegisterId", target = "eventRegister.id")
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    AdditionalInfo updateAdditionalInfoFromAdditionalInfoRequest(AdditionalInfoRequest additionalInfoRequest, @MappingTarget AdditionalInfo additionalInfo);
}
