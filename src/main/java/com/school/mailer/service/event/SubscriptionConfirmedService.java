package com.school.mailer.service.event;

import com.school.mailer.endpoint.event.model.SubscriptionConfirmed;
import com.school.mailer.mail.Email;
import com.school.mailer.mail.Mailer;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionConfirmedService implements Consumer<SubscriptionConfirmed> {
  private final Mailer mailer;

  @SneakyThrows
  @Override
  public void accept(SubscriptionConfirmed event) {
    var email =
        new Email(
            new InternetAddress(event.getStudentEmail()),
            List.of(),
            List.of(),
            "Confirmation d'inscription",
            "<p>Vous êtes bien inscrit au cours : <b>" + event.getCourseTitle() + "</b></p>",
            List.of());
    mailer.accept(email);
  }
}
