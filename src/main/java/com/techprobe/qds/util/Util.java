package com.techprobe.qds.util;

import com.techprobe.qds.constant.HttpResponseCTE;
import com.techprobe.qds.models.Meta;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Util {
    public static Meta findByCode(String responseCode) {
        Meta meta = new Meta(HttpResponseCTE.GENERIC_ERROR.getCode(), HttpResponseCTE.GENERIC_ERROR.getMessage());

        for (HttpResponseCTE error : HttpResponseCTE.values()) {
            if (error.getCode().equals(responseCode))
                meta = new Meta(error.getCode(), error.getMessage());
        }

        return meta;
    }

    public static void validateArray (List<List<Integer>> inputArray) throws Exception {
        log.info("Validando arreglo");
        int rowNumber = inputArray.size();
        if (rowNumber == 0) throw new Exception(HttpResponseCTE.EMPTY_ARRAY.getCode());
        boolean isNxNMatrix = inputArray.stream().allMatch(array -> array.size() == rowNumber);
        if (!isNxNMatrix) throw new Exception(HttpResponseCTE.NOT_NXN_MATRIX.getCode());
    }

    public static List<List<Integer>> rotateMatrix (List<List<Integer>> inputArray) {
        log.info("Rotando arreglo");
        int numberOfRows = inputArray.size();
        int lastItem = numberOfRows;
        List<List<Integer>> resultArray = new ArrayList<>();
        for(int i = 0; i < numberOfRows ; i++) {
            int finalLastItem = --lastItem;
            List<Integer> result = inputArray
                    .stream().map(list -> list.get(finalLastItem)).collect(Collectors.toList());
            resultArray.add(result);
        }
        return resultArray;
    }
}
