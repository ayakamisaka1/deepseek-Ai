package ai.dream.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

import java.io.Serializable;
@Data
public class ReqBody<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private commonRequest commonRequest;

    // 请求体
    @Valid
    private T data;

    @Data
    public static class commonRequest implements Serializable {
        @Schema(description = "流水号")
        private String serialNo;//流水号
        @Schema(description = "渠道")
        private String channel;//渠道
        @Schema(description = "token")
        private String token;//token
        @Schema(description = "当前页")
        private Integer pageNumber;//当前页
        @Schema(description = "分页数")
        private Integer pageSize;//分页数
        @Schema(description = "ip")
        private String IP;
        @Schema(description = "操作人")
        private String userName;
    }
}
