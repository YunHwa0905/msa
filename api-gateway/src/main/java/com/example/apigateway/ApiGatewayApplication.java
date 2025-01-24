package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient
 * - 이 서비스는 유레카 클라이언트이다
 * - 디스커버리 서비스가 찾아서 등록하는 대상 서비스
 * - 게이트웨이 담당
 *    - 다른 서비스들을 등록, 관리, 중계 등 처리 수행 -> 라우팅
 *    - 환경변수 파트에서 설정
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
