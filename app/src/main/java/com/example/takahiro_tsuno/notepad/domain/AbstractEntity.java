package com.example.takahiro_tsuno.notepad.domain;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class AbstractEntity<I extends Identity<?>, E extends Entity<I, E>> implements Entity<I, E> {

    private I identity;

    public AbstractEntity(I identity) {
        this.identity = identity;
    }

    @Override
    public I getIdentity() {
        return identity;
    }

    @Override
    public boolean equals(final Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public E clone() {
        return SerializationUtils.clone((E) this);
    }
}
