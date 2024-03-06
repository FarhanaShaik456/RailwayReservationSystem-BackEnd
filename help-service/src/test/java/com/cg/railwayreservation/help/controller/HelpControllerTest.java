package com.cg.railwayreservation.help.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.railwayreservation.help.controller.HelpControler;
import com.cg.railwayreservation.help.exception.HelpException;
import com.cg.railwayreservation.help.model.HelpModel;
import com.cg.railwayreservation.help.serviceImpl.HelpServiceImpl;
import com.cg.railwayreservation.help.vo.LoginModel;
import com.cg.railwayreservation.help.vo.UserIssueVO;

@SpringBootTest
public class HelpControllerTest {

    @InjectMocks
    private HelpControler helpController;

    @Mock
    private HelpServiceImpl helpService;

    @Test
    public void testAddIssueWithValidInput() {
        HelpModel helpModel = new HelpModel();
        helpModel.setIssue("new issue");

        when(helpService.addissue(helpModel)).thenReturn("Apologies for hearing the Issue. Our Admin will look into these");

        ResponseEntity<String> response = helpController.addissue(helpModel);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apologies for hearing the Issue. Our Admin will look into these", response.getBody());
    }

      @Test
    public void testUpdateIssueWithValidInput() {
        HelpModel helpModel = new HelpModel();
        helpModel.setIssue("Test issue");
        String username = "testUser";

        when(helpService.updateIssue(helpModel, username)).thenReturn(helpModel);

        ResponseEntity<HelpModel> response = helpController.updateIssue(helpModel, username);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(helpModel, response.getBody());
    }
      
      @Test
      public void testGetAllIssuesEmpty() {
          when(helpService.getAllIssues()).thenThrow(new HelpException("Data is not Found"));

          HelpException exception = assertThrows(HelpException.class, () -> helpController.getAllIssues());
          assertEquals("Data is not Found", exception.getMessage());
          verify(helpService, times(1)).getAllIssues();
      }

      @Test
      public void testGetAllIssues_Success() {
          // Create a list of HelpModel objects for testing
          List<HelpModel> helpList = new ArrayList<>();
          HelpModel issue1 = new HelpModel();
          issue1.setUsername("user1");
          issue1.setIssue("Issue 1");
          issue1.setStatus("Resolved");
          issue1.setSolution("Solution 1");

          HelpModel issue2 = new HelpModel();
          issue2.setUsername("user2");
          issue2.setIssue("Issue 2");
          issue2.setStatus("Processing");
          issue2.setSolution("Solution 2");

          helpList.add(issue1);
          helpList.add(issue2);

          when(helpService.getAllIssues()).thenReturn(helpList);

      
          ResponseEntity<List<HelpModel>> responseEntity = helpController.getAllIssues();

          // Assert that the response is not null
          assertNotNull(responseEntity);
          assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

          // Assert that the response body contains the list of HelpModel objects
          List<HelpModel> responseBody = responseEntity.getBody();
          assertNotNull(responseBody);
          assertEquals(2, responseBody.size());

      }
      
      @Test
      public void testGetByUsername() {
          String username = "user1";

          LoginModel loginModel = new LoginModel();
          loginModel.setUsername(username);
       
          List<HelpModel> helpModels = new ArrayList<>();
         
          UserIssueVO userIssueVO = new UserIssueVO();
          userIssueVO.setLoginModel(loginModel);
          userIssueVO.setHelpModel(helpModels);        
          when(helpService.getByUsername(username)).thenReturn(userIssueVO);
         
          ResponseEntity<UserIssueVO> responseEntity = helpController.getByUsername(username);
          assertNotNull(responseEntity);

          assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
      }
      
      @Test
      public void testUpdateIssue_Success() {
          HelpModel helpModel = new HelpModel();
          String issueId = "issue123";
          when(helpService.updateIssue(helpModel, issueId)).thenReturn(helpModel);
          ResponseEntity<HelpModel> responseEntity = helpController.updateIssue(helpModel, issueId);
          assertNotNull(responseEntity);
          assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); 
      }
     
      
}

