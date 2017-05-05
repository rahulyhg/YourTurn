package com.social.yourturn;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.social.yourturn.adapters.MemberGroupAdapter;
import com.social.yourturn.fragments.GroupFragment;
import com.social.yourturn.models.Contact;
import com.social.yourturn.models.Group;
import com.social.yourturn.utils.CircularImageView;
import com.social.yourturn.utils.ParseConstant;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class GroupListActivity extends AppCompatActivity {

    private static final String TAG = GroupListActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private MemberGroupAdapter mAdapter;
    private ArrayList<Contact> mContactList = new ArrayList<>();
    private Toolbar mActionBarToolbar;
    private LinearLayoutManager mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.members_rv);

        Intent intent = getIntent();
        if(intent != null) {
            Group group = intent.getParcelableExtra(GroupFragment.GROUP_KEY);
            getSupportActionBar().setTitle(group.getName());
            if(group.getThumbnail() != null && group.getThumbnail().length() > 0){
                 //Drawable drawable = Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/" + ParseConstant.YOUR_TURN_FOLDER + "/" + group.getName() + "/" + group.getThumbnail());
                //getSupportActionBar().setIcon(drawable);
            }
            mContactList = group.getContactList();
            mAdapter = new MemberGroupAdapter(this, mContactList);
            mLinearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLinearLayout);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}