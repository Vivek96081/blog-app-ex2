package blogapp.payload;

import lombok.Data;

import java.util.List;

@Data
public class ListMoonDto {

    List<MoonDto> moonDtos;

    private  int totalElements;
    private int totalPages;
    private int pageSize;
    private boolean firstPage;
    private boolean lastPage;

}
