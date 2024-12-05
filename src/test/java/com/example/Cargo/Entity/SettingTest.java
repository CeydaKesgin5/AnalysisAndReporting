package com.example.Cargo.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingTest {

    @Test
    void testSettingEntity() {
        Setting setting = new Setting();
        setting.setSettingKey("theme");
        setting.setSettingValue("dark");

        // Assertions
        assertEquals("theme", setting.getSettingKey());
        assertEquals("dark", setting.getSettingValue());
    }
}
