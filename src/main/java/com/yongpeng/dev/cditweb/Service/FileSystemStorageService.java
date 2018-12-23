package com.yongpeng.dev.cditweb.Service;

import com.yongpeng.dev.cditweb.DTO.UserDTO;
import com.yongpeng.dev.cditweb.Exception.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSystemStorageService implements StorageService {

    @Override
    public List<UserDTO> processFile(MultipartFile file){

        List<UserDTO> userList = new ArrayList<UserDTO>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String input;
            while((input = br.readLine())!=null){
                String [] variable = input.split(",");
                userList.add(new UserDTO(HtmlUtils.htmlEscape(variable[0]),Double.parseDouble(variable[1])));
            }

        }catch(Exception e){
            throw new StorageException("Invalid Input in file: " + file.getOriginalFilename(), e);
        }

        return userList;
    }
}
