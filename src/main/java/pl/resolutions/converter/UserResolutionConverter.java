package pl.resolutions.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.UserResolutionRepository;

public class UserResolutionConverter implements Converter<String, UserResolution> {

    @Autowired
    private UserResolutionRepository userResolutionRepository;

    @Override
    public UserResolution convert(String s) {
        return userResolutionRepository.findOne(Long.parseLong(s));
    }
}
