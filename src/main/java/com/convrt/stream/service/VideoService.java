package com.convrt.stream.service;


import com.convrt.stream.entity.Video;
import com.convrt.stream.repository.VideoRepository;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Transactional(readOnly = true)
    public Video readVideoMetadata(String id) {
        Video video = videoRepository.findByIdAndStreamUrlExpireDateNotNull(id);
        if (video == null) {
            return null;
        }
        Instant expireDate = video.getStreamUrlExpireDate();
        if (Instant.now().isBefore(expireDate)) {
            return video;
        }
        return null;
    }

    @Transactional
    public Video updateVideo(Video video) {
        Video videoPersistent = videoRepository.findById(video.getId());
        if (videoPersistent == null) {
            throw new RuntimeException(String.format("No video found to update: video id %s", video.getId()));
        }
        return videoRepository.save(video);
    }

    @Transactional(readOnly = true)
    public Video readVideo(String id) {
        Video video = videoRepository.findOne(id);
        if (video == null) {
            throw new RuntimeException(String.format("Cannot find video with video id %s. Video must exist first.", id));
        }
        return video;
    }

    @Transactional(readOnly = true)
    public Video readVideoByVideoId(String id) {
        return videoRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByVideoId(String videoId) {
        return videoRepository.exists(videoId);
    }

}
