package com.school.mailer.endpoint.event.consumer.model;

import com.school.mailer.PojaGenerated;
import com.school.mailer.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
