package com.yongpeng.dev.cditweb.cditweb;

import com.yongpeng.dev.cditweb.DTO.UserDTO;
import com.yongpeng.dev.cditweb.Service.FileSystemProcessingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileSystemProcessingServiceTest {
    private FileSystemProcessingService service;

    @Before
    public void init() {
        service = new FileSystemProcessingService();
    }

    @Test
    public void ProcessFile(){
        List<UserDTO> result = service.processFile(new MockMultipartFile("Test","Test.csv", MediaType.TEXT_PLAIN_VALUE,"Test Name, 1000.0".getBytes()));
        UserDTO user = new UserDTO("Test Name",1000.0);
        assertThat(result.get(0).equals(user ));
    }

    @Test
    public void ProcessInvalidInputFile(){
        try {
            List<UserDTO> result = service.processFile(new MockMultipartFile("Test", "Test.csv", MediaType.TEXT_PLAIN_VALUE, "Test Name, Test Name".getBytes()));
        }catch(Exception e){
            assertThat(e.getMessage().equals("Invalid Input in file: Test.csv"));
        }
    }
}
