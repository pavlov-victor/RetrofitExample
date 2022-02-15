package com.nire.retrofitexample.network;

import com.nire.retrofitexample.network.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);

    @GET("/posts")
    public Call<List<Post>> getAllPosts();

    @POST("/posts")
    public Call<Post> postCreate(@Body Post post);
}
