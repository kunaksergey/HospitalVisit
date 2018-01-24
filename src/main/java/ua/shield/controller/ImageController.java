package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.shield.entity.Doctor;
import ua.shield.entity.User;
import ua.shield.service.UserService;
import ua.shield.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class ImageController {
    @Autowired
    UserService userService;

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

//        byte[] image = userService.findOne(id).getPhoto();


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    ResponseEntity<String> upload(@RequestParam("userId") Integer userId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
        User user = userService.findOne(userId);
        try {
            user.setPhoto(file.getBytes());
            userService.update(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Doctor doctor = doctorService.findOne(doctorId);
//        try {
//            doctor.getUser().setPhoto(imageFile.getBytes());
//            doctorService.update(doctor);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        System.out.println();
//        return new ResponseEntity<>("/image/doctor/" + doctorId, HttpStatus.OK);
        return null;
    }
}
