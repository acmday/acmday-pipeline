package com.acmday.pipeline.demo;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationLoader.class)
public abstract class BaseTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

}
