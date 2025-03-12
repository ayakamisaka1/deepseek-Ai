package ai.dream.httpRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompletionsReq {
    /**
     * 要补全的内容
     */
    @Schema(description = "要补全的内容")
    @NotBlank(message = "content不能为空")
    private String content;

    /**
     * ai模型
     */
    @Schema(description = "ai模型 0:基础模型 1:强化模型")
    private String modelType;
}
