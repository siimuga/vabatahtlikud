package com.example.vabatahtlikud.domain.event.additional_info;

import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdditionalInfoService {

    @Resource
    private AdditionalInfoRepository additionalInfoRepository;

    @Resource
    private AdditionalInfoMapper additionalInfoMapper;

    public void getValidAdditionalInfo(AdditionalInfoRequest request) {
        boolean existsByNameAndEventRegisterIdAndStatus = additionalInfoRepository.existsByNameAndEventRegisterIdAndStatus(request.getName(), request.getEventRegisterId());
//        boolean existsByName = additionalInfoRepository.existsByName(request.getName());
//        boolean existsByEventRegisterIdAndStatusTrue = additionalInfoRepository.existsByEventRegisterIdAndStatusTrue(request.getEventRegisterId());
        ValidationService.validateAdditionalInfoExists(existsByNameAndEventRegisterIdAndStatus);
    }

    public List<AdditionalInfo> findByStatusTrueAndEventRegisterId(AdditionalInfoRequest request) {
        return additionalInfoRepository.findByStatusTrueAndEventRegisterId(request.getEventRegisterId());
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoRequest request) {
        getValidAdditionalInfo(request);
        AdditionalInfo additionalInfo = additionalInfoMapper.additionalInfoRequestToAdditionalInfo(request);
        additionalInfoRepository.save(additionalInfo);
        List<AdditionalInfo> additionalInfos = findByStatusTrueAndEventRegisterId(request);
        return additionalInfoMapper.additionalInfosToAdditionalInfoResponses(additionalInfos);
    }
}
