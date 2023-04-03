package com.casestudy.mapper;

import com.casestudy.dto.CandidateDto;
import com.casestudy.dto.CandidateWithInteractionDto;
import com.casestudy.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    Candidate convertCandidateWithInteractionDtoToCandidate(CandidateWithInteractionDto candidateWithInteractionDto);

    Candidate convertCandidateDtoToCandidate(CandidateDto candidateDto);
}
