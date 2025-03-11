package ai.dream.service;

import ai.dream.httpRequest.ChatAiReq;
import ai.dream.httpResponse.ChatAiResp;

public interface DeepseekService {
    public ChatAiResp chat(ChatAiReq req);
}
