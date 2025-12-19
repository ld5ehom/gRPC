package com.ld5ehom.grpc.global.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

// Logs gRPC request access information before and after service execution
// gRPC 서비스 실행 전후의 접근 정보를 로깅하는 인터셉터
@Component
@Slf4j
public class AccessLoggingInterceptor implements ServerInterceptor {

    // Intercept gRPC calls to record request metadata and execution duration
    // gRPC 호출을 가로채 요청 메타데이터와 처리 시간을 기록
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next
    ) {

        // 현재 시간
        Instant startTime = Instant.now();

        // Retrieve client IP forwarded by load balancer or proxy via gRPC Metadata
        // 로드밸런서 또는 프록시가 전달한 클라이언트 IP를 gRPC Metadata에서 조회
        String clientIP = headers.get(Metadata.Key.of("X-Forwarded-For", Metadata.ASCII_STRING_MARSHALLER));

        log.info(
                "{} - - [{}] \"{}\" {}",
                clientIP,
                startTime,
                call.getMethodDescriptor().getFullMethodName(),
                headers
        );

        // Forward the call to the next interceptor or gRPC service handler
        // 다음 인터셉터 또는 실제 gRPC 서비스 핸들러로 호출을 전달
        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {

            @Override
            public void onComplete() {
                Duration duration = Duration.between(startTime, Instant.now());
                String clientIP =
                        headers.get(Metadata.Key.of("X-Forwarded-For", Metadata.ASCII_STRING_MARSHALLER));

                log.info(
                        "{} - - [{}] \"{}\" {} {}ms",
                        clientIP != null ? clientIP : "-",
                        Instant.now(),
                        call.getMethodDescriptor().getFullMethodName(),
                        headers,
                        duration.toMillis()
                );

                super.onComplete();
            }
        };
    }
}
