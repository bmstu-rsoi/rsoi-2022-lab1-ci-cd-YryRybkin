package bmstu.student.rybkin.lab1application.webcontroller;

import bmstu.student.rybkin.lab1application.models.ErrorResponse;
import bmstu.student.rybkin.lab1application.models.PersonRequest;
import bmstu.student.rybkin.lab1application.models.PersonResponse;
import bmstu.student.rybkin.lab1application.models.ValidationErrorResponse;
import bmstu.student.rybkin.lab1application.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@Tag(name = "Person REST API operations")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {

        this.personService = personService;

    }

    @Operation(summary = "Get all persons", operationId = "listPersons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Persons",
                content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PersonResponse.class))) })
    })
    @GetMapping
    public List<PersonResponse> getPersons() {

        return personService.getPersons();

    }

    @Operation(summary = "Create new Person", operationId = "createPerson")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created new Person",
                    headers = { @Header(name = "Location", description = "Path to new Person") }),
            @ApiResponse(responseCode = "400", description = "Invalid data",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ValidationErrorResponse.class)) })
    })
    @PostMapping
    public ResponseEntity<String> postPerson(@Valid @RequestBody PersonRequest personRequest) {

        long id = personService.postPerson(personRequest);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()
        ).build();

    }

    @Operation(summary = "Get Person by ID", operationId = "getPerson")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person for ID",
                content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = PersonResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found person for ID",
                content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @GetMapping("/{id}")
    public PersonResponse getPerson(@PathVariable long id) {

        return personService.getPerson(id);

    }

    @Operation(summary = "Remove Person by ID", operationId = "editPerson_1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Person for ID was removed")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) {

        personService.deletePerson(id);

    }

    @Operation(summary = "Update Person by ID", operationId = "editPerson")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person for ID was updated",
                content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PersonResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid data",
                content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ValidationErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found person for ID",
                content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @PatchMapping("/{id}")
    public PersonResponse editPerson(@PathVariable long id, @Valid @RequestBody PersonRequest personRequest) {

        return personService.patchPerson(id, personRequest);

    }

}
