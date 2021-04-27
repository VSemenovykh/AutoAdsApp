package ru.ncedu.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ncedu.model.*;

@ExtendWith(MockitoExtension.class)
class DeleteAutoControllerTest {

    @InjectMocks
    DeleteAutoController deleteAutoController;

    @Test
    public void testDeleteAuto() {
        ResponseEntity<DataAuto> result = deleteAutoController.deleteAuto(1L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
