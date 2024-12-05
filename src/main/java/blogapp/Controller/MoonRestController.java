package blogapp.Controller;

import blogapp.Service.MoonService;
import blogapp.payload.ListMoonDto;
import blogapp.payload.MoonDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moon")
public class MoonRestController {

    private MoonService moonService;

    public MoonRestController(MoonService moonService) {
        this.moonService = moonService;
    }

    //http://localhost:8080/api/moon
    @PostMapping
    public ResponseEntity<?> savedatabase(@Valid @RequestBody MoonDto moonDto,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){ //It gives true then goes to the if block
           return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MoonDto saveddata = moonService.createdata(moonDto);
   return new ResponseEntity<>(saveddata, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/moon/8
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable long id){
        moonService.deleteData(id);
        return new ResponseEntity<>("Deleted SuccessFully!!!",HttpStatus.OK);
    }
    //http://localhost:8080/api/moon?pageNo=0&pageSize=2&sortBy=description&sortDir=desc
    @GetMapping
    public ResponseEntity <ListMoonDto> fetchRecord(
            @RequestParam (name="pageNo",defaultValue ="0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "5",required = false)int pageSize,
            @RequestParam(name="sortBy",defaultValue ="id",required = false)String sortBy,
            @RequestParam(name="sortDir",defaultValue ="asc",required = false)String sortDir
    ){
        ListMoonDto listMoonDto= moonService.fetchdataDB(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(listMoonDto,HttpStatus.OK);
    }
}
