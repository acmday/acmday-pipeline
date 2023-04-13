package com.acmday.pipeline.demo;

import com.acmday.pipeline.demo.executor.DefaultPipelineManager;
import com.acmday.pipeline.demo.pipeline.request.EventRequest;
import com.acmday.pipeline.demo.pipeline.request.PipelineRequest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Author wushaofeng
 * @Date 2023/4/13 7:59 下午
 */
public class ProcessorTest extends BaseTest {

    @Resource
    private DefaultPipelineManager defaultPipelineManager;

    @Test
    public void process() {
        EventRequest request = EventRequest.builder()
            .tableName("supplier")
            .baseId(10010L).build();
        PipelineRequest<EventRequest> processorRequest = new PipelineRequest<>("acmday", request);
        defaultPipelineManager.execute(processorRequest);
    }
}
