package fr.balijon.centrale.responses;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private List<String> errors;
    private int status = 999;
    private Object object = null;

}
