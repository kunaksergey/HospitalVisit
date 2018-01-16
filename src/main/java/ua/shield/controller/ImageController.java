package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.shield.entity.Doctor;
import ua.shield.service.DoctorService;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    DoctorService doctorService;

//    @RequestMapping(value="/doctor/{doctorId}",method= RequestMethod.GET,produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
//    @ResponseBody
//    public byte[] load(@PathVariable Integer doctorId){
//        System.out.println(new File(".").getPath());
//        File posterFile = new File("../static/img/no_photo.png");
//        InputStream posterFileStream = null;
//        try {
//            posterFileStream = new FileInputStream(posterFile);
//            return IOUtils.toByteArray(posterFileStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        return new byte[]{};
//    }


//        File noPhotoFile = new File("/image/no-photo.png");
//        InputStream imgFileStream = null;
//        try {
//            imgFileStream = new FileInputStream(noPhotoFile);
//            return new ResponseEntity(IOUtils.toByteArray(imgFileStream),HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//           new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

//        byte[] image = userService.findOne(id).getImage();


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    ResponseEntity<String> upload(@RequestParam("doctor-id") Integer doctorId, @RequestParam("file") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
        Doctor doctor = doctorService.findOne(doctorId);
        try {
            doctor.getUser().setImage(imageFile.getBytes());
            doctorService.update(doctor);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("/image/doctor/" + doctorId, HttpStatus.OK);
    }
}
