package com.example.demo.Controller;

import com.example.demo.DTO.DetailPersonWorkDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.DTO.ResponseFrontendDefine;
import com.example.demo.DTO.ResponseModel;
import com.example.demo.Exception.RalException;
import com.example.demo.Model.DetailPerSonWork;
import com.example.demo.Model.Person;
import com.example.demo.Service.DetailPersonWorkService;
import com.example.demo.Service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("person-work")
public class DetailPersonWorkController {
    DetailPersonWorkService service;

    @GetMapping("/all")
    public ResponseModel getAll()
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            List<DetailPerSonWork> list = service.getAll();

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
    public ResponseModel doCreate(@RequestBody DetailPersonWorkDTO dto)
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            DetailPerSonWork detailPerSonWork = service.create(dto);
            if(detailPerSonWork != null) {
                responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            }else {
                responseModel.setCode(59);
            }
            responseModel.setContent(detailPerSonWork);
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
            responseModel.setContent("Delete detail by id successful .");
        }catch (Exception e)
        {
            System.out.println("delete detail fail " +e);
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
            DetailPerSonWork detailPerSonWork = service.getById(id).get();
            if(detailPerSonWork == null){
                throw new RalException(200, ResponseFrontendDefine.CODE_NOT_FOUND,
                        "can't found user with id " + id);
            }
            responseModel.setContent(detailPerSonWork);
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
    public ResponseModel doUpdate(@RequestBody DetailPersonWorkDTO dto)
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            DetailPerSonWork detailPerSonWork = service.update(dto, dto.getId());

            if(detailPerSonWork == null)
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
