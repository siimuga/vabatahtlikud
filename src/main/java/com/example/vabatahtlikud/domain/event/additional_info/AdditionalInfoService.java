package com.example.vabatahtlikud.domain.event.additional_info;

import com.example.vabatahtlikud.domain.event.event.EventRepository;
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

    public void getValidAdditionalInfo(AdditionalInfoInfo request) {
        boolean existsByNameAndEventIdAndStatus = additionalInfoRepository.existsByNameAndEventIdAndStatus(request.getName(), request.getEventId());
        ValidationService.validateAdditionalInfo(existsByNameAndEventIdAndStatus, request.getName());
    }

    public List<AdditionalInfo> findByStatusTrueAndEventId(AdditionalInfoInfo request) {
        return additionalInfoRepository.findByStatusTrueAndEventId(request.getEventId());
    }

    public List<AdditionalInfoResponse> addInfo(AdditionalInfoInfo request) {
        getValidAdditionalInfo(request);
        AdditionalInfo additionalInfo = additionalInfoMapper.additionalInfoInfoToAdditionalInfo(request);
        additionalInfoRepository.save(additionalInfo);
        List<AdditionalInfo> additionalInfos = findByStatusTrueAndEventId(request);
        return additionalInfoMapper.additionalInfosToAdditionalInfoResponses(additionalInfos);
    }

    public void deleteAdditionalInfo(Integer additionalInfoId) {
        Optional<AdditionalInfo> additionalInfo = additionalInfoRepository.findById(additionalInfoId);
        Boolean status = additionalInfo.get().getStatus();
        additionalInfo.get().setStatus(!status);
        additionalInfoRepository.save(additionalInfo.get());
    }

    public List<AdditionalInfoResponse> findAdditionalInfosByEvent(Integer eventId) {
        List<AdditionalInfo> additionalInfos = additionalInfoRepository.findByStatusTrueAndEventId(eventId);
        return additionalInfoMapper.additionalInfosToAdditionalInfoResponses(additionalInfos);
    }

    public List<AdditionalInfoRequest> findAdditionalInfoRequestsByEvent(Integer eventId) {
        List<AdditionalInfo> additionalInfos = additionalInfoRepository.findByStatusTrueAndEventId(eventId);
        return additionalInfoMapper.additionalInfosToAdditionalInfoRequests(additionalInfos);
    }

}
