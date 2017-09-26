package core;

import java.lang.reflect.Type;
import java.util.*;

import beans.VkGetFacultiesAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.VkGetFacultiesConstance.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static core.VkGetFacultiesConstance.*;
import static org.hamcrest.Matchers.lessThan;

/**
 * Created by X240 on 9/17/2017.
 */
public class VkGetFacultiesApi {

    private VkGetFacultiesApi(){

    }
    private HashMap<String, Integer> params = new HashMap<String, Integer>();

    public static class ApiBuilder{
        VkGetFacultiesApi getFacultiesApi;

        private ApiBuilder(VkGetFacultiesApi facultiesApi){
            getFacultiesApi = facultiesApi;
        }

        public ApiBuilder universityId(int uId){
            getFacultiesApi.params.put(PARAM_UNIVERSITY_ID, uId);
            return this;
        }

        public ApiBuilder offset(int off){
            getFacultiesApi.params.put(PARAM_OFFSET, off);
            return this;
        }

        public ApiBuilder count(int c){
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

    //set base request and response specifications tu use in tests
    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration(){
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .addHeader("custom header2", "header2.value")
                .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri(VK_GET_FACULTIES_URI)
                .build();
    }

    public static List<VkGetFacultiesAnswer> getVkGetUniversitiesAnswers(Response response) {

            String responseStr = response.print();
            int beginIndex = responseStr.indexOf("{", 2);
            int endIndex = responseStr.lastIndexOf("]");
            String jsonStr = "[" + responseStr.substring(beginIndex, endIndex) + "]";
            Type listType = new TypeToken<List<VkGetFacultiesAnswer>>() {
            }.getType();
            List<VkGetFacultiesAnswer> list = new Gson().fromJson(jsonStr, listType);
            return list;

    }

    public static ApiBuilder with(){
        VkGetFacultiesApi api = new VkGetFacultiesApi();
        return new ApiBuilder(api);
    }

}
