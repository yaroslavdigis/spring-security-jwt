package com.kekfeed.jwt.batch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "memes-batches")
@TypeAlias("memes-batch")
public class MemesBatch {
    @Id
    private String id;
    private Long fromId;
    private Long toId;
    private List<Long> memeIds = new ArrayList<>();
}