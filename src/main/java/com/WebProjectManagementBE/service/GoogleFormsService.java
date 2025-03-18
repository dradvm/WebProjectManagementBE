package com.WebProjectManagementBE.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.FormsScopes;
import com.google.api.services.forms.v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;

@Service
public class GoogleFormsService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String APPLICATION_NAME = "Web Project Management";
    private static final List<String> SCOPES = Collections.singletonList(FormsScopes.FORMS_BODY);
    private static final String CREDENTIALS_PATH = "classpath:credentials.json";

    @Autowired
    private ResourceLoader resourceLoader;

    private Forms formsService;

    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {
        initializeFormsService();
    }

    private void initializeFormsService() throws GeneralSecurityException, IOException {
        var credentialsFile = resourceLoader.getResource(CREDENTIALS_PATH);
        if (!credentialsFile.exists()) {
            throw new IOException("Không tìm thấy file credentials.json trong thư mục resources");
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(credentialsFile.getInputStream()));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
                .authorize("user");

        formsService = new Forms.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String createForm(String title, String description) throws IOException {
        Form form = new Form()
                .setInfo(new Info()
                        .setTitle(title)
                        .setDocumentTitle(description));

        Form createdForm = formsService.forms().create(form).execute();
        return createdForm.getFormId();
    }

    public Form getForm(String formId) throws IOException {
        return formsService.forms().get(formId).execute();
    }

    public Form updateForm(String formId, String title, String description) throws IOException {
        Form form = formsService.forms().get(formId).execute();
        form.getInfo()
                .setTitle(title)
                .setDescription(description);

        BatchUpdateFormRequest updateRequest = new BatchUpdateFormRequest()
                .setRequests(Collections.singletonList(
                        new Request().setUpdateFormInfo(
                                new UpdateFormInfoRequest()
                                        .setInfo(form.getInfo())
                                        .setUpdateMask("title,description")
                        )
                ));

        formsService.forms().batchUpdate(formId, updateRequest).execute();
        return form;
    }


    public Map<String, Object> getFormResponses(String formId) throws IOException {
        // Lấy thông tin form
        Form form = formsService.forms().get(formId).execute();

        // Lấy danh sách câu trả lời
        ListFormResponsesResponse responses = formsService.forms()
                .responses()
                .list(formId)
                .execute();

        // Tổng hợp kết quả
        Map<String, Object> result = new HashMap<>();
        result.put("formInfo", form.getInfo());
        result.put("totalResponses", responses.getResponses() != null ? responses.getResponses().size() : 0);
        result.put("responses", responses.getResponses());

        // Thống kê cho từng câu hỏi
        Map<String, Map<String, Integer>> questionStats = new HashMap<>();
        if (responses.getResponses() != null) {
            for (FormResponse response : responses.getResponses()) {
                for (Map.Entry<String, Answer> answer : response.getAnswers().entrySet()) {
                    String questionId = answer.getKey();
                    Answer answerValue = answer.getValue();

                    // Tạo hoặc lấy thống kê cho câu hỏi
                    Map<String, Integer> stats = questionStats.computeIfAbsent(questionId, k -> new HashMap<>());

                    // Cập nhật thống kê dựa trên loại câu trả lời
                    if (answerValue.getTextAnswers() != null) {
                        for (TextAnswer textAnswer : answerValue.getTextAnswers().getAnswers()) {
                            stats.merge(textAnswer.getValue(), 1, Integer::sum);
                        }
                    }
                }
            }
        }
        result.put("questionStats", questionStats);

        return result;
    }

    public String generateResponseUrl(String formId) {
        return "https://docs.google.com/forms/d/" + formId + "/viewform";
    }

    public String generateEditUrl(String formId) {
        return "https://docs.google.com/forms/d/" + formId + "/edit";
    }

    public String generateResponsesUrl(String formId) {
        return "https://docs.google.com/forms/d/" + formId + "/responses";
    }
}