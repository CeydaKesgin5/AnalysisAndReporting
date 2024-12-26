package com.example.Cargo.Controller;

import com.example.Cargo.DTO.SettingDto;
import com.example.Cargo.Entity.Setting;
import com.example.Cargo.Service.SettingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SettingController.class)
public class TestSettingController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SettingService settingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllSettings() throws Exception {
        Setting setting1 = new Setting();
        setting1.setId(1L);
        setting1.setSettingKey("key1");
        setting1.setSettingValue("value1");

        Setting setting2 = new Setting();
        setting2.setId(2L);
        setting2.setSettingKey("key2");
        setting2.setSettingValue("value2");

        when(settingService.getAllSettings()).thenReturn(Arrays.asList(setting1, setting2));

        mockMvc.perform(get("/api/settings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].settingKey", is("key1")))
                .andExpect(jsonPath("$[1].settingKey", is("key2")));

        verify(settingService, times(1)).getAllSettings();
    }

    @Test
    void testGetSettingById() throws Exception {
        Setting setting = new Setting();
        setting.setId(1L);
        setting.setSettingKey("key1");
        setting.setSettingValue("value1");

        when(settingService.getSettingById(1L)).thenReturn(Optional.of(setting));

        mockMvc.perform(get("/api/settings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.settingKey", is("key1")))
                .andExpect(jsonPath("$.settingValue", is("value1")));

        verify(settingService, times(1)).getSettingById(1L);
    }

    @Test
    void testGetSettingById_NotFound() throws Exception {
        when(settingService.getSettingById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/settings/1"))
                .andExpect(status().isNotFound());

        verify(settingService, times(1)).getSettingById(1L);
    }

    @Test
    void testCreateSetting() throws Exception {
        SettingDto settingDto = new SettingDto();
        Setting createdSetting = new Setting();
        createdSetting.setId(1L);
        createdSetting.setSettingKey("key1");
        createdSetting.setSettingValue("value1");

        when(settingService.createSetting(any(SettingDto.class))).thenReturn(createdSetting);

        mockMvc.perform(post("/api/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(settingDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.settingKey", is("key1")))
                .andExpect(jsonPath("$.settingValue", is("value1")));

        verify(settingService, times(1)).createSetting(any(SettingDto.class));
    }

    @Test
    void testUpdateSetting() throws Exception {
        SettingDto settingDto = new SettingDto();
        Setting updatedSetting = new Setting();
        updatedSetting.setId(1L);
        updatedSetting.setSettingKey("key1");
        updatedSetting.setSettingValue("updatedValue");

        when(settingService.updateSetting(eq(1L), any(SettingDto.class))).thenReturn(updatedSetting);

        mockMvc.perform(put("/api/settings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(settingDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.settingValue", is("updatedValue")));

        verify(settingService, times(1)).updateSetting(eq(1L), any(SettingDto.class));
    }

    @Test
    void testUpdateSetting_NotFound() throws Exception {
        SettingDto settingDto = new SettingDto();

        when(settingService.updateSetting(eq(1L), any(SettingDto.class))).thenThrow(new RuntimeException("Setting not found"));

        mockMvc.perform(put("/api/settings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(settingDto)))
                .andExpect(status().isNotFound());

        verify(settingService, times(1)).updateSetting(eq(1L), any(SettingDto.class));
    }

    @Test
    void testDeleteSetting() throws Exception {
        doNothing().when(settingService).deleteSetting(1L);

        mockMvc.perform(delete("/api/settings/1"))
                .andExpect(status().isNoContent());

        verify(settingService, times(1)).deleteSetting(1L);
    }
}
