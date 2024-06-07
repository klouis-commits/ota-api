package ph.com.ota.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class NotesDTOTest {

    @Test
    public void testCreateFromUpdateNotes() {
        // Given
        String id = "123";
        UpdateNotesDTO updateNotesDTO = new UpdateNotesDTO();
        updateNotesDTO.setNote("This is a test note.");

        // When
        NotesDTO result = NotesDTO.createFromUpdateNotes(id, updateNotesDTO);

        // Then
        assertNotNull("Result should not be null", result);
        assertEquals("ID should be correctly parsed and set", Integer.parseInt(id), result.getId().intValue());
        assertEquals("Note should be correctly set", "This is a test note.", result.getNote());
    }
}