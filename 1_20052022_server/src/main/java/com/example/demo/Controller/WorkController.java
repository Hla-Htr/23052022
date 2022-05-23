package com.example.demo.Controller;

import com.example.demo.DTO.ResponseFrontendDefine;
import com.example.demo.DTO.ResponseModel;
import com.example.demo.DTO.WorkDTO;
import com.example.demo.Exception.RalException;
import com.example.demo.Model.Work;
import com.example.demo.Service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("work")
@AllArgsConstructor
public class WorkController {
    WorkService service;

    @GetMapping("all")
    public ResponseModel getAll()
    {
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            List<Work> list = service.getAllWork();
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
    public ResponseModel doCreate(@RequestBody WorkDTO dto){
        StopWatch sw = new StopWatch();
        sw.start();
        ResponseModel responseModel = new ResponseModel();
        try {
            Work newWork = service.create(dto);
            if(newWork != null) {
                responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            }else {
                responseModel.setCode(59);
            }
            responseModel.setContent(newWork);
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
            Work work = service.getWorkById(id).get();
            if(work == null){
                throw new RalException(200, ResponseFrontendDefine.CODE_NOT_FOUND,
                        "can't found user with id " + id);
            }
            responseModel.setContent(work);
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
    public ResponseModel doUpdate(@RequestBody WorkDTO dto)
    {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ResponseModel responseModel = new ResponseModel();
        try{
            Work work = service.update(dto,dto.getId());
            if(work == null)
            {
                System.out.println("update work fail.");
            }
            responseModel.setStatusCode(200);
            responseModel.setCode(ResponseFrontendDefine.CODE_SUCCESS);
            responseModel.setContent("Update successful");
        }catch (Exception e)
        {
            System.out.println("update work fail " + e);
        }finally {
            stopWatch.stop();
        }

        return responseModel;
    }

}
