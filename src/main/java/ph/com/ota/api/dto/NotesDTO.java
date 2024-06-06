package ph.com.ota.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * NotesDTO class represents a Data Transfer Object for notes. It contains
 * fields for the note's ID and the note content, with validation annotations.
 * The class also provides a method to create a NotesDTO object from an
 * UpdateNotesDTO object.
 */
@Data
public class NotesDTO {

	@NotNull(message = "Parameter 'id' is required")
	private Integer id;

	@NotBlank(message = "Parameter 'note' is required")
	private String note;

	/**
	 * Creates a NotesDTO object from an UpdateNotesDTO object.
	 * 
	 * @param id             the ID of the note as a String
	 * @param updateNotesDTO the UpdateNotesDTO object containing the updated note
	 *                       information
	 * @return a NotesDTO object with the given ID and updated note content
	 */
	public static NotesDTO createFromUpdateNotes(String id, UpdateNotesDTO updateNotesDTO) {
		NotesDTO notesDTO = new NotesDTO();
		notesDTO.id = Integer.parseInt(id);
		notesDTO.note = updateNotesDTO.getNote();
		return notesDTO;
	}
}
