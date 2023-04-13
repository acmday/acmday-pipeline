package com.acmday.pipeline.demo.processor;

import com.acmday.pipeline.demo.annotation.Processor;
import com.acmday.pipeline.demo.pipeline.base.Pipeline;
import com.acmday.pipeline.demo.pipeline.request.EventRequest;
import com.acmday.pipeline.demo.pipeline.request.PipelineRequest;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author acmday
 * @Date 2023/4/13 7:55 下午
 *
 * 修改节点
 */
@Slf4j
@Processor(groupName = "acmday", order = 1)
public class Set02SupplierProcessor implements Pipeline<EventRequest> {


    @Override
    public boolean invoke(PipelineRequest<EventRequest> request, Map<String, Object> context) {
        log.info("Set02SupplierProcessor#invoke context={}", new Gson().toJson(context));
        Long baseId = request.getRequest().getBaseId();
        context.put("baseId", baseId);
        return true;
    }

    @Override
    public boolean preProcess(PipelineRequest<EventRequest> request, Map<String, Object> context) {
        return true;
    }
}
