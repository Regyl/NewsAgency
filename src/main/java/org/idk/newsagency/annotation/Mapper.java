package org.idk.newsagency.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Component
@Target(ElementType.TYPE)
public @interface Mapper {
}
