package ai.dream.common;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author : ayaka
 * @version : 1.0
 * @appName : PolarStar
 * @moduleName : PolarStar
 * @className : polar.star.gateway.common.ServiceException
 * @date : 2024/9/17 12:40
 */

public class ServiceException extends RuntimeException{
    private String code = CommonEnum.INTERNAL_SERVER_ERROR.getCode();

    public ServiceException() { }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause,
                            String code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /** 400 请求有错误*/
    public static ServiceException invalidRequest(String message){
        return new ServiceException(message, CommonEnum.BODY_NOT_MATCH.getCode());
    }

    /** 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作。 */
    public static ServiceException notFound(String message){
        return new ServiceException(message, CommonEnum.NOT_FOUND.getCode());
    }

    /**用户请求的资源被删除，且不会再得到的。*/
    public static ServiceException gone(String message){
        return new ServiceException(message, CommonEnum.MESSAGE_IS_NULL.getCode());
    }


    //返回服务器忙的异常
    public static ServiceException busy(){
        return new ServiceException("数据库忙", CommonEnum.SERVER_BUSY.getCode());
    }

}
