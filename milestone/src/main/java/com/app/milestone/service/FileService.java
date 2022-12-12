package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.entity.File;
import com.app.milestone.entity.User;
import com.app.milestone.repository.FileRepository;
import com.app.milestone.repository.UserRepository;
import com.app.milestone.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    //    추가
    public void register(Long userId, FileDTO fileDTO) {
        User user = userRepository.findById(userId).get();
        File file = new File(fileDTO.getFileName(), fileDTO.getFilePath(), fileDTO.getFileUuid(), fileDTO.getFileSize(), fileDTO.isFileImageCheck(), fileDTO.getFileType());
        file.changeUser(user);
        fileRepository.save(file);
    }

    //    삭제
    public void remove(Long fileId) {
        fileRepository.deleteById(fileId);
    }

//    프로필 조회
    public FileDTO showProfile(Long userId) {
        return fileRepository.findProfileByUserId(userId);
    }

    //    전체조회
    public List<FileDTO> showAll(Long userId) {
        return fileRepository.findByUserId(userId);
    }

}
