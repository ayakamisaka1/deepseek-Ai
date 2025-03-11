package ai.dream.service.impl;


import ai.dream.aiRequest.ChatMessage;
import ai.dream.aiRequest.ChatRequest;
import ai.dream.common.ServiceException;
import ai.dream.httpRequest.ChatAiReq;
import ai.dream.httpResponse.ChatAiResp;
import ai.dream.service.DeepseekService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeepseekServiceImpl implements DeepseekService {

    /**
     * restTemplate
     */
    @Resource
    private RestTemplate restTemplate;

    /**
     * deepseek-ai-dream-api-keys
     */
    @Value("${deepseek-ai-dream-api-keys}")
    private String API_KEYS;

    /**
     * deepseek-ai-chat-url
     */
    @Value("${deepseek-ai-chat-url}")
    private String CHAT_URL;

    /**
     * deepseek-ai-chat-requestType
     */
    @Value("${deepseek-ai-chat-requestType}")
    private String CHAT_REQUEST_TYPE;

    /**
     * deepseek-ai-completions-url
     */
    @Value("${deepseek-ai-completions-url}")
    private String COMPLETIONS_URL;

    /**
     * deepseek-ai-completions-requestType
     */
    @Value("${deepseek-ai-completions-requestType}")
    private String COMPLETIONS_REQUEST_TYPE;

    /**
     * deepseek-ai-balance-url
     */
    @Value("${deepseek-ai-balance-url}")
    private String BALANCE_URL;

    /**
     * deepseek-ai-balance-requestType
     */
    @Value("${deepseek-ai-balance-requestType}")
    private String BALANCE_REQUEST_TYPE;

    @Override
    public ChatAiResp chat(ChatAiReq req) {
        ChatAiResp entity = new ChatAiResp();
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEYS);
        ChatRequest request = new ChatRequest();
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage message = new ChatMessage();
        message.setContent(req.getContent()+"记住以后没叫你返回英语  你就给我返回中文 。请以 JSON 格式返回响应，确保数据结构符合 JSON 规范。");
        message.setRole("system");
        messages.add(message);
        request.setMessages(messages);
        ChatRequest.Responseformat type = new ChatRequest.Responseformat();
        type.setType("json_object");  // ✅ 确保返回 JSON 格式
        request.setResponse_format(type);
        //ai模型选择
        request.setModel("deepseek-chat");
        request.setStop(null);
        request.setMax_tokens(4096);
        request.setLogprobs(false);
        request.setTop_loprivate(null);
        request.setPresence_penalty(0.9);
        request.setTools(null);
        request.setTop_p(1.0);
        request.setStream(false);
        request.setTool_choice("auto");
        request.setFrequency_penalty(0.7);
        Map map = mapper.convertValue(request, Map.class);
        // 构建 HttpEntity
        HttpEntity<Map<String, Object>> httpRequest = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(CHAT_URL, HttpMethod.valueOf(CHAT_REQUEST_TYPE), httpRequest, String.class);
        // 解析返回值
        try {
            if (response!=null&&response.getBody()!=null) {
                ObjectMapper objectMapper = new ObjectMapper();
                // 将 JSON 字符串转换为 JsonNode 对象
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                // 从 JsonNode 中获取 'choices' 数组
                JsonNode choicesNode = rootNode.path("choices").get(0).path("message").path("content");
                // 进一步解析 content 中的 response 字段
                ObjectMapper contentMapper = new ObjectMapper();
                JsonNode contentNode = contentMapper.readTree(choicesNode.asText());
                // 提取 'response' 字段
                entity.setMsg(contentNode.path("response").asText());
                return entity;
            }else {
                throw new ServiceException();
            }
        } catch (Exception e) {
            throw new ServiceException();
        }
    }
}