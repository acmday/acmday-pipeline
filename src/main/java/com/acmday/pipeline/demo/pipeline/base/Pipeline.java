package com.acmday.pipeline.demo.pipeline.base;

import com.acmday.pipeline.demo.pipeline.request.PipelineRequest;

import java.util.Map;


/**
 * Pipeline通用接口
 */
public interface Pipeline<Req> {

    /**
     * <p>方法描述:管道业务调用方法<br/>
     * 创建时间：2021/9/9 11:41<br/>
     * 方法名： invoke<br/>
     * </p>
     *
     * @param request Pipeline请求
     * @param context Pipeline上下文，传递内容载体
     * @return boolean
     */
    boolean invoke(PipelineRequest<Req> request, Map<String, Object> context);

    /**
     * <p>方法描述:管道业务 预处理方法，用户业务组装参数，业务校验等场景<br/>
     * 创建时间：2021/9/9 11:41<br/>
     * 方法名： preProcess<br/>
     * </p>
     *
     * @param request Pipeline请求
     * @param context Pipeline上下文，传递内容载体
     * @return boolean
     */
    boolean preProcess(PipelineRequest<Req> request, Map<String, Object> context);

}
