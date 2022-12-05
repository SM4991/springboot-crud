package com.sm4991.springbootcrud.controller.api;

import com.sm4991.springbootcrud.entity.Product;
import com.sm4991.springbootcrud.response.ResponseWrapper;
import com.sm4991.springbootcrud.service.ProductService;
import com.sm4991.springbootcrud.dto.ProductDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Api to fetch all products
     * @param request
     * @return
     */
    @ApiOperation(value = "List of products", response = ResponseWrapper.class)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseWrapper getAll(HttpServletRequest request) {
        return ResponseWrapper.success(request, "Product List", productService.getAll());
    }

    /**
     * Api to fetch product by id
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "Find a product by id", response = ResponseWrapper.class)
    @GetMapping(value = "/{id}")
    public ResponseWrapper getOne(HttpServletRequest request, @PathVariable UUID id) {
        Optional<Product> user = productService.getById(id);
        if(user.isPresent()) {
            return ResponseWrapper.success(request, "Product Data", user.get());
        } else {
            return ResponseWrapper.error(request, HttpStatus.NOT_FOUND, "Product not found.", null);
        }
    }

    /**
     * Api to create a new product
     * @param request
     * @param productDto
     * @return
     */
    @ApiOperation(value = "Add a new product", response = ResponseWrapper.class)
    @PostMapping(value = "/")
    public ResponseWrapper create(HttpServletRequest request, @RequestBody ProductDto productDto) {
        Pair<Boolean, Object> result = productService.createProduct(productDto);
        if(result.getFirst() == true) {
            return ResponseWrapper.success(request, "Product created successfully.", result.getSecond());
        } else {
            return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, result.getSecond().toString(), null);
        }
    }

    /**
     * Api to update existing product
     * @param request
     * @param productDto
     * @return
     */
    @ApiOperation(value = "Update an existing product by id", response = ResponseWrapper.class)
    @PutMapping(value = "/{id}")
    public ResponseWrapper update(HttpServletRequest request, @PathVariable UUID id, @RequestBody ProductDto productDto) {
        Optional<Product> user = productService.getById(id);
        if(user.isPresent()) {
            Pair<Boolean, Object> result = productService.updateProduct(user.get(), productDto);
            if(result.getFirst() == true) {
                return ResponseWrapper.success(request, "Product updated successfully.", result.getSecond());
            } else {
                return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, result.getSecond().toString(), null);
            }
        } else {
            return ResponseWrapper.error(request, HttpStatus.NOT_FOUND, "Product not found.", null);
        }
    }

    /**
     * Delete a product by id
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "Delete a product by id", response = ResponseWrapper.class)
    @DeleteMapping(value = "/{id}")
    public ResponseWrapper delete(HttpServletRequest request, @PathVariable UUID id) {
        try{
            Boolean result = productService.deleteById(id);
            if(result == true) {
                return ResponseWrapper.success(request, "Product deleted successfully", null);
            } else {
                return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, "Product could not be deleted.", null);
            }
        } catch (EmptyResultDataAccessException ex) {
            return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, "Product with id does not exists.", null);
        }
    }
}
