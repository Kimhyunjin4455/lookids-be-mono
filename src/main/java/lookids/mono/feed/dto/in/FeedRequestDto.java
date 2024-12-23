package Lookids.Feed.feed.dto.in;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.in.FeedRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedRequestDto {

    private ObjectId id;
    private String feedCode;
    private String uuid;
    private List<String> petCode;
    private String content;
    private List<String> tagList;
    private boolean state;
    private List<String> mediaUrlList;
    private LocalDateTime createdAt;

    @Builder
    public FeedRequestDto(ObjectId id, String feedCode, String uuid, List<String> petCode, String content, List<String> tagList,
        boolean state, List<String> mediaUrlList, LocalDateTime createdAt) {
        this.id = id;
        this.feedCode = feedCode;
        this.uuid = uuid;
        this.petCode = petCode;
        this.content = content;
        this.tagList = tagList;
        this.state = state;
        this.mediaUrlList = mediaUrlList;
        this. createdAt = createdAt;
    }

    public Feed toEntity() {
        return Feed.builder()
            .feedCode(UUID.randomUUID().toString())
            .uuid(uuid)
            .petCode(petCode)
            .content(content)
            .tagList(tagList.stream().map(String::toLowerCase).collect(Collectors.toList()))
            .mediaUrlList(mediaUrlList)
            .state(true)
            .createdAt(createdAt)
            .build();
    }

    public static FeedRequestDto toDto(FeedRequestVo feedRequestVo, String uuid) {
        return FeedRequestDto.builder()
            .uuid(uuid)
            .petCode(feedRequestVo.getPetCode())
            .content(feedRequestVo.getContent())
            .tagList(feedRequestVo.getTagList())
            .mediaUrlList(feedRequestVo.getMediaUrlList())
            .build();
    }

    public static FeedRequestDto toDelete(Feed feed) {
        return FeedRequestDto.builder()
            .id(feed.getId())
            .uuid(feed.getUuid())
            .feedCode(feed.getFeedCode())
            .petCode(feed.getPetCode())
            .state(false)
            .content(feed.getContent())
            .tagList(feed.getTagList())
            .mediaUrlList(feed.getMediaUrlList())
            .createdAt(feed.getCreatedAt())
            .build();
    }

    public Feed toEntityForUpdate() {
        return Feed.builder()
            .id(id)
            .feedCode(feedCode)
            .uuid(uuid)
            .petCode(petCode)
            .content(content)
            .tagList(tagList)
            .mediaUrlList(mediaUrlList)
            .state(state)
            .createdAt(createdAt)
            .build();
    }

    public FeedKafkaDto toDto(Feed savefeed) {
        return FeedKafkaDto.builder()
            .feedCode(savefeed.getFeedCode())
            .uuid(uuid)
            .petCode(petCode)
            .content(content)
            .tagList(tagList.stream().map(String::toLowerCase).collect(Collectors.toList()))
            .state(savefeed.isState())
            .mediaUrlList(mediaUrlList)
            .createdAt(savefeed.getCreatedAt())
            .build();
    }
}
