package com.example.vabatahtlikud.domain.event.volunteer.volunteer_event_date;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VolunteerEventDateMapper {
    @Mapping(source = "volunteerId", target = "volunteer.id")
    @Mapping(source = "eventDateId", target = "eventDate.id")
    VolunteerEventDate volunteerEventDateInfoToVolunteerEventDate(VolunteerEventDateInfo volunteerEventDateInfo);

    List<VolunteerEventDate> volunteerEventDateInfosToVolunteerEventDates(List<VolunteerEventDateInfo> volunteerEventDateInfos);

    @InheritInverseConfiguration(name = "volunteerEventDateInfoToVolunteerEventDate")
    VolunteerEventDateInfo volunteerEventDateToVolunteerEventDateInfo(VolunteerEventDate volunteerEventDate);

    @InheritConfiguration(name = "volunteerEventDateInfoToVolunteerEventDate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VolunteerEventDate updateVolunteerEventDateFromVolunteerEventDateInfo(VolunteerEventDateInfo volunteerEventDateInfo, @MappingTarget VolunteerEventDate volunteerEventDate);


}
