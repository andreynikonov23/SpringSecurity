package nick.pack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private int id;
    private String firstname;
    private String lastname;

    @Override
    public String toString(){
        return String.format("DEV(%d, %s, %s); ", id, firstname, lastname);
    }
}
