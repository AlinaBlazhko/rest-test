package core;

import java.lang.reflect.Type;
import java.util.*;

import beans.Item;
import beans.VkGetFacultiesAnswer;
import beans.VkGetSchoolsAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static core.VkGetFacultiesConstance.*;
import static core.VkGetSchoolConstant.PARAM_CITY_ID;
import static core.VkGetSchoolConstant.PARAM_Q;
import static core.VkGetSchoolConstant.VK_GET_SCHOOLS_URI;
import static org.hamcrest.Matchers.lessThan;

/**
 * Created by X240 on 9/17/2017.
 */
public class VkGetMethodsApi {

    private VkGetMethodsApi(){

    }
    private HashMap<String, Integer> params = new HashMap<String, Integer>();

    public static class ApiBuilderForFaculties{
        VkGetMethodsApi getFacultiesApi;

        private ApiBuilderForFaculties(VkGetMethodsApi facultiesApi){
            getFacultiesApi = facultiesApi;
        }

        public ApiBuilderForFaculties universityId(int uId){
            getFacultiesApi.params.put(PARAM_UNIVERSITY_ID, uId);
            return this;
        }

        public ApiBuilderForFaculties offset(int off){
            getFacultiesApi.params.put(PARAM_OFFSET, off);
            return this;
        }

        public ApiBuilderForFaculties count(int c){
            getFacultiesApi.params.put(PARAM_COUNT, c);
            return this;
        }

        public Response callApi(){
            return RestAssured.with()
                    .queryParams(getFacultiesApi.params)
                    .log().all()
                    .get(VK_GET_FACULTIES_URI).prettyPeek();
        }

    }

    public static class ApiBuilderForSchool{
        VkGetMethodsApi getSchoolsApi;

        private ApiBuilderForSchool(VkGetMethodsApi schoolsApi){
            getSchoolsApi = schoolsApi;
        }

        public ApiBuilderForSchool q(int q){
            getSchoolsApi.params.put(PARAM_Q, q);
            return this;
        }

        public ApiBuilderForSchool cityId(int id){
            getSchoolsApi.params.put(PARAM_CITY_ID, id);
            return this;
        }

        public ApiBuilderForSchool count(int c){
            getSchoolsApi.params.put(VkGetSchoolConstant.PARAM_COUNT, c);
            return this;
        }

        public ApiBuilderForSchool offset(int off){
            getSchoolsApi.params.put(VkGetSchoolConstant.PARAM_COUNT, off);
            return this;
        }

        public Response callApi(){
            return RestAssured.with()
                    .queryParams(getSchoolsApi.params)
                    .log().all()
                    .get(VK_GET_SCHOOLS_URI).prettyPeek();
        }

    }

    //set base request and response specifications tu use in tests
    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration(String url){
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .addHeader("custom header2", "header2.value")
                .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri(url)
                .build();
    }

    public static List<VkGetFacultiesAnswer> getVkGetFacultiesAnswers(Response response) {

            String responseStr = response.print();
            int beginIndex = responseStr.indexOf("{", 2);
            int endIndex = responseStr.lastIndexOf("]");
            String jsonStr = "[" + responseStr.substring(beginIndex, endIndex) + "]";
            Type listType = new TypeToken<List<VkGetFacultiesAnswer>>() {
            }.getType();
            List<VkGetFacultiesAnswer> listF = new Gson().fromJson(jsonStr, listType);
            return listF;

    }

    public static List<Item> getVkGetSchoolAnswers(Response response) {

        String responseStr = response.print();
        int beginIndex = responseStr.indexOf("{", 2);
        int endIndex = responseStr.lastIndexOf("]");
        String jsonStr = "[" + responseStr.substring(beginIndex, endIndex) + "]";
        Type listType = new TypeToken<List<Item>>() {
        }.getType();
        List<Item> listS = new Gson().fromJson(jsonStr, listType);
        return listS;

    }

    public static ApiBuilderForFaculties withForFaculties(){
        VkGetMethodsApi api = new VkGetMethodsApi();
        return new ApiBuilderForFaculties(api);
    }

    public static ApiBuilderForSchool withForSchool(){
        VkGetMethodsApi api = new VkGetMethodsApi();
        return new ApiBuilderForSchool(api);
    }

}
