package top.xiamuyao.fastspringdevelop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.xiamuyao.fastspringdevelop.enums.ResultCode;
import top.xiamuyao.fastspringdevelop.util.ResultUtil;
import top.xiamuyao.fastspringdevelop.util.RetResult;

/**
 * ================================================
 * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
 * 版    本：1.0
 * 创建日期： 2018/10/29
 * 描    述：全局异常处理
 * 修订历史：
 * ================================================
 */
// TODO: 2018/12/18 需要全局处理一下异常的问题  ServiceException 去判断这个的code 组合业务代码使用
//@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public RetResult handleBadRequest(Exception e) {
        /*
         * 绑定参数异常
         */
        if (e instanceof BindException || e instanceof MissingServletRequestParameterException) {
//            BindingResult bindingResult = ((BindException) e).getBindingResult();
//            if (null != bindingResult && bindingResult.hasErrors()) {
//                List<Object> jsonList = new ArrayList<>();
//                bindingResult.getFieldErrors().stream().forEach(fieldError -> {
//                    Map<String, Object> jsonObject = new HashMap<>(2);
//                    jsonObject.put("name", fieldError.getField());
//                    jsonObject.put("msg", fieldError.getDefaultMessage());
//                    jsonList.add(jsonObject);
//                });
//                return ResultUtil.makeRsp(ResultCode.FAIL, "参数校验异常", jsonList);
//            }
            return ResultUtil.makeRsp(ResultCode.MISS_PARAMETER, "参数校验异常", e.getMessage());
        }


        return ResultUtil.makeErrRsp(null);
    }

    @ExceptionHandler(ServiceException.class)
    public RetResult serviceException(ServiceException e) {
        return ResultUtil.makeRsp(ResultCode.TOKEN_FALSE, e.getMessage());
    }

}
