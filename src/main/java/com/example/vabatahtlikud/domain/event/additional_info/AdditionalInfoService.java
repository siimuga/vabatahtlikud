package com.example.vabatahtlikud.domain.event.additional_info;

import com.example.vabatahtlikud.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class AdditionalInfoService {

    @Resource
    private AdditionalInfoRepository additionalInfoRepository;

    @Resource
    private AdditionalInfoMapper additionalInfoMapper;

    public void getValidAdditionalInfo(AdditionalInfoRequest request) {
        boolean existsByNameAndEventIdAndStatus = additionalInfoRepository.existsByNameAndEventIdAndStatus(request.getName(), request.getEventId());
        ValidationService.validateAdditionalInfoExists(existsByNameAndEventIdAndStatus);
    }

    public List<AdditionalInfo> findByStatusTrueAndEventRegisterId(AdditionalInfoRequest request) {
        return additionalInfoRepository.findByStatusTrueAndEventId(request.getEventId());
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoRequest request) {
        getValidAdditionalInfo(request);
        AdditionalInfo additionalInfo = additionalInfoMapper.additionalInfoRequestToAdditionalInfo(request);
        additionalInfoRepository.save(additionalInfo);
        List<AdditionalInfo> additionalInfos = findByStatusTrueAndEventRegisterId(request);
        return additionalInfoMapper.additionalInfosToAdditionalInfoResponses(additionalInfos);
    }

    public void deleteAdditionalInfo(Integer additionalInfoId) {
        Optional<AdditionalInfo> additionalInfo = additionalInfoRepository.findById(additionalInfoId);
        Boolean status = additionalInfo.get().getStatus();
        additionalInfo.get().setStatus(!status);
        additionalInfoRepository.save(additionalInfo.get());
    }
}
