package ai.dream.service;

import ai.dream.httpRequest.ChatAiReq;
import ai.dream.httpRequest.CompletionsReq;
import ai.dream.httpResponse.BalanceResp;
import ai.dream.httpResponse.ChatAiResp;
import ai.dream.httpResponse.CompletionsResp;

public interface DeepseekService {
    //对话补全
    public ChatAiResp chat(ChatAiReq req);
    //剧情补全
    public CompletionsResp completions(CompletionsReq req);
    //查询余额
    public BalanceResp balance();
}
