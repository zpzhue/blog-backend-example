package cn.irisz.irisz_blog_backend.common.exception;

import cn.irisz.irisz_blog_backend.common.result.R;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import com.sun.corba.se.spi.ior.ObjectKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 统一异常处理类
 * <li>RestControllerAdvice：在controller层添加通知。如果使用@ControllerAdvice，则方法上需要添加@ResponseBody</li>
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/8/22 0022
 */
@Slf4j
@Component

@RestControllerAdvice
public class UnifiedExceptionHandler {
    /**
     * 捕获所有异常，统一异常处理，
     * 当controller中抛出Exception，则捕获
     * @param e 运行时所抛出的具体异常
     * @return R
     */
    @ExceptionHandler(value = Exception.class)
    public R<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error().data(null);
    }

    /**
     * 处理自己业务中抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public R<Object> handleBusinessException(BusinessException e){
        log.error(e.getMessage(), e);
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Object> handleMethodArgumentException(MethodArgumentNotValidException e){
        Map<String, String> map = new HashMap<>();
        for (FieldError i: e.getBindingResult().getFieldErrors()){
            map.put(i.getField(), i.getDefaultMessage());
        }
        return R.setResult(ResponseEnum.PARAMS_VALID_ERROR).data(map);
    }

    /**
     * Controller上一层相关异常
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public R handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        return R.error().message(ResponseEnum.SERVLET_ERROR.getMessage()).code(ResponseEnum.SERVLET_ERROR.getCode());
    }
}
