package com.project.ssback.controllers;

import com.project.ssback.dto.CustomerRequestDto;
import com.project.ssback.dto.CustomerResponseDto;
import com.project.ssback.dto.PageDto;
import com.project.ssback.entities.Customer;
import com.project.ssback.services.ICustomerService;
import com.project.ssback.services.IUploadFileService;
import com.project.ssback.utilities.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.project.ssback.utilities.Constants.*;
import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final ICustomerService customerService;
    private final IUploadFileService uploadFileService;
    private final CustomMapper mapper;

    @GetMapping("/customers-all")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomer(){
        List<Customer> customers = customerService.findAll();
        List<CustomerResponseDto> customersDto = customers.stream().map(mapper::toCustomerDto).collect(toList());
        return customersDto.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(customersDto);
    }

    @GetMapping("/customers")
    public ResponseEntity<PageDto<CustomerResponseDto>> getAllCustomerPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customers = customerService.findAll(pageable);
        PageDto<CustomerResponseDto> customersPages = mapper.createCustomerPageDto(customers);
        return customersPages.getContent().isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(customersPages);
    }

    @GetMapping("/customers/{idCustomer}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable("idCustomer") Long idCustomer){
        Customer customer = customerService.findById(idCustomer);
        return ResponseEntity.ok().body(mapper.toCustomerDto(customer));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        Customer customer = customerService.save(mapper.toCustomerEntity(customerRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toCustomerDto(customer));
    }

    @PutMapping("customers/{idCustomer}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable("idCustomer") Long idCustomer,@Valid @RequestBody CustomerRequestDto customerRequestDto){
        Customer customer = customerService.update(idCustomer, mapper.toCustomerEntity(customerRequestDto));
        return ResponseEntity.ok().body(mapper.toCustomerDto(customer));
    }

    @DeleteMapping("customers/{idCustomer}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("idCustomer") Long idCustomer) {
        Customer customer = customerService.findById(idCustomer);

        String namePreviousImage = customer.getPhoto();
        uploadFileService.eliminarFoto(namePreviousImage);

        customerService.delete(idCustomer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("customers/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("fileImage") MultipartFile fileImage, @RequestParam(value = "idCustomer") Long idCustomer){
        Map<String, Object> response = new HashMap<>();
        Customer customer = customerService.findById(idCustomer);

        if (!fileImage.isEmpty()) {
            String nameFile = null;
            try {
                nameFile = uploadFileService.copiar(fileImage);
            } catch (IOException e) {
                response.put("message", "Error al subir la imagen: "+nameFile);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String namePreviousImage = customer.getPhoto();
            uploadFileService.eliminarFoto(namePreviousImage);

            customer.setPhoto(nameFile);
            customerService.update(idCustomer, customer);

            response.put("message", "Imagen subida correctamente: "+nameFile);
            response.put("customer", mapper.toCustomerDto(customer));
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/uploads/img/{namePhoto:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String namePhoto){
        Resource resource = null;

        try {
            resource = uploadFileService.cargar(namePhoto);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<Resource>(resource, cabecera, HttpStatus.OK);
    }

}
