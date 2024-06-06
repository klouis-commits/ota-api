package ph.com.ota.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * UpdateNotesDTO class represents a Data Transfer Object for updating notes. It
 * contains a single field for the updated note content, with a validation
 * annotation to ensure the note is not blank.
 */
@Data
public class UpdateNotesDTO {

	@NotBlank(message = "Parameter 'note' is required")
	private String note;

}
