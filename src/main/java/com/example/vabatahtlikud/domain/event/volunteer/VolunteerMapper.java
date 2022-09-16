package com.example.vabatahtlikud.domain.event.volunteer;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VolunteerMapper {

@Mapping(source = "userId", target = "user.id")
@Mapping(source = "eventId", target = "event.id")
    Volunteer volunteerRequestToVolunteer(VolunteerRequest volunteerRequest);

    VolunteerRequest volunteerToVolunteerRequest(Volunteer volunteer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Volunteer updateVolunteerFromVolunteerRequest(VolunteerRequest volunteerRequest, @MappingTarget Volunteer volunteer);
}
