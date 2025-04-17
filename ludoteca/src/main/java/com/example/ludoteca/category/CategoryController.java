package com.example.ludoteca.category;

import com.example.ludoteca.category.model.Category;
import com.example.ludoteca.category.model.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "Category", description = "API of CAtegory")
@RequestMapping(value = "/category")
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper mapper;
//
//    private long SEQUENCE = 1;
//    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

    @Operation(summary = "Find", description = "Method that retirn a list of Categories ")
    @RequestMapping(path = {""}, method = RequestMethod.GET)
    public List<CategoryDto> findAll() {
        List<Category> categories = this.categoryService.findAll();
//        return new ArrayList<CategoryDto>(this.categories.values());
        return categories.stream().map(e -> mapper.map(e, CategoryDto.class)).collect(Collectors.toList());
    }

    @Operation(summary = "Save or Update", description = "Method that saves or updates a Category")
    @RequestMapping(path = {"", "/{id}"}, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CategoryDto dto) {
        this.categoryService.save(id, dto);
        //        CategoryDto category;
//        if( id ==null){
//            category= new CategoryDto();
//            category.setId(this.SEQUENCE++);
//            this.categories.put(category.getId(), category);
//        }else{
//            category= this.categories.get(id);
//        }
//        category.setName(dto.getName());
    }

    @Operation(summary = "Delete", description = "Method that deletes a Category")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception{
        this.categoryService.delete(id);
//        this.categories.remove(id);
    }
//
//    @RequestMapping(path = "", method = RequestMethod.GET)
//    public String prueba() {
//        return "PRobando controller";
//    }
}
