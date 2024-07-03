package rafael.rest;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String mensagem){
        this.errors = Arrays.asList(mensagem);
    }

    public ApiErrors(List<String> erros) {
        this.errors = erros;
    }
}
