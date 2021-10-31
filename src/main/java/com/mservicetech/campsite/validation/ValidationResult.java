package com.mservicetech.campsite.validation;

import java.util.ArrayList;
import java.util.List;
import com.mservicetech.campsite.model.Error;

public class ValidationResult {
    boolean isError;
    Class<? extends BaseValidator> validatorClass;
    List<Error> errorCodes;

    public ValidationResult(Class<? extends BaseValidator> validatorClass) {
        isError = false;
        this.validatorClass = validatorClass;
        errorCodes = new ArrayList<>();
    }

    public Class<? extends BaseValidator> getValidatorClass() {
        return validatorClass;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public List<Error> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodesCodes(List<Error> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public void addErrorCodes(Error errorCode) {
        this.errorCodes.add(errorCode);
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "isError=" + isError +
                ", validatorClass=" + validatorClass +
                ", errorCodes=" + errorCodes.toString() +
                '}';
    }
}
