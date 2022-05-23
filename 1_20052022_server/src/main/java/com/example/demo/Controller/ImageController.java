package com.example.demo.Controller;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.DTO.ResponseFrontendDefine;
import com.example.demo.DTO.ResponseModel;
import com.example.demo.Model.Person;
import com.example.demo.Service.PersonService;
import org.apache.commons.io.FilenameUtils;
import com.example.demo.Exception.RalException;
import com.example.demo.Model.Image;
import com.example.demo.Service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("image")
@AllArgsConstructor
public class ImageController {

    ImageService service;
    PersonService personService;

    @GetMapping("all")
    public ResponseModel getAll()
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            List<Image> list = service.getAllImage();
            responseModel.setStatusCode(200);
            responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            responseModel.setContent(list);
        }catch (Exception e)
        {
            System.out.println(e);
        }finally {
            sw.stop();
        }
        return responseModel;
    }

    @PostMapping("/add")
    public ResponseModel doCreate(@RequestParam("personId") Integer personId,
                                  @RequestParam("fileUpload") MultipartFile fileUpload,
                                  @RequestParam("description") String description){
        StopWatch sw = new StopWatch();
        ImageDTO dto = new ImageDTO();
        LocalDateTime myObj = LocalDateTime.now();
        Person person = personService.getPersonById(personId).get();
        dto.setPersonId(personId);
        dto.setDescription(description);
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try {
            Path pathImg = Paths.get("C:\\Users\\hlade\\Downloads");

            InputStream inputStream = fileUpload.getInputStream();
            String ext = FilenameUtils.getExtension(fileUpload.getOriginalFilename());
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyyHHmmss");

            String formattedDate = myObj.format(myFormatObj);
            String nameFile = formattedDate + "-" + person.getName() + "." + ext;
            Files.copy(inputStream,pathImg.resolve(nameFile),
                    StandardCopyOption.REPLACE_EXISTING);
            dto.setLinkImage(nameFile);
            Image image = service.create(dto);
            if(image != null) {
                responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            }else {
                responseModel.setCode(59);
            }
            responseModel.setContent(image);
            responseModel.setStatusCode(200);
        } catch (Exception e) {
            System.err.println(e);
        }finally {
            sw.stop();
        }
        return responseModel;
    }
    @DeleteMapping("delete/{id}")
    public ResponseModel doDelete(@PathVariable Integer id)
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try {
            if(id == null) System.out.println("Not have id");
            service.delete(id);
            responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            responseModel.setStatusCode(200);
            responseModel.setContent("Delete image by id successful .");
        }catch (Exception e)
        {
            System.out.println("delete image fail " +e);
        }finally {
            sw.stop();
        }

        return responseModel;

    }
    @GetMapping("get/{id}")
    public ResponseModel doRetrieve(@PathVariable Integer id) {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try {
            Image image = service.getImageById(id).get();
            if(image == null){
                throw new RalException(200, ResponseFrontendDefine.CODE_NOT_FOUND,
                        "can't found image with id " + id);
            }
            responseModel.setContent(image);
            responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            responseModel.setStatusCode(200);

        } catch (Exception e)
        {
            System.out.println(e);
        }finally {
            sw.stop();
        }

        return responseModel;
    }
    @PutMapping("update")
    public ResponseModel doUpdate(@RequestParam("id") Integer id,
                                  @RequestParam("personId") Integer personId,
                                  @RequestParam("fileUpload") MultipartFile fileUpload,
                                  @RequestParam("description") String description)
    {
        StopWatch sw = new StopWatch();
        ImageDTO dto = new ImageDTO();
        LocalDateTime myObj = LocalDateTime.now();
        Person person = personService.getPersonById(personId).get();
        dto.setPersonId(personId);
        dto.setDescription(description);
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try {
            if(!fileUpload.isEmpty()){
                Path pathImg = Paths.get("C:\\Users\\hlade\\Downloads");
                InputStream inputStream = fileUpload.getInputStream();
                String ext = FilenameUtils.getExtension(fileUpload.getOriginalFilename());
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyyHHmmss");

                String formattedDate = myObj.format(myFormatObj);
                String nameFile = formattedDate + "-" + person.getName() + "." + ext;
                Files.copy(inputStream,pathImg.resolve(nameFile),
                        StandardCopyOption.REPLACE_EXISTING);
                Image image = service.getImageById(id).get();
                String url = "C:\\Users\\hlade\\Downloads\\" + image.getLinkImage();
                File file = new File(url);
                if(file.delete())
                {
                    System.out.println(file.getName() + " is deleted!");
                }else {
                    System.out.println("Delete operation is failed.");
                }

                dto.setLinkImage(nameFile);
            }

            Image image = service.update(dto, id);
            if(image != null) {
                responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            }else {
                responseModel.setCode(59);
            }
            responseModel.setContent(image);
            responseModel.setStatusCode(200);
        } catch (Exception e) {
            System.err.println(e);
        }finally {
            sw.stop();
        }

        return responseModel;
    }
}
