package com.acmday.pipeline.demo.pipeline.request;

import lombok.Builder;
import lombok.Data;

/**
 * @Author acmday
 * @Date 2023/4/13 7:55 下午
 */

@Data
@Builder
public class EventRequest {
    /**
     * 各表的主键id
     */
    private Long baseId;

    /**
     * 自定义业务唯一key
     */
    private String uniqueKey;

    /**
     * 各表的表名
     */
    private String tableName;
    /**
     * 自定义字段 用于成本价sku标签变更，默认false
     */
    private boolean isUpdateSkuTag = false;
}





