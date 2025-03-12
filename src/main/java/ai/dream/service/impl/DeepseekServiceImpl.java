package ai.dream.service.impl;


import ai.dream.aiRequest.ChatMessage;
import ai.dream.aiRequest.ChatRequest;
import ai.dream.aiRequest.CompletionsRequest;
import ai.dream.common.ServiceException;
import ai.dream.httpRequest.ChatAiReq;
import ai.dream.httpRequest.CompletionsReq;
import ai.dream.httpResponse.BalanceResp;
import ai.dream.httpResponse.ChatAiResp;
import ai.dream.httpResponse.CompletionsResp;
import ai.dream.service.DeepseekService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
        message.setContent(req.getContent());
        message.setRole("user");
        messages.add(message);
        request.setMessages(messages);
        ChatRequest.Responseformat type = new ChatRequest.Responseformat();
        type.setType("text");  // ✅ 确保返回 JSON 格式
        request.setResponse_format(type);
        //ai模型选择
        request.setModel(modelType(req.getModelType()));
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
        // 解析返回值
        try {
            ResponseEntity<String> response = restTemplate.exchange(CHAT_URL, HttpMethod.valueOf(CHAT_REQUEST_TYPE), httpRequest, String.class);
            if (response != null && response.getBody() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                log.info(response.getBody());
                JsonNode responseJson = objectMapper.readTree(response.getBody());
                // 提取 content 字段
                String content = responseJson.path("choices").get(0).path("message").path("content").asText();
                entity.setMsg(content);
                return entity;
            } else {
                throw new ServiceException();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException();
        }
    }


    @Override
    public CompletionsResp completions(CompletionsReq req) {
        CompletionsRequest request = new CompletionsRequest();
        // 构建请求实体
        request.setModel(modelType(req.getModelType()));
        request.setPrompt(req.getContent());
        request.setEcho(false);
        request.setFrequencyPenalty(0.2);
        request.setLogprobs(1);
        request.setMaxTokens(4096);
        request.setPresencePenalty(0.3);
        request.setStop(null);
        request.setStream(false);
        request.setStreamOptions(null);
        request.setSuffix(null);
        request.setTemperature(0.2);
        request.setTopP(1);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEYS);
        // 组合请求
        HttpEntity<CompletionsRequest> requestEntity = new HttpEntity<>(request, headers);
        // 解析返回值
        try {
            // 发送 POST 请求
            ResponseEntity<String> response = restTemplate.exchange(COMPLETIONS_URL, HttpMethod.valueOf(COMPLETIONS_REQUEST_TYPE), requestEntity, String.class);
            // 输出响应
            log.info("Response: " + response.getBody());
            CompletionsResp entity = new CompletionsResp();
            if (response != null && response.getBody() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                log.info(response.getBody());
                JsonNode responseJson = objectMapper.readTree(response.getBody());
                // 提取 content 字段
                String content = responseJson.path("choices").get(0).path("text").asText();
                entity.setMsg(content);
                return entity;
            } else {
                throw new ServiceException();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException();
        }
    }


    @Override
    public BalanceResp balance() {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEYS);
        // 创建请求实体（GET 请求不需要请求体）
        HttpEntity<String> req = new HttpEntity<>(headers);
        try {
            // 发送 GET 请求
            ResponseEntity<String> response = restTemplate.exchange(BALANCE_URL, HttpMethod.valueOf(BALANCE_REQUEST_TYPE), req, String.class);
            // 输出响应
            log.info(response.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            BalanceResp entity = new BalanceResp();
            JsonNode responseJson = objectMapper.readTree(response.getBody());
            // 提取 content 字段
            String content = responseJson.path("balance_infos").get(0).path("total_balance").asText();
            entity.setMsg(content);
            return entity;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException();
        }
    }

    public String modelType(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("0", "deepseek-chat");//基础AI deepseek-chat
        map.put("1", "deepseek-reasoner");//升级AI deepseek-reasoner
        return map.get(type) == null ? "deepseek-chat" : map.get(type);
    }
}