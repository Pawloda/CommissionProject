package com.task.commissions.validation;

import com.task.commissions.exception.BusinessException;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class StrategyValidator {
    
    /**
     * Method throws proper exception if there is something missing in input ids.
     *
     * @param ids given value.
     * @return true if every given value is valid.
     * @throws BusinessException if data are incorrect.
     */
    public static boolean isValid(List<String> ids) throws BusinessException {
        if(ids.size() == 0) {
            throw new BusinessException("There are no ids!");
        }
        for(String id: ids) {
            if(!id.matches("[0-9]") && !Objects.equals("all", id.toLowerCase(Locale.ROOT))) {
                throw new BusinessException("Invalid data!");
            }
        }
        return true;
    }

}
