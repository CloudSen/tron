package com.clouds3n.common.torn.autoconfigure.exception;

import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import com.clouds3n.common.torn.autoconfigure.Res;
import com.clouds3n.common.torn.autoconfigure.system.properties.StarterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author CloudS3n
 * @date 2019/11/29 15:32
 */
@Slf4j
@RestController
@ControllerAdvice
@AutoConfigureAfter(StarterProperties.class)
@ConditionalOnProperty(name = "config.starter.enable-global-exception-handler", havingValue = "true")
public class GlobalExceptionHandlerAutoConfiguration {

    public GlobalExceptionHandlerAutoConfiguration() {
        log.info(AutoConfigConstants.LOADING_GLOBAL_EXCEPTION_AUTO_CONFIGURE);
    }

    @ExceptionHandler(BusinessException.class)
    public Res businessExceptionHandler(BusinessException e, WebRequest request) {
        return commonHandle(e, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Res validationExceptionHandler(MethodArgumentNotValidException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> {
            Arrays.stream(Objects.requireNonNull(error.getArguments()))
                .filter(o -> o instanceof DefaultMessageSourceResolvable)
                .forEach(
                    o -> msg.append(((DefaultMessageSourceResolvable) o).getDefaultMessage()).append(" => ")
                );
            msg.append(error.getDefaultMessage()).append(" | ");
        });
        return Res.error(
            StringUtils.isBlank(msg.toString())
                ? AutoConfigConstants.SERVER_ERROR
                : msg.substring(0, msg.toString().length() - 1)
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Res illegalArgumentExceptionHandler(IllegalArgumentException e, WebRequest request) {
        return commonHandle(e, request);
    }


    @ExceptionHandler(Exception.class)
    public Res otherExceptionHandler(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        String msg = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
        if (StringUtils.isBlank(msg)) {
            msg = AutoConfigConstants.SERVER_ERROR;
        }
        return Res.error(msg);
    }

    private Res commonHandle(Exception e, WebRequest request) {
        log.error(request.getContextPath());
        log.error(ExceptionUtils.getStackTrace(e));
        return Res.error(StringUtils.isBlank(e.getMessage()) ? AutoConfigConstants.SERVER_ERROR : e.getMessage());
    }
}
