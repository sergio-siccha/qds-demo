package com.techprobe.qds.service;

import com.techprobe.qds.models.Meta;
import com.techprobe.qds.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MatrixServiceImpl implements MatrixService{

    @Override
    public Object rotate(List<List<Integer>> inputArrays) {
        Meta responseError;

        try {
            Util.validateArray(inputArrays);
            return Util.rotateMatrix(inputArrays);
        } catch (Exception e) {
            responseError = Util.findByCode(e.getMessage());
            log.error("Ocurrio un error al procesar la rotacion. [{}]", responseError.getMessage());
        }

        return responseError;
    }

}
