package ph.com.ota.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ph.com.ota.api.dto.NotesDTO;
import ph.com.ota.api.dto.UpdateNotesDTO;
import ph.com.ota.api.service.NotesService;

/**
 * NotesController class handles HTTP requests related to notes. It provides
 * endpoints for creating, updating, retrieving, and deleting notes.
 */
@RestController
@RequestMapping("/")
public class NotesController {

	@Autowired
	private NotesService service;

	@GetMapping(path = "notes")
	public List<NotesDTO> getNotes() {
		return service.getAllNotes();
	}

	@GetMapping(path = "notes/{id}")
	public ResponseEntity<NotesDTO> getNotes(@PathVariable("id") String id) {
		service.getNotesById(id);
		return new ResponseEntity<NotesDTO>(service.getNotesById(id), HttpStatus.OK);
	}

	@Validated
	@PostMapping(path = "notes")
	public ResponseEntity<Void> postNotes(@Valid @RequestBody NotesDTO notesDTO) {
		service.addNotes(notesDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Validated
	@PutMapping(path = "notes/{id}")
	public ResponseEntity<Void> postNotes(@PathVariable("id") String id,
			@Valid @RequestBody UpdateNotesDTO updatesNotesDTO) {

		service.updateNotes(NotesDTO.createFromUpdateNotes(id, updatesNotesDTO));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "notes/{id}")
	public ResponseEntity<Void> deleteNotes(@PathVariable("id") String id) {
		service.deleteNotes(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
