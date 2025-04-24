package com.ttaengju.websocket.ai.infra;

import com.ttaengju.websocket.ai.domain.OpenAiCommandService;
import org.springframework.stereotype.Service;

@Service
public class OpenAiCommandServiceImpl implements OpenAiCommandService {
    @Override
    public String chatConversation(String command) {
        String systemPrompt = "이 명령에 맞는 액션에 대해서 알려줘";
        return systemPrompt;
    }
}
