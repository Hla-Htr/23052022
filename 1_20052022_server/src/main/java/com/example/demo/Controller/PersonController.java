package com.example.demo.Controller;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.DTO.ResponseFrontendDefine;
import com.example.demo.DTO.ResponseModel;
import com.example.demo.Exception.RalException;
import com.example.demo.Model.Person;
import com.example.demo.Model.Work;
import com.example.demo.Service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    PersonService service;

    @GetMapping("/all")
    public ResponseModel getAll()
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            List<Person> list = service.getAllPerson();

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
    public ResponseModel doCreate(@RequestBody PersonDTO dto)
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            Person person = service.create(dto);
            if(person != null) {
                responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            }else {
                responseModel.setCode(59);
            }
            responseModel.setContent(person);
            responseModel.setStatusCode(200);
        }catch (Exception e)
        {
            System.out.println(e);
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
            responseModel.setContent("Delete work by id successful .");
        }catch (Exception e)
        {
            System.out.println("delete work fail " +e);
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
            Person person = service.getPersonById(id).get();
            if(person == null){
                throw new RalException(200, ResponseFrontendDefine.CODE_NOT_FOUND,
                        "can't found user with id " + id);
            }
            responseModel.setContent(person);
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

    @PutMapping("/update")
    public ResponseModel doUpdate(@RequestBody PersonDTO personDTO)
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            Person person = service.update(personDTO, personDTO.getId());

            if(person == null)
            {
                System.out.println("update work fail.");
            }
            responseModel.setStatusCode(200);
            responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            responseModel.setContent("Update successful");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            sw.stop();
        }

        return responseModel;
    }
}
