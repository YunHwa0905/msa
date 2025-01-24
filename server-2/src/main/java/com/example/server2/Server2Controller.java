package com.example.server2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class Server2Controller {
    // 2. 서비스 1의 고유한 요청 처리
    @GetMapping("/service2")
    public String service2() {
        // 편의상 문자열로 응답 -> 통상  json 응답
        return "주문관리";
    }
}
