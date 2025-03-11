package ai.dream.aiRequest;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;  // 消息内容
    private String role;     // 消息角色，通常是 "system" 或 "user"
    private String name;     //参与者 为模型提供信息以区分相同角色的参与者。
}
