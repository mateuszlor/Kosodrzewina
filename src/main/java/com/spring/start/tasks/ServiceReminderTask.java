
package com.spring.start.tasks;

import com.spring.start.service.CarService;
import com.spring.start.service.ServiceService;
import com.spring.start.service.UserService;
import com.spring.start.service.dto.ServiceDto;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 01.04.2017.
 */
@var
@Log4j
@Component
public class ServiceReminderTask {

    private DateTime startDate = new DateTime();

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private Environment environment;

    @Async
    @Scheduled(cron = "${jobs.serviceReminderTask.cron}")
    public void remindAboutExpiringServices() {

        log.info("Service remidner task started");

        var daysString = environment.getProperty("jobs.serviceReminderTask.daysToCheck");

        var days = Integer.parseInt(daysString);

        var expiringTasks = serviceService.getServicesSoonToExpire(days);
        var recipientList = userService.getEmails();

        if(recipientList.size() > 0 && expiringTasks.size() > 0) {

            var cars = carService.findCarsByIdList(expiringTasks
                            .stream()
                            .map(t -> t.getCar())
                            .distinct()
                            .collect(Collectors.toList()))
                    .stream()
                    .collect(Collectors.toMap(c -> c.getId(), c -> String.format("%s %s", c.getBrand(), c.getModel())));

            log.info(String.format("About to send email - %s expiring services to send on %s email addresses",
                    expiringTasks.size(),
                    recipientList.size()));
            var sb = new StringBuilder();
            sb.append(String.format("W przeciągu %s dni wygasają następujace serwisy:\n\r\n\r", days));

            expiringTasks.stream()
                    .sorted(Comparator.comparingInt(ServiceDto::getRemainingDays))
                    .map(s -> String.format("\t- %s | %s | do %s (pozostało %s dni)\n\r",
                            cars.get(s.getCar()),
                            s.getType(),
                            s.getDateTo(),
                            s.getRemainingDays()))
                    .forEach(sb::append);

            try {
                var message = getMailStub();

                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", recipientList)));
                message.setSubject("[Kosodrzewina] Przypomnienie o wygasających serwisach");
                message.setText(sb.toString());
                Transport.send(message);
            } catch (Exception e) {
                log.error("Exception during sending email: {}", e);
            }
        }
        else {
            log.warn(String.format("Skipped expiring services emails sending - %s expiring services to send on %s email addresses",
                    expiringTasks.size(),
                    recipientList.size()));
        }

        log.info("Service remidner task finished");
    }

    private MimeMessage getMailStub() throws UnsupportedEncodingException, MessagingException {

        var props = new Properties();
        props.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", environment.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));

        var session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(environment.getProperty("mail.username"), environment.getProperty("mail.password"));
                    }
                });

        var message = new MimeMessage(session);

        message.setFrom(new InternetAddress(environment.getProperty("mail.username"), "Kosodrzewina"));

        return message;
    }

}
