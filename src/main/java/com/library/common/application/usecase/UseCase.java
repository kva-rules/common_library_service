package com.library.common.application.usecase;

import java.util.Optional;

public interface UseCase<I, O> {
    Optional<O> execute(I input);
}

