import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Rest {

    String llSession = "501";
    String accountId = "1000002";
    String projectId = "cdqa_problem-1000002-zLM";
    String baseUrl = String.format("https://prod-global-ci.lo.test-ai.net/cailapub/api/caila/accounts/%s/projects/%s/intents?logLabelingSessionId=%s", accountId, projectId, llSession);

    @Test
    public void getSessions() {
        given()
                .cookie("CC-SESSION-ID", "4klIxzeTVgyGJocNIIXuLgs")
                .cookie("SESSION", "ZjZlYmNiNmEtMGIzMi00ZDE1LWIyYmYtNDM2MzM2NGNjZDY4")
                .cookie("XSRF-TOKEN", "x9LCGc6itmOVv4yq4juGQ9PzEWoRMAKhCRLPHUzrVL0")
                .cookie("JSESSIONID", "D6C0843356D5256FD363E9FE98A819F5")
                .get(baseUrl)
                .then()
                .statusCode(200);
    }
}
