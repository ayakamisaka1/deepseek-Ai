package ai.dream.httpRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatAiReq {
    /**
     * 问题
     */
    @Schema(description = "问题")
    @NotBlank(message = "content不能为空")
    private String content;
}
