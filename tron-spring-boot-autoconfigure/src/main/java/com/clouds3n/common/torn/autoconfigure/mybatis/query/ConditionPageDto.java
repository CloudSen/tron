package com.clouds3n.common.torn.autoconfigure.mybatis.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author wpj
 */
@Data
@Accessors(chain = true)
public class ConditionPageDto<T> implements Serializable {

    private static final long serialVersionUID = 4047151035414011960L;

    @NotNull(message = "分页参数不能为空")
    private Page<T> page;

    @Valid
    private List<QueryConditionDto> conditionList;
}
