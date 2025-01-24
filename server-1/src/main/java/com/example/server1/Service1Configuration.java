package com.example.server1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClient(매번생성) or (*)WebClient.Builder(1회생성후 재사용) 빈 구성
 *  - 다른 서비스와 통신이 필요한 경우 사용
 *  - HTTP를 이용하여 서비스 내에서 외부 서비스와 통신 담당
 *  - 비동기, 동기, 스트리딩등 다양한 분야 활용
 *  - 반응형(Reactive) 방식으로 설계 가능
 *  - 비동기, 넌블러킹 방식 통신 진행
 */
@Configuration
public class Service1Configuration {
    // 빈 구성 -> DI 사용 처리
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
