package com.project.ssback.controllers;

import com.project.ssback.dto.RegionResponseDto;
import com.project.ssback.entities.Region;
import com.project.ssback.services.IRegionService;
import com.project.ssback.utilities.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {
    private final IRegionService regionService;
    private final CustomMapper mapper;

    @GetMapping("/regions")
    public ResponseEntity<List<RegionResponseDto>> getAllRegions(){
        List<Region> regions = regionService.findAll();
        List<RegionResponseDto> customersDto = regions.stream().map(mapper::toRegionDto).collect(toList());
        return customersDto.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(customersDto);
    }

    @GetMapping("/regions/{idRegion}")
    public ResponseEntity<RegionResponseDto> getRegion(@PathVariable("idRegion") Long idRegion){
        Region region = regionService.findById(idRegion);
        return ResponseEntity.ok().body(mapper.toRegionDto(region));
    }

}
