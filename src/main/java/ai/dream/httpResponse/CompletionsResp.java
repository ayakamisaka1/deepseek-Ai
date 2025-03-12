package ai.dream.httpResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CompletionsResp {
    /**
     * 返回值
     */
    @Schema(description = "返回值")
    private String msg;
}
