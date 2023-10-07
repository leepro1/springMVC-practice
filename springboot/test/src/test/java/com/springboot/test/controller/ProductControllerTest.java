package com.springboot.test.controller;

import com.google.gson.Gson;
import com.springboot.test.dto.ProductDTO;
import com.springboot.test.dto.ProductResponseDTO;
import com.springboot.test.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Product 데이터 가져오기")
    void getProductTest() throws Exception {

        given(productService.getProduct(123L))
                .willReturn(new ProductResponseDTO(123L, "cake", 25000, 100));

        String productId = "123";

        mockMvc.perform(get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("Product 데이터 생성하기")
    void createProductTest() {

        given(productService.saveProduct(new ProductDTO("cake", 30000, 500)))
                .willReturn(new ProductResponseDTO(12315L, "cake", 30000, 500));

        ProductDTO productDTO = new ProductDTO("cake", 30000, 500);

        Gson gson = new Gson();
        String content = gson.toJson(productDTO);

        mockMvc.perform(post("/product").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDTO("cake", 30000, 500));
    }

}