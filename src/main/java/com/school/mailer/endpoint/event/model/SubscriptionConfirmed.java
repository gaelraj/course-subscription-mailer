package com.school.mailer.endpoint.event.model;

import com.school.mailer.PojaGenerated;
import java.time.Duration;
import lombok.*;

@PojaGenerated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class SubscriptionConfirmed extends PojaEvent {
  private String firstName;
  private String lastName;
  private String studentEmail;
  private String courseTitle;

  @Override
  public Duration maxConsumerDuration() {
    return Duration.ofSeconds(45);
  }

  @Override
  public Duration maxConsumerBackoffBetweenRetries() {
    return Duration.ofSeconds(30);
  }
}
