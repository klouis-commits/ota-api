package ph.com.ota.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import ph.com.ota.api.dto.NotesDTO;

/**
 * NotesService class provides functionalities to manage notes.
 * It allows adding, updating, retrieving, and deleting notes.
 * Notes are stored in a map where the key is the note ID and the value is the NotesDTO object.
 * This class handles operations related to notes and ensures appropriate exceptions are thrown for error cases.
 */
@Service
public class NotesService {

	// Constants for error messages
	private final static String EXISTING_NOTE = "Notes with ID already existing: ";
	private final static String NOT_FOUND_NOTE = "Could not find note with ID: ";

	// A map to store notes, where the key is the note ID
	private Map<Integer, NotesDTO> notes = new HashMap<>();

	/**
	 * Retrieves all notes.
	 *
	 * @return a list of all NotesDTO objects
	 */
	public List<NotesDTO> getAllNotes() {
		return new ArrayList<>(notes.values());
	}

	/**
	 * Retrieves a note by its ID.
	 *
	 * @param id the ID of the note as a String
	 * @return the NotesDTO object corresponding to the ID
	 * @throws NumberFormatException if the ID is not a valid integer
	 * @throws NotFoundException     if no note with the given ID is found
	 */
	public NotesDTO getNotesById(String id) throws NumberFormatException, NotFoundException {
		NotesDTO notesDTO = null;
		int idNum = Integer.parseInt(id);
		if (notes.containsKey(idNum)) {
			notesDTO = notes.get(idNum);
		} else {
			throw new NotFoundException(NOT_FOUND_NOTE + id);
		}
		return notesDTO;
	}

	/**
	 * Adds a new note.
	 *
	 * @param notesDTO the NotesDTO object to be added
	 * @throws KeyAlreadyExistsException if a note with the same ID already exists
	 */
	public void addNotes(NotesDTO notesDTO) throws KeyAlreadyExistsException {
		final int id = notesDTO.getId();
		if (notes.containsKey(notesDTO.getId())) {
			throw new KeyAlreadyExistsException(EXISTING_NOTE + id);
		} else {
			notes.put(id, notesDTO);
		}
	}

	/**
	 * Updates an existing note.
	 *
	 * @param notesDTO the NotesDTO object with updated information
	 * @throws NotFoundException if no note with the given ID is found
	 */
	public void updateNotes(NotesDTO notesDTO) {
		final int id = notesDTO.getId();
		if (notes.containsKey(id)) {
			notes.put(id, notesDTO);
		} else {
			throw new NotFoundException(NOT_FOUND_NOTE + id);
		}
	}

	/**
	 * Deletes a note by its ID.
	 *
	 * @param id the ID of the note as a String
	 * @throws NumberFormatException if the ID is not a valid integer
	 * @throws NotFoundException     if no note with the given ID is found
	 */
	public void deleteNotes(String id) {
		final int idNum = Integer.parseInt(id);
		if (notes.containsKey(idNum)) {
			notes.remove(idNum);
		} else {
			throw new NotFoundException(NOT_FOUND_NOTE + id);
		}
	}
}
