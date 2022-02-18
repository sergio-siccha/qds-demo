package com.techprobe.qds.controller;

import com.techprobe.qds.constant.HttpResponseCTE;
import com.techprobe.qds.models.HttpResponse;
import com.techprobe.qds.models.Meta;
import com.techprobe.qds.service.MatrixServiceImpl;
import com.techprobe.qds.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("qds-api/v1/matrix")
public class MatrixController {

    private MatrixServiceImpl matrixService;

    @Autowired
    public MatrixController (MatrixServiceImpl matrixService) {
        this.matrixService = matrixService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public HttpResponse rotate(@RequestBody List<List<Integer>> inputArrays) {
        Object serviceResponse = matrixService.rotate(inputArrays);

        if (serviceResponse instanceof List) {
            return new HttpResponse(Util.findByCode(HttpResponseCTE.ROTATION_SUCCESS.getCode()), serviceResponse);
        } else {
            return new HttpResponse((Meta) serviceResponse, null);
        }
    }


}
