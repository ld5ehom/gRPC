package com.ld5ehom.grpc.global.utils;

import com.google.protobuf.Timestamp;

import java.util.Date;

// Converts between Java Date and Protobuf Timestamp
// Java Date와 Protobuf Timestamp 간 변환 유틸리티
public class TimestampConverter {

    public static Date fromProto(Timestamp timestamp) {
        long milliseconds = timestamp.getSeconds() * 1000 + timestamp.getNanos() / 1_000_000; // Google
        return new Date(milliseconds);
    }

    public static Timestamp toProto(Date date) {
        return Timestamp.newBuilder()
                .setSeconds(date.getTime() / 1000)
                .setNanos((int) ((date.getTime() % 1000) * 1_000_000)) // Google
                .build();
    }
}
