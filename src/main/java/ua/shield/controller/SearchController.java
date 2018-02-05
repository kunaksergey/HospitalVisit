package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.shield.domen.AjaxResponseBody;
import ua.shield.domen.SearchCriteria;
import ua.shield.dto.DoctorDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Specialization;
import ua.shield.entity.User;
import ua.shield.service.DoctorService;
import ua.shield.service.SpecializationService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final DoctorService doctorService;

    @Autowired
    public SearchController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "name", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<DoctorDto> searchName(@ModelAttribute SearchCriteria searchCriteria,
                                      BindingResult result) {
        List<Doctor> doctors;
        if (searchCriteria.getDistrict() == null) {
            doctors = doctorService.findAllByFullNameStartsWith(searchCriteria.getSearchStr());
        } else {
            doctors = doctorService.findAllByFullNameStartsWithAndDistrict(searchCriteria.getSearchStr(), searchCriteria.getDistrict());
        }
        return doctors.stream().map(DoctorDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "spec", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<DoctorDto> searchSpec(@ModelAttribute SearchCriteria searchCriteria,
                                     BindingResult result
    ) {
        List<Doctor> doctors;
        if (searchCriteria.getDistrict() == null) {
            doctors = doctorService.findAllBySpecializationStartsWith(searchCriteria.getSearchStr());
        } else {
            doctors = doctorService.findAllBySpecializationStartsWithAndDistrict(searchCriteria.getSearchStr(), searchCriteria.getDistrict());
        }
        List<DoctorDto> l=doctors.stream().map(DoctorDto::new).collect(Collectors.toList());
        return l;
    }
}
