package com.project.ssback.services;

import com.project.ssback.entities.Region;

import java.util.List;

public interface IRegionService {
    public List<Region> findAll();
    public Region findById(Long idRegion);
}
