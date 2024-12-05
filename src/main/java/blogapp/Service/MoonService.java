
package blogapp.Service;

import blogapp.payload.ListMoonDto;
import blogapp.payload.MoonDto;

public interface MoonService {

    public MoonDto createdata(MoonDto moonDto);

    void deleteData(long id);

   ListMoonDto fetchdataDB(int pageNo, int pageSize, String sortBy, String sortDir);
}
