package guru.qa.niffler.db.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.qameta.allure.attachment.AttachmentData;
import io.qameta.allure.attachment.AttachmentProcessor;
import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import lombok.SneakyThrows;

import java.util.Objects;

public class JsonAllureAppender {
    private final String templateName = "json.ftl";
    private final AttachmentProcessor<AttachmentData> attachmentProcessor = new DefaultAttachmentProcessor();
    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @SneakyThrows
    public void logJson(Object o, String message) {
        if(!Objects.equals(null, o)) {
            String json = objectWriter.writeValueAsString(o);
            JsonAttachment attachment = new JsonAttachment(message, json);
            attachmentProcessor.addAttachment(attachment, new FreemarkerAttachmentRenderer(templateName));
        }
    }
}
