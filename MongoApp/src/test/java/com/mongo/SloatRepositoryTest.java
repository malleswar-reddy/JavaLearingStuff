package com.mongo;

import com.mongo.model.Sloat;
import com.mongo.repository.CustomItemRepository;
import com.mongo.repository.SloatRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@EnableMongoRepositories
@Slf4j
public class SloatRepositoryTest {

    private final static List< Integer > USER_ID_LIST = Arrays.asList(8, 4);
    private static final Random RANDOM = new Random();
    @MockBean
    private SloatRepository sloatRepository;

    @MockBean
    private CustomItemRepository customItemRepository;

    @BeforeEach
    public void dataSetup() {
        Sloat sloat;
        sloat = new Sloat(4L, "Slot 4");

        sloatRepository.save(sloat);
        sloat = new Sloat(6L, "Slot 6");

        sloatRepository.save(sloat);
    }


    @Test
    public void test() {

        Long startTime = System.currentTimeMillis();

        sloatRepository.findAll().forEach(System.out::println);

       // Long endTeime = System.currentTimeMillis();
        log.info("Time taken  {} " , (System.currentTimeMillis() - startTime));
    }
}
