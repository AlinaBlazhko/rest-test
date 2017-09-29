import beans.VkGetFacultiesAnswer;
import core.VkGetFacultiesApi;
import io.restassured.RestAssured;
import org.apache.commons.codec.language.bm.Languages;
import org.junit.Test;

import java.util.List;

import static core.VkGetFacultiesConstance.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Created by X240 on 9/17/2017.
 */
public class TestVkGetFacultiesJSON {
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(VkGetFacultiesApi.baseRequestConfiguration())
                .param(PARAM_UNIVERSITY_ID, universityId)
                .get().prettyPeek()
                .then().specification(VkGetFacultiesApi.successResponse());
    }

    @Test
    public void useBaseRequestAndResponseSpecifications2() {
        RestAssured
                .given(VkGetFacultiesApi.baseRequestConfiguration())
                .param(PARAM_UNIVERSITY_ID, universityId)
                .param(PARAM_COUNT, count)
                .param(PARAM_OFFSET, offset)
                .get().prettyPeek()
                .then().specification(VkGetFacultiesApi.successResponse());
    }

    @Test
    public void reachBuilderUsage() {
        VkGetFacultiesApi.with()
                .universityId(2)
                .count(5)
                .offset(2)
                .callApi()
                .then().specification(VkGetFacultiesApi.successResponse());
    }

    @Test
    public void validateGetFacultiesAnswerAsAnObject() {
        List<VkGetFacultiesAnswer> answers = VkGetFacultiesApi.getVkGetUniversitiesAnswers(
                VkGetFacultiesApi.with()
                        .universityId(2)
                        .offset(0)
                        .count(count)
                        .callApi());

        assertThat(answers.size(), lessThanOrEqualTo(Integer.parseInt(String.valueOf(count))));
        assertThat(answers.get(0).id, equalTo(20));
        assertThat(answers.get(0).title, equalTo("Факультет биоинженерии и биоинформатики"));
        assertThat(answers.get(2).id, equalTo(22));
        assertThat(answers.get(2).title, equalTo("Высшая школа бизнеса"));
    }


}
