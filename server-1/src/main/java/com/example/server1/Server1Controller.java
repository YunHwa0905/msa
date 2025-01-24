package com.example.server1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class Server1Controller {
    // 1. 외부 서비스와 통신을 위한 객체 -> DI
    @Autowired
    private WebClient.Builder webClientBuilder;

    // 2. 서비스 1의 고유한 요청 처리
    @GetMapping("/service1")
    public String service1() {
        // 편의상 문자열로 응답 -> 통상  json 응답
        return "서비스1에서 완결되는 고유한 작업 요청에 대한 응답";
    }

    // 3. 서비스 2를 요청 처리 -> 결과를 획득 -> 응답
    @GetMapping("/service1/biz")
    public Mono<String> service1Biz() {
        // 요청 1개에 대해 결과가 0개 혹은 1개만 나오게 구성예정(전제)
        // 데이터 스트림 타입으로 반환 Mono<상세타입구성>
        // 다른 서비스와 통신
        return webClientBuilder
                // 요청전 설정
                .baseUrl("http://127.0.0.1:8082") // 서비스2에 대한 특정 url 요청
                .build()
                // 요청
                .get()
                .uri("/service2")
                // 응답 처리
                .retrieve()
                .bodyToMono(String.class)
                .map(res->"서비스2로부터 응답결과:" + res)
                ;
    }
}
