package com.acmday.pipeline.demo.pipeline.base;


import com.acmday.pipeline.demo.pipeline.request.PipelineRequest;
import com.acmday.pipeline.demo.pipeline.response.PipelineResponse;

/**
 * @Author acmday
 * @Date 2023/4/13 7:55 下午
 */
public interface AbstractPipelineManager {

    <Req,Resp> PipelineResponse<Resp> execute(PipelineRequest<Req> pipelineRequest);
}
