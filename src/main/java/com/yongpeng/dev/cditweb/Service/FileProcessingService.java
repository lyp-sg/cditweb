package com.yongpeng.dev.cditweb.Service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileProcessingService {

    List processFile(MultipartFile file);
}
