package pl.resolutions.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.resolutions.entity.Resolution;
import pl.resolutions.repository.ResolutionRepository;

public class ResolutionConverter implements Converter<String, Resolution> {


    @Autowired
    private ResolutionRepository resolutionRepository;


    @Override
    public Resolution convert(String s) {
        return resolutionRepository.findOne(Long.parseLong(s));
    }
}
