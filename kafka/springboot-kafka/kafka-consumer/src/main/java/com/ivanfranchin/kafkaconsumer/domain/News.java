package com.ivanfranchin.kafkaconsumer.domain;

import lombok.Data;

@Data
public class News {

    private String id;
    private String source;
    private String title;
}
