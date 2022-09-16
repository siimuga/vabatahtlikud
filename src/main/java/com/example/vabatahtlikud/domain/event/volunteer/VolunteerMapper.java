package com.example.vabatahtlikud.domain.event.volunteer;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VolunteerMapper {


    Volunteer volunteerRequestToVolunteer(VolunteerRequest volunteerRequest);

    VolunteerRequest volunteerToVolunteerRequest(Volunteer volunteer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Volunteer updateVolunteerFromVolunteerRequest(VolunteerRequest volunteerRequest, @MappingTarget Volunteer volunteer);
}
