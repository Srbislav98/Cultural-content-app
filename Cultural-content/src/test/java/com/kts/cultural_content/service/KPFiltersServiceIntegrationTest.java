package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static com.kts.cultural_content.constants.KPFiltersConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KPFiltersServiceIntegrationTest {

    @Autowired
    private KulturnaPonudaService kulturnaPonudaService;

    @Test
    public void testFilterByContent() {
        List<KulturnaPonuda> found = kulturnaPonudaService.filterByContent("g");
        assertEquals(FIND_ALL_FILTERED_BY_CONTENT_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFilterByLocation() {
        List<KulturnaPonuda> found = kulturnaPonudaService.filterByLocation(DB_LOCATION_NAME);
        assertEquals(FIND_ALL_FILTERED_BY_LOCATION_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFilterByLocationPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_0, PAGEABLE_SIZE);
        Page<KulturnaPonuda> found = kulturnaPonudaService.filterByLocationPage(pageable, DB_LOCATION_NAME);
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_0, found.getNumberOfElements());
    }

    @Test
    public void testFilterByLocationPageable2(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_1, PAGEABLE_SIZE);
        Page<KulturnaPonuda> found = kulturnaPonudaService.filterByLocationPage(pageable, DB_LOCATION_NAME);
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_1, found.getNumberOfElements());
    }

    @Test
    public void testFilterByLocationPageableFail(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_2, PAGEABLE_SIZE);
        Page<KulturnaPonuda> found = kulturnaPonudaService.filterByLocationPage(pageable, DB_LOCATION_NAME);
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_2,  found.getNumberOfElements());
    }

    @Test
    public void testFilterByContentPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_1, PAGEABLE_SIZE);
        Page<KulturnaPonuda> found = kulturnaPonudaService.filterByContentPage(pageable, DB_CONTENT_FILTER);
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_1, found.getNumberOfElements());
    }

    @Test
    public void testFilterByContentPageable2(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_0, PAGEABLE_SIZE);
        Page<KulturnaPonuda> found = kulturnaPonudaService.filterByContentPage(pageable, DB_CONTENT_FILTER);
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_0, found.getNumberOfElements());
    }

    @Test
    public void testFilterByContentPageableFail(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_2, PAGEABLE_SIZE);
        Page<KulturnaPonuda> found = kulturnaPonudaService.filterByContentPage(pageable, DB_CONTENT_FILTER);
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_2, found.getNumberOfElements());
    }

}
