package com.clouds3n.common.torn.autoconfigure.mybatis.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wpj
 */
@Data
@Accessors(chain = true)
@SuppressWarnings("WeakerAccess")
public class QueryConditionDto {
    /**
     * 数据库字段名
     */
    @NotBlank(message = "查询条件的数据库字段名不能为空")
    private String column;

    /**
     * 字段值
     */
    private String value;

    /**
     * <p>条件类型</p>
     * <p>eq: 全等</p>
     * <p>like: 全模糊匹配查询</p>
     * <p>leftLike: 左模糊匹配查询</p>
     * <p>rightLike: 右模糊匹配查询</p>
     * <p>notLike: 全模糊不匹配查询</p>
     * <p>gt: 大于</p>
     * <p>lt: 小于</p>
     * <p>ge: 大于等于</p>
     * <p>le: 小于等于</p>
     */
    @NotNull(message = "查询条件的搜索类型不能为空")
    private QueryType queryType;

}
