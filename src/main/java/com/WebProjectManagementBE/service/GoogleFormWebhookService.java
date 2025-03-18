package com.WebProjectManagementBE.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleFormWebhookService {

    private final RestTemplate restTemplate;
    private final String WEBHOOK_URL = "https://script.google.com/macros/s/AKfycbwM4ZZyVmMX2ldQyn_VqHGk4yqhbrIqZF4D9sljPLnTwJmw1piuFYVkQWCIV1Zi7BVlgQ/exec";

    public GoogleFormWebhookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Tạo Google Form thông qua webhook.
     *
     * @param title       Tiêu đề của Form.
     * @param description Mô tả của Form.
     * @return Thông tin Form được trả về từ webhook bao gồm URL chỉnh sửa và URL trả lời.
     */
    public Map<String, String> createGoogleForm(String title, String description) {
        validateInput(title, description);

        // Chuẩn bị payload
        Map<String, String> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("description", description);

        // Cấu hình headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Đóng gói yêu cầu
        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        try {
            // Gửi POST request tới webhook
            ResponseEntity<Map> response = restTemplate.exchange(WEBHOOK_URL, HttpMethod.POST, request, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                Map<String, Object> responseBody = response.getBody();
                return parseResponse(responseBody);
            } else {
                throw new RuntimeException("Invalid response received from Webhook: "
                        + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while sending request to Webhook: " + ex.getMessage(), ex);
        }
    }

    /**
     * Kiểm tra đầu vào (input validation).
     *
     * @param title       Tiêu đề Form.
     * @param description Mô tả Form.
     */
    private void validateInput(String title, String description) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title không được để trống.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description không được để trống.");
        }
    }

    /**
     * Xử lý phản hồi từ webhook.
     *
     * @param responseBody Đối tượng phản hồi từ webhook.
     * @return Thông tin từ webhook sau khi được parse.
     */
    private Map<String, String> parseResponse(Map<String, Object> responseBody) {
        if (responseBody == null || responseBody.isEmpty()) {
            throw new RuntimeException("Webhook response is empty or null.");
        }

        // Kiểm tra lỗi từ webhook trả về
        if (responseBody.containsKey("error") && Boolean.parseBoolean(responseBody.get("error").toString())) {
            throw new RuntimeException("Webhook returned an error: " + responseBody.get("message"));
        }

        // Lấy dữ liệu cần thiết từ phản hồi
        Map<String, String> result = new HashMap<>();
        result.put("formEditUrl", safeCast(responseBody.get("formEditUrl")));
        result.put("formResponseUrl", safeCast(responseBody.get("formResponseUrl")));
        result.put("formName", safeCast(responseBody.get("formName")));
        result.put("formDescription", safeCast(responseBody.get("formDescription")));

        return result;
    }

    /**
     * Chuyển đổi kiểu dữ liệu an toàn (nếu null thì trả về chuỗi rỗng).
     *
     * @param value Giá trị cần chuyển đổi.
     * @return Chuỗi đã chuyển đổi.
     */
    private String safeCast(Object value) {
        return value == null ? "" : value.toString();
    }
}