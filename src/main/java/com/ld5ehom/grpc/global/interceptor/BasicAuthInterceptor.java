package com.ld5ehom.grpc.global.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

// Intercepts gRPC calls before service execution to perform basic authentication
// gRPC 서비스 실행 전에 요청을 가로채 기본 인증을 수행하는 인터셉터
@Component
@Slf4j
public class BasicAuthInterceptor implements ServerInterceptor {

    // Intercept gRPC requests using Metadata and control flow with Status
    // Metadata를 검사하고 Status로 요청 흐름을 제어하는 gRPC 인터셉션 진입 지점
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next
    ) {
        log.info("## BasicAuthInterceptor");

        String username = "ld5ehom";
        String password = "taewook";

        // Retrieve Authorization metadata from gRPC request headers
        // gRPC 요청 Metadata에서 Authorization 헤더 값 조회
        String authorization =
                headers.get(Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER));


        if (authorization == null || !authorization.startsWith("Basic ")) {
            call.close(
                    Status.UNAUTHENTICATED.withDescription("Missing or invalid Authorization header"),
                    new Metadata()
            );
            return new ServerCall.Listener<>() {};
        }

        // ld5ehom:taewook -> base64
        String credentials = authorization.substring(6);
        String[] usernameAndPassword =
                new String(Base64.getDecoder().decode(credentials)).split(":");

        String providedUsername = usernameAndPassword[0];
        String providedPassword = usernameAndPassword[1];

        if (!providedUsername.equals(username) || !providedPassword.equals(password)) {
            call.close(
                    Status.PERMISSION_DENIED.withDescription("Invalid username or password"),
                    new Metadata()
            );
            return new ServerCall.Listener<>() {};
        }

        return next.startCall(call, headers);
    }
}
