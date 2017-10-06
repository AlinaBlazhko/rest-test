import beans.VkGetFacultiesAnswer;
import beans.VkGetSchoolsAnswer;
import core.VkGetMethodsApi;
import core.VkGetFacultiesConstance;
import core.VkGetSchoolConstant;
import io.restassured.RestAssured;
import org.junit.Test;
import java.util.*;

import static core.VkGetFacultiesConstance.*;
import static core.VkGetSchoolConstant.*;
import static core.VkGetSchoolConstant.PARAM_COUNT;
import static core.VkGetSchoolConstant.count;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Created by X240 on 9/17/2017.
 */
public class TestVkGetFacultiesJSON {
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(VkGetMethodsApi.baseRequestConfiguration(VK_GET_FACULTIES_URI))
                .param(PARAM_UNIVERSITY_ID, universityId)
                .get().prettyPeek()
                .then().specification(VkGetMethodsApi.successResponse());
    }

    @Test
    public void useBaseRequestAndResponseSpecifications2() {
        RestAssured
                .given(VkGetMethodsApi.baseRequestConfiguration(VK_GET_SCHOOLS_URI))
                .param(PARAM_Q, q)
                .param(PARAM_CITY_ID, cityId)
                .param(PARAM_COUNT, VkGetFacultiesConstance.count)
                .get().prettyPeek()
                .then().specification(VkGetMethodsApi.successResponse());
    }


    @Test
    public void reachBuilderUsage() {
        VkGetMethodsApi.withForFaculties()
                .universityId(2)
                .count(5)
                .offset(2)
                .callApi()
                .then().specification(VkGetMethodsApi.successResponse());
    }

    @Test
    public void reachBuilderUsageForSchool() {
        VkGetMethodsApi.withForSchool()
                .q(57)
                .cityId(1)
                .offset(1)
                .count(5)
                .callApi()
                .then().specification(VkGetMethodsApi.successResponse());
    }

    @Test
    public void validateGetFacultiesAnswerAsAnObject() {
        List<VkGetFacultiesAnswer> answers = VkGetMethodsApi.getVkGetFacultiesAnswers(
                VkGetMethodsApi.withForFaculties()
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

    @Test
    public void validateGetSchoolsAnswerAsAnObject() {
        List<VkGetSchoolsAnswer> answers = VkGetMethodsApi.getVkGetSchoolAnswers(
                VkGetMethodsApi.withForSchool()
                        .q(57)
                        .cityId(1)
                        .offset(0)
                        .count(count)
                        .callApi());
        assertThat(answers.size(), lessThanOrEqualTo(Integer.parseInt(String.valueOf(count))));
    }


}
