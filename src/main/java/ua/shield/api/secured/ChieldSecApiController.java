package ua.shield.api.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.ChiledEntityDtoConverter;
import ua.shield.dto.ChieldDto;
import ua.shield.dto.ErrorResponseDto;
import ua.shield.entity.Chield;
import ua.shield.entity.Patient;
import ua.shield.factory.ErrorResponseFactory;
import ua.shield.service.ChieldService;
import ua.shield.service.PatientService;
import ua.shield.validator.ChieldDtoValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/secured/v1/chield", produces = "application/json")
public class ChieldSecApiController {
    private final PatientService patientService;
    private final ChiledEntityDtoConverter chieldConverter;
    private final ChieldService chieldService;
    private final ChieldDtoValidator chieldDtoValidator;
    private final ErrorResponseFactory errorResponseFactory;


    @Autowired
    public ChieldSecApiController(PatientService patientService,
                                  ChiledEntityDtoConverter chieldConverter,
                                  ChieldService chieldService,
                                  ErrorResponseFactory errorResponseFactory,
                                  ChieldDtoValidator chieldDtoValidator
    ) {
        this.patientService = patientService;
        this.chieldConverter = chieldConverter;
        this.chieldService = chieldService;
        this.errorResponseFactory = errorResponseFactory;
        this.chieldDtoValidator = chieldDtoValidator;
    }

    @RequestMapping(value = "/listdata/patient", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<List<ChieldDto>> listdata(Principal principal) {
        Set<Chield> chields = patientService.findByName(principal.getName()).getChields();
        if (chields.size() > 0) {
            return new ResponseEntity<>(chieldConverter.createFromEntities(chields), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity add(@RequestBody ChieldDto chieldDto, BindingResult bindingResult, Principal principal) {
        chieldDtoValidator.validate(chieldDto, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponseDto errorResponse = errorResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, "bad request", bindingResult.getFieldErrors());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
         }
        Patient patient = patientService.findByName(principal.getName());
        Chield chield = chieldConverter.createFromDto(chieldDto);
        chield.setPatient(patient);
        Chield savedChield = chieldService.add(chield);
        return new ResponseEntity<>(chieldConverter.createFromEntity(savedChield), HttpStatus.OK);
    }
}

