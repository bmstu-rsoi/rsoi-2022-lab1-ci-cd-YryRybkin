package bmstu.student.rybkin.lab1application.models;

import java.util.Objects;

public class ErrorResponse {

    protected final String message;

    public String getMessage() {

        return this.message;

    }

    public ErrorResponse(String message) {

        this.message = message;

    }

    @Override
    public boolean equals(Object comp) {

        if (this == comp) {

            return true;

        }

        if (!(comp instanceof ErrorResponse)) {

            return false;

        }

        ErrorResponse errorResponse = (ErrorResponse) comp;

        return Objects.equals(this.message, errorResponse.message);

    }

    @Override
    public int hashCode() {

        return Objects.hash(this.message);

    }

}
