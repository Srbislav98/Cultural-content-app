package com.kts.cultural_content.suites;

import com.kts.cultural_content.controller.*;
import com.kts.cultural_content.repository.*;
import com.kts.cultural_content.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.TestPropertySource;

@RunWith(Suite.class)
@Suite.SuiteClasses({KPFiltersControllerIntegrationTest.class, LokacijaControllerIntegrationTest.class, NovostControllerIntegrationTest.class, KPFilterRepositoryIntegrationTest.class, LokacijaRepositoryIntegrationTest.class, NovostRepositoryIntegrationTest.class, KPFiltersServiceIntegrationTest.class, LokacijaServiceIntegrationTest.class, NovostServiceIntegrationTest.class})
@TestPropertySource("classpath:test.properties")
public class SuiteAllAboutNovostiLokacijaIKPFilter {
}
