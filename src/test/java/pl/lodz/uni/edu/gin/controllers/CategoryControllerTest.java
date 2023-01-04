package pl.lodz.uni.edu.gin.controllers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.lodz.uni.edu.gin.Stubs;
import pl.lodz.uni.edu.gin.services.CategoryService;

//@RunWith(SpringRunner.class)
//@WebMvcTest(CategoryController.class)
public class CategoryControllerTest extends Stubs {
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private CategoryController categoryController;
//    @MockBean
//    private CategoryService categoryService;
//    @Autowired
//    private CategoryMapper categoryMapper;
//
//    @Before
//    public void setUp() {
//        when(categoryService.getAllCategories())
//                .thenReturn(categoryStubs.stream().map(categoryMapper::categoryToDto).collect(Collectors.toList()));
//
//    }

//    @Test
//    public void getCategories() throws Exception {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/categories")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(categoryStubs.size())));
//    }
}
