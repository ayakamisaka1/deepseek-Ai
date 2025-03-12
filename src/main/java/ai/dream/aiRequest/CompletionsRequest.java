package ai.dream.aiRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompletionsRequest {
    /**
     * 使用的模型，例如 "deepseek-chat"
     */
    private String model;

    /**
     * 需要生成文本的提示内容（即输入的文本）
     */
    private String prompt;

    /**
     * 是否回显用户的 `prompt`，默认 `false`
     */
    private boolean echo;

    /**
     * 频率惩罚参数，控制模型重复使用常见单词的程度，值越高，越少重复
     */
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;

    /**
     * 返回 `logprobs` 的数量，用于查看模型选择某个 token 的概率信息
     */
    private Integer logprobs;

    /**
     * 最大生成的 token 数量，限制输出文本的长度
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * 存在惩罚参数，控制模型是否倾向于引入新的单词
     */
    @JsonProperty("presence_penalty")
    private Double presencePenalty;

    /**
     * 生成时遇到该列表中的单词或短语时会停止生成
     */
    private List<String> stop;

    /**
     * 是否使用流式输出（实时返回数据）
     */
    private boolean stream;

    /**
     * 流式请求的选项（一般为空或 `null`）
     */
    @JsonProperty("stream_options")
    private Object streamOptions;

    /**
     * 在生成的文本后添加的后缀，一般为 `null`
     */
    private String suffix;

    /**
     * 生成文本的温度值，控制生成文本的随机性，值越高，输出越随机
     */
    private double temperature;

    /**
     * `top_p` 采样，控制模型输出的多样性（类似 `temperature`），推荐范围 0-1
     */
    @JsonProperty("top_p")
    private double topP;
}
