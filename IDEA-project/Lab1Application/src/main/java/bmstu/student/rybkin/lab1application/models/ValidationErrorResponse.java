package bmstu.student.rybkin.lab1application.models;

import bmstu.student.rybkin.lab1application.jpa.Person;

import java.util.Map;
import java.util.Objects;

public class ValidationErrorResponse extends ErrorResponse {

    private final Map<String, String> errors;

    public Map<String, String> getErrors() {

        return this.errors;

    }

    public ValidationErrorResponse(String message, Map<String, String> errors) {

        super(message);

        this.errors = errors;

    }

    @Override
    public boolean equals(Object comp) {

        if (this == comp) {

            return true;

        }

        if (!(comp instanceof Person)) {

            return false;

        }

        ValidationErrorResponse validationErrorResponse = (ValidationErrorResponse) comp;

        return Objects.equals(this.message, validationErrorResponse.message) &&
                Objects.equals(this.errors, validationErrorResponse.errors);

    }

    @Override
    public int hashCode() {

        return Objects.hash(this.message, this.errors);

    }

}
