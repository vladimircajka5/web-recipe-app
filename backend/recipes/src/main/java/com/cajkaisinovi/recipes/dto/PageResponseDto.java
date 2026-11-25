package com.cajkaisinovi.recipes.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageResponseDto<T> {
	private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    
    
    public PageResponseDto(Page<T> result) {
        this.content = result.getContent();
        this.page = result.getNumber();
        this.size = result.getSize();
        this.totalElements = result.getTotalElements();
        this.totalPages = result.getTotalPages();
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
