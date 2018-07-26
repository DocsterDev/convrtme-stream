package com.convrt.stream.repository;


import com.convrt.stream.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String> {

	Video findByIdAndStreamUrlExpireDateNotNull(String id);

	Video findById(String id);

	List<Video> findVideosByIdIn(List<String> videosIds);
}
