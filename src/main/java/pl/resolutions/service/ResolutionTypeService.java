package pl.resolutions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.resolutions.entity.Resolution;
import pl.resolutions.repository.ResolutionRepository;

import java.util.List;

@Service
@Transactional
public class ResolutionTypeService {

    private ResolutionRepository resolutionRepository;

    @Autowired
    public ResolutionTypeService(ResolutionRepository resolutionRepository) {
        this.resolutionRepository = resolutionRepository;
    }

    public List<Resolution> getAllResolutionTypes(){
        return resolutionRepository.findAll();
    }

    public void deleteResolutionType(Long id){
        resolutionRepository.delete(id);
    }

    public Resolution getResolutionById(Long id){
        return resolutionRepository.findOne(id);
    }

    public void saveResolutionType(Resolution resolution){
        resolution.setUnitMin(1);
        resolutionRepository.save(resolution);
    }


}
