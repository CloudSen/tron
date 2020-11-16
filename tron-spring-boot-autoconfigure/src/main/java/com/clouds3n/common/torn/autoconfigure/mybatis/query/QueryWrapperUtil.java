package com.clouds3n.common.torn.autoconfigure.mybatis.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author wpj
 */
@SuppressWarnings("DuplicatedCode")
public class QueryWrapperUtil {

    public static <T> QueryWrapper<T> parseWhereSql(List<QueryConditionDto> conditionList, T type) throws IllegalAccessException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (CollectionUtils.isNotEmpty(conditionList)) {
            for (QueryConditionDto queryCondition : conditionList) {
                switch (queryCondition.getQueryType()) {
                    case EQ:
                        queryWrapper.eq(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case NE:
                        queryWrapper.ne(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case LIKE:
                        queryWrapper.like(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case LEFT_LIKE:
                        queryWrapper.likeLeft(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case RIGHT_LIKE:
                        queryWrapper.likeRight(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case NOT_LIKE:
                        queryWrapper.notLike(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case GT:
                        queryWrapper.gt(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case LT:
                        queryWrapper.lt(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case GE:
                        queryWrapper.ge(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    case LE:
                        queryWrapper.le(queryCondition.getColumn(), queryCondition.getValue());
                        break;
                    default:
                        throw new IllegalAccessException("无法匹配查询类型");
                }
            }
        }
        return queryWrapper;
    }
}
