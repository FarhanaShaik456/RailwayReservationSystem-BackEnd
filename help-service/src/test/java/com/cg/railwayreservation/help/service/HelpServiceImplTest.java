package com.cg.railwayreservation.help.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.railwayreservation.help.exception.HelpException;
import com.cg.railwayreservation.help.model.HelpModel;
import com.cg.railwayreservation.help.repository.HelpRepository;
import com.cg.railwayreservation.help.serviceImpl.HelpServiceImpl;


@SpringBootTest
public class HelpServiceImplTest {

    @InjectMocks
    private HelpServiceImpl helpService;

    @Mock
    private HelpRepository helpRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddIssueWithValidInput() {
        HelpModel helpModel = new HelpModel();
        helpModel.setIssue("Test issue");

        when(helpRepository.insert(helpModel)).thenReturn(helpModel);

        String response = helpService.addissue(helpModel);

        assertEquals("Apologies for hearing the Issue. Our Admin will look into these", response);
        verify(helpRepository, times(1)).insert(helpModel);
    }

    @Test
    public void testAddIssueWithEmptyIssue() {
        HelpModel helpModel = new HelpModel();
        helpModel.setIssue("");

        assertThrows(HelpException.class, () -> helpService.addissue(helpModel));
        verify(helpRepository, never()).insert(helpModel); 
    }

    @Test
    public void testGetAllIssuesWithEmptyList() {
        when(helpRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(HelpException.class, () -> helpService.getAllIssues());
        verify(helpRepository, times(1)).findAll();
    }


    @Test
    public void testUpdateIssueWithNonExistingIssue() {
        String username = "nonExistingUser";
        HelpModel helpModel = new HelpModel();

        when(helpRepository.findByUsername(username)).thenReturn(null);

        assertThrows(HelpException.class, () -> helpService.updateIssue(helpModel, username));
       // verify(helpRepository, never()).save(helpModel); // Ensure repository method was never called
    }


}
