package fr.balijon.centrale.responses;

import lombok.*;




@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response {

    private int status = 999;

    private String message = "Interessant...";

    private Object object = null;

    public Response(int status, String message)
    {
        this.status = status;
        this.message = message;
    }
    public Response(int status, String message, Object object)
    {
        this(status,message);
        this.object = object;
    }

}
