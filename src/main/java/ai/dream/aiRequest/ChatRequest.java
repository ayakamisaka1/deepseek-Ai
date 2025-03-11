package ai.dream.aiRequest;

import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {
    //message
    private List<ChatMessage> messages;
    //model 模型
    private String model;
    //介于 -2.0 和 2.0 之间的数字。如果该值为正，那么新 token 会根据其在已有文本中的出现频率受到相应的惩罚，降低模型重复相同内容的可能性。
    private Double frequency_penalty;
    //输入 token 和输出 token 的总长度受模型的上下文长度的限制。
    //如未指定 max_tokens参数，默认使用 4096。
    private Integer max_tokens;
    //介于 -2.0 和 2.0 之间的数字。如果该值为正，那么新 token 会根据其是否已在已有文本中出现受到相应的惩罚，从而增加模型谈论新主题的可能性。
    private Double presence_penalty;
    //控制 AI 在遇到特定词或标点时停止输出
    private String stop;
    //如果设置为 True，将会以 SSE（server-sent events）的形式以流式发送消息增量。消息流以 data: [DONE] 结尾。
    private Boolean stream;
    //流式输出相关选项。只有在 stream 参数为 true 时，才可设置此参数。
    private String stream_options;
    //控制 AI 生成文本的随机性：
    //取值范围：0 到 2
    //默认值：1
    //影响：
    //低温度（接近 0）：AI 更倾向于选择最可能的答案，回答更稳定、更保守。
    //高温度（接近 2）：AI 更具创造性，可能会有更有趣但不一定准确的回答。
    private String temperature;
    //影响 AI 选择词汇的范围：
    //取值范围：0 到 1
    //默认值：1
    //影响：
    //低值（接近 0）：AI 只会选择最高概率的词，回答更确定。
    //高值（接近 1）：AI 可能会选择概率较低的词，回答更有创意。
    private Double top_p;
    //指定 AI 可以调用的工具（比如函数调用、插件等）。
    //默认值：null（不开启工具）。
    //未来可能支持：
    //让 AI 调用 API 或数据库查询。
    private String tools;
    //控制 AI 如何选择工具：
    //"none"：不使用工具。
    //"auto"：AI 自动选择最合适的工具。
    //可以指定某个工具的名称。
    private String tool_choice;
    //是否返回生成文本的概率信息：
    //true：返回 AI 选择每个词时的概率值。
    //false：不返回（默认）。
    private Boolean logprobs;
    //如果 logprobs = true，可以指定返回多少个候选词的概率。
    //默认值：null（不返回）。
    private String top_loprivate;

    private Responseformat response_format;

    @Data
    public static class Responseformat {
        //[text, json_object] 使用 JSON 模式时，你还必须通过系统或用户消息指示模型生成 JSON。否则，
        // 模型可能会生成不断的空白字符，直到生成达到令牌限制，从而导致请求长时间运行并显得“卡住”。
        // 此外，如果 finish_reason="length"，这表示生成超过了 max_tokens 或对话超过了最大上下文长度，消息内容可能会被部分截断。
       private String type;
    }
}