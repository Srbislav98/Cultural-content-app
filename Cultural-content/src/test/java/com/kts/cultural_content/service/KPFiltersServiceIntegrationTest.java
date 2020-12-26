package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        List<KulturnaPonuda> found = kulturnaPonudaService.filterByLocation("Novi Sad");
        assertEquals(FIND_ALL_FILTERED_BY_LOCATION_NUMBER_OF_ITEMS, found.size());
    }



}
