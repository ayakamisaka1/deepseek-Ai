package ai.dream.common;

public enum CommonEnum {
    //数据操作错误定义
    SUCCESS("0000", "成功!"),
    FAIL("9999", "失败!"),
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    MESSAGE_IS_NULL("410", "资源不存在!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!");

    //响应码
    private String code;

    //响应描述
    private String desc;

    CommonEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }
}
