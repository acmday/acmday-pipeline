package com.acmday.pipeline.demo.executor;

import com.acmday.pipeline.demo.annotation.Processor;
import com.acmday.pipeline.demo.pipeline.base.AbstractPipelineManager;
import com.acmday.pipeline.demo.pipeline.base.Pipeline;
import com.acmday.pipeline.demo.pipeline.request.PipelineRequest;
import com.acmday.pipeline.demo.pipeline.response.PipelineResponse;
import com.acmday.pipeline.demo.pipeline.response.ResponseEnum;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author acmday
 * @Date 2023/4/13 2:23 下午
 */
@Service
public class DefaultPipelineManager implements AbstractPipelineManager, InitializingBean {

    private Map<String, List<Pipeline>> processors = null;

    @Resource
    private List<Pipeline> pipelines;

    private static final RateLimiter rateLimiter = RateLimiter.create(80);

    @Override
    public <Req, Resp> PipelineResponse<Resp> execute(PipelineRequest<Req> pipelineRequest) {
        rateLimiter.acquire();
        //校验参数
        PipelineResponse<Resp> pipelineResponse = checkParamters(pipelineRequest);
        if (pipelineResponse.getCode() != ResponseEnum.SUCCESS.getCode()) {
            return pipelineResponse;
        }
        //执行流程
        List<Pipeline> pipelineList = processors.get(pipelineRequest.getBizCode());
        // 上下文
        Map<String, Object> context = new HashMap<>();
        //遍历执行
        for (Pipeline pipeline : pipelineList) {
            //执行预处理 业务调用前的前置校验或参数业务组装
            boolean preProcess = pipeline.preProcess(pipelineRequest, context);
            if (!preProcess) {
                Object invokeResult = context.get(PipelineResponse.class.getName());
                if (invokeResult == null) {
                    // 业务没有指定 按照 预处理校验失败处理 上层业务 认为处理完毕即可
                    context.put(PipelineResponse.class.getName(), PipelineResponse.success(true));
                }
                break;
            }
            boolean invoke = pipeline.invoke(pipelineRequest, context);
            if (!invoke) {
                Object invokeResult = context.get(PipelineResponse.class.getSimpleName());
                if (invokeResult == null) {
                    context.put(PipelineResponse.class.getSimpleName(),
                        PipelineResponse.fail(ResponseEnum.PIPELINE_ERROR));
                }
                break;
            }
        }
        //返回结果
        return (PipelineResponse<Resp>) context.get(PipelineResponse.class.getSimpleName());
    }

    /**
     * 校验pipeline请求参数
     */
    private <Req, Resp> PipelineResponse<Resp> checkParamters(PipelineRequest<Req> pipelineRequest) {
        if (pipelineRequest == null || pipelineRequest.getBizCode() == null
            || pipelineRequest.getRequest() == null) {
            return PipelineResponse.fail(ResponseEnum.PARAM_ERROR);
        }
        List<Pipeline> pipelineList = processors.get(pipelineRequest.getBizCode());
        if (CollectionUtils.isEmpty(pipelineList)) {
            return PipelineResponse.fail(ResponseEnum.PARAM_ERROR);
        }
        return PipelineResponse.success(null);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        processors = pipelines.stream()
            .filter(t -> t.getClass().isAnnotationPresent(Processor.class))
            .collect(
                Collectors.groupingBy(
                    t -> t.getClass().getAnnotation(Processor.class).groupName(),
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            list.sort((pipeline1, pipeline2) -> {
                                int order1 = pipeline1.getClass().getAnnotation(Processor.class).order();
                                int order2 = pipeline2.getClass().getAnnotation(Processor.class).order();
                                return Integer.compare(order1, order2);
                            });
                            return list;
                        })
                )
            );
    }
}