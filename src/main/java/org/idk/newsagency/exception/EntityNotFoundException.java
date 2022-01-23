package org.idk.newsagency.exception;

import lombok.extern.java.Log;
import org.idk.newsagency.entity.enumeration.Role;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
import java.util.function.Supplier;

@Log
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends RuntimeException {

    private static String obtainMessage(String id) {
        return String.format("Entity with id %s not found", id);
    }

    public static Supplier<EntityNotFoundException> supplierOf(String id) {
        return () -> new EntityNotFoundException(id);
    }

    public static Supplier<EntityNotFoundException> supplierOf(UUID id) {
        return () -> new EntityNotFoundException(id.toString());
    }

    public EntityNotFoundException(String id) {
        super(obtainMessage(id));
        log.warning(obtainMessage(id));
    }


}
