package ua.shield.api.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    private final ErrorResponseFactory errorResponseFactory;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ChieldDtoValidator());
    }

    @Autowired
    public ChieldSecApiController(PatientService patientService,
                                  ChiledEntityDtoConverter chieldConverter,
                                  ChieldService chieldService,
                                  ErrorResponseFactory errorResponseFactory) {
        this.patientService = patientService;
        this.chieldConverter = chieldConverter;
        this.chieldService = chieldService;
        this.errorResponseFactory = errorResponseFactory;
    }

    @RequestMapping(value = "/listdata/patient", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<List<ChieldDto>> listdata(Principal principal) {
        Set<Chield> chields = patientService.findByName(principal.getName()).getChields();
        if (chields.size() > 0) {
            return  ResponseEntity.ok(chieldConverter.createFromEntities(chields));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> add(@Validated @RequestBody ChieldDto chieldDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            ErrorResponseDto errorResponse = errorResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, "bad request", bindingResult.getFieldErrors());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Patient patient = patientService.findByName(principal.getName());
        Chield chield = chieldConverter.createFromDto(chieldDto);
        chield.setPatient(patient);
        Chield savedChield = chieldService.add(chield);
        return ResponseEntity.ok(chieldConverter.createFromEntity(savedChield));
    }
}

