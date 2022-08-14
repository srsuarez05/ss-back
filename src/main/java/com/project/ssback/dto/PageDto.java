package com.project.ssback.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto<T> {
    private List<T> content;
    private Integer itemsPerPage;
    private Long totalElements;
    private Integer numberOfElements;
    private Integer totalPages;
    private Integer nextPage;
    private Integer previousPage;

}
