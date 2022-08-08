package com.management.fresher.mapper;

import com.management.fresher.dto.FresherDto;
import com.management.fresher.entity.Fresher;
import org.springframework.stereotype.Component;

@Component
public class FresherMapper implements Mapper<FresherDto, Fresher>{
    @Override
    public Fresher DtoToEntity(FresherDto fresherDto) {
        Fresher res = new Fresher();
        res.setId(fresherDto.getId());
        res.setEmail(fresherDto.getEmail());
        res.setFinalScore(fresherDto.getFinalScore());
        res.setName(fresherDto.getName());
        res.setProgramLanguage(fresherDto.getProgramLanguage());
        res.setTestScore1(fresherDto.getTestScore1());
        res.setTestScore2(fresherDto.getTestScore2());
        res.setTestScore3(fresherDto.getTestScore3());
        return res;
    }

    @Override
    public FresherDto EntityToDto(Fresher fresher) {
        FresherDto res = new FresherDto();
        res.setId(fresher.getId());
        res.setEmail(fresher.getEmail());
        res.setFinalScore(fresher.getFinalScore());
        res.setName(fresher.getName());
        res.setProgramLanguage(fresher.getProgramLanguage());
        res.setTestScore1(fresher.getTestScore1());
        res.setTestScore2(fresher.getTestScore2());
        res.setTestScore3(fresher.getTestScore3());
        return res;
    }
}
