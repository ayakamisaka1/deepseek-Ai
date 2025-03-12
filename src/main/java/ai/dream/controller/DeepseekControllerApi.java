package ai.dream.controller;

import ai.dream.common.ReqBody;
import ai.dream.common.ResultBody;
import ai.dream.httpRequest.ChatAiReq;
import ai.dream.httpRequest.CompletionsReq;
import ai.dream.httpResponse.BalanceResp;
import ai.dream.httpResponse.ChatAiResp;
import ai.dream.httpResponse.CompletionsResp;
import ai.dream.service.DeepseekService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "基本api")
public class DeepseekControllerApi {
    @Resource
    private DeepseekService deepseekService;

    /**
     * 对话补全
     */
    @PostMapping("/chat")
    public ResultBody<ChatAiResp> chat(@RequestBody @Validated ReqBody<ChatAiReq> req) {
        return ResultBody.ok(deepseekService.chat(req.getData()));
    }

    /**
     * fim补全
     */
    @PostMapping("/completions")
    public ResultBody<CompletionsResp> completions(@RequestBody @Validated ReqBody<CompletionsReq> req) {
        return ResultBody.ok(deepseekService.completions(req.getData()));
    }

    /**
     * 余额查询
     */
    @GetMapping("/balance")
    public ResultBody<BalanceResp> balance() {
        return ResultBody.ok(deepseekService.balance());
    }
}
