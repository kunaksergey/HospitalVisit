package ua.shield.api;

import org.jcodec.common.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.shield.entity.User;
import ua.shield.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    private final String DEFAULT_PHOTO = "static/img/no_photo.png";
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] load(@PathVariable Integer userId) {
        User user = userService.findOne(userId);
        if (user.getPhoto() != null && user.getPhoto().length > 0) {
            return user.getPhoto();
        } else {

            try {
                return IOUtils.toByteArray(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_PHOTO));
            } catch (IOException e) {
                return new byte[]{};

            }
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    ResponseEntity<String> upload(@RequestParam("userId") Integer userId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
        User user = userService.findOne(userId);
        try {
            user.setPhoto(file.getBytes());
            userService.update(user);
            return new ResponseEntity<>("Successfully uploaded", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
