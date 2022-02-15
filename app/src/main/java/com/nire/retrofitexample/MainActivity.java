package com.nire.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.nire.retrofitexample.network.model.Post;
import com.nire.retrofitexample.network.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView postId, postUserId, postTitle, postBody;
    Button createPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postId = findViewById(R.id.postId);
        postUserId = findViewById(R.id.postUserId);
        postTitle = findViewById(R.id.postTitle);
        postBody = findViewById(R.id.postBody);
        createPostButton = findViewById(R.id.buttonCreatePost);

        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(1)
                .enqueue(new Callback<Post>() {
                             @Override
                             public void onResponse(Call<Post> call, Response<Post> response) {
                                 Post post = response.body();
                                 postId.setText("" + post.getId());
                                 postUserId.setText("" + post.getUserId());
                                 postTitle.setText(post.getTitle());
                                 postBody.setText(post.getBody());
                             }

                             @Override
                             public void onFailure(Call<Post> call, Throwable t) {
                                 postId.setText("Error with request");
                                 t.printStackTrace();
                             }
                         }
                );

        Post newPost = new Post();
        newPost.setId(6);
        newPost.setUserId(1);
        newPost.setTitle("from");
        newPost.setBody("android");

        NetworkService.getInstance()
                .getJSONApi()
                .postCreate(newPost)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        System.out.println("Check");
                        System.out.println("1");
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        System.out.println("ERROR");
                        t.printStackTrace();
                    }
                });

        NetworkService.getInstance()
                .getJSONApi()
                .getAllPosts()
                .enqueue(new Callback<List<Post>>() {
                             @Override
                             public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                                 List<Post> postList = response.body();
                                 for (Post post: postList){
                                     System.out.println(post.getTitle());
                                 }
                             }

                             @Override
                             public void onFailure(Call<List<Post>> call, Throwable t) {
                                 t.printStackTrace();
                             }
                         }
                );
    }
}