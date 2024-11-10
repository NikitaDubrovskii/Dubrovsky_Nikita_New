package dev.dubrovsky.controller;

import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.util.response.ResponseUtil;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
public abstract class AbstractController<S extends ICommonService<R, N, U>, R, N, U> implements ICommonController<N, U> {

    protected final S service;

    @Override
    public ResponseEntity<?> create(@RequestBody @Valid N request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            service.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> getById(@PathVariable @Parameter(description = "Id объекта") Integer id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@PathVariable @Parameter(description = "Id обновляемоего объекта") Integer id,
                                    @RequestBody @Valid U request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            service.update(request, id);
            return new ResponseEntity<>(ResponseStatus.UPDATED.getDescription(), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable @Parameter(description = "Id удаляемого объекта") Integer id) {
        service.delete(id);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

    protected String getUsername(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

}
