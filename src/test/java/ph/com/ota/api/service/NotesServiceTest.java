package ph.com.ota.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webjars.NotFoundException;

import ph.com.ota.api.dto.NotesDTO;

public class NotesServiceTest {

	private NotesService notesService;

	private NotesDTO generateNotesDTO() {
		NotesDTO note = new NotesDTO();
		note.setId(1);
		note.setNote("this is a note");
		return note;
		
	}
	
	@BeforeEach
	public void setUp() {
		notesService = new NotesService();
	}

	@Test
	public void testGetAllNotes_emptyList() {
		List<NotesDTO> notes = notesService.getAllNotes();
		assertTrue(notes.isEmpty());
	}

	@Test
	public void testGetAllNotes_nonEmptyList() {
		NotesDTO note = generateNotesDTO();
		notesService.addNotes(note);

		List<NotesDTO> notes = notesService.getAllNotes();

		assertEquals(1, notes.size());
		assertEquals(note, notes.get(0));
	}

	@Test
	public void testGetNotesById_existingId() {
		NotesDTO note = generateNotesDTO();
		notesService.addNotes(note);

		NotesDTO retrievedNote = notesService.getNotesById("1");

		assertEquals(note, retrievedNote);
	}

	@Test
	public void testGetNotesById_nonExistingId() {
		NotFoundException thrown = assertThrows(
				"Expected getNotesById() to throw, but it didn't",
				NotFoundException.class,
				() -> notesService.getNotesById("1"));

		assertTrue(thrown.getMessage().contains("Could not find note with ID: 1"));
	}

	@Test
	public void testGetNotesById_invalidIdFormat() {
		assertThrows(
				"Expected getNotesById() to throw, but it didn't",
				NumberFormatException.class,
				() -> notesService.getNotesById("invalid"));
	}

	@Test
	public void testAddNotes_existingId() {
		NotesDTO note = generateNotesDTO();
		notesService.addNotes(note);

		KeyAlreadyExistsException thrown = assertThrows(
				"Expected addNotes() to throw, but it didn't",
				KeyAlreadyExistsException.class,
				() -> notesService.addNotes(note));

		assertTrue(thrown.getMessage().contains("Notes with ID already existing: 1"));
	}

	@Test
	public void testUpdateNotes_success() {
		NotesDTO note = generateNotesDTO();
		notesService.addNotes(note);

		NotesDTO updatedNote = generateNotesDTO();
		updatedNote.setNote("updated note");
		notesService.updateNotes(updatedNote);

		NotesDTO retrievedNote = notesService.getNotesById("1");
		assertEquals(updatedNote.getNote(), retrievedNote.getNote());
	}

	@Test
	public void testUpdateNotes_nonExistingId() {
		NotesDTO note = generateNotesDTO();

		NotFoundException thrown = assertThrows(
				"Expected updateNotes() to throw, but it didn't",
				NotFoundException.class,
				() -> notesService.updateNotes(note));

		assertTrue(thrown.getMessage().contains("Could not find note with ID: 1"));
	}

	@Test
	public void testDeleteNotes_success() {
		NotesDTO note = generateNotesDTO();
		notesService.addNotes(note);

		notesService.deleteNotes("1");

		NotFoundException thrown = assertThrows(
				"Expected getNotesById() to throw, but it didn't",
				NotFoundException.class,
				() -> notesService.getNotesById("1"));

		assertTrue(thrown.getMessage().contains("Could not find note with ID: 1"));
	}

	@Test
	public void testDeleteNotes_invalidIdFormat() {
		assertThrows(
				"Expected deleteNotes() to throw, but it didn't",
				NumberFormatException.class,
				() -> notesService.deleteNotes("invalid"));
	}
}