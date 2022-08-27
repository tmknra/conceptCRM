package com.example.catalogue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
        (classes = {
        ConceptCRMApplication.class,
        H2TestProfileJPAConfig.class})
class ConceptCRMApplicationTests {

    // @Test
    // void contextLoads() {
    // }

}
