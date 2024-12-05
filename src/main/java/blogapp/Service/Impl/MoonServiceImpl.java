package blogapp.Service.Impl;

import blogapp.Entity.Moon;
import blogapp.Repository.MoonRepository;
import blogapp.Service.MoonService;
import blogapp.payload.ListMoonDto;
import blogapp.payload.MoonDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoonServiceImpl implements MoonService {

    private MoonRepository moonRepository;
private ModelMapper modelMapper;

    public MoonServiceImpl(MoonRepository moonRepository,ModelMapper modelMapper) {
        this.moonRepository = moonRepository;
        this.modelMapper=modelMapper;
    }


    @Override
    public MoonDto createdata(MoonDto moonDto) {
        Moon moon = mapToEntity(moonDto);
        Moon save = moonRepository.save(moon);
        MoonDto dto = mapToDto(moon);
       return dto;
    }

    @Override
    public void deleteData(long id) {
        moonRepository.deleteById(id);
    }

    @Override
    public ListMoonDto fetchdataDB(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Moon> all = moonRepository.findAll(pageable);
        List<Moon> moon = all.getContent();
        //  List<Moon> moon = moonRepository.findAll();
        List<MoonDto> dto = moon.stream().map(n -> mapToDto(n)).collect(Collectors.toUnmodifiableList());
        ListMoonDto listMoonDto=new ListMoonDto();
        listMoonDto.setMoonDtos(dto );
        listMoonDto.setTotalElements((int) all.getTotalElements());
        listMoonDto.setTotalPages(all.getTotalPages());
        listMoonDto.setFirstPage(all.isFirst());
        listMoonDto.setLastPage(all.isLast());
        listMoonDto.setPageSize(all.getSize());
        return listMoonDto;
    }
    public Moon mapToEntity(MoonDto moonDto){
        Moon moon = modelMapper.map(moonDto, Moon.class);
        return moon;
    }
public MoonDto mapToDto(Moon moon){
    MoonDto dto = modelMapper.map(moon, MoonDto.class);
   return dto;
}
}
