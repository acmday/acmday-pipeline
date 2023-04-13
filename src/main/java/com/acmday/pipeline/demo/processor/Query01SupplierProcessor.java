package com.acmday.pipeline.demo.processor;

import com.acmday.pipeline.demo.annotation.Processor;
import com.acmday.pipeline.demo.pipeline.base.Pipeline;
import com.acmday.pipeline.demo.pipeline.request.EventRequest;
import com.acmday.pipeline.demo.pipeline.request.PipelineRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @Author acmday
 * @Date 2023/4/13 7:55 下午
 *
 * 查询节点
 */
@Slf4j
@Processor(groupName = "acmday", order = 1)
public class Query01SupplierProcessor implements Pipeline<EventRequest> {


    @Override
    public boolean invoke(PipelineRequest<EventRequest> request, Map<String, Object> context) {
        log.info("构建清单索引 Query01ListDetailProcessor--入参请求 request: {}", request.getRequest());
        try {
            Long baseId = request.getRequest().getBaseId();
            context.put("time", new Date());
            log.info("Query01SupplierProcessor#invoke baseId={}", baseId);
            if(Objects.isNull(baseId)) {
                return false;
            }
        } catch (Exception e) {
            log.error("Query01SupplierProcessor#invoke, context={}", new Gson().toJson(context));
            return false;
        }
        return true;
    }

    @Override
    public boolean preProcess(PipelineRequest<EventRequest> request, Map<String, Object> context) {
        return true;
    }
}
