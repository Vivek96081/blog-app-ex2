package blogapp.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MoonDto {

    private long id;
    @NotEmpty
    @Size(min=3,message = "Title Should be Atleast 3 Character")
    private String title;
    @NotEmpty
    @Size(min = 3,message = "Description Should be Atleast 3 characters")
    private String description;
    private String content;
}
