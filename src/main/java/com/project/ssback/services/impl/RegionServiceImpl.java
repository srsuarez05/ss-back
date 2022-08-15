package com.project.ssback.services.impl;

import com.project.ssback.entities.Region;
import com.project.ssback.exceptions.ResourceNotFoundException;
import com.project.ssback.repositories.RegionRepository;
import com.project.ssback.services.IRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.project.ssback.utilities.Constants.REGION;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements IRegionService {

    private final RegionRepository regionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Region findById(Long idRegion) {
        return regionRepository.findById(idRegion).orElseThrow(
                () -> new ResourceNotFoundException(REGION, idRegion));
    }
}
