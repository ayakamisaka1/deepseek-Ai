package ai.dream.httpResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChatAiResp {
    /**
     * 返回值
     */
    @Schema(description = "返回值")
    private String msg;
}
