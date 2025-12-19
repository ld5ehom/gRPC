package com.ld5ehom.grpc.domain.reviews.service;

import bookstore.Bookstore;
import bookstore.ReviewServiceGrpc;
import com.ld5ehom.grpc.domain.reviews.entity.Review;
import com.ld5ehom.grpc.global.interceptor.AccessLoggingInterceptor;
import com.ld5ehom.grpc.global.interceptor.BasicAuthInterceptor;
import com.ld5ehom.grpc.global.utils.TimestampConverter;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Exposes review-related operations via gRPC endpoints
// gRPC를 통해 리뷰 관련 기능을 제공하는 진입 서비스
@GrpcService(interceptors = { AccessLoggingInterceptor.class, BasicAuthInterceptor.class })
public class ReviewGrpcService extends ReviewServiceGrpc.ReviewServiceImplBase {

    private final ReviewService reviewService;

    @Autowired
    public ReviewGrpcService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Handle gRPC request to retrieve reviews for a book
    // gRPC 책 기준 리뷰 조회 요청 처리
    @Override
    public void getReviews(Bookstore.GetReviewsRequest request, StreamObserver<Bookstore.Review> responseObserver) {
        List<Review> reviews = reviewService.findByBookId(request.getBookId());

        for (Review review : reviews) {
            responseObserver.onNext(
                    Bookstore.Review.newBuilder()
                            .setId(review.getId())
                            .setBookId(review.getBook().getId())
                            .setRating(review.getRating())
                            .setCreatedDate(TimestampConverter.toProto(review.getCreatedDate()))
                            .setContent(review.getContent())
                            .build()
            );
        }

        responseObserver.onCompleted();
    }
}
