package ua.shield.api.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.ChiledEntityDtoConverter;
import ua.shield.dto.ChieldDto;
import ua.shield.entity.Chield;
import ua.shield.entity.Patient;
import ua.shield.service.ChieldService;
import ua.shield.service.PatientService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/secured/v1/chield", produces = "application/json")
public class ChieldSecApiController {
    private final PatientService patientService;
    private final ChiledEntityDtoConverter chieldConverter;
    private final ChieldService chieldService;

    @Autowired
    public ChieldSecApiController(PatientService patientService, ChiledEntityDtoConverter chieldConverter, ChieldService chieldService) {
        this.patientService = patientService;
        this.chieldConverter = chieldConverter;
        this.chieldService = chieldService;
    }

    @RequestMapping(value = "/listdata",method= RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<List<ChieldDto>> listdata(Principal principal){
        Set<Chield> chields = patientService.findByName(principal.getName()).getChields();
        if(chields.size()>0){
            return new ResponseEntity<>(chieldConverter.createFromEntities(chields),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public ResponseEntity<ChieldDto> add(@RequestBody ChieldDto chieldDto, Principal principal) {
        Patient patient=patientService.findByName(principal.getName());
        Chield chield = chieldConverter.createFromDto(chieldDto);
        chield.setPatient(patient);
        Chield savedChield=chieldService.add(chield);
        return new ResponseEntity<>(chieldConverter.createFromEntity(savedChield),HttpStatus.OK);
    }
}

